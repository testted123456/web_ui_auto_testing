package com.nonobank.apps.testcase.portal;

import org.testng.annotations.Test;
import com.nonobank.apps.business.portal.Biz_Register;
import com.nonobank.apps.testcase.base.BaseCase;

public class RegisterValidateRegisterMobile extends BaseCase {

	Biz_Register biz_Register;

	@Test(dataProvider = "dataSource")
	public void test(String mobile, String user_name, String password, String password2) {
		biz_Register.register(mobile, user_name, password, password2);
	}
}
