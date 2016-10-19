package com.nonobank.apps.testcase.bindcard;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import com.nonobank.apps.business.account.Biz_Account;
import com.nonobank.apps.business.bank.Biz_User_Banks;
import com.nonobank.apps.business.bank.Biz_User_BindCard1;
import com.nonobank.apps.business.bank.Biz_User_BindCard2;
import com.nonobank.apps.business.portal.Biz_Portal;
import com.nonobank.apps.business.portal.Biz_Register;
import com.nonobank.apps.testcase.base.BaseCase;

public class BindCardSuccess extends BaseCase {
	public static Logger logger = LogManager.getLogger(BindCardSuccess.class);

	Biz_Register biz_Register;
	Biz_Portal biz_Portal;
	Biz_Account biz_Account;
	Biz_User_Banks biz_User_Banks;
	Biz_User_BindCard1 biz_User_BindCard1;
	Biz_User_BindCard2 biz_User_BindCard2;

	@Test(dataProvider = "dataSource")
	public void test(String mobile, String user_name, String password, String password2, String check_code,
			String sms_code, String myname, String mycard, String payPassword, String payPassword1, String bank_name,
			String bankcard_no) {
		biz_Register.register(mobile, user_name, password, password2, check_code, sms_code);
		biz_Portal.close_dialog();
		biz_Portal.navigate_to_myaccount();
		biz_Account.IDVerification(myname, mycard);
		biz_Account.setPayPassword(payPassword, payPassword1);
		biz_Account.navigate_to_userbanks();
		biz_User_Banks.add_bankcard();
		biz_User_BindCard1.select_bank(bank_name);
		biz_User_BindCard2.bindCardSuccess(bank_name, bankcard_no);
	}
}
