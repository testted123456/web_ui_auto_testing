package com.nonobank.apps.objectRepository;

import org.openqa.selenium.WebDriver;

public class WebRadioBox extends BaseWebElement {
	
	public WebRadioBox(WebDriver driver, String xpath){
		super(driver, xpath);
	}
	
	public void check() {
		logger.info("选中radiobox :" + this.getXpath());
        if (!isSelected()) {
            click();
        }
    }

    public boolean isSelected() {
        return super.getWebElement().isSelected();
    }

    public void uncheck() {
    	logger.info("选中radionbox :" + this.getXpath());
        if (isSelected()) {
        	click();
        }
    }
}
