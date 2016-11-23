package com.nonobank.apps.testcase.portal;

import org.testng.annotations.Test;
import com.nonobank.apps.business.portal.Biz_Register;
import com.nonobank.apps.testcase.base.BaseCase;
import com.nonobank.apps.utils.data.RegisterResult;

public class RegisterValidateNotexistInvite extends BaseCase {

	Biz_Register biz_Register;

	@Test(dataProvider = "dataSource")
	public void test(String mobile, String user_name, String password, String password2, RegisterResult registerResult,
			String invite) {
		biz_Register.register(mobile, user_name, password, password2, RegisterResult.SUCCESS, invite);
	}
}
