package com.nonobank.apps.objectRepository;

import org.openqa.selenium.WebDriver;

public class WebRadioBox extends BaseWebElement {
	
	public WebRadioBox(WebDriver driver, String xpath){
		super(driver, xpath);
	}
	
	public void check() {
        if (!isSelected()) {
            click();
        }
    }

    public boolean isSelected() {
        return super.getWebElement().isSelected();
    }

    public void uncheck() {
        if (isSelected()) {
        	click();
        }
    }
}
