package com.nonobank.apps.objectRepository;

import org.openqa.selenium.WebDriver;

public class WebSpan extends BaseWebElement {

	public WebSpan(WebDriver driver, String xpath){
		super(driver, xpath);
	}
	
	public String getText(){
		return super.getWebElement().getText();
	}
}
