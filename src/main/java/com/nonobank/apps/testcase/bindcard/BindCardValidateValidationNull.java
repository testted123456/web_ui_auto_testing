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

public class BindCardValidateValidationNull extends BaseCase {
	public static Logger logger = LogManager.getLogger(BindCardValidateValidationNull.class);
	Biz_Portal biz_Portal;
	Biz_Login biz_Login;
	Biz_Account biz_Account;
	Biz_User_Banks biz_User_Banks;
	Biz_User_BindCard1 biz_User_BindCard1;
	Biz_User_BindCard2 biz_User_BindCard2;

	@Test(dataProvider = "dataSource")
	public void test(String mobile, String user_name, String password, String password2, String checkCode,
			String validation, String myname, String identity_ID, String payPassword, String payPassword2,
			String bank_name, String bankcard_no, String validation2) {
		biz_Portal.navigate_to_login();
		biz_Login.login(mobile, password, checkCode, "成功");
		biz_Account.navigate_to_banks();
		biz_User_Banks.navigate_to_bindCard1();
		biz_User_BindCard1.select_bank(bank_name);
		biz_User_BindCard2.bindCard(bankcard_no, checkCode, "请输入验证码");

	}
}
