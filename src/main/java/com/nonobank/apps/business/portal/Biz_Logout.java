package com.nonobank.apps.business.portal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import com.nonobank.apps.page.portal.Page_Logout;

public class Biz_Logout {

	public static Logger logger = LogManager.getLogger(Biz_Logout.class);

	Page_Logout page_Logout = new Page_Logout();

	public void logout() {
		logger.info("退出登录...");
		page_Logout.nagivate_to_logout();

		if (page_Logout.isLoginExist()) {
			logger.info("退出登录成功...");
		} else {
			logger.error("退出登录失败...");
			Assert.fail("logout failed...");
		}
	}
}
