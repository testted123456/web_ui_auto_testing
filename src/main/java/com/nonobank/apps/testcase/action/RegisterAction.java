package com.nonobank.apps.testcase.action;

import com.nonobank.apps.business.portal.Biz_Register;

public class RegisterAction {
	Biz_Register biz_Register = new Biz_Register();

	// 注册操作
	public boolean register(String mobile, String user_name, String password, String password2, String check_code,
			String sms_code, String... strs) {
		biz_Register.navigate_to_register();
		biz_Register.register(mobile, user_name, password, password2, check_code, sms_code, strs);
		boolean registerResult = biz_Register.isRegisterSuccess();
		return registerResult;
	}
}
