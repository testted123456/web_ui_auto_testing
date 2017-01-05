package com.nonobank.apps.interfaces.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nonobank.apps.interfaces.util.SendRequestByCookies;



public class ajaxLoginTest {
	public static Logger logger = LogManager.getLogger(ajaxLoginTest.class);
	public static List<HashMap<String, String>> params = new ArrayList<HashMap<String, String>>();
	public static String url = "https://www.stb.nonobank.com/v6/Login/AjaxLogin";
		
	public static void ajaxLogin(String loginname,String loginpwd,String checkCode,
			String loginFrom,String loginBiz){		
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("loginname", loginname);
		requestParams.put("loginpwd", loginpwd);
		requestParams.put("checkCode", checkCode);
		requestParams.put("loginFrom", loginFrom);
		requestParams.put("loginBiz", loginBiz);
		SendRequestByCookies.httpCommonPostForLogin(url, requestParams);			
	}
	
}
