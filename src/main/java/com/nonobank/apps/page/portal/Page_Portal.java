package com.nonobank.apps.page.portal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.nonobank.apps.objectRepository.WebElementType;
import com.nonobank.apps.objectRepository.WebLink;
import com.nonobank.apps.page.base.BasePage;
import com.nonobank.apps.utils.file.ParseProperties;
import com.nonobank.apps.utils.variable.CodisTool;

public class Page_Portal extends BasePage {

	public static Logger logger = LogManager.getLogger(Page_Portal.class);

	/**
	 * 跳转到注册页
	 */
	public void click_login() {
		WebLink link_login = objectFactory.getWebLink("login");
		link_login.click();
	}

	/**
	 * 跳转到注册页
	 */
	public String click_register() {
		WebLink link_register = objectFactory.getWebLink("register");
		link_register.click();
		// WebElement element = null;
		// String url = "https://www.sit.nonobank.com/v6/Uuid";
		// try {
		// // element = driver.findElement(By.tagName("body"));
		// // element.sendKeys(Keys.CONTROL + "t");
		// driver.get(url);
		// } catch (Exception e1) {
		// e1.printStackTrace();
		// }
		// WebElement element2 = driver.findElement(By.tagName("body"));
		// String uuid = element2.getText();
		// System.out.println(uuid);
		// driver.navigate().back();
		//
		// int i = 1;
		// String host = "";
		// int port = 0;
		// if (i == 0) {
		// host = "192.168.3.130";
		// port = 6379;
		// } else {
		// host = "192.168.4.53";
		// port = 19000;
		// }
		// CodisTool.init(host, port);
		// String imgcode = CodisTool.getValue(uuid);
		// return imgcode;
		return null;
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
