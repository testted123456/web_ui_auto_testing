package com.nonobank.apps.page.common;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nonobank.apps.objectRepository.WebLink;
import com.nonobank.apps.page.base.BasePage;

public class Page_Common extends BasePage{
	public static Logger logger = LogManager.getLogger(Page_Common.class);
	public String username;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	// 点击登录链接
	public void click_login(){
		logger.info("点击登录链接...");
		WebLink click_login=objectFactory.getWebLink("登录链接");
		click_login.click();
	}
	// 点击信用证书查询
	public void click_creditBookSearch(){
		logger.info("信用证书查询...");
		WebLink click_creditBookSearch=objectFactory.getWebLink("信用证书查询");
		click_creditBookSearch.click();
	}
	// 点击安全退出
	public void click_logout(){
		logger.info("点击安全退出...");
		WebLink click_logout=objectFactory.getWebLink("安全退出");
		click_logout.click();
	}
	// 点击合作请联系邮箱
	public void click_cooperate(){
		logger.info("点击合作请联系邮箱...");
		WebLink click_cooperate=objectFactory.getWebLink("合作请联系邮箱");
		click_cooperate.click();
	}
	// 点击投诉及建议请联系邮箱
	public void click_complaint(){
		logger.info("点击投诉及建议请联系邮箱...");
		WebLink click_complaint=objectFactory.getWebLink("投诉及建议请联系邮箱");
		click_complaint.click();
	}
	//点击你好：用户名
	public void click_userName(){
		logger.info("点击你好：用户名...");
		WebLink click_userName=objectFactory.getWebLink("你好：账号名");
		click_userName.click();
	}
	//获取用户名
	public String getUserName(){
		logger.info("获取用户名...");
		WebLink link_account=objectFactory.getWebLink("你好：账号名");
		String username = link_account.getText();
		return username;
	}
	
	
}
