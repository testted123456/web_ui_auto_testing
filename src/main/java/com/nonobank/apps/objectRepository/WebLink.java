package com.nonobank.apps.objectRepository;

import org.openqa.selenium.WebDriver;

public class WebLink extends BaseWebElement {
	
	public WebLink(WebDriver driver, String xpath){
		super(driver, xpath);
	}
	
	public boolean isValid() {
        return (null!=super.getAttribute("href")||super.getAttribute("href").length()>0)?true:false;
    }

    public String getUrl(){
        return super.getAttribute("href");
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
