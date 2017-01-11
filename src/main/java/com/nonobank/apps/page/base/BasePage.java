package com.nonobank.apps.page.base;

import java.io.File;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.nonobank.apps.objectRepository.ObjectFactory;
import com.nonobank.apps.objectRepository.WebCommon;
import com.nonobank.apps.objectRepository.WebElementType;
import com.nonobank.apps.utils.driver.WebDriverUtils;

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
	 * 
	 * @param by
	 * @param time
	 *            超时时间
	 * @return
	 */
	public boolean isElementExists(final By by, long time) {
		try {
			WebElement element = objectFactory.getWebElement(by, time);
			return (null != element);
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 元素是否存在
	 * 
	 * @param elementName
	 *            元素在配置文件中名称
	 * @param elementType
	 *            元素类型
	 * @param time
	 *            超时时间
	 * @return
	 */
	public boolean isElementExists(String elementName, WebElementType elementType, long time) {
		try {
			WebElement element = objectFactory.getWebElement(elementName, elementType, time);
			return (null != element);
		} catch (Exception e) {
			return false;
		}
	}

	public boolean isElementDisplayed(String elementName, WebElementType elementType, long time) {
		for (int i = 0; i < time; i++) {
			WebElement element = objectFactory.getWebElement(elementName, elementType);
			if (element.isDisplayed()) {
				return true;
			} else {
				sleep(1000);
			}
		}
		return false;
	}

	public String getElementText(String elementPath) {
		WebCommon webCommon = objectFactory.getWebCommon(elementPath);
		String text = webCommon.getText();
		int count = 0;
		if (text == null || text.length() == 0 && count < 10) {
			count++;
			text = getElementText(elementPath);
		}
		return text;
	}

	/**
	 * alert窗口是否存在
	 * 
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

	public void acceptAlert() {
		driver.switchTo().alert().accept();
	}

}
