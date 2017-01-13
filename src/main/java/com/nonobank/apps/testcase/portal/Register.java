package com.nonobank.apps.testcase.portal;

import org.testng.annotations.Test;
import com.nonobank.apps.business.account.Biz_Account;
import com.nonobank.apps.business.portal.Biz_Portal;
import com.nonobank.apps.business.portal.Biz_Register;
import com.nonobank.apps.testcase.base.BaseCase;

public class Register extends BaseCase {
	Biz_Portal biz_Portal;
	Biz_Register biz_Register;
	Biz_Account biz_Account;

	@Test(dataProvider = "dataSource")
	public void test(String testcaseName, String testcaseDescription, String mobile, String user_name, String password,
			String password2, String checkCode, String validation, String expectResult) {
		caseName = testcaseName;
		caseDescription = testcaseDescription;
		inputParams = mobile;
		biz_Portal.navigate_to_register();
		biz_Register.register(mobile, user_name, password, password2, checkCode, validation, expectResult);
		biz_Portal.navigate_to_myaccount();
	}
}
