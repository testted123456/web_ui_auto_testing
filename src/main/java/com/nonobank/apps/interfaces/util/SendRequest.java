package com.nonobank.apps.interfaces.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.alibaba.fastjson.JSONObject;
import com.nonobank.apps.utils.file.ParseProperties;

public class SendRequest {

	public static Logger logger = LogManager.getLogger(SendRequest.class);
	
	//普通get请求
	public static String httpCommonGet(String url) {
		try {
			Properties prop = ParseProperties.getInstance();
			url = prop.getProperty("url") + url;
			CloseableHttpClient httpClient = HttpClients.createDefault();
			CloseableHttpResponse response = null;
			HttpGet httpGet = new HttpGet();
			httpGet.setURI(new URI(url));
			response = httpClient.execute(httpGet);
			// 如果server端返回http 200
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				HttpEntity responseEntity = response.getEntity();
				return EntityUtils.toString(responseEntity, "UTF-8");
			} else {
				logger.error("请求失败 : " + response.getStatusLine().getReasonPhrase());
				return null;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	// 普通post
	public static String httpCommonPost(String url, HashMap<String, String> requestParams) {
		Properties prop = ParseProperties.getInstance();
		url = prop.getProperty("url") + url;

		CloseableHttpClient httpClient = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		HttpPost httpPost = new HttpPost(url);

		List<NameValuePair> params = new ArrayList<NameValuePair>();

		if (null != requestParams) {
			// 对request中的value转换
			Iterator<Entry<String, String>> requestIte = requestParams.entrySet().iterator();

			while (requestIte.hasNext()) {
				Map.Entry<String, String> requestEntry = requestIte.next();
				requestEntry.setValue(requestEntry.getValue());
			}

			Set<String> keys = requestParams.keySet();

			for (String key : keys) {
				params.add(new BasicNameValuePair(key, requestParams.get(key)));
			}
		}

		try {
			if (null != requestParams) {
				UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params, "utf-8");
				httpPost.setEntity(entity);
			}

			response = httpClient.execute(httpPost);
			// 如果server端返回http 200
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				HttpEntity responseEntity = response.getEntity();
				return EntityUtils.toString(responseEntity, "UTF-8");
			} else {
				logger.error("请求失败 : " + response.getStatusLine().getReasonPhrase());
				return null;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}


	// post json数据
	public static String httpJsonPost(String url, JSONObject jsonObj) {
		Properties prop = ParseProperties.getInstance();
		url = prop.getProperty("url") + url;
		CloseableHttpClient httpClient = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		HttpPost httpPost = new HttpPost(url);

		try {
			StringEntity strEntity = new StringEntity(jsonObj.toString());
			strEntity.setContentEncoding("UTF-8");
			strEntity.setContentType("application/json");
			httpPost.setEntity(strEntity);
			response = httpClient.execute(httpPost);

			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				HttpEntity responseEntity = response.getEntity();
				return EntityUtils.toString(responseEntity, "UTF-8");
			} else {
				logger.error("请求失败 : " + response.getStatusLine().getReasonPhrase());
				return null;
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
