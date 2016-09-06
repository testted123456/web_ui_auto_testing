package com.nonobank.apps.objectRepository;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
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

	public boolean isElementExists(final By by) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 15);

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

	public boolean isElementExists(String elementPath, WebElementType elementType) {
		String xpath = ParseXML.getXPath(elementPath, elementType, xmlFile);

		final By by = By.xpath(xpath);

		try {
			WebDriverWait wait = new WebDriverWait(driver, 15);

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
	 * 函数说明：alert窗口是否存在
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
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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

	@SuppressWarnings("unchecked")
	public <E extends BaseWebElement> E getWebElement(WebElementType elementType, String elementPath) {
		String xpath = null;

		xpath = ParseXML.getXPath(elementPath, elementType, xmlFile);

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
			logger.error("调用构造函数生成页面对象失败，xpath : " + elementPath);
			Assert.fail("调用构造函数生成页面对象失败，xpath : " + elementPath);
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
}
