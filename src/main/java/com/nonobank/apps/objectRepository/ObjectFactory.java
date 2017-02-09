package com.nonobank.apps.objectRepository;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.nonobank.apps.testcase.base.BaseCase;
import com.nonobank.apps.utils.file.ParseXML;

public class ObjectFactory {

	private WebDriver driver;

	private String xmlFile;

	private static final Logger logger = LogManager.getLogger(ObjectFactory.class);

	public ObjectFactory(WebDriver driver, String xmlFile) {
		this.driver = driver;
		this.xmlFile = xmlFile;
	}

	public String getXmlFile() {
		return xmlFile;
	}

	public void setXmlFile(String xmlFile) {
		this.xmlFile = xmlFile;
	}

	public WebButton getWebButton(String elementPath) {
		return (WebButton) getBaseWebElement(WebElementType.WebButton, elementPath);
	}

	public WebInput getWebInput(String elementPath) {
		return (WebInput) getBaseWebElement(WebElementType.WebInput, elementPath);
	}

	public WebLink getWebLink(String elementPath) {
		return (WebLink) getBaseWebElement(WebElementType.WebLink, elementPath);
	}

	public WebSelect getWebSelect(String elementPath) {
		return (WebSelect) getBaseWebElement(WebElementType.WebSelect, elementPath);
	}

	public WebImage getWebImage(String elementPath) {
		return (WebImage) getBaseWebElement(WebElementType.WebImage, elementPath);
	}

	public WebCheckBox getWebCheckBox(String elementPath) {
		return (WebCheckBox) getBaseWebElement(WebElementType.WebCheckBox, elementPath);
	}

	public WebLabel getWebLabel(String elementPath) {
		return (WebLabel) getBaseWebElement(WebElementType.WebLabel, elementPath);
	}

	public WebRadioBox getWebRadioBox(String elementPath) {
		return (WebRadioBox) getBaseWebElement(WebElementType.WebRadioBox, elementPath);
	}

	public WebTable getWebTable(String elementPath) {
		return (WebTable) getBaseWebElement(WebElementType.WebTable, elementPath);
	}

	public WebTextArea getWebTextArea(String elementPath) {
		return (WebTextArea) getBaseWebElement(WebElementType.WebTextArea, elementPath);
	}

	public WebSpan getWebSpan(String elementPath) {
		return (WebSpan) getBaseWebElement(WebElementType.WebSpan, elementPath);
	}

	public WebFont getWebFont(String elementPath) {
		return (WebFont) getBaseWebElement(WebElementType.WebFont, elementPath);
	}

	public WebCommon getWebCommon(String elementPath) {
		return (WebCommon) getBaseWebElement(WebElementType.WebCommon, elementPath);
	}

	public WebLi getWebLi(String elementPath) {
		return (WebLi) getBaseWebElement(WebElementType.WebLi, elementPath);
	}

	@SuppressWarnings("unchecked")
	public <E extends BaseWebElement> E getBaseWebElement(WebElementType elementType, String elementName) {
		String xpath = null;

		xpath = ParseXML.getXPath(elementName, elementType, xmlFile);

		Object object = null;

		Constructor<?> constructor = null;

		try {
			constructor = Class.forName("com.nonobank.apps.objectRepository." + elementType)
					.getConstructor(WebDriver.class, String.class);
		} catch (NoSuchMethodException e) {
			Assert.fail(e.getMessage());
		} catch (SecurityException e) {
			Assert.fail(e.getMessage());
		} catch (ClassNotFoundException e) {
			Assert.fail(e.getMessage());
		}
		try {
			object = constructor.newInstance(driver, xpath);
		} catch (InstantiationException e) {
			Assert.fail(e.getMessage());
		} catch (IllegalAccessException e) {
			Assert.fail(e.getMessage());
		} catch (IllegalArgumentException e) {
			Assert.fail(e.getMessage());
		} catch (InvocationTargetException e) {
			String msg = "调用构造函数生成页面对象失败，xpath : " + elementName;
			BaseCase baseCase = new BaseCase();
			baseCase.setErrorMessage(msg);
			baseCase.setActualResult("失败");
			logger.error(msg);
			Assert.fail(msg);
		}
		return (E) object;
	}

	public WebElement getWebElement(String elementName, WebElementType elementType) {
		return getWebElement(elementName, elementType, 20);
	}

	public WebElement getWebElement(String elementName, WebElementType elementType, final long time) {
		String xpath = ParseXML.getXPath(elementName, elementType, xmlFile);
		return getWebElement(xpath, time);
	}

	public WebElement getWebElement(String xpath) {
		return getWebElement(xpath, 20);
	}

	public WebElement getWebElement(String xpath, final long time) {
		final By by = By.xpath(xpath);
		return getWebElement(by, time);
	}

	public WebElement getWebElement(final By by, final long time) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, time);
			return wait.until(new ExpectedCondition<WebElement>() {
				@Override
				public WebElement apply(WebDriver d) {
					return d.findElement(by);
				}
			});
		} catch (NoSuchElementException e) {
			String msg = "找不页面对象，xpath : " + by.toString();
			logger.info(msg);
			return null;
		}
	}

	public WebElement getWebElement(final By by) {
		return getWebElement(by, 20);
	}

	public List<WebElement> getWebElements(By by) {
		return driver.findElements(by);
	}

	public List<WebElement> getWebElements(String xpath) {
		By by = By.xpath(xpath);
		return driver.findElements(by);
	}

	public List<WebElement> getWebElements(String elementName, WebElementType elementType, String xmlFile) {
		String xpath = ParseXML.getXPath(elementName, elementType, xmlFile);
		By by = By.xpath(xpath);
		return driver.findElements(by);
	}
}
