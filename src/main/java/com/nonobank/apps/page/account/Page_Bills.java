package com.nonobank.apps.page.account;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nonobank.apps.objectRepository.WebCommon;
import com.nonobank.apps.objectRepository.WebInput;
import com.nonobank.apps.objectRepository.WebLi;
import com.nonobank.apps.objectRepository.WebLink;
import com.nonobank.apps.objectRepository.WebSpan;
import com.nonobank.apps.page.base.BasePage;

public class Page_Bills extends BasePage{
	public static Logger logger = LogManager.getLogger(Page_Account.class);
	//点击已出账单
	public void click_OutBill(){
		logger.info("点击已出账单");
		WebLi click_OutBill=objectFactory.getWebLi("已出账单");
		click_OutBill.click();
	}
	//点击未出账单
	public void click_noOutBill(){
		logger.info("点击未出账单");
		WebLi click_noOutBill=objectFactory.getWebLi("未出账单");
		click_noOutBill.click();
	}
	//点击提前还款
	public void click_prePayment(){
		logger.info("点击提前还款");
		WebLink click_prePayment=objectFactory.getWebLink("提前还款");
		click_prePayment.click();
	}
	//立即还款-期数1
	public void click_immediatelyPayment1(){
		logger.info("立即还款-期数1");
		WebInput click_immediatelyPayment1=objectFactory.getWebInput("立即还款-期数1");
		click_immediatelyPayment1.click();
	}
	//立即还款-期数2
	public void click_immediatelyPayment2(){
		logger.info("立即还款-期数2");
		WebInput click_immediatelyPayment2=objectFactory.getWebInput("立即还款-期数2");
		click_immediatelyPayment2.click();
	}
	//立即还款-期数3
	public void click_immediatelyPayment3(){
		logger.info("立即还款-期数3");
		WebInput click_immediatelyPayment3=objectFactory.getWebInput("立即还款-期数3");
		click_immediatelyPayment3.click();
	}
	//立即还款-期数4
	public void click_immediatelyPayment4(){
		logger.info("立即还款-期数4");
		WebInput click_immediatelyPayment4=objectFactory.getWebInput("立即还款-期数4");
		click_immediatelyPayment4.click();
	}
	//立即还款-期数5
	public void click_immediatelyPayment5(){
		logger.info("立即还款-期数5");
		WebInput click_immediatelyPayment5=objectFactory.getWebInput("立即还款-期数5");
		click_immediatelyPayment5.click();
	}
	//立即还款-期数6
	public void click_immediatelyPayment6(){
		logger.info("立即还款-期数6");
		WebInput click_immediatelyPayment6=objectFactory.getWebInput("立即还款-期数6");
		click_immediatelyPayment6.click();
	}
	//立即还款-期数7
	public void click_immediatelyPayment7(){
		logger.info("立即还款-期数7");
		WebInput click_immediatelyPayment7=objectFactory.getWebInput("立即还款-期数7");
		click_immediatelyPayment7.click();
	}
	//立即还款-期数8
	public void click_immediatelyPayment8(){
		logger.info("立即还款-期数8");
		WebInput click_immediatelyPayment8=objectFactory.getWebInput("立即还款-期数8");
		click_immediatelyPayment8.click();
	}
	//立即还款-期数9
	public void click_immediatelyPayment9(){
		logger.info("立即还款-期数9");
		WebInput click_immediatelyPayment9=objectFactory.getWebInput("立即还款-期数9");
		click_immediatelyPayment9.click();
	}
	//立即还款-期数10
	public void click_immediatelyPayment10(){
		logger.info("立即还款-期数10");
		WebInput click_immediatelyPayment10=objectFactory.getWebInput("立即还款-期数10");
		click_immediatelyPayment10.click();
	}
	//获取本次还款金额
	public String getText_theRepayMoney(){
		logger.info("获取本次还款金额");
		WebSpan getText_theRepayMoney=objectFactory.getWebSpan("本次应还金额");
		String theRepayMoney=getText_theRepayMoney.getText();
		return theRepayMoney;
	}
	//获取账户余额
	public String getText_accountBalance(){
		logger.info("获取账户余额");
		WebSpan getText_accountBalance=objectFactory.getWebSpan("您的账户余额");
		String accountBalance=getText_accountBalance.getText();
		return accountBalance;
	}
	//还款详情-确定
	public void click_repayEnter(){
		logger.info("还款详情-确定");
		WebCommon click_repayEnter=objectFactory.getWebCommon("");
		click_repayEnter.click();	
	}
	
	
}
