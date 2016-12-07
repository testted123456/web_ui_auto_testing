package com.nonobank.apps.business.bank;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.nonobank.apps.page.bank.Page_User_BindCard2;
import com.nonobank.apps.utils.data.BankCardUtils;

public class Biz_User_BindCard2 {

	public static Logger logger = LogManager.getLogger(Biz_User_BindCard2.class);

	Page_User_BindCard2 page_User_BindCard2 = new Page_User_BindCard2();

	/**
	 * 绑定银行卡成功
	 * 
	 * @param bankcard_no
	 */
	public void bindCardSuccess(String bank_name, String bankcard_no) {
		logger.info("输入银行卡号...");
		page_User_BindCard2.input_bankcard_no(bankcard_no);
		logger.info("获取短信验证码...");
		page_User_BindCard2.click_getValidate();
		page_User_BindCard2.input_validateCode();
		logger.info("提交");
		page_User_BindCard2.submit();
		page_User_BindCard2.isBindCardSuccess();
	}
}
