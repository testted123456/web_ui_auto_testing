package com.nonobank.apps.page.licai;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;

import com.nonobank.apps.objectRepository.WebButton;
import com.nonobank.apps.page.base.BasePage;

public class Page_Licai_Order extends BasePage {
	public static Logger logger = LogManager.getLogger(Page_Licai_Order.class);

	// 确认购买
	public void submit() {
		String setscroll = "document.documentElement.scrollTop=" + 400;
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript(setscroll);
		WebButton button = objectFactory.getWebButton("确认购买");
		button.click();

	}
}
