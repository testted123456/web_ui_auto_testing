package com.nonobank.apps.business.student;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nonobank.apps.page.student.Page_CreditBookSearch;
import com.nonobank.apps.utils.page.PageUtils;

import junit.framework.Assert;

public class Biz_CreditBook {
	public static Logger logger = LogManager.getLogger(Biz_Improve.class);
	Page_CreditBookSearch page_CreditBookSearch=new Page_CreditBookSearch();
	
	public void creditBookVerifyBus(String creditCode_CreditBook){
		logger.info("------------开始：信用证书查询-----------------");
		String universityCredit=page_CreditBookSearch.getText_universityCredit();
		Assert.assertEquals(universityCredit, "大学生信用等级证书");
		page_CreditBookSearch.input_creditCode(creditCode_CreditBook);
		PageUtils.sleep(3000);
		page_CreditBookSearch.click_creditQuery();
		logger.info("------------结束：信用证书查询-----------------");
	}
}
