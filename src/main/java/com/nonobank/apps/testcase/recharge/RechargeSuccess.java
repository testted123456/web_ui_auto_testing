package com.nonobank.apps.testcase.recharge;

import org.testng.annotations.Test;
import com.nonobank.apps.business.portal.Biz_Login;
import com.nonobank.apps.business.portal.Biz_Portal;
import com.nonobank.apps.business.recharge.Biz_User_Recharge;
import com.nonobank.apps.business.recharge.Biz_User_RechargeConfirm;
import com.nonobank.apps.testcase.base.BaseCase;
import com.nonobank.apps.utils.data.LoginResult;

public class RechargeSuccess extends BaseCase {

	Biz_Login biz_Login;
	Biz_Portal biz_Portal;
	Biz_User_Recharge biz_User_Recharge;
	Biz_User_RechargeConfirm biz_User_RechargeConfirm;

	@Test(dataProvider = "dataSource")
	public void test(String mobile, String password, String cardno, String money, String pay_password) {
		biz_Login.login(mobile, password, LoginResult.SUCESS);
		biz_Portal.navigate_to_myaccount();
		biz_User_Recharge.recharge(cardno, mobile);
		biz_User_RechargeConfirm.rechargeConfirm(money, pay_password);
	}
}
