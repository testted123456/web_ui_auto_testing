package com.nonobank.apps.page.recharge;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import com.nonobank.apps.objectRepository.WebCommon;
import com.nonobank.apps.objectRepository.WebElementType;
import com.nonobank.apps.page.base.BasePage;
import com.nonobank.apps.utils.file.ParseProperties;
import com.nonobank.apps.utils.page.PageUtils;

public class Page_User_Recharge extends BasePage {

	public static Logger logger = LogManager.getLogger(Page_User_Recharge.class);

	/**
	 * 跳转到充值页面
	 */
	public void navigate_to_recharge() {
		String url = ParseProperties.getInstance().getProperty("url") + "/User/Recharge1";
		PageUtils.openPage(url);
		PageUtils.waitForPageLoad();
		logger.info(PageUtils.getUrl());
	}

	// 下一步按钮
	public void nextStep() {
		PageUtils.waitForPageLoad();
		sleep(1000);
		WebCommon input = objectFactory.getWebCommon("paybtn");
		input.click();
	}

	// 判断点击下一步是否成功
	public void isNextStepSuccess() {
		if (!isElementExists("paybtn", WebElementType.WebButton, 15)) {
			logger.info("点击下一步失败...");
			Assert.fail("click next step failed.");
		}
	}
}
