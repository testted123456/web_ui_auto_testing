package com.nonobank.apps.business.recharge;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.nonobank.apps.page.account.Page_Account;
import com.nonobank.apps.page.recharge.Page_User_Recharge;
import com.nonobank.apps.utils.data.IdBankGenerator;

public class Biz_User_Recharge {

	public static Logger logger = LogManager.getLogger(Biz_User_Recharge.class);

	Page_User_Recharge page_User_Recharge = new Page_User_Recharge();
	Page_Account page_Account = new Page_Account();

	/**
	 * 根据卡的尾号选择银行卡
	 * 
	 * @param cardno
	 */
	public void recharge(String bankName) {
		logger.info("选择银行卡......");
		String bankCode = IdBankGenerator.map.get(bankName);
		page_User_Recharge.click_otherBank(bankCode, bankName);
		page_User_Recharge.nextStep();
	}
}
