package com.nonobank.apps.testcase.portal;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.nonobank.apps.testcase.action.LoginAction;
import com.nonobank.apps.testcase.base.BaseCase;

public class LoginSuccessByMobile extends BaseCase {

	LoginAction loginAction = new LoginAction();

	@Test(dataProvider = "dataSource")
	public void test(String loginName, String password) {
		boolean loginResult = loginAction.login(loginName, password, "mobile_num");
		Assert.assertEquals(loginResult, true);
	}
}
