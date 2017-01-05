package com.nonobank.apps.interfaces.mxd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.nonobank.apps.interfaces.util.SendRequest;
import com.nonobank.apps.utils.data.BankCardUtils;
import com.nonobank.apps.utils.data.UserInfoUtils;



public class authApplyTest {
	public static Logger logger = LogManager.getLogger(authApplyTest.class);
	public static List<HashMap<String, String>> params = new ArrayList<HashMap<String, String>>();
	public static String url = "/msapi/rebuild/bankcard/authApply";
	
	public void dataProvider(){
		String phone=UserInfoUtils.getUnregisterMobile();
		String imgSessionId=getSessionIdTest.getImgSessionId();
		String smsCode=sendMessageByValidateCodeTest.getSmsCode(imgSessionId, phone);
		String smscodeSessionId=sendMessageByValidateCodeTest.getSmscodeSessionId(imgSessionId, phone);
		String sessionId=registerTest.getSessionId(smsCode, phone, smscodeSessionId);
		String bankCardNo=BankCardUtils.getUnUsedBankCard("622202100112");
		
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("sessionId", sessionId);
		requestParams.put("bankCardNo", bankCardNo);
		requestParams.put("bankCode", "ICBC");
		requestParams.put("provinceCode","370000");
		requestParams.put("cityCode", "370282");
		requestParams.put("mobile", "18663049557");
		requestParams.put("type", "4");
		
		params.add(requestParams);
	}
	@Test
	public void test(){
		dataProvider();
		for (int i = 0; i < params.size(); i++) {
			String response = SendRequest.httpCommonPost(url, params.get(i));
			logger.info("响应消息：" + response);
			JSONObject jsonObj = JSON.parseObject(response);
			Assert.assertEquals("0000000", jsonObj.get("errorCode"));
		}
	}
	
	public static String authApply(String sessionId){
		String bankCardNo=BankCardUtils.getUnUsedBankCard("622202100112");
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("sessionId", sessionId);
		requestParams.put("bankCardNo", bankCardNo);
		requestParams.put("bankCode", "ICBC");
		requestParams.put("provinceCode","370000");
		requestParams.put("cityCode", "370282");
		requestParams.put("mobile", "18663049557");
		requestParams.put("type", "4");
		String response = SendRequest.httpCommonPost(url, requestParams);
		return response;	
	}
	
	public static String getTransNo(String sessionId){
		String response = authApply(sessionId);
		JSONObject jsonObj = JSON.parseObject(response);
		String data=jsonObj.get("data").toString();
		JSONObject jsonObj2 = JSON.parseObject(data);
		String transNo = jsonObj2.get("transNo").toString();
		return transNo;
	}
	
	public static String getOrderNo(String sessionId){
		String response = authApply(sessionId);
		JSONObject jsonObj = JSON.parseObject(response);
		String data=jsonObj.get("data").toString();
		JSONObject jsonObj2 = JSON.parseObject(data);
		String orderNo = jsonObj2.get("orderNo").toString();
		return orderNo;
	}
	
	public static String getBankCardShortNo(String sessionId){
		String response = authApply(sessionId);
		JSONObject jsonObj = JSON.parseObject(response);
		String data=jsonObj.get("data").toString();
		JSONObject jsonObj2 = JSON.parseObject(data);
		String bankCardShortNo = jsonObj2.get("bankCardShortNo").toString();
		return bankCardShortNo;
	}
	
	public static String getToken(String sessionId){
		String response = authApply(sessionId);
		JSONObject jsonObj = JSON.parseObject(response);
		String data=jsonObj.get("data").toString();
		JSONObject jsonObj2 = JSON.parseObject(data);
		String token = jsonObj2.get("token").toString();
		return token;
	}
}
