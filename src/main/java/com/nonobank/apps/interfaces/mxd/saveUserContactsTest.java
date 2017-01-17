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


public class saveUserContactsTest {
	public static Logger logger = LogManager.getLogger(saveUserContactsTest.class);
	public static List<HashMap<String, String>> params = new ArrayList<HashMap<String, String>>();
	public static String url = "/msapi/rebuild/userinfo/saveUserContacts";
	
	public void dataProvider(){
		String phone=UserInfoUtils.getUnUsedMobileNum();
		String imgSessionId=getSessionIdTest.getImgSessionId();
		String smsCode=sendMessageByValidateCodeTest.getSmsCode(imgSessionId, phone);
		String smscodeSessionId=sendMessageByValidateCodeTest.getSmscodeSessionId(imgSessionId, phone);
		String sessionId=registerTest.getSessionId(smsCode, phone, smscodeSessionId);
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("sessionId", sessionId);
		requestParams.put("parentNameSaveFlag", "no");
		requestParams.put("parentName", "张三");
		requestParams.put("parentMobileSaveFlag","no");
		requestParams.put("parentMobile", "13100001001");
		requestParams.put("friendNameSaveFlag", "no");
		requestParams.put("friendName", "李四");
		requestParams.put("friendMobileSaveFlag", "no");
		requestParams.put("friendMobile", "13100001002");
		requestParams.put("counselorNameSaveFlag", "no");
		requestParams.put("counselorName", "王五");
		requestParams.put("counselorMobileSaveFlag", "no");
		requestParams.put("counselorMobile", "13100001003");
		requestParams.put("homeAddrSaveFlag", "no");
		requestParams.put("homeAddr", "上海市徐汇区肇嘉浜路1033号徐家汇国际大厦");
		
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
	public static void saveUserContacts(String sessionId){
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("sessionId", sessionId);
		requestParams.put("parentNameSaveFlag", "no");
		requestParams.put("parentName", "张三");
		requestParams.put("parentMobileSaveFlag","no");
		requestParams.put("parentMobile", "13100001001");
		requestParams.put("friendNameSaveFlag", "no");
		requestParams.put("friendName", "李四");
		requestParams.put("friendMobileSaveFlag", "no");
		requestParams.put("friendMobile", "13100001002");
		requestParams.put("counselorNameSaveFlag", "no");
		requestParams.put("counselorName", "王五");
		requestParams.put("counselorMobileSaveFlag", "no");
		requestParams.put("counselorMobile", "13100001003");
		requestParams.put("homeAddrSaveFlag", "no");
		requestParams.put("homeAddr", "上海市徐汇区肇嘉浜路1033号徐家汇国际大厦");	
		SendRequest.httpCommonPost(url, requestParams);
	}
}
