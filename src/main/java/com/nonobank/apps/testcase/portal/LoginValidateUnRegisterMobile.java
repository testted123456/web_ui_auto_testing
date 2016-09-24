package com.nonobank.apps.testcase.portal;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.nonobank.apps.business.portal.Biz_Login;
import com.nonobank.apps.testcase.base.BaseCase;

public class LoginValidateUnRegisterMobile extends BaseCase {

	Biz_Login biz_Login = new Biz_Login();

	@Test(dataProvider = "dataSource")
	public void test(String mobile, String password) {
		biz_Login.login(mobile, password, "mobile_num");
		boolean loginResult = biz_Login.is_login_success();
		Assert.assertEquals(loginResult, false);

	}
}
