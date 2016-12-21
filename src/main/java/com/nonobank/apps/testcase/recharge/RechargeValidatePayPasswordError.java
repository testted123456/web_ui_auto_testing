package com.nonobank.apps.testcase.recharge;

import org.testng.annotations.Test;
import com.nonobank.apps.business.account.Biz_Account;
import com.nonobank.apps.business.portal.Biz_Login;
import com.nonobank.apps.business.portal.Biz_Portal;
import com.nonobank.apps.business.recharge.Biz_User_Recharge;
import com.nonobank.apps.business.recharge.Biz_User_RechargeConfirm;
import com.nonobank.apps.testcase.base.BaseCase;

public class RechargeValidatePayPasswordError extends BaseCase {

	Biz_Login biz_Login;
	Biz_Portal biz_Portal;
	Biz_Account biz_Account;
	Biz_User_Recharge biz_User_Recharge;
	Biz_User_RechargeConfirm biz_User_RechargeConfirm;

	@Test(dataProvider = "dataSource")
	public void test(String mobile, String password, String checkCode, String money, String pay_password) {
		biz_Portal.navigate_to_login();
		biz_Login.login(mobile, password, checkCode, "成功");
		biz_Account.navigate_to_recharge();
		biz_User_Recharge.recharge();
		biz_User_RechargeConfirm.rechargeConfirm(money, pay_password, "支付密码错误！");
	}
}
