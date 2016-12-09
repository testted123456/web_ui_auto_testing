package com.nonobank.apps.testcase.portal;

import org.testng.annotations.Test;
import com.nonobank.apps.business.portal.Biz_Portal;
import com.nonobank.apps.business.portal.Biz_Register;
import com.nonobank.apps.testcase.base.BaseCase;

public class RegisterValidateMobileError extends BaseCase {
	Biz_Portal biz_Portal;
	Biz_Register biz_Register;

	@Test(dataProvider = "dataSource")
	public void test(String mobile, String user_name, String password, String password2, String checkCode,
			String validation) {
		biz_Portal.navigate_to_register();
		biz_Register.register(mobile, user_name, password, password2, checkCode, validation, "请输入有效的手机号码，以便找回密码");
	}
}
