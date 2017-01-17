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


public class checkValidationCodeTest {
	public static Logger logger = LogManager.getLogger(checkValidationCodeTest.class);
	public static List<HashMap<String, String>> params = new ArrayList<HashMap<String, String>>();
	public static String url = "/msapi/user/checkValidationCode";
	
	public void dataProvider(){
		String phone=UserInfoUtils.getUnUsedMobileNum();
		String imgSessionId=getSessionIdTest.getImgSessionId();
		String smscodeSessionId=sendMessageByValidateCodeTest.getSmscodeSessionId(imgSessionId, phone);
		String smsCode=sendMessageByValidateCodeTest.getSmsCode(imgSessionId, phone);
		
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("sessionId", smscodeSessionId);
		requestParams.put("phone", phone);
		requestParams.put("validatemobile", smsCode);
		params.add(requestParams);
	}
	@Test
	public void test(){
		dataProvider();
		for (int i = 0; i < params.size(); i++) {
			String response = SendRequest.httpCommonPost(url, params.get(i));
			logger.info("响应消息：" + response);
			JSONObject jsonObj = JSON.parseObject(response);

			Assert.assertEquals("1", jsonObj.get("flag").toString());
		}
	}
	public static void checkValidationCode(String smscodeSessionId,String phone,String smsCode){
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("sessionId", smscodeSessionId);
		requestParams.put("phone", phone);
		requestParams.put("validatemobile", smsCode);
		String response = SendRequest.httpCommonPost(url, requestParams);
		JSONObject jsonObj = JSON.parseObject(response);
		
		Assert.assertEquals("1", jsonObj.get("flag").toString());
	}
}
