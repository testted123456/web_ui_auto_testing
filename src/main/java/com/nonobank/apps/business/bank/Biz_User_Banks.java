package com.nonobank.apps.business.bank;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.nonobank.apps.page.bank.Page_User_Banks;

public class Biz_User_Banks {
	public static Logger logger = LogManager.getLogger(Biz_User_Banks.class);

	Page_User_Banks page_User_Banks= new Page_User_Banks();
	

	/**
	 * 添加银行卡
	 */
	public void add_bankcard(){
		logger.info("添加银行卡...");
		page_User_Banks.click_add_bankcard();
	}
	
	public void verifyNameID(String realName, String idNo){
		logger.info("实名认证...");
		page_User_Banks.verifyNameID(realName, idNo);
	}
}
