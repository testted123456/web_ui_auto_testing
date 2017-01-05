package com.nonobank.apps.interfaces.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.Map.Entry;

import org.apache.http.HeaderIterator;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.cookie.CookieSpecProvider;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.impl.cookie.BestMatchSpecFactory;
import org.apache.http.impl.cookie.BrowserCompatSpecFactory;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.alibaba.fastjson.JSONObject;
import com.nonobank.apps.utils.file.ParseProperties;

public class SendRequestByCookies {
	public static Logger logger = LogManager.getLogger(SendRequestByCookies.class);
	
	static CloseableHttpClient httpClient = HttpClients.createDefault();
	static CloseableHttpResponse response = null; 

	// 创建CookieStore实例
	static CookieStore cookieStore = null;

	public static void setCookieStore(HttpResponse httpResponse) {
		System.out.println("----setCookieStore");
		String[] cookies;
		String[] cooks;
		HashMap cook = new HashMap();
		cookieStore = new BasicCookieStore();
		CookieStore cookieStore = new BasicCookieStore();
		for (int i = 0; i < httpResponse.getHeaders("Set-Cookie").length; i++) {
			String cookie = httpResponse.getHeaders("Set-Cookie")[i].getValue();
			cookies = cookie.split(";");
			cooks = cookies[0].split("=");
			cook.put(cooks[0], cooks[1]);
		}
		// 新建一个Cookie
		BasicClientCookie cookie = new BasicClientCookie("AAA", null);
		cookie.setVersion(0);
		cookie.setDomain("127.0.0.1");
		cookie.setPath("/CwlProClient");
		cookie.setAttribute("PHPSESSID", cook.get("PHPSESSID").toString());
		cookieStore.addCookie(cookie);
	}

	// 普通post--登录调用
	public static void httpCommonPostForLogin(String url, HashMap<String, String> requsetParams) {
//		Properties prop = ParseProperties.getInstance();
//		url = prop.getProperty("url") + url;
		
		HttpPost httpPost = new HttpPost(url);
		List<NameValuePair> params = new ArrayList<NameValuePair>();

		if (null != requsetParams) {
			// 对request中的value转换
			Iterator<Entry<String, String>> requestIte = requsetParams.entrySet().iterator();
			while (requestIte.hasNext()) {
				Map.Entry<String, String> requestEntry = requestIte.next();
				requestEntry.setValue(requestEntry.getValue());
			}
			Set<String> keys = requsetParams.keySet();
			for (String key : keys) {
				params.add(new BasicNameValuePair(key, requsetParams.get(key)));
			}
		}
		try {
			if (null != requsetParams) {
				UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params, "utf-8");
				httpPost.setEntity(entity);
			}
			response = httpClient.execute(httpPost);
			System.out.println("response:" + response);
//			setCookieStore(response);
			// 如果server端返回http 200
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				HttpEntity responseEntity = response.getEntity();
				System.out.println("response Body:" + EntityUtils.toString(responseEntity, "UTF-8"));
			} else {
				logger.error("请求失败 : " + response.getStatusLine().getReasonPhrase());
			}
		} catch (IOException e) {
			e.printStackTrace();
		} 

	}

	// 普通get
	public static void httpCommonGet(String url) {
//		Properties prop = ParseProperties.getInstance();
//		url = prop.getProperty("url") + url;
//		CloseableHttpClient httpClient = HttpClients.custom().setDefaultCookieStore(cookieStore).build();
//		CloseableHttpResponse response = null;
		HttpGet httpGet = new HttpGet();
		
		try {
			httpGet.setURI(new URI(url));
			response = httpClient.execute(httpGet);
//			System.out.println("cookie store:" + cookieStore.getCookies());
			// 如果server端返回http 200
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				HttpEntity responseEntity = response.getEntity();
				System.out.println(EntityUtils.toString(responseEntity, "UTF-8"));
			} else {
				logger.error("请求失败 : " + response.getStatusLine().getReasonPhrase());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// 普通post
	public static void httpCommonPost(String url, HashMap<String, String> requsetParams) {
		Properties prop = ParseProperties.getInstance();
		url = prop.getProperty("url") + url;
//		CloseableHttpClient httpClient = HttpClients.custom().setDefaultCookieStore(cookieStore).build();
//		CloseableHttpResponse response = null;
		HttpPost httpPost = new HttpPost(url);
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		if (null != requsetParams) {
			// 对request中的value转换
			Iterator<Entry<String, String>> requestIte = requsetParams.entrySet().iterator();
			while (requestIte.hasNext()) {
				Map.Entry<String, String> requestEntry = requestIte.next();
				requestEntry.setValue(requestEntry.getValue());
			}
			Set<String> keys = requsetParams.keySet();
			for (String key : keys) {
				params.add(new BasicNameValuePair(key, requsetParams.get(key)));
			}
		}
		try {
			if (null != requsetParams) {
				UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params, "utf-8");
				httpPost.setEntity(entity);
			}
			response = httpClient.execute(httpPost);
			System.out.println("cookie store:" + cookieStore.getCookies());
			// 如果server端返回http 200
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				HttpEntity responseEntity = response.getEntity();
				System.out.println("response Body:" + EntityUtils.toString(responseEntity, "UTF-8"));
			} else {
				logger.error("请求失败 : " + response.getStatusLine().getReasonPhrase());
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}

	// post json数据
	public static String httpJsonPost(String url, JSONObject jsonObj) {
		Properties prop = ParseProperties.getInstance();
		url = prop.getProperty("url") + url;
//		CloseableHttpClient httpClient = HttpClients.custom().setDefaultCookieStore(cookieStore).build();
//		CloseableHttpResponse response = null;
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
