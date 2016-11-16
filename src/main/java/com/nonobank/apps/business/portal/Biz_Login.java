package com.nonobank.apps.business.portal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.nonobank.apps.objectRepository.WebElementType;
import com.nonobank.apps.page.portal.Page_Login;
import com.nonobank.apps.utils.data.Assertion;
import com.nonobank.apps.utils.data.LoginResult;

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
	 * 
	 * @param username
	 *            用户名
	 * @param password
	 *            密码
	 * @param param
	 *            参数
	 */
	public void login(String username, String password, LoginResult loginResult) {
		// try {
		nagivate_to_login();
		logger.info("登录...");
		page_Login.input_username(username);
		page_Login.input_password(password);
		page_Login.input_checkCode();
		page_Login.submit();
		boolean flag = page_Login.isElementDisplayed("account_name", WebElementType.WebLink, 15);
		Assertion.assertEquals(true, flag, Biz_Login.class, "登录成功脚本");
		// } catch (Exception e) {
		// switch (loginResult.getCode()) {
		// case 2:
		//
		// break;
		//
		// }
		// }
	}

}
