package com.nonobank.apps.testcase.licai;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.nonobank.apps.testcase.action.LoginAction;
import com.nonobank.apps.testcase.action.PaymentAction;
import com.nonobank.apps.testcase.action.RechargeAction;
import com.nonobank.apps.testcase.base.BaseCase;

/**
 * 用账户余额购买精选成功
 * 
 * @author ted
 *
 */
public class TiexinByBalanceSuccessful extends BaseCase {

	public static Logger logger = LogManager.getLogger(TiexinByBalanceSuccessful.class);
	LoginAction loginAction = new LoginAction();
	RechargeAction rechargeAction = new RechargeAction();
	PaymentAction paymentAction = new PaymentAction();


	@Test(dataProvider = "dataSource")
	public void test(String mobile, String password, String cardno, String money, String pay_password, String smsCode,
			String id, String amount, String payPassword) {
		boolean loginResult = loginAction.login(mobile, password, "mobile_num");
		Assert.assertEquals(loginResult, true);
		boolean rechargeResult = rechargeAction.recharge(mobile, cardno, money, pay_password, smsCode);
		Assert.assertEquals(rechargeResult, true);

		boolean paymentResult = paymentAction.payment(id, amount, payPassword);
		Assert.assertEquals(paymentResult, true);
	}
}
