package com.nonobank.apps.testcase.bindcard;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import com.nonobank.apps.business.account.Biz_DegreeCard;
import com.nonobank.apps.business.bank.Biz_User_Banks;
import com.nonobank.apps.business.bank.Biz_User_BindCard1;
import com.nonobank.apps.business.bank.Biz_User_BindCard2;
import com.nonobank.apps.business.portal.Biz_Register;
import com.nonobank.apps.business.user.Biz_Profile;
import com.nonobank.apps.testcase.base.BaseCase;

public class BindCardSuccess extends BaseCase {
	public static Logger logger = LogManager.getLogger(BindCardSuccess.class);

	Biz_Register biz_Register;
	Biz_Profile biz_Profile;
	Biz_DegreeCard biz_DegreeCard;
	Biz_User_Banks biz_User_Banks;
	Biz_User_BindCard1 biz_User_BindCard1;
	Biz_User_BindCard2 biz_User_BindCard2;

	@Test(dataProvider = "dataSource")
	public void test(String mobile, String user_name, String password, String password2, String checkCode,
			String validation, String myname, String identity_ID, String payPassword, String payPassword2,
			String bank_name, String bankcard_no) {
		biz_Register.register(mobile, user_name, password, password2, checkCode, validation, "success");
		biz_Profile.setPayPassword(payPassword, payPassword2);
		biz_DegreeCard.IDVerification(myname, identity_ID);
		biz_User_Banks.add_bankcard();
		biz_User_BindCard1.select_bank(bank_name);
		biz_User_BindCard2.bindCardSuccess(bank_name, bankcard_no);
	}
}
