package com.nonobank.apps.testcase.portal;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.nonobank.apps.business.portal.Biz_Register;
import com.nonobank.apps.testcase.base.BaseCase;

public class RegisterSuccess extends BaseCase {

	Biz_Register biz_Register = new Biz_Register();

	@Test(dataProvider = "dataSource")
	public void test(String mobile, String user_name, String password, String password2, String check_code,
			String sms_code) {
		biz_Register.navigate_to_register();
		biz_Register.register(mobile, user_name, password, password2, check_code, sms_code);
		boolean registerResult = biz_Register.isRegisterSuccess();
		Assert.assertEquals(registerResult, true);
	}
}
