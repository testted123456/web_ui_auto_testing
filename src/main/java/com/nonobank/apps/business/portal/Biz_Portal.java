package com.nonobank.apps.business.portal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.nonobank.apps.page.portal.Page_Portal;

public class Biz_Portal {

	public static Logger logger = LogManager.getLogger(Biz_Portal.class);

	Page_Portal page_Portal= new Page_Portal();


	public void navigate_to_myaccount() {
		logger.info("跳转到我的账户页面...");
		page_Portal.click_my_account();
	}

	public void close_dialog() {
		logger.info("关闭首页悬浮窗口...");
		page_Portal.close_dialog();
	}
}
