package com.nonobank.apps.page.account;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.nonobank.apps.objectRepository.WebCommon;
import com.nonobank.apps.objectRepository.WebLink;
import com.nonobank.apps.page.base.BasePage;
import com.nonobank.apps.utils.page.PageUtils;

public class Page_Account extends BasePage {

	public static Logger logger = LogManager.getLogger(Page_Account.class);

	// 点击借款记录
	public void click_borrowsRecord() {
		logger.info("点击借款记录.....");
		WebCommon common_borrowsRecord = objectFactory.getWebCommon("借款记录");
		common_borrowsRecord.click();
	}

	// 点击我的账单
	public void click_myBill() {
		logger.info("点击我的账单.....");
		WebCommon common_myBill = objectFactory.getWebCommon("我的账单");
		common_myBill.click();
	}

	// 点击借款资料
	public void click_logout() {
		logger.info("点击安全退出.....");
		WebLink link_borrowsData = objectFactory.getWebLink("logout");
		link_borrowsData.click();
		PageUtils.waitForPageLoad();
	}

	public void click_withdrawal() {
	}
}
