package com.nonobank.apps.objectRepository;

import org.openqa.selenium.WebDriver;

public class WebLi extends BaseWebElement {

	public WebLi(WebDriver driver, String xpath){
		super(driver, xpath);
	}
	
	public String getText(){
		return super.getWebElement().getText();
	}
}
