package com.nonobank.apps.testcase.action;

import com.nonobank.apps.business.portal.Biz_Login;

public class LoginAction {
	Biz_Login biz_Login = new Biz_Login();

	public boolean login(String loginName, String password, String param) {
		biz_Login.nagivate_to_login();
		biz_Login.login(loginName, password, "mobile_num");
		boolean loginResult = biz_Login.is_login_success();
		return loginResult;
	}
}
