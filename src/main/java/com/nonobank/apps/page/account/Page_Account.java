package com.nonobank.apps.page.account;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.nonobank.apps.objectRepository.WebCommon;
import com.nonobank.apps.objectRepository.WebInput;
import com.nonobank.apps.objectRepository.WebLink;
import com.nonobank.apps.page.base.BasePage;
import com.nonobank.apps.utils.file.ParseProperties;
import com.nonobank.apps.utils.page.PageUtils;

public class Page_Account extends BasePage {

	public static Logger logger = LogManager.getLogger(Page_Account.class);

	// 点击借款记录
	public void click_borrowsRecord() {
		WebCommon common_borrowsRecord = objectFactory.getWebCommon("借款记录");
		common_borrowsRecord.click();
	}

	// 点击我的账单
	public void click_myBill() {
		WebCommon common_myBill = objectFactory.getWebCommon("我的账单");
		common_myBill.click();
	}

	// 退出
	public void click_logout() {
		String url = ParseProperties.getInstance().getProperty("url") + "/Login/logout";
		PageUtils.openPage(url);
		PageUtils.waitForPageLoad();
		logger.info(PageUtils.getUrl());
	}

	// 点击个人设置
	public void click_profile() {
		WebLink link_profile = objectFactory.getWebLink("profile");
		link_profile.click();
	}

	// 点击认证管理
	public void click_degreeCard() {
		WebLink link_degreeCard = objectFactory.getWebLink("degreeCard");
		link_degreeCard.click();
	}

	// 点击银行账户
	public void click_banks() {
		WebLink link_banks = objectFactory.getWebLink("banks");
		link_banks.click();
	}

	// 点击充值
	public void click_recharge() {
		WebInput input_recharge = objectFactory.getWebInput("recharge");
		input_recharge.click();
	}

	// 点击提现
	public void click_withDrawal() {
		WebInput input_recharge = objectFactory.getWebInput("withDrawal");
		input_recharge.click();
	}
}
