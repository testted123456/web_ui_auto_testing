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
		String elementName = null;
		try {
			nagivate_to_login();
			logger.info("登录...");
			page_Login.input_username(username);
			page_Login.input_password(password);
			page_Login.input_checkCode();
			page_Login.submit();
			elementName = "account_name";
			boolean flag = login_check(elementName);
			Assertion.assertEquals(true, flag, Biz_Login.class, "登录成功脚本");
		} catch (Exception e) {
			switch (loginResult.getCode()) {
			case 2:

				break;

			default:
				Assertion.assertEquals(Biz_Login.class, "查找name=" + elementName + "元素");
				break;
			}
		}
	}

	/**
	 * is_login_success 登录成功或失败
	 * 
	 * @return true:成功 false:失败
	 */
	public boolean login_check(String elementName) {
		boolean flag = page_Login.isElementDisplayed(elementName, WebElementType.WebLink, 15);
		return flag;
	}
}
