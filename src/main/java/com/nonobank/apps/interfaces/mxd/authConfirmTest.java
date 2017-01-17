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

public class authConfirmTest {
	public static Logger logger = LogManager.getLogger(authApplyTest.class);
	public static List<HashMap<String, String>> params = new ArrayList<HashMap<String, String>>();
	public static String url = "/msapi/rebuild/bankcard/authConfirm";
	
	public void dataProvider(){
		String phone=UserInfoUtils.getUnUsedMobileNum();
		String imgSessionId=getSessionIdTest.getImgSessionId();
		String smsCode=sendMessageByValidateCodeTest.getSmsCode(imgSessionId, phone);
		String smscodeSessionId=sendMessageByValidateCodeTest.getSmscodeSessionId(imgSessionId, phone);
		
		String sessionId=registerTest.getSessionId(smsCode, phone, smscodeSessionId);
		String orderNo=authApplyTest.getOrderNo(sessionId); 
		String transNo=authApplyTest.getTransNo(sessionId);
		String bankCardNo=BankCardUtils.getUnUsedBankCard("622202100112");
		String bankCardShortNo=authApplyTest.getBankCardShortNo(sessionId);
		String token=authApplyTest.getToken(sessionId);
		
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("sessionId", sessionId);
		requestParams.put("orderNo", orderNo);
		requestParams.put("transNo", transNo);
		requestParams.put("bankCardNo", bankCardNo);
		requestParams.put("bankCardShortNo", bankCardShortNo);
		requestParams.put("token", token);
		requestParams.put("smsCode", "0615");
		
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
	
	public static String authConfirm(String sessionId,String orderNo,String transNo,
			String bankCardShortNo,String token){
		String bankCardNo=BankCardUtils.getUnUsedBankCard("622202100112");
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("sessionId", sessionId);
		requestParams.put("orderNo", orderNo);
		requestParams.put("transNo", transNo);
		requestParams.put("bankCardNo", bankCardNo);
		requestParams.put("bankCardShortNo", bankCardShortNo);
		requestParams.put("token", token);
		requestParams.put("smsCode", "0615");
		String response = SendRequest.httpCommonPost(url, requestParams);
		return response;	
	}
}
