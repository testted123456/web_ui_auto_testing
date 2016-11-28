package com.nonobank.apps.business.portal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.nonobank.apps.page.portal.Page_Portal;
import com.nonobank.apps.utils.webintegration.Info;

@Info(desc="账户页面",dependency="com.nonobank.apps.business.portal.Biz_Login",isDisabled=false)
public class Biz_Portal {

	public static Logger logger = LogManager.getLogger(Biz_Portal.class);

	Page_Portal page_Portal= new Page_Portal();


	/**
	 * 跳转到我的账户页面
	 */
	@Info(desc="跳转到我的账户页面",dependency="",isDisabled=false)
	public void navigate_to_myaccount() {
		logger.info("跳转到我的账户页面...");
		page_Portal.click_my_account();
	}

	/**
	 * 关闭悬浮窗口
	 */
	@Info(desc="关闭悬浮窗口",dependency="navigate_to_myaccount()",isDisabled=false)
	public void close_dialog() {
		logger.info("关闭首页悬浮窗口...");
		page_Portal.close_dialog();
	}
}
