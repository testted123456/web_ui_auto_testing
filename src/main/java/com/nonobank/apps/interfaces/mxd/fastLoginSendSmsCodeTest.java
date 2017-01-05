package com.nonobank.apps.interfaces.mxd;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.alibaba.fastjson.JSONObject;
import com.nonobank.apps.interfaces.util.MD5Util;
import com.nonobank.apps.interfaces.util.SendRequest;
import com.nonobank.apps.interfaces.util.SendRequestAbsoluteURL;

public class fastLoginSendSmsCodeTest {
	public static Logger logger = LogManager.getLogger(fastLoginSendSmsCodeTest.class);
	public static List<HashMap<String, String>> params = new ArrayList<HashMap<String, String>>();
	public static String url = "https://openapi.stb.nonobank.com/nono-web/app5/fastLoginSendSmsCode";

	public static String fastLoginSendSmsCode(String mobile) {
		// 生成msgKey
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		String date = format.format(new Date());
		String msgKey = MD5Util.toMD5(mobile + date);

		JSONObject jsonObj = new JSONObject();
		jsonObj.put("mobileNumber", mobile);
		jsonObj.put("msgkey", msgKey);
		HashMap<String, String> requsetParams = new HashMap<String, String>();
		requsetParams.put("request", jsonObj.toJSONString());
		params.add(requsetParams);
		String response = SendRequestAbsoluteURL.httpCommonPost(url, requsetParams);
		return response;
	}

}
