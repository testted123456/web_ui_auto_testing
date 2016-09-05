package com.nonobank.apps.business.bank;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.nonobank.apps.page.bank.Page_User_BindCard1;

public class Biz_User_BindCard1 {
	public static Logger logger = LogManager.getLogger(Biz_User_BindCard1.class);

	Page_User_BindCard1 page_User_BindCard1 = new Page_User_BindCard1();

	public void select_bank(String bank_name) {
		logger.info("选择银行...");
		page_User_BindCard1.select_bank(bank_name);
		page_User_BindCard1.click_next_step();
	}
}
