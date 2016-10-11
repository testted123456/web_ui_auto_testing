package com.nonobank.apps.business.repayment;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import com.nonobank.apps.business.student.Biz_Apply;
import com.nonobank.apps.page.account.Page_Account;
import com.nonobank.apps.page.account.Page_Bills;
import com.nonobank.apps.page.account.Page_BorrowList;
import com.nonobank.apps.utils.page.PageUtils;

public class Biz_PrePayment {
	public static Logger logger = LogManager.getLogger(Biz_PrePayment.class);
	Page_Account page_Account=new Page_Account();
	Page_BorrowList page_BorrowList=new Page_BorrowList();
	
	public void prePaymentBus(String borrowsMoney_apply){
		logger.info("--------------开始：提前还款流程----------------");
		page_Account.click_borrowsRecord();
		PageUtils.sleep(3000);
		//检查借款金额、年费率、借款状态
		String borrowsMoney=page_BorrowList.getText_borrowsMoney();
		String yearRate=page_BorrowList.getText_yearRate();
		String borrowsState=page_BorrowList.getText_borrowsState();
		Assert.assertEquals(borrowsMoney, borrowsMoney_apply);
		Assert.assertEquals(yearRate, "11.88%");
		Assert.assertEquals(borrowsState, "借款成功");
		PageUtils.sleep(3000);
		//点击提前还款
		page_BorrowList.click_preRepayment();
		PageUtils.sleep(3000);
		//提前还款提示框验证
		String prePaymentPrompt=page_BorrowList.getAlertText();
		Assert.assertEquals(prePaymentPrompt, "您确定要提前还款吗？");
		page_BorrowList.closeAlert();
		//检查本次应还金额、账号余额
		String currentBalance=page_BorrowList.getText_currentBalance();
		String balance=page_BorrowList.getText_balance();
		float float_currentBalance=Float.parseFloat(currentBalance);
		float float_balance=Float.parseFloat(balance);
		if(float_balance-float_currentBalance>0){
			page_BorrowList.click_enter();
		}
		PageUtils.sleep(5000);
		String borrowSuccessPrompt=page_BorrowList.getAlertText();
		Assert.assertEquals(borrowSuccessPrompt, "还款成功");
		page_BorrowList.closeAlert();
		logger.info("--------------结束：提前还款流程----------------");
	}
}
//=======
//package com.nonobank.apps.business.repayment;
//
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.testng.Assert;
//
//import com.nonobank.apps.business.student.Biz_Apply;
//import com.nonobank.apps.page.account.Page_Account;
//import com.nonobank.apps.page.account.Page_Bills;
//import com.nonobank.apps.page.account.Page_BorrowList;
//import com.nonobank.apps.utils.page.PageUtils;
//
//public class Biz_PrePayment {
//	public static Logger logger = LogManager.getLogger(Biz_Apply.class);
//	Page_Account page_Account=new Page_Account();
//	Page_BorrowList page_BorrowList=new Page_BorrowList();
//	
//	public void prePaymentBus(String borrowsMoney_apply){
//		logger.info("--------------开始：提前还款流程----------------");
//		page_Account.click_borrowsRecord();
//		PageUtils.sleep(3000);
//		//检查借款金额、年费率、借款状态
//		String borrowsMoney=page_BorrowList.getText_borrowsMoney();
//		String yearRate=page_BorrowList.getText_yearRate();
//		String borrowsState=page_BorrowList.getText_borrowsState();
//		Assert.assertEquals(borrowsMoney, borrowsMoney_apply);
//		Assert.assertEquals(yearRate, "11.88%");
//		Assert.assertEquals(borrowsState, "借款成功");
//		PageUtils.sleep(3000);
//		//点击提前还款
//		page_BorrowList.click_preRepayment();
//		PageUtils.sleep(3000);
//		//提前还款提示框验证
//		String prePaymentPrompt=page_BorrowList.getAlertText();
//		Assert.assertEquals(prePaymentPrompt, "您确定要提前还款吗？");
//		page_BorrowList.closeAlert();
//		//检查本次应还金额、账号余额
//		String currentBalance=page_BorrowList.getText_currentBalance();
//		String balance=page_BorrowList.getText_balance();
//		float float_currentBalance=Float.parseFloat(currentBalance);
//		float float_balance=Float.parseFloat(balance);
//		if(float_balance-float_currentBalance>0){
//			page_BorrowList.click_enter();
//		}
//		PageUtils.sleep(5000);
//		String borrowSuccessPrompt=page_BorrowList.getAlertText();
//		Assert.assertEquals(borrowSuccessPrompt, "还款成功");
//		page_BorrowList.closeAlert();
//		logger.info("--------------结束：提前还款流程----------------");
//	}
//}

