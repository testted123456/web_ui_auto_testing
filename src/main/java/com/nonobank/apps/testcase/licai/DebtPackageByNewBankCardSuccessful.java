package com.nonobank.apps.testcase.licai;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import com.nonobank.apps.business.account.Biz_Account;
import com.nonobank.apps.business.licai.Biz_Licai_FinancePlan;
import com.nonobank.apps.business.licai.Biz_Licai_Order;
import com.nonobank.apps.business.licai.Biz_Licai_Payment;
import com.nonobank.apps.business.licai.Biz_Licai_Payment_Successful;
import com.nonobank.apps.business.portal.Biz_Portal;
import com.nonobank.apps.business.portal.Biz_Register;
import com.nonobank.apps.testcase.base.BaseCase;

public class DebtPackageByNewBankCardSuccessful extends BaseCase {

	public static Logger logger = LogManager.getLogger(DebtPackageByNewBankCardSuccessful.class);
	Biz_Register biz_Register;
	Biz_Portal biz_Portal;
	Biz_Account biz_Account;

	Biz_Licai_FinancePlan biz_Licai_FinancePlan;
	Biz_Licai_Order biz_Licai_Order;
	Biz_Licai_Payment biz_Licai_Payment;
	Biz_Licai_Payment_Successful biz_Licai_Payment_Successful;

	@Test(dataProvider = "dataSource")
	public void test(String mobile, String user_name, String password, String password2, String checkCode,
			String validation, String myname, String identity_ID, String payPassword, String payPassword2, String id,
			String amount, String bank_name, String cardNo) {
		biz_Register.register(mobile, user_name, password, password2, checkCode, validation, null);
		biz_Portal.close_dialog();
		biz_Portal.navigate_to_myaccount();
		biz_Account.IDVerification(myname, identity_ID);
		biz_Account.setPayPassword(payPassword, payPassword2);

		biz_Licai_FinancePlan.purchase(id, amount, "/Debt/ViewDebtPackage/");
		biz_Licai_Order.submit();
		biz_Licai_Payment.payByNewCard(bank_name, cardNo, payPassword);
	}

	// @Test(dataProvider = "dataSource")
	// public void test(String mobile, String password, String id, String
	// amount, String bank_name, String cardNo,
	// String validationCode, String payPassword) {
	// biz_Register.register(mobile, user_name, password, password2, check_code,
	// sms_code);
	// biz_Portal.close_dialog();
	// biz_Portal.navigate_to_myaccount();
	// biz_Account.IDVerification(myname, identity_ID);
	// biz_Account.setPayPassword(payPassword, payPassword1);
	//
	// biz_Licai_FinancePlan.purchase(id, amount, "/Debt/ViewDebtPackage/");
	// biz_Licai_Order.submit();
	// biz_Licai_Payment.payByNewCard(bank_name, cardNo, validationCode,
	// payPassword);
	// }
}
