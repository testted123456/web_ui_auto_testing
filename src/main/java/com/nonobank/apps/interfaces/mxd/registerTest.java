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


public class registerTest {
	public static Logger logger = LogManager.getLogger(registerTest.class);
	public static List<HashMap<String, String>> params = new ArrayList<HashMap<String, String>>();
	public static String url = "/msapi/user/register";
	
	public void dataProvider(){
		String phone=UserInfoUtils.getUnregisterMobile();
		String imgSessionId=getSessionIdTest.getImgSessionId();
		String smsCode=sendMessageByValidateCodeTest.getSmsCode(imgSessionId, phone);
		String smscodeSessionId=sendMessageByValidateCodeTest.getSmscodeSessionId(imgSessionId, phone);
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("borrowtype", "学生");
		requestParams.put("validatemobile", smsCode);
		requestParams.put("password", "d051d170235c6682e334e6a5abd8ebdb");
		requestParams.put("terminal","13");
		requestParams.put("mobile", phone);
		requestParams.put("approach2", "5.5.0");
		requestParams.put("approach", "mxd_andorid");
		requestParams.put("sessionId", smscodeSessionId);
		requestParams.put("am_id", "719");
		params.add(requestParams);
	}
	@Test
	public void test(){
		dataProvider();
		for (int i = 0; i < params.size(); i++) {
			String response = SendRequest.httpCommonPost(url, params.get(i));
			logger.info("响应消息：" + response);
			JSONObject jsonObj = JSON.parseObject(response);

			Assert.assertEquals("1", jsonObj.get("flag"));
		}
	}
	public static String register(String smsCode,String phone,String smscodeSessionId){
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("borrowtype", "学生");
		requestParams.put("validatemobile", smsCode);
		requestParams.put("password", "d051d170235c6682e334e6a5abd8ebdb");
		requestParams.put("terminal","13");
		requestParams.put("mobile", phone);
		requestParams.put("approach2", "5.5.0");
		requestParams.put("approach", "mxd_andorid");
		requestParams.put("sessionId", smscodeSessionId);
		requestParams.put("am_id", "719");
		String response = SendRequest.httpCommonPost(url, requestParams);
		return response;
	}
	public static String getSessionId(String smsCode,String phone,String smscodeSessionId){
		String response=register(smsCode,phone,smscodeSessionId);
		JSONObject jsonObj = JSON.parseObject(response);
		String data=jsonObj.get("data").toString();
		JSONObject jsonObj2 = JSON.parseObject(data);
		String sessionId=jsonObj2.get("session_id").toString();
		return sessionId;
	}
	public static String getUserId(String smsCode,String phone,String smscodeSessionId){
		String response=register(smsCode,phone,smscodeSessionId);
		JSONObject jsonObj = JSON.parseObject(response);
		String data=jsonObj.get("data").toString();
		JSONObject jsonObj2 = JSON.parseObject(data);
		String userId=jsonObj2.get("m_id").toString();
		return userId;
	}
	public static String getUserName(String smsCode,String phone,String smscodeSessionId){
		String response=register(smsCode,phone,smscodeSessionId);
		JSONObject jsonObj = JSON.parseObject(response);
		String data=jsonObj.get("data").toString();
		JSONObject jsonObj2 = JSON.parseObject(data);
		String userName=jsonObj2.get("m_username").toString();
		return userName;
	}
}
