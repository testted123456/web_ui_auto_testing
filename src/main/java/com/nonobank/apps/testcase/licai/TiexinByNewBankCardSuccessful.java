package com.nonobank.apps.testcase.licai;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import com.nonobank.apps.business.licai.Biz_Licai_FinancePlan;
import com.nonobank.apps.business.licai.Biz_Licai_Order;
import com.nonobank.apps.business.licai.Biz_Licai_Payment;
import com.nonobank.apps.business.licai.Biz_Licai_Payment_Successful;
import com.nonobank.apps.business.portal.Biz_Login;
import com.nonobank.apps.testcase.base.BaseCase;

public class TiexinByNewBankCardSuccessful extends BaseCase {

	public static Logger logger = LogManager.getLogger(TiexinByNewBankCardSuccessful.class);
	Biz_Login biz_Login = new Biz_Login();
	Biz_Licai_FinancePlan biz_Licai_FinancePlan = new Biz_Licai_FinancePlan();
	Biz_Licai_Order biz_Licai_Order = new Biz_Licai_Order();
	Biz_Licai_Payment biz_Licai_Payment = new Biz_Licai_Payment();
	Biz_Licai_Payment_Successful biz_Licai_Payment_Successful = new Biz_Licai_Payment_Successful();

	@Test(dataProvider = "dataSource")
	public void test(String mobile, String password, String id, String amount, String bank_name, String cardNO,
			String validationCode, String payPassword) {

		biz_Login.login(mobile, password, "mobile_num");
		biz_Licai_FinancePlan.purchase(id, amount, "/Licai/FinancePlan/");
		biz_Licai_Order.submit();
		biz_Licai_Payment.payByBalance(payPassword);

	}
}
