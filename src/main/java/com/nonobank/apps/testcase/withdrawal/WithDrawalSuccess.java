package com.nonobank.apps.testcase.withdrawal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import com.nonobank.apps.business.portal.Biz_Login;
import com.nonobank.apps.business.portal.Biz_Portal;
import com.nonobank.apps.business.withdrawal.Biz_User_Withdrawal;
import com.nonobank.apps.business.withdrawal.Biz_User_WithdrawalConfirm;
import com.nonobank.apps.testcase.base.BaseCase;
import com.nonobank.apps.utils.data.BankCardUtils;
import com.nonobank.apps.utils.data.UserInfoUtils;

public class WithDrawalSuccess extends BaseCase {
	public static Logger logger = LogManager.getLogger(WithDrawalSuccess.class);

	Biz_Login biz_Login;
	Biz_Portal biz_Portal;
	Biz_User_Withdrawal biz_User_Withdrawal;
	Biz_User_WithdrawalConfirm biz_User_WithdrawalConfirm;

	@Test(dataProvider = "dataSource")
	public void test(String mobile, String password, String cardno, String money, String pay_password) {
		logger.info("begin to test...");
		biz_Login.login(mobile, password, "mobile_num");
		biz_Portal.close_dialog();
		biz_Portal.navigate_to_myaccount();
		biz_User_Withdrawal.navigate_to_withdrawal();

		if (cardno.equals("random")) {
			mobile = UserInfoUtils.getUserName(mobile, "mobile_num");
			cardno = BankCardUtils.getBankCardByMobile(mobile);
		}

		biz_User_Withdrawal.withDrawal(cardno, money);
		biz_User_WithdrawalConfirm.confirm(pay_password);
		biz_User_WithdrawalConfirm.isWithDrawalConfirmSuccess();
	}
}
