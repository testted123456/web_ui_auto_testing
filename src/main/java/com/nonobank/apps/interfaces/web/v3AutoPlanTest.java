package com.nonobank.apps.interfaces.web;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nonobank.apps.interfaces.util.SendRequestByCookies;


public class v3AutoPlanTest {
	public static Logger logger = LogManager.getLogger(v3AutoPlanTest.class);
	
	
	public static void v3AutoPlan(String boId){
		String url="https://www.stb.nonobank.com/T/TestTimingTaskApi/no_v3_auto_auditing/"+boId;
		SendRequestByCookies.httpCommonGet(url);
	}
}
