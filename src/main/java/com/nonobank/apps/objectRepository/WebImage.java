package com.nonobank.apps.objectRepository;

import org.openqa.selenium.WebDriver;

public class WebImage extends BaseWebElement {
	
	public WebImage(WebDriver driver, String xpath){
		super(driver, xpath);
	}
	
	public String getImgSource(){
        return super.getAttribute("src");
    }
}
