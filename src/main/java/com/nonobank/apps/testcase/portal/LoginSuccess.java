package com.nonobank.apps.testcase.portal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import com.nonobank.apps.business.account.Biz_Account;
import com.nonobank.apps.business.portal.Biz_Login;
import com.nonobank.apps.business.portal.Biz_Portal;
import com.nonobank.apps.testcase.base.BaseCase;

public class LoginSuccess extends BaseCase {
	Biz_Portal biz_Portal;
	Biz_Login biz_Login;
	Biz_Account biz_Account;
	public static Logger logger = LogManager.getLogger(LoginSuccess.class);

	@Test(dataProvider = "dataSource")
	public void test(String loginName, String password, String checkCode) {
		biz_Portal.navigate_to_login();
		biz_Login.login(loginName, password, checkCode, "成功");
		biz_Account.logout();
	}

}
