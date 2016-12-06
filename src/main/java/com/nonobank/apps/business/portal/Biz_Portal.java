package com.nonobank.apps.business.portal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.nonobank.apps.page.portal.Page_Portal;
import com.nonobank.apps.utils.page.PageUtils;
import com.nonobank.apps.utils.webintegration.Info;

@Info(name = "Biz_Portal", desc = "账户页面", dependency = "com.nonobank.apps.business.portal.Biz_Login", isDisabled = false)
public class Biz_Portal {

	public static Logger logger = LogManager.getLogger(Biz_Portal.class);

	Page_Portal page_Portal = new Page_Portal();

	/**
	 * 跳转到我的账户页面
	 */
	@Info(name = "navigate_to_myaccount", desc = "跳转到我的账户页面", dependency = "", isDisabled = false)
	public void navigate_to_myaccount() {
		logger.info("跳转到我的账户页面...");
		page_Portal.click_my_account();
		PageUtils.waitForPageLoad();
	}

	/**
	 * 跳转到我的账户页面
	 */
	@Info(name = "navigate_to_login", desc = "跳转到登录页面", dependency = "", isDisabled = false)
	public void navigate_to_login() {
		logger.info("点击登录...");
		page_Portal.click_login();
		PageUtils.waitForPageLoad();
	}

	/**
	 * 跳转到我的账户页面
	 */
	@Info(name = "navigate_to_register", desc = "跳转到注册页面", dependency = "", isDisabled = false)
	public void navigate_to_register() {
		logger.info("点击注册...");
		page_Portal.click_register();
		PageUtils.waitForPageLoad();
	}

	/**
	 * 关闭悬浮窗口
	 */
	@Info(name = "close_dialog", desc = "关闭悬浮窗口", dependency = "navigate_to_myaccount()", isDisabled = false)
	public void close_dialog() {
		logger.info("关闭首页悬浮窗口...");
		page_Portal.close_dialog();
	}
}
