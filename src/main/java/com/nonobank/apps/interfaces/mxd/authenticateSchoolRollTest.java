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
import com.nonobank.apps.interfaces.util.SendRequestAbsoluteURL;
import com.nonobank.apps.utils.data.IdCardGenerator;
import com.nonobank.apps.utils.data.UserInfoUtils;


public class authenticateSchoolRollTest {
	public static Logger logger = LogManager.getLogger(authenticateSchoolRollTest.class);
	public static List<HashMap<String, String>> params = new ArrayList<HashMap<String, String>>();
	public static String url = "https://openapi.stb.nonobank.com/nono-web/app5/authenticateSchoolRoll";
	IdCardGenerator IdCardGenerator=new IdCardGenerator();
	
	public static void authenticateSchoolRoll(String userId,String idCard,
			String college,String schoolRoll,String entranceYear){
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("userId", userId);
		jsonObj.put("college", college);
		jsonObj.put("entranceYear", entranceYear);
		jsonObj.put("idNumber", idCard);
		jsonObj.put("name", "韩诺");
		jsonObj.put("schoolRoll", schoolRoll);
		HashMap<String, String> requsetParams = new HashMap<String, String>();
		requsetParams.put("request", jsonObj.toJSONString());
		params.add(requsetParams);
		String response=SendRequestAbsoluteURL.httpCommonPost(url, requsetParams);
	}
	
	
	
	
}
