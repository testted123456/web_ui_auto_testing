package com.nonobank.apps.testcase.portal;

import org.testng.annotations.Test;
import com.nonobank.apps.business.portal.Biz_Portal;
import com.nonobank.apps.business.portal.Biz_Register;
import com.nonobank.apps.testcase.base.BaseCase;

public class RegisterValidateUsernameLength extends BaseCase {
	Biz_Portal biz_Portal;
	Biz_Register biz_Register;

	@Test(dataProvider = "dataSource")
	public void test(String mobile, String user_name, String password, String password2, String checkCode,
			String validation) {
		biz_Portal.navigate_to_register();
		biz_Register.register(mobile, user_name, password, password2, checkCode, validation, "6-16位字符，可以是字母、数字、下划线的组合");
	}
}
