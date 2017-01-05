package com.nonobank.apps.interfaces.mxd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.nonobank.apps.interfaces.util.SendRequest;

public class loanApplyPublishBorrowsTest {
	
	public static Logger logger = LogManager.getLogger(loanApplyTest.class);
	public static List<HashMap<String, String>> params = new ArrayList<HashMap<String, String>>();
	public static String url = "/msapi/rebuild/reloan/loanApplyPublishBorrows";
	
	public static String loanApplyPublishBorrows(String sessionId){
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("sessionId", sessionId);
		requestParams.put("terminal", "android");
		String response = SendRequest.httpCommonPost(url, requestParams);
		return response;	
	}
	
	public static String getboId(String sessionId){
		String response=loanApplyPublishBorrows(sessionId);
		JSONObject jsonObj = JSON.parseObject(response);
		String data=jsonObj.get("data").toString();
		JSONObject jsonObj2 = JSON.parseObject(data);
		String boId=jsonObj2.get("boId").toString();
		return boId;
	}

}
