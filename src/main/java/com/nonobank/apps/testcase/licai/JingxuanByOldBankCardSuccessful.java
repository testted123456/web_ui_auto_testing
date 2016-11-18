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
import com.nonobank.apps.utils.data.LoginResult;

public class JingxuanByOldBankCardSuccessful extends BaseCase {

	public static Logger logger = LogManager.getLogger(JingxuanByOldBankCardSuccessful.class);
	Biz_Login biz_Login;
	Biz_Licai_FinancePlan biz_Licai_FinancePlan;
	Biz_Licai_Order biz_Licai_Order;
	Biz_Licai_Payment biz_Licai_Payment;
	Biz_Licai_Payment_Successful biz_Licai_Payment_Successful;

	@Test(dataProvider = "dataSource")
	public void test(String mobile, String password, String id, String amount, String cardNo, String payPassword) {

		biz_Login.login(mobile, password, LoginResult.SUCESS);
		biz_Licai_FinancePlan.purchase(id, amount, "/Licai/FinancePlan/");
		biz_Licai_Order.submit();
		biz_Licai_Payment.payByOldNewCard(cardNo, payPassword);
	}
}
