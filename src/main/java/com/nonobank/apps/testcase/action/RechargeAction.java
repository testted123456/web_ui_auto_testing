package com.nonobank.apps.testcase.action;

import com.nonobank.apps.business.portal.Biz_Portal;
import com.nonobank.apps.business.recharge.Biz_User_Recharge;
import com.nonobank.apps.business.recharge.Biz_User_RechargeConfirm;

public class RechargeAction {
	Biz_Portal biz_Portal = new Biz_Portal();
	Biz_User_Recharge biz_User_Recharge = new Biz_User_Recharge();
	Biz_User_RechargeConfirm biz_User_RechargeConfirm = new Biz_User_RechargeConfirm();

	public boolean recharge(String mobile, String cardno, String money, String pay_password, String smsCode) {
		biz_Portal.navigate_to_myaccount();
		biz_User_Recharge.navigateToRecharge();
		biz_User_Recharge.recharge(cardno, mobile);
		biz_User_RechargeConfirm.rechargeConfirm(money, pay_password, smsCode);
		boolean rechargeResult = biz_User_RechargeConfirm.isRechargeConfirmSuccess(money);

		return rechargeResult;
	}
}
