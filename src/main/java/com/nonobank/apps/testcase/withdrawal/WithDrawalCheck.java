package com.nonobank.apps.testcase.withdrawal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import com.nonobank.apps.business.portal.Biz_Login;
import com.nonobank.apps.business.portal.Biz_Portal;
import com.nonobank.apps.business.withdrawal.Biz_User_Withdrawal;
import com.nonobank.apps.testcase.base.BaseCase;

public class WithDrawalCheck extends BaseCase {
	public static Logger logger = LogManager.getLogger(WithDrawalCheck.class);
	Biz_Login biz_Login;
	Biz_Portal biz_Portal;
	Biz_User_Withdrawal biz_User_Withdrawal;

	@Test(dataProvider = "dataSource")
	public void test(String mobile, String password, String checkCode) {
		logger.info("begin to test...");

		biz_Login.login(mobile, password, checkCode, null);
		biz_Portal.close_dialog();
		biz_Portal.navigate_to_myaccount();
		biz_User_Withdrawal.navigate_to_withdrawal();
		biz_User_Withdrawal.checkWithdrawalCash();
	}
}
