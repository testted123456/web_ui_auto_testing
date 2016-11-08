package com.nonobank.apps.business.portal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.nonobank.apps.objectRepository.WebElementType;
import com.nonobank.apps.page.portal.Page_Login;

public class Biz_Login {

	public static Logger logger = LogManager.getLogger(Biz_Login.class);

	Page_Login page_Login = new Page_Login();

	/**
	 * nagivate_to_login 跳转到登录页面
	 */
	public void nagivate_to_login() {
		logger.info("跳转到登录页面...");
		page_Login.nagivate_to_login();
	}

	/**
	 * 
	 * login 登录
	 * @param username 用户名
	 * @param password 密码
	 * @param param 参数
	 */
	public void login(String username, String password, String param) {
		nagivate_to_login();
		logger.info("登录...");
		page_Login.input_username(username, param);
		page_Login.input_password(password);
		page_Login.input_checkCode();
		page_Login.submit();
	}

	/**
	 * is_login_success 登录成功或失败
	 * 
	 * @return true:成功 false:失败
	 */
	public boolean is_login_success() {
		boolean flag = page_Login.isElementDisplayed("head_name", WebElementType.WebLink, 15);

		if (flag == true) {
			logger.info("登录成功...");
		} else {
			logger.error("登录失败...");
		}
		return flag;
	}
}
