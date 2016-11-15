package com.nonobank.apps.testcase.portal;

import org.testng.annotations.Test;
import com.nonobank.apps.business.portal.Biz_Login;
import com.nonobank.apps.testcase.base.BaseCase;
import com.nonobank.apps.utils.data.LoginResult;

public class LoginSuccess extends BaseCase {

	Biz_Login biz_Login;

	@Test(dataProvider = "dataSource")
	public void test(String loginName, String password) {
		biz_Login.login(loginName, password, LoginResult.SUCESS);
	}
}
