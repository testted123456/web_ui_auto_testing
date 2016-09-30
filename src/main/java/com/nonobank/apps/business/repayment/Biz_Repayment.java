package com.nonobank.apps.business.repayment;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nonobank.apps.business.student.Biz_Apply;
import com.nonobank.apps.page.account.Page_Account;
import com.nonobank.apps.page.account.Page_Bills;
import com.nonobank.apps.utils.page.PageUtils;

public class Biz_Repayment {
	public static Logger logger = LogManager.getLogger(Biz_Apply.class);
	Page_Account page_Account=new Page_Account();
	Page_Bills page_Bills=new Page_Bills();
	
	public void repaymentBus(){
		logger.info("--------------开始：还款流程----------------");
		page_Account.click_myBill();
		PageUtils.sleep(3000);
		page_Bills.click_noOutBill();
		PageUtils.sleep(3000);
		page_Bills.click_immediatelyPayment1();
		PageUtils.sleep(3000);
		String theRepayMoney=page_Bills.getText_theRepayMoney();
		String accountBalance=page_Bills.getText_accountBalance();
		float float_theRepayMoney=Float.parseFloat(theRepayMoney);
		float float_accountBalance=Float.parseFloat(accountBalance);
		if(float_accountBalance-float_theRepayMoney>=0){
			page_Bills.click_repayEnter();
		}
		logger.info("--------------结束：还款流程----------------");
		
	}
	
	
	
	
}
