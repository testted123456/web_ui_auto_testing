package com.nonobank.apps.testcase.recharge;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.nonobank.apps.business.portal.Biz_Login;
import com.nonobank.apps.business.portal.Biz_Portal;
import com.nonobank.apps.business.recharge.Biz_User_Recharge;
import com.nonobank.apps.business.recharge.Biz_User_RechargeConfirm;
import com.nonobank.apps.testcase.base.BaseCase;

public class RechargeSuccess extends BaseCase {

	Biz_Login biz_Login = new Biz_Login();
	Biz_Portal biz_Portal = new Biz_Portal();
	Biz_User_Recharge biz_User_Recharge = new Biz_User_Recharge();
	Biz_User_RechargeConfirm biz_User_RechargeConfirm = new Biz_User_RechargeConfirm();

	@Test(dataProvider = "dataSource")
	public void test(String mobile, String password, String cardno, String money, String pay_password, String smsCode) {
		biz_Login.login(mobile, password, "mobile_num");
		boolean loginResult = biz_Login.is_login_success();
		Assert.assertEquals(loginResult, true);
		biz_Portal.navigate_to_myaccount();
		biz_User_Recharge.navigateToRecharge();
		biz_User_Recharge.recharge(cardno, mobile);
		biz_User_RechargeConfirm.rechargeConfirm(money, pay_password, smsCode);
		boolean rechargeResult = biz_User_RechargeConfirm.isRechargeConfirmSuccess(money);
		Assert.assertEquals(rechargeResult, true);

	}
}
