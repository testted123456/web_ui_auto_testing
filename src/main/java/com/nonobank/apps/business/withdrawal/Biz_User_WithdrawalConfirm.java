package com.nonobank.apps.business.withdrawal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.nonobank.apps.page.withdrawal.Page_User_WithdrawalConfirm;

public class Biz_User_WithdrawalConfirm {

	public static Logger logger = LogManager.getLogger(Biz_User_WithdrawalConfirm.class);
	Page_User_WithdrawalConfirm page_User_WithdrawalConfirm = new Page_User_WithdrawalConfirm();

	/**
	 * 提现
	 * 
	 * @param pay_password
	 */
	public void confirm(String pay_password) {
		logger.info("提现校验...");
		page_User_WithdrawalConfirm.input_pay_password(pay_password);
		page_User_WithdrawalConfirm.submit();
	}

	/**
	 * 判断提现是否成功
	 */
	public void isWithDrawalConfirmSuccess() {
		logger.info("判断提现是否成功...");
		page_User_WithdrawalConfirm.isWithDrawalConfirmSuccess();
	}
}
