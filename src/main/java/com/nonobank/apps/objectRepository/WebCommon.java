package com.nonobank.apps.objectRepository;

import org.openqa.selenium.WebDriver;

public class WebCommon extends BaseWebElement {

	public WebCommon(WebDriver driver, String xpath){
		super(driver, xpath);
	}
	
	public String getText(){
		return super.getWebElement().getText();
	}
}
