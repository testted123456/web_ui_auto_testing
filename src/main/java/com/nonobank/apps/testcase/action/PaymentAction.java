package com.nonobank.apps.testcase.action;

import com.nonobank.apps.business.licai.Biz_Licai_FinancePlan;
import com.nonobank.apps.business.licai.Biz_Licai_Order;
import com.nonobank.apps.business.licai.Biz_Licai_Payment;
import com.nonobank.apps.business.licai.Biz_Licai_Payment_Successful;

public class PaymentAction {
	Biz_Licai_FinancePlan biz_Licai_FinancePlan = new Biz_Licai_FinancePlan();
	Biz_Licai_Order biz_Licai_Order = new Biz_Licai_Order();
	Biz_Licai_Payment biz_Licai_Payment = new Biz_Licai_Payment();
	Biz_Licai_Payment_Successful biz_Licai_Payment_Successful = new Biz_Licai_Payment_Successful();

	public boolean payment(String id, String amount, String payPassword) {
		biz_Licai_FinancePlan.purchase(id, amount, "/Debt/View/");
		biz_Licai_Order.submit();
		biz_Licai_Payment.payByBalance(payPassword);
		boolean paymentResult = biz_Licai_Payment_Successful.paymentSuccessful();
		return paymentResult;
	}
}
