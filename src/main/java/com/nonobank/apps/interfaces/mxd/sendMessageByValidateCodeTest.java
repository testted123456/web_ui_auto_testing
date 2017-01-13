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

public class sendMessageByValidateCodeTest {
	public static Logger logger = LogManager.getLogger(sendMessageByValidateCodeTest.class);
	public static List<HashMap<String, String>> params = new ArrayList<HashMap<String, String>>();
	public static String url = "/msapi/user/sendMessageByValidateCode";

	public void dataProvider() {
		String sessionId = getSessionIdTest.getImgSessionId();
		String phone = UserInfoUtils.getUnregisterMobile();
		String sms_type = "4";
		String black_box = "";
		String validateCode = "0615";
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("sessionId", sessionId);
		requestParams.put("phone", phone);
		requestParams.put("sms_type", sms_type);
		requestParams.put("black_box", black_box);
		requestParams.put("validateCode", validateCode);
		params.add(requestParams);
	}

	@Test
	public void test() {
		dataProvider();
		for (int i = 0; i < params.size(); i++) {
			String response = SendRequest.httpCommonPost(url, params.get(i));
			logger.info("响应消息：" + response);
			JSONObject jsonObj = JSON.parseObject(response);

			Assert.assertEquals("1", jsonObj.get("flag"));
		}
	}

	public static String sendMessageByValidateCode(String imgSessionId, String phone) {
		String sms_type = "4";
		String black_box = "";
		String validateCode = "0615";
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("sessionId", imgSessionId);
		requestParams.put("phone", phone);
		requestParams.put("sms_type", sms_type);
		requestParams.put("black_box", black_box);
		requestParams.put("validateCode", validateCode);
		String response = SendRequest.httpCommonPost(url, requestParams);
		return response;
	}

	public static String getSmscodeSessionId(String imgSessionId, String phone) {
		String response = sendMessageByValidateCode(imgSessionId, phone);
		JSONObject jsonObj = JSON.parseObject(response);
		String data = jsonObj.get("data").toString();
		JSONObject jsonObj2 = JSON.parseObject(data);
		String smscodeSessionId = jsonObj2.get("session_id").toString();
		return smscodeSessionId;
	}

	public static String getSmsCode(String imgSessionId, String phone) {
		String response = sendMessageByValidateCode(imgSessionId, phone);
		JSONObject jsonObj = JSON.parseObject(response);
		String data = jsonObj.get("data").toString();
		JSONObject jsonObj2 = JSON.parseObject(data);
		String smsCode = jsonObj2.get("validation").toString();
		return smsCode;
	}
}
