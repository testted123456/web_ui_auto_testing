package com.nonobank.apps.business.portal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.nonobank.apps.objectRepository.WebElementType;
import com.nonobank.apps.page.portal.Page_Logout;
import com.nonobank.apps.utils.data.Assertion;

public class Biz_Logout {

	public static Logger logger = LogManager.getLogger(Biz_Logout.class);

	Page_Logout page_Logout = new Page_Logout();

	/**
	 * 退出登录
	 */
	public void logout() {
		logger.info("退出登录...");
		page_Logout.nagivate_to_logout();
		boolean flag = page_Logout.isElementDisplayed("login_btn2", WebElementType.WebLink, 15);
		Assertion.assertEquals(true, flag, Biz_Login.class, "注销成功脚本");
	}
}