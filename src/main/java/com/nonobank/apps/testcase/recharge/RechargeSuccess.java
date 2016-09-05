package com.nonobank.apps.testcase.recharge;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.nonobank.apps.business.portal.Biz_Portal;
import com.nonobank.apps.business.recharge.Biz_User_Recharge;
import com.nonobank.apps.business.recharge.Biz_User_RechargeConfirm;
import com.nonobank.apps.testcase.action.LoginAction;
import com.nonobank.apps.testcase.action.RechargeAction;
import com.nonobank.apps.testcase.base.BaseCase;

public class RechargeSuccess extends BaseCase {

	LoginAction loginAction = new LoginAction();
	RechargeAction rechargeAction = new RechargeAction();
	Biz_Portal biz_Portal;
	Biz_User_Recharge biz_User_Recharge1;
	Biz_User_RechargeConfirm biz_User_RechargeConfirm;

	@Test(dataProvider = "dataSource")
	public void test(String mobile, String password, String cardno, String money, String pay_password, String smsCode) {
		boolean loginResult = loginAction.login(mobile, password, "mobile_num");
		Assert.assertEquals(loginResult, true);
		boolean rechargeResult = rechargeAction.recharge(mobile, cardno, money, pay_password, smsCode);
		Assert.assertEquals(rechargeResult, true);

	}
}
