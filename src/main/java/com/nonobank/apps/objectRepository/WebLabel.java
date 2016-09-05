package com.nonobank.apps.objectRepository;

import org.openqa.selenium.WebDriver;

public class WebLabel extends BaseWebElement {
	
	public WebLabel(WebDriver driver, String xpath){
		super(driver, xpath);
	}
	
	public String getText(){
		return super.getWebElement().getText();
	}
	
	/**
	 * 函数说明：获取元素class
	 * @return
	 */
	public String getClzz() {
		return super.getWebElement().getAttribute("class");
	}
}
