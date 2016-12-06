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
		logger.info("退出登录...");
		page_Account.click_logout();
		PageUtils.waitForPageLoad();
	}
}
