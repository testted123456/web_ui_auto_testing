package com.nonobank.apps.testcase.portal;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.nonobank.apps.testcase.action.LoginAction;
import com.nonobank.apps.testcase.base.BaseCase;

public class LoginValidateUnRegisterMobile extends BaseCase {

	LoginAction loginAction = new LoginAction();

	@Test(dataProvider = "dataSource")
	public void test(String mobile, String password) {
		boolean result = loginAction.login(mobile, password, "mobile_num");
		Assert.assertEquals(result, false);

	}
}
