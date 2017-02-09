package com.nonobank.apps.testcase.bindcard;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import com.nonobank.apps.business.account.Biz_Account;
import com.nonobank.apps.business.bank.Biz_User_Banks;
import com.nonobank.apps.business.bank.Biz_User_BindCard1;
import com.nonobank.apps.business.bank.Biz_User_BindCard2;
import com.nonobank.apps.business.portal.Biz_Login;
import com.nonobank.apps.business.portal.Biz_Portal;
import com.nonobank.apps.testcase.base.BaseCase;

public class LoginBindCard extends BaseCase {
	public static Logger logger = LogManager.getLogger(LoginBindCard.class);
	Biz_Portal biz_Portal;
	Biz_Account biz_Account;
	Biz_User_Banks biz_User_Banks;
	Biz_User_BindCard1 biz_User_BindCard1;
	Biz_User_BindCard2 biz_User_BindCard2;
	Biz_Login biz_Login;

	@Test(dataProvider = "dataSource")
	public void test(String testcaseName, String testcaseDescription, String loginName, String password,
			String checkCode, String bank_name, String bankcard_no, String validation, String expectResult) {
		caseName = testcaseName;
		caseDescription = testcaseDescription;
		inputParams = loginName;
		actualResult = "成功";
		errorMessage = "无错误信息";
		
		biz_Portal.navigate_to_login();
		biz_Login.login(loginName, password, checkCode, "success");
		
		biz_Account.navigate_to_banks();
		biz_User_Banks.navigate_to_bindCard1();
		biz_User_BindCard1.select_bank(bank_name);
		biz_User_BindCard2.bindCard(bankcard_no, validation, expectResult);
	}
}
