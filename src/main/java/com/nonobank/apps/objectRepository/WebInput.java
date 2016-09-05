package com.nonobank.apps.objectRepository;

import org.openqa.selenium.WebDriver;

public class WebInput extends BaseWebElement {
	
	public WebInput(WebDriver driver, String xpath){
		super(driver, xpath);
	}
	
	public void input(String text) {
		super.getWebElement().sendKeys(text);
	}


	
	public void clear(){
		super.getWebElement().clear();
	}
	
	public void clearAndInput(String text) {
		clear();
		input(text);
    }
	
    /**
     * 函数说明：获取元素属性value值
     * @return
     */
    public String getValue() {
        return super.getWebElement().getAttribute("value");
    }
    
	/**
	 * 函数说明：获取元素class
	 * @return
	 */
	public String getClzz() {
		return super.getWebElement().getAttribute("class");
	}
}
