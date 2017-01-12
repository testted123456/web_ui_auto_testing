package com.nonobank.apps.business.repayment;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.nonobank.apps.page.account.Page_Account;
import com.nonobank.apps.page.account.Page_BorrowList;
import com.nonobank.apps.utils.page.PageUtils;

public class Biz_PrePayment {
	public static Logger logger = LogManager.getLogger(Biz_PrePayment.class);
	Page_Account page_Account = new Page_Account();
	Page_BorrowList page_BorrowList = new Page_BorrowList();

	public void prePaymentBus() {
		logger.info("--------------开始：提前还款流程----------------");
		page_Account.click_borrowsRecord();
		PageUtils.sleep(3000);
		// 点击提前还款
		page_BorrowList.click_preRepayment();
		PageUtils.sleep(3000);
		// 提前还款提示框验证
		page_BorrowList.getAlertText();
		page_BorrowList.closeAlert();
		// 检查本次应还金额、账号余额
		PageUtils.sleep(3000);
		page_BorrowList.click_enter();
		PageUtils.sleep(5000);
		page_BorrowList.getAlertText();
		page_BorrowList.closeAlert();
		logger.info("--------------结束：提前还款流程----------------");
	}
}
