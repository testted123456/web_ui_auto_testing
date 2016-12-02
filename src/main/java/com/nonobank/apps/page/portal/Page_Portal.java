package com.nonobank.apps.page.portal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.nonobank.apps.objectRepository.WebElementType;
import com.nonobank.apps.objectRepository.WebLink;
import com.nonobank.apps.page.base.BasePage;
import com.nonobank.apps.utils.file.ParseProperties;
import com.nonobank.apps.utils.page.PageUtils;

public class Page_Portal extends BasePage {

	public static Logger logger = LogManager.getLogger(Page_Portal.class);

	/**
	 * 跳转到注册页
	 */
	public void navigate_to_register() {
		String url_register = ParseProperties.getInstance().getProperty("url") + "/Register";
		PageUtils.openPage(url_register);
		PageUtils.waitForPageLoad();
		logger.info(PageUtils.getUrl());
	}

	/**
	 * 点击进入我的账户
	 */
	public void click_my_account() {
		WebLink link_myaccount = objectFactory.getWebLink("myaccount");
		if (link_myaccount.isDisplayed()) {
			link_myaccount.click();
		} else {
			String url = ParseProperties.getInstance().getProperty("url");
			url = url + "/Account";
			driver.get(url);
		}
	}

	/**
	 * 关闭首页悬浮框
	 */
	public void close_dialog() {
		if (isElementExists("dialog_close", WebElementType.WebLink, 15)) {
			WebLink link_close_dialog = objectFactory.getWebLink("dialog_close");
			if (link_close_dialog.isDisplayed()) {
				link_close_dialog.click();
			}
		} else {
			logger.info("首页悬浮窗口不存在...");
		}
	}
}
