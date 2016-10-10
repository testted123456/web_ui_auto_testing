package com.nonobank.apps.business.admin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nonobank.apps.objectRepository.WebElementType;
import com.nonobank.apps.page.admin.Page_Login;

public class Biz_Login {

	public static Logger logger = LogManager.getLogger(Biz_Login.class);

	Page_Login page_Admin_Login = new Page_Login();

	public void login(String username, String password) {
		page_Admin_Login.nagivate_to_adminpage();
		page_Admin_Login.sleep(10000);
		page_Admin_Login.input_username(username);
		page_Admin_Login.sleep(3000);
		page_Admin_Login.input_password(password);
		page_Admin_Login.sleep(3000);
		page_Admin_Login.login();
	}

	public boolean is_login_success() {
		boolean flag = page_Admin_Login.isElementDisplayed("logout", WebElementType.WebLink, 15);

		if (flag == true) {
			logger.info("登录成功...");
		} else {
			logger.error("登录失败...");
		}
		return flag;
	}
}
