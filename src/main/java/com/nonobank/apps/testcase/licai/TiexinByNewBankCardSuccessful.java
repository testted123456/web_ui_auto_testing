package com.nonobank.apps.testcase.licai;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.nonobank.apps.testcase.action.LoginAction;
import com.nonobank.apps.testcase.action.PaymentAction;
import com.nonobank.apps.testcase.base.BaseCase;

public class TiexinByNewBankCardSuccessful extends BaseCase {

	public static Logger logger = LogManager.getLogger(TiexinByNewBankCardSuccessful.class);
	LoginAction loginAction = new LoginAction();
	PaymentAction paymentAction = new PaymentAction();


	@Test(dataProvider = "dataSource")
	public void test(String mobile, String password, String id, String amount, String bank_name, String cardNO,
			String validationCode, String payPassword) {

		boolean loginResult = loginAction.login(mobile, password, "mobile_num");
		Assert.assertEquals(loginResult, true);
		boolean paymentResult = paymentAction.payment(id, amount, payPassword);
		Assert.assertEquals(paymentResult, true);
	}
}
