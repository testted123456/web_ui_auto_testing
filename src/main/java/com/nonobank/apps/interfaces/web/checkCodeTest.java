package com.nonobank.apps.interfaces.web;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nonobank.apps.interfaces.util.SendRequestByCookies;

public class checkCodeTest {
	public static Logger logger = LogManager.getLogger(checkCodeTest.class);
	
	public static void checkCode(){
		double num= 0.8999999999999999*Math.random()+0.1;
		String url="https://www.stb.nonobank.com/v6/Validate/checkCode?&i="+num;
		SendRequestByCookies.httpCommonGet(url);	
	}	
}
