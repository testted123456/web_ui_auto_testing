package com.nonobank.apps.testcase.portal;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.nonobank.apps.testcase.action.LoginAction;
import com.nonobank.apps.testcase.base.BaseCase;

public class LoginSuccessByUserName extends BaseCase {


	LoginAction loginAction = new LoginAction();

	@Test(dataProvider = "dataSource")
	public void test(String userName, String password) {
		boolean result = loginAction.login(userName, password, "user_name");
		Assert.assertEquals(result, true);
	}

}
