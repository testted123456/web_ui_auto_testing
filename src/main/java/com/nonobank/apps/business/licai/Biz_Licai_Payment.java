package com.nonobank.apps.business.licai;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.nonobank.apps.page.licai.Page_Licai_Payment;
import com.nonobank.apps.utils.data.BankCardUtils;

public class Biz_Licai_Payment {

	public static Logger logger = LogManager.getLogger(Biz_Licai_Payment.class);

	Page_Licai_Payment page_Licai_Payment = new Page_Licai_Payment();

	// 余额支付
	public void payByBalance(String payPassword) {
		logger.info("余额支付...");
		page_Licai_Payment.click_nextStep();
		page_Licai_Payment.input_payPassword(payPassword);
		page_Licai_Payment.submit();
	}

	// 老银行卡支付
	public void payByOldNewCard(String cardNo, String payPassword, String smsCode) {
		logger.info("老银行卡支付...");
		page_Licai_Payment.unCheckBanlance();
		page_Licai_Payment.click_otherCard();
		page_Licai_Payment.select_OldCard(cardNo);
		page_Licai_Payment.click_nextStep();
		page_Licai_Payment.input_paypasswordForOldBankCard(payPassword);
		page_Licai_Payment.submit();
		page_Licai_Payment.input_smsCodeForOldBankCard(smsCode);
		page_Licai_Payment.submit_smsCodeForOldBankCard();
	}

	// 新银行卡支付
	public void payByNewCard(String bank_name, String cardNO, String validationCode, String payPassword) {
		logger.info("新银行卡支付...");
		page_Licai_Payment.unCheckBanlance();

		boolean flag = page_Licai_Payment.is_NewBankCard_exist();

		if (flag) {
			page_Licai_Payment.click_newBankCard();
			page_Licai_Payment.select_newBank(bank_name, flag);
			page_Licai_Payment.click_nextStepForNewCard();
		} else {
			page_Licai_Payment.select_newBank(bank_name, flag);
			page_Licai_Payment.click_nextStep();
		}

		if (cardNO.trim().toLowerCase().equals("random")) {
			if (bank_name.equals("建设银行")) {
				cardNO = BankCardUtils.getBankCard("436742121737");
			} else if (bank_name.equals("工商银行")) {
				cardNO = BankCardUtils.getBankCard("622202100112");
			}
		}

		page_Licai_Payment.input_newBankCard(cardNO);
		page_Licai_Payment.click_validationCodeForNewCard();
		page_Licai_Payment.input_validationCodeForNewCard(validationCode);
		page_Licai_Payment.input_payPasswordForNewCard(payPassword);
		page_Licai_Payment.submitForNewCard();
		page_Licai_Payment.closeAlert();
	}
}
