package com.nonobank.apps.interfaces.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nonobank.apps.interfaces.util.MD5Util;
import com.nonobank.apps.interfaces.util.SendRequestByCookies;

public class AjaxLoginTest {
	public static Logger logger = LogManager.getLogger(AjaxLoginTest.class);
	public static List<HashMap<String, String>> params = new ArrayList<HashMap<String, String>>();
	public static String url = "https://www.sit.nonobank.com/v6/Login/AjaxLogin";

	public static void ajaxLogin(String loginname, String loginpwd, String checkCode, String loginFrom,
			String loginBiz) {
		loginpwd = MD5Util.toMD5(loginpwd);
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("loginname", loginname);
		requestParams.put("loginpwd", loginpwd);
		requestParams.put("checkCode", checkCode);
		requestParams.put("loginFrom", loginFrom);
		requestParams.put("loginBiz", loginBiz);
		SendRequestByCookies.httpCommonPostForLogin(url, requestParams);
	}

	// FAILED: test("ZTV740sb", "24848", "18602521267", "a1b0", "it789123",
	// "it789123", "上海", "复旦大学", "张江校区", "2015", "专科", "141023199305134569",
	// "韩诺", "14092819931200595X", "管理学", "微信", "0615", "消费购物", "消费购物消费购物消费购物",
	// "500", "a1b0", "2", "2", "'838388@192.com", "上海市浦东新区张东路1158弄", "4",
	// "一二三", "13100000123", "二三四", "13100001234", "四五六", "13100001003", "五六七",
	// "13100001004", "六七八", "13100001005", "png/id.png", "6222021001126370060",
	// "中国工商银行", "18663049557", "0615")
	public static void main(String[] args) {
		CheckCodeTest.checkCode();
		ajaxLogin("hanhao", "it789123", "8888", "pc", "nono");
	}

}
