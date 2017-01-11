package com.nonobank.apps.testcase.licai;

import org.testng.annotations.Test;

import com.nonobank.apps.business.account.Biz_Account;
import com.nonobank.apps.business.licai.Biz_Licai_FinancePlan;
import com.nonobank.apps.business.licai.Biz_Licai_Order;
import com.nonobank.apps.business.licai.Biz_Licai_Payment;
import com.nonobank.apps.business.licai.Biz_Licai_Payment_Successful;
import com.nonobank.apps.business.portal.Biz_Login;
import com.nonobank.apps.business.portal.Biz_Portal;
import com.nonobank.apps.business.recharge.Biz_User_Recharge;
import com.nonobank.apps.business.recharge.Biz_User_RechargeConfirm;
import com.nonobank.apps.testcase.base.BaseCase;

/**
 * 用账户余额购买债券成功
 * 
 * @author ted
 *
 */
public class DebtPackageByBalanceSuccessful extends BaseCase {
	Biz_Account biz_Account;
	Biz_Login biz_Login;
	Biz_Portal biz_Portal;
	Biz_User_Recharge biz_User_Recharge;
	Biz_User_RechargeConfirm biz_User_RechargeConfirm;
	Biz_Licai_FinancePlan biz_Licai_FinancePlan;
	Biz_Licai_Order biz_Licai_Order;
	Biz_Licai_Payment biz_Licai_Payment;
	Biz_Licai_Payment_Successful biz_Licai_Payment_Successful;

	@Test(dataProvider = "dataSource")
	public void test(String mobile, String password, String checkCode, String cardno, String money, String pay_password,
			String id, String amount, String expectMessage) {
		biz_Portal.navigate_to_login();
		biz_Login.login(mobile, password, checkCode, "success");
		biz_Portal.navigate_to_myaccount();
		biz_Account.navigate_to_recharge();
		biz_User_Recharge.recharge();
		biz_User_RechargeConfirm.rechargeConfirm(money, pay_password, "success");
		biz_Licai_FinancePlan.purchase(id, amount, "/Licai/FinancePlan/");
		biz_Licai_Order.submit();
		biz_Licai_Payment.payByBalance(pay_password);

	}
}
