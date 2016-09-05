package com.nonobank.apps.objectRepository;

import org.openqa.selenium.WebDriver;

public class WebFont extends BaseWebElement {

	public WebFont(WebDriver driver, String xpath){
		super(driver, xpath);
	}
	
	public String getText(){
		return super.getWebElement().getText();
	}
}
