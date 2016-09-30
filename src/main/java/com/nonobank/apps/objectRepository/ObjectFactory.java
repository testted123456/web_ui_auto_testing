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
		return (WebButton) getWebElement(WebElementType.WebButton, elementPath);
	}

	public WebInput getWebInput(String elementPath) {
		return (WebInput) getWebElement(WebElementType.WebInput, elementPath);
	}

	public WebLink getWebLink(String elementPath) {
		return (WebLink) getWebElement(WebElementType.WebLink, elementPath);
	}

	public WebSelect getWebSelect(String elementPath) {
		return (WebSelect) getWebElement(WebElementType.WebSelect, elementPath);
	}

	public WebImage getWebImage(String elementPath) {
		return (WebImage) getWebElement(WebElementType.WebImage, elementPath);
	}

	public WebCheckBox getWebCheckBox(String elementPath) {
		return (WebCheckBox) getWebElement(WebElementType.WebCheckBox, elementPath);
	}

	public WebLabel getWebLabel(String elementPath) {
		return (WebLabel) getWebElement(WebElementType.WebLabel, elementPath);
	}

	public WebRadioBox getWebRadioBox(String elementPath) {
		return (WebRadioBox) getWebElement(WebElementType.WebRadioBox, elementPath);
	}

	public WebTextArea getWebTextArea(String elementPath) {
		return (WebTextArea) getWebElement(WebElementType.WebTextArea, elementPath);
	}

	public WebSpan getWebSpan(String elementPath) {
		return (WebSpan) getWebElement(WebElementType.WebSpan, elementPath);
	}

	public WebFont getWebFont(String elementPath) {
		return (WebFont) getWebElement(WebElementType.WebFont, elementPath);
	}

	public WebCommon getWebCommon(String elementPath) {
		return (WebCommon) getWebElement(WebElementType.WebCommon, elementPath);
	}

	public WebLi getWebLi(String elementPath){
		return (WebLi) getWebElement(WebElementType.WebLi, elementPath);
	}
	
	@SuppressWarnings("unchecked")
	public <E extends BaseWebElement> E getWebElement(WebElementType elementType, String elementName) {
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
			// TODO Auto-generated catch block
			logger.error("调用构造函数生成页面对象失败，xpath : " + elementName);
			Assert.fail("调用构造函数生成页面对象失败，xpath : " + elementName);
		}
		return (E) object;
	}

	public WebElement getWebElement(String xpath) {
		final By by = By.xpath(xpath);

		try {
			WebDriverWait wait = new WebDriverWait(driver, 20);
			return wait.until(new ExpectedCondition<WebElement>() {
				@Override
				public WebElement apply(WebDriver d) {
					return d.findElement(by);
				}
			});
		} catch (NoSuchElementException e) {
			logger.info("找不页面对象，xpath : " + by.toString());
			Assert.fail("找不页面对象，xpath : " + by.toString());
			return null;
		}
	}

	public WebElement getWebElement(final By by) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 20);
			return wait.until(new ExpectedCondition<WebElement>() {
				@Override
				public WebElement apply(WebDriver d) {
					return d.findElement(by);
				}
			});
		} catch (NoSuchElementException e) {
			logger.info("找不页面对象，xpath : " + by.toString());
			Assert.fail("找不页面对象，xpath : " + by.toString());
			return null;
		}
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
