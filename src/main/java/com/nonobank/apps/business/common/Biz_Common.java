package com.nonobank.apps.business.common;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nonobank.apps.page.common.Page_Common;


public class Biz_Common {

	public static Logger logger = LogManager.getLogger(Biz_Common.class);
	Page_Common page_Common=new Page_Common();
	//点击登录链接
	public void click_loginBus(){
		page_Common.click_login();
	}
	//点击信用证书查询
	public void click_creditBookSearchBus(){
		page_Common.click_creditBookSearch();
	}
	//点击安全退出
	public void click_logoutBus(){
		page_Common.click_logout();
	}
	//点击合作请联系邮箱
	public void click_cooperateBus(){
		page_Common.click_cooperate();
	}
	//点击投诉及建议请联系邮箱
	public void click_complaintBus(){
		page_Common.click_complaint();
	}
	//点击你好：用户名
	public void click_userNameBus(){
		page_Common.click_userName();
	}
	
	
}
