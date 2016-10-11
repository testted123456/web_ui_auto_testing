package com.nonobank.apps.page.account;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nonobank.apps.objectRepository.WebCommon;
import com.nonobank.apps.objectRepository.WebElementType;
import com.nonobank.apps.objectRepository.WebInput;
import com.nonobank.apps.objectRepository.WebLi;
import com.nonobank.apps.objectRepository.WebLink;
import com.nonobank.apps.objectRepository.WebSpan;
import com.nonobank.apps.page.base.BasePage;

public class Page_Bills extends BasePage {
	public static Logger logger = LogManager.getLogger(Page_Bills.class);

	// 点击已出账单
	public void click_OutBill() {
		logger.info("点击已出账单");
		WebLi click_OutBill = objectFactory.getWebLi("已出账单");
		click_OutBill.click();
	}

	// 点击未出账单
	public void click_noOutBill() {
		logger.info("点击未出账单");
		WebLi click_noOutBill = objectFactory.getWebLi("未出账单");
		click_noOutBill.click();
	}

	// 点击提前还款
	public void click_prePayment() {
		logger.info("点击提前还款");
		WebLink click_prePayment = objectFactory.getWebLink("提前还款");
		click_prePayment.click();
	}

	// 立即还款-当前需还款的期数
	public void click_immediatelyCurrentPayment() {
		logger.info("立即还款-当前需还款的期数");
		WebInput click_immediatelyCurrentPayment = objectFactory.getWebInput("立即还款-当前需还款的期数");
		click_immediatelyCurrentPayment.click();
	}

	// 检查"立即还款-当前需还款的期数"按钮是否存在
	public Boolean isExist_immediatelyCurrentPayment() {
		logger.info("检查立'即还款-当前需还款的期数'按钮是否存在...");
		if (isElementExists("立即还款-当前需还款的期数", WebElementType.WebInput, 10)) {
			return true;
		} else {
			return false;
		}
	}

	// 获取本次还款金额
	public String getText_theRepayMoney() {
		logger.info("获取本次还款金额");
		WebSpan getText_theRepayMoney = objectFactory.getWebSpan("本次应还金额");
		String theRepayMoney = getText_theRepayMoney.getText();
		return theRepayMoney;
	}

	// 获取账户余额
	public String getText_accountBalance() {
		logger.info("获取账户余额");
		WebSpan getText_accountBalance = objectFactory.getWebSpan("您的账户余额");
		String accountBalance = getText_accountBalance.getText();
		return accountBalance;
	}

	// 还款详情-确定
	public void click_repayEnter() {
		logger.info("还款详情-确定");
		WebCommon click_repayEnter = objectFactory.getWebCommon("");
		click_repayEnter.click();
	}

}
