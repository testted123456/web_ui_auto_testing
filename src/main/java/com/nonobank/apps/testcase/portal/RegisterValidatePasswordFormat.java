package com.nonobank.apps.testcase.portal;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.nonobank.apps.testcase.action.RegisterAction;
import com.nonobank.apps.testcase.base.BaseCase;

public class RegisterValidatePasswordFormat extends BaseCase {

	RegisterAction registerAction = new RegisterAction();

	@Test(dataProvider = "dataSource")
	public void test(String mobile, String user_name, String password, String password2, String check_code,
			String sms_code) {
		boolean result = registerAction.register(mobile, user_name, password, password2, check_code, sms_code);
		Assert.assertEquals(result, false);
	}
}
