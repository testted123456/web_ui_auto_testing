package com.nonobank.apps.page.base;

import java.io.File;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.nonobank.apps.objectRepository.BaseWebElement;
import com.nonobank.apps.objectRepository.ObjectFactory;
import com.nonobank.apps.objectRepository.WebElementType;
import com.nonobank.apps.utils.driver.WebDriverUtils;
import com.nonobank.apps.utils.file.ParseXML;

/**
 * 类说明：所有page的基类
 * 
 * @author
 */
public class BasePage {
	protected WebDriver driver;
	protected ObjectFactory objectFactory;
	protected String xmlFile;
	public static Logger logger = LogManager.getLogger(BasePage.class);

	public ObjectFactory getObjectFactory() {
		return objectFactory;
	}

	public void setObjectFactory(ObjectFactory objectFactory) {
		this.objectFactory = objectFactory;
	}

	public String getXmlFile() {
		return xmlFile;
	}

	public void setXmlFile(String xmlFile) {
		this.xmlFile = xmlFile;
	}

	public BasePage() {
		driver = WebDriverUtils.getWebDriver();

		xmlFile = "resources" + File.separator + "elements" + File.separator
				+ this.getClass().getName().replace(".", File.separator) + ".xml";

		objectFactory = new ObjectFactory(driver, xmlFile);
	}

	public void sleep(long t) {
		try {
			Thread.sleep(t);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	/** 
	 * 元素是否存在
	 * @param by
	 * @param time 超时时间
	 * @return
	 */
	public boolean isElementExists(final By by, long time) {
		
		try {
			WebDriverWait wait = new WebDriverWait(driver, time);

			WebElement element = wait.until(new ExpectedCondition<WebElement>() {

				@Override
				public WebElement apply(WebDriver d) {
					try {
						return d.findElement(by);
					} catch (Exception e) {
						return null;
					}
				}
			});
			return (null != element);
		} catch (Exception e) {
			return false;
		}
	}
    
	/**
	 * 元素是否存在
	 * @param elementName 元素在配置文件中名称
	 * @param elementType 元素类型
	 * @param time 超时时间　
	 * @return
	 */
	public boolean isElementExists(String elementName, WebElementType elementType, long time) {
		String xpath = ParseXML.getXPath(elementName, elementType, xmlFile);
		final By by = By.xpath(xpath);
		return isElementExists(by, time);
	}
	
	/**
	 * 元素是否显示
	 * @param by
	 * @param time
	 * @return
	 */
	public boolean isElementDisplayed(final By by, long time){
		boolean is_exists = isElementExists(by, time);
		if(is_exists == true){
			return objectFactory.getWebElement(by).isDisplayed();
		}else{
			return false;
		}
	}
	
	public boolean isElementDisplayed(String elementName, WebElementType elementType, long time){
		boolean is_exists = isElementExists(elementName, elementType, time);
		
		if(is_exists == true){
			return objectFactory.getWebElement(elementType, elementName).isDisplayed();
		}else{
			return false;
		}
	}

	/**
	 * alert窗口是否存在
	 * @param driver
	 * @return
	 */
	public boolean isAlertExists(long ms) {
		int round = (int) ms / 1000;
		for (int i = 0; i <= round; i++) {
			try {
				driver.switchTo().alert();
				return true;
			} catch (NoAlertPresentException ex) {
				sleep(1000);
			}
		}
		return false;
	}

	// 关闭alert
	public void closeAlert() {
		driver.switchTo().alert().accept();
	}

	// 获取alert内容
	public String getAlertText() {
		return driver.switchTo().alert().getText();
	}
}
