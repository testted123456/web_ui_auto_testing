package com.nonobank.apps.testcase.withdrawal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import com.nonobank.apps.business.account.Biz_Account;
import com.nonobank.apps.business.portal.Biz_Login;
import com.nonobank.apps.business.portal.Biz_Portal;
import com.nonobank.apps.business.withdrawal.Biz_User_Withdrawal;
import com.nonobank.apps.business.withdrawal.Biz_User_WithdrawalConfirm;
import com.nonobank.apps.testcase.base.BaseCase;

public class WithDrawalSuccess extends BaseCase {
	public static Logger logger = LogManager.getLogger(WithDrawalSuccess.class);
	Biz_Portal biz_Portal;
	Biz_Login biz_Login;
	Biz_Account biz_Account;
	Biz_User_Withdrawal biz_User_Withdrawal;
	Biz_User_WithdrawalConfirm biz_User_WithdrawalConfirm;

	@Test(dataProvider = "dataSource")
	public void test(String mobile, String password, String checkCode, String cardno, String money,
			String pay_password) {
		biz_Portal.navigate_to_login();
		biz_Login.login(mobile, password, checkCode, "成功");
		biz_Account.navigate_to_withDrawal();
		biz_User_Withdrawal.withDrawal(cardno, money);
		biz_User_WithdrawalConfirm.confirm(pay_password);
		biz_User_WithdrawalConfirm.isWithDrawalConfirmSuccess();
	}
}
