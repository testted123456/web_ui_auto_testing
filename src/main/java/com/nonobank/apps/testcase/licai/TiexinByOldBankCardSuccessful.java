package com.nonobank.apps.testcase.licai;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.nonobank.apps.business.licai.Biz_Licai_FinancePlan;
import com.nonobank.apps.business.licai.Biz_Licai_Order;
import com.nonobank.apps.business.licai.Biz_Licai_Payment;
import com.nonobank.apps.business.licai.Biz_Licai_Payment_Successful;
import com.nonobank.apps.business.portal.Biz_Login;
import com.nonobank.apps.business.portal.Biz_Portal;
import com.nonobank.apps.business.recharge.Biz_User_Recharge;
import com.nonobank.apps.business.recharge.Biz_User_RechargeConfirm;
import com.nonobank.apps.testcase.base.BaseCase;
import com.nonobank.apps.utils.data.BankCardUtils;

public class TiexinByOldBankCardSuccessful extends BaseCase {

	public static Logger logger = LogManager.getLogger(TiexinByOldBankCardSuccessful.class);
	Biz_Login biz_Login = new Biz_Login();

	Biz_Portal biz_Portal;
	Biz_User_Recharge biz_User_Recharge1;
	Biz_User_RechargeConfirm biz_User_RechargeConfirm;
	Biz_Licai_FinancePlan biz_Licai_FinancePlan;
	Biz_Licai_Order biz_Licai_Order;
	Biz_Licai_Payment biz_Licai_Payment;
	Biz_Licai_Payment_Successful biz_Licai_Payment_Successful;

	@Test(dataProvider = "dataSource")
	public void test(String mobile, String password, String id, String amount, String cardNO, String payPassword,
			String smsCode) {

		biz_Login.login(mobile, password, "mobile_num");
		boolean loginResult = biz_Login.is_login_success();
		Assert.assertEquals(loginResult, true);
		biz_Licai_FinancePlan.purchase(id, amount, "/Licai/FinancePlan/");
		biz_Licai_Order.submit();

		if (cardNO.equals("random")) {
			cardNO = BankCardUtils.getBankCardByMobile(mobile);
		}

		biz_Licai_Payment.payByOldNewCard(cardNO, payPassword, smsCode);
		biz_Licai_Payment_Successful.paymentSuccessful();
	}
}
