package com.nonobank.apps.interfaces.mxd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nonobank.apps.interfaces.util.SendRequest;

public class saveLoanUserCardUploadImgTest {
	
	public static Logger logger = LogManager.getLogger(authApplyTest.class);
	public static List<HashMap<String, String>> params = new ArrayList<HashMap<String, String>>();
	public static String url = "/msapi/rebuild/userinfo/saveLoanUserCardUploadImg";
		
	public static String saveLoanUserCardUploadImg(String sessionId,String base64Media){
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("sessionId", sessionId);
		requestParams.put("base64Media", base64Media);
		String response = SendRequest.httpCommonPost(url, requestParams);
		return response;	
	}
}
