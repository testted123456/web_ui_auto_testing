package com.nonobank.apps.testcase.licai;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.nonobank.apps.testcase.action.LoginAction;
import com.nonobank.apps.testcase.action.PaymentAction;
import com.nonobank.apps.testcase.base.BaseCase;

public class JingxuanByOldBankCardSuccessful extends BaseCase {

	public static Logger logger = LogManager.getLogger(JingxuanByOldBankCardSuccessful.class);
	LoginAction loginAction = new LoginAction();
	PaymentAction paymentAction = new PaymentAction();

	@Test(dataProvider = "dataSource")
	public void test(String mobile, String password, String id, String amount, String cardNO, String payPassword,
			String smsCode) {

		boolean loginResult = loginAction.login(mobile, password, "mobile_num");
		Assert.assertEquals(loginResult, true);
		boolean paymentResult = paymentAction.payment(id, amount, payPassword);
		Assert.assertEquals(paymentResult, true);
	}
}
