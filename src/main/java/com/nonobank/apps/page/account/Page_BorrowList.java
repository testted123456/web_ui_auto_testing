package com.nonobank.apps.page.account;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.nonobank.apps.page.base.BasePage;
import com.nonobank.apps.objectRepository.WebLink;
import com.nonobank.apps.objectRepository.WebSpan;
import com.nonobank.apps.objectRepository.WebTable;

public class Page_BorrowList extends BasePage{
	public static Logger logger = LogManager.getLogger(Page_BorrowList.class);
	
	public String getText_borrowsMoney(){
		logger.info("获取借款金额...");
		WebTable getText_borrowsMoney=objectFactory.getWebTable("借款金额");
		String borrowsMoney=getText_borrowsMoney.getText();
		return borrowsMoney;
	}
	public String getText_yearRate(){
		logger.info("获取年费率...");
		WebTable getText_yearRate=objectFactory.getWebTable("年费率");
		String yearRate=getText_yearRate.getText();
		return yearRate;
	}
	public String getText_borrowsState(){
		logger.info("获取借款状态...");
		WebTable getText_borrowsState=objectFactory.getWebTable("借款状态");
		String borrowsState=getText_borrowsState.getText();
		return borrowsState;
	}
	public void click_preRepayment(){
		logger.info("点击提前还款...");
		WebLink click_preRepayment=objectFactory.getWebLink("提前还款");
		click_preRepayment.click();
	}
	public void click_repaymentDetails(){
		logger.info("点击还款详情...");
		WebLink click_repaymentDetails=objectFactory.getWebLink("还款详情");
		click_repaymentDetails.click();
	}
	public void click_borrowProtocol(){
		logger.info("点击借款协议...");
		WebLink click_borrowProtocol=objectFactory.getWebLink("查看借款协议");
		click_borrowProtocol.click();
	}
	public void click_consultingProtocol(){
		logger.info("点击咨询协议...");
		WebLink click_consultingProtocol=objectFactory.getWebLink("查看咨询及管理服务协议");
		click_consultingProtocol.click();
	}
	public String getText_currentBalance(){
		logger.info("获取本次应还金额...");
		WebSpan getText_currentBalance=objectFactory.getWebSpan("本次应还金额");
		String currentBalance=getText_currentBalance.getText();
		return currentBalance;
	}
	public String getText_balance(){
		logger.info("获取你的账号余额...");
		WebSpan getText_balance=objectFactory.getWebSpan("您的账户余额");
		String balance=getText_balance.getText();
		return balance;
	}
	public void click_enter(){
		logger.info("点击确定...");
		WebSpan click_enter=objectFactory.getWebSpan("确定");
		click_enter.click();
	}
	
}
