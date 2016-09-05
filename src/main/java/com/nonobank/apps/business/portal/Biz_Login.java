package com.nonobank.apps.business.portal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nonobank.apps.objectRepository.WebElementType;
import com.nonobank.apps.page.portal.Page_Login;

public class Biz_Login {

	public static Logger logger = LogManager.getLogger(Biz_Login.class);

	Page_Login page_Login = new Page_Login();

	public void nagivate_to_login() {
		logger.info("跳转到登录页面...");
		page_Login.nagivate_to_login();
	}

	public void login(String arg, String password, String param) {
		logger.info("登录...");
		page_Login.input_username(arg, param);
		page_Login.input_password(password);
		page_Login.input_CheckCode();
		page_Login.submit();
	}

	public boolean is_login_success() {
		boolean flag = page_Login.isDisplayed(WebElementType.WebLink, "logout");
		if (flag == true) {
			logger.info("登录成功...");
		} else {
			logger.info("登录失败...");
		}
		return flag;
	}
}
