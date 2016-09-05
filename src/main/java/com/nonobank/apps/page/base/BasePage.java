package com.nonobank.apps.page.base;

import java.io.File;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.nonobank.apps.objectRepository.BaseWebElement;
import com.nonobank.apps.objectRepository.ObjectFactory;
import com.nonobank.apps.objectRepository.WebElementType;
import com.nonobank.apps.utils.driver.WebDriverUtils;

/**
 * 类说明：所有page的基类
 * 
 * @author
 *
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

	public boolean isDisplayed(WebElementType elementType, String elementPath) {
		BaseWebElement element = (BaseWebElement) objectFactory.getWebElement(elementType, elementPath);
		return element.isDisplayed();
	}
}
