package com.nonobank.apps.testcase.recharge;

import org.testng.annotations.Test;
import com.nonobank.apps.business.account.Biz_Account;
import com.nonobank.apps.business.portal.Biz_Login;
import com.nonobank.apps.business.portal.Biz_Portal;
import com.nonobank.apps.business.recharge.Biz_User_Recharge;
import com.nonobank.apps.business.recharge.Biz_User_RechargeConfirm;
import com.nonobank.apps.testcase.base.BaseCase;

public class Recharge extends BaseCase {

	Biz_Login biz_Login;
	Biz_Portal biz_Portal;
	Biz_Account biz_Account;
	Biz_User_Recharge biz_User_Recharge;
	Biz_User_RechargeConfirm biz_User_RechargeConfirm;

	@Test(dataProvider = "dataSource")
	public void test(String testcaseName, String testcaseDescription, String mobile, String password, String checkCode,
			String bankName, String money, String pay_password, String expectResult) {
		caseName = testcaseName;
		caseDescription = testcaseDescription;
		inputParams = mobile;
		actualResult = "成功";
		errorMessage = "无错误信息";

		biz_Portal.navigate_to_login();
		biz_Login.login(mobile, password, checkCode, "success");
		biz_Account.navigate_to_recharge();
		biz_User_Recharge.recharge(bankName);
		biz_User_RechargeConfirm.rechargeConfirm(money, pay_password, expectResult);
		biz_Portal.navigate_to_myaccountByName();
	}
}
