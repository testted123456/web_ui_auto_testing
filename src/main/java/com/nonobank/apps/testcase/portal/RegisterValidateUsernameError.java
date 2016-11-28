package com.nonobank.apps.testcase.portal;

import org.testng.annotations.Test;
import com.nonobank.apps.business.portal.Biz_Register;
import com.nonobank.apps.testcase.base.BaseCase;

public class RegisterValidateUsernameError extends BaseCase {

	Biz_Register biz_Register;

	@Test(dataProvider = "dataSource")
	public void test(String mobile, String user_name, String password, String password2, String checkCode,
			String validation) {
		biz_Register.register(mobile, user_name, password, password2, checkCode, validation, "usernameError",
				"只能使用字母、数字或下划线");
	}
}
