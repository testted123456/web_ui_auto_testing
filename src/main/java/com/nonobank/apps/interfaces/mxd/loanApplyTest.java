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
import com.nonobank.apps.utils.data.UserInfoUtils;


public class loanApplyTest {
	public static Logger logger = LogManager.getLogger(loanApplyTest.class);
	public static List<HashMap<String, String>> params = new ArrayList<HashMap<String, String>>();
	public static String url = "/msapi/rebuild/reloan/loanApply";
	
	public void dataProvider(){
		String phone=UserInfoUtils.getUnUsedMobileNum();
		String imgSessionId=getSessionIdTest.getImgSessionId();
		String smsCode=sendMessageByValidateCodeTest.getSmsCode(imgSessionId, phone);
		String smscodeSessionId=sendMessageByValidateCodeTest.getSmscodeSessionId(imgSessionId, phone);
		String sessionId=registerTest.getSessionId(smsCode, phone, smscodeSessionId);
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("sessionId", sessionId);
		requestParams.put("pId", "77");
		requestParams.put("purpose", "消费购物");
		requestParams.put("term","12");
		requestParams.put("amt", "10000");
		requestParams.put("bangCode", "");
		requestParams.put("channelNo", "1");
		requestParams.put("systemId", "mxd");
		requestParams.put("newBorrower", "1");
		requestParams.put("freshMan", "0");
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
	
	public static String loanApply(String sessionId,String pId,String period,
			String amount){
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("sessionId", sessionId);
		requestParams.put("pId", pId);
		requestParams.put("purpose", "消费购物");
		requestParams.put("term",period);
		requestParams.put("amt", amount);
		requestParams.put("bangCode", "");
		requestParams.put("channelNo", "1");
		requestParams.put("systemId", "mxd");
		requestParams.put("newBorrower", "1");
		requestParams.put("freshMan", "0");
		String response = SendRequest.httpCommonPost(url, requestParams);
		return response;
	}
	public static String getbpId(String sessionId,String pId,String period,
			String amount){
		String response=loanApply(sessionId,pId,period,amount);
		JSONObject jsonObj = JSON.parseObject(response);
		String loanApplyData=jsonObj.get("loanApplyData").toString();
		JSONObject jsonObj2 = JSON.parseObject(loanApplyData);
		String bpId=jsonObj2.get("bpId").toString();
		return bpId;
	}
	
	
	
}
