package com.nonobank.apps.testcase.portal;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.nonobank.apps.business.portal.Biz_Login;
import com.nonobank.apps.testcase.base.BaseCase;

public class LoginSuccessByMobile extends BaseCase {

	Biz_Login biz_Login = new Biz_Login();

	@Test(dataProvider = "dataSource")
	public void test(String loginName, String password) {
		 biz_Login.login(loginName, password, "mobile_num");
		 boolean loginResult =biz_Login.is_login_success();
		Assert.assertEquals(loginResult, true);
	}
}
