package com.nonobank.apps.objectRepository;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class WebSelect extends BaseWebElement {
	
	private Select select ;
	
    private List<WebElement> options;
    
    public List<WebElement> getOptions(){
    	return options;
    }
	
	public WebSelect(WebDriver driver, String xpath){
		super(driver, xpath);
		
		this.select = new Select(getWebElement());

		options=select.getOptions();
	}
	
    public void deselectAll(){
    	logger.info("deselect all :" + this.getXpath());
    	
        select.deselectAll();
    }

	public void deselectByIndex(int index) {
		logger.info("deselect by index :" + this.getXpath());
		
        select.deselectByIndex(index);
	}

	public void deselectByText(String text) {
		logger.info("deselect by text :" + this.getXpath());
		
	    select.deselectByVisibleText(text);
	}

	public void deselectByValue(String value) {
		logger.info("deselect by value :" + this.getXpath());
		
	    select.deselectByValue(value);
	}

	public String getSelectedText() {
		for (WebElement option : options) {
			if (option.isSelected()) {
				return option.getText();
			}
		}
		return null;
	}

	public List<String> getSelectTexts() {
		List<String> textList = new ArrayList<String>();
		for (WebElement option : options) {
			if (option.isSelected()) {
				textList.add(option.getText());
			}
		}

		return textList;
	}

	public String getSelectedValue() {
		for (WebElement option : options) {
			if (option.isSelected()) {
				return option.getAttribute("value");
			}
		}
		return null;
	}

	public List<String> getSelectValues() {

		List<String> valueList = new ArrayList<String>();
		for (WebElement option : options) {
			if (option.isSelected()) {
				valueList.add(option.getAttribute("value"));
			}
		}
        return valueList;
	}

	public boolean isMultiple() {
        return select.isMultiple();

	}

	public void selectByIndex(int index) {
		logger.info("select by index : " + index + " : " + this.getXpath());
	    select.selectByIndex(index);
	}

	public void selectByVisibleText(List<String> texts) {
        for (String text : texts) {
            select.selectByVisibleText(text);
        }
	}
	
	public void selectByExactValue(String value){
		logger.info("select by exact visible text :" + this.getXpath());
		select.selectByVisibleText(value);
	}

	public void selectByVisibleText(String value){
		logger.info("select by visible text :" + this.getXpath());

		List<WebElement> options = select.getOptions();
		
		String v = null;
		
		for(WebElement e : options){
			v = e.getAttribute("text");
			if(v.contains(value)){
				select.selectByVisibleText(v);
				this.sleep(1000);
				break;
			}
		}
	}
	
	public void selectByValue(String value) {
		logger.info("select by value :" + this.getXpath());
        select.selectByValue(value);
	}

	public void selectByValue(String[] values) {
        for (String value : values) {
            select.selectByValue(value);
        }
    }
}
