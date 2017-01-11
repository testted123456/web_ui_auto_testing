package com.nonobank.apps.testcase.portal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import com.nonobank.apps.business.account.Biz_Account;
import com.nonobank.apps.business.portal.Biz_Login;
import com.nonobank.apps.business.portal.Biz_Portal;
import com.nonobank.apps.testcase.base.BaseCase;

public class Login extends BaseCase {
	Biz_Portal biz_Portal;
	Biz_Login biz_Login;
	Biz_Account biz_Account;
	public static Logger logger = LogManager.getLogger(Login.class);

	@Test(dataProvider = "dataSource")
	public void test(String testcaseName, String testcaseDescription, String loginName, String password,
			String checkCode, String expectResult) {
		caseName = testcaseName;
		caseDescription = testcaseDescription;
		inputParams = loginName;
		biz_Portal.navigate_to_login();
		biz_Login.login(loginName, password, checkCode, expectResult);
		biz_Account.logout();
	}

}
