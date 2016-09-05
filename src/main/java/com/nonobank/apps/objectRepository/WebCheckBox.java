package com.nonobank.apps.objectRepository;

import org.openqa.selenium.WebDriver;

public class WebCheckBox extends BaseWebElement {

	public WebCheckBox(WebDriver driver, String xpath) {
		super(driver, xpath);
	}

	/**
	 * 函数说明：获取元素是否被选中
	 * 
	 * @return
	 */
	boolean isSelected() {
		return super.getWebElement().isSelected();
	}

	public void check() {
		logger.info("选中checkbox :" + this.getXpath());
		if (!isSelected()) {
			click();
		}
	}

	public void uncheck() {
		logger.info("不选中checkbox :" + this.getXpath());
		if (isSelected()) {
			click();
		}
	}

	public void click() {
		super.getWebElement().click();
	}

	public void checkOrUncheck(boolean checkFlag) {
		if (checkFlag) {
			check();
		} else {
			uncheck();
		}
	}
}
