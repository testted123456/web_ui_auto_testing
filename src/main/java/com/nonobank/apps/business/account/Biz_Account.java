package com.nonobank.apps.business.account;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.nonobank.apps.page.account.Page_Account;
import com.nonobank.apps.utils.page.PageUtils;
import com.nonobank.apps.utils.webintegration.Info;

@Info(name = "Biz_Account", desc = "账户页面", dependency = "com.nonobank.apps.business.admin.Biz_Audit_VideoAuditView", isDisabled = false)
public class Biz_Account {
	public static Logger logger = LogManager.getLogger(Biz_Account.class);
	public Page_Account page_Account = new Page_Account();

	/**
	 * 退出登录
	 */
	public void logout() {
		logger.info("退出登录......");
		page_Account.click_logout();
		PageUtils.waitForPageLoad();
	}

	public void navigate_to_profile() {
		logger.info("点击个人设置......");
		page_Account.click_profile();
		PageUtils.waitForPageLoad();
	}

	public void navigate_to_degreeCard() {
		logger.info("点击认证管理......");
		page_Account.click_degreeCard();
		PageUtils.waitForPageLoad();
	}

	public void navigate_to_banks() {
		logger.info("点击银行账户......");
		page_Account.click_banks();
		PageUtils.waitForPageLoad();
	}

	public void navigate_to_recharge() {
		logger.info("点击充值......");
		page_Account.click_recharge();
		PageUtils.waitForPageLoad();
	}

}
