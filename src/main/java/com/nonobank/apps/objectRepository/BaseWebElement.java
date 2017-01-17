package com.nonobank.apps.objectRepository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import junit.framework.Assert;

public class BaseWebElement {

	protected static final Logger logger = LogManager.getLogger(BaseWebElement.class);

	private WebElement webElement;

	private WebDriver driver;

	private String xpath;

	public void setWebElement(WebElement webElement) {
		this.webElement = webElement;
	}

	public WebElement getWebElement() {
		return webElement;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	public WebDriver getDriver() {
		return driver;
	}

	public void setXpath(String xpath) {
		this.xpath = xpath;
	}

	public String getXpath() {
		return xpath;
	}

	/**
	 * 函数说明：构造函数
	 * 
	 * @param driver
	 * @param xpath
	 */
	public BaseWebElement(WebDriver driver, final String xpath) {
		this.driver = driver;
		this.xpath = xpath;
		WebDriverWait wait = new WebDriverWait(driver, 20);

		this.webElement = wait.until(new ExpectedCondition<WebElement>() {
			@Override
			public WebElement apply(WebDriver d) {
				return d.findElement(By.xpath(xpath));
			}
		});

		if (this.webElement == null) {
			Assert.fail("找不到对象元素，xpath：" + xpath);
		}
	}

	/**
	 * 函数说明：获取元素tag
	 * 
	 * @return
	 */
	public String getTag() {
		return webElement.getTagName();
	}

	/**
	 * 函数说明：等待指定时间
	 * 
	 * @return
	 */
	public void sleep(long t) {
		try {
			Thread.sleep(t);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 函数说明：点击元素
	 */
	public void click() {
		this.webElement.click();
		sleep(5000);
	}

	/**
	 * 函数说明：获取指定元素属性值
	 * 
	 * @param name
	 * @return
	 */
	public String getAttribute(String name) {
		return this.webElement.getAttribute(name);
	}

	/**
	 * 函数说明：获取指定元素css值
	 * 
	 * @param name
	 * @return
	 */
	public String getCssValue(String propertyName) {
		return this.webElement.getCssValue(propertyName);
	}

	/**
	 * 函数说明：获取指定元素高度
	 * 
	 * @param name
	 * @return
	 */
	public int getHeight() {
		return webElement.getSize().getHeight();
	}

	/**
	 * 函数说明：获取指定元素宽度
	 * 
	 * @param name
	 * @return
	 */
	public int getWidth() {
		return webElement.getSize().getWidth();
	}

	public Dimension getSize() {
		return webElement.getSize();
	}

	/**
	 * 函数说明：获取元素是否可操作
	 * 
	 * @return
	 */
	public boolean isEnabled() {
		return this.webElement.isEnabled();
	}

	/**
	 * 函数说明：获取元素是否显示
	 * 
	 * @return
	 */
	public boolean isDisplayed() {
		int time = 6000;
		for (int i = 0; i < time; i++) {
			if (this.webElement.isDisplayed()) {
				return true;
			} else {
				sleep(1000);
			}
		}
		return false;
	}

	/**
	 * 函数说明:获取元素错误信息
	 * 
	 * @param text
	 * @return
	 */
	public boolean isTextPresent(String text) {
		try {
			return webElement.getText().trim().toLowerCase().contains(text.trim().toLowerCase());
		} catch (StaleElementReferenceException e) {
			logger.info("element_display_error={}", e);
			return false;
		}
	}

	/**
	 * 函数说明：高亮显示页面元素
	 */
	public void highlightElement() {

		JavascriptExecutor js = (JavascriptExecutor) driver;

		js.executeScript("element = arguments[0];" +

				"original_style = element.getAttribute('style');" +

				"element.setAttribute('style', original_style + \";" +

				"background: yellow; border: 2px solid red;\");" +

				"setTimeout(function(){element.setAttribute('style', original_style);}, 1000);", webElement);
	}

	/**
	 * 函数说明：左键点击
	 */
	public void leftClick() {
		Actions action = new Actions(driver);
		action.click(webElement);
	}

	/**
	 * 函数说明：右键点击
	 */
	public void rightClick() {
		Actions action = new Actions(driver);
		action.contextClick(webElement);
	}

	/**
	 * 函数说明：双击
	 */
	public void doubleClick() {
		Actions action = new Actions(driver);
		action.doubleClick(webElement);
	}

	/**
	 * 函数说明：鼠标悬停
	 */
	public void clickAndHold() {
		Actions action = new Actions(driver);
		action.clickAndHold(webElement);
	}

	/**
	 * 函数说明：鼠标释放
	 */
	public void release() {
		Actions action = new Actions(driver);
		action.release(webElement);
	}

	/**
	 * 函数说明：模拟键盘输入
	 * 
	 * @param keys
	 */
	public void sendKeys(Keys keys) {
		Actions action = new Actions(driver);
		action.sendKeys(webElement, keys).perform();
	}
}
