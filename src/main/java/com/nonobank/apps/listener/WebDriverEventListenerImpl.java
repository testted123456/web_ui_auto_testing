package com.nonobank.apps.listener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;
import org.testng.Assert;

public class WebDriverEventListenerImpl implements WebDriverEventListener {
	
	private Logger logger = LogManager.getLogger(this.getClass().getName());
	
	@Override
	public void afterChangeValueOf(WebElement element, WebDriver driver) {
		// TODO Auto-generated method stub
	}

	@Override
	public void afterClickOn(WebElement element, WebDriver driver) {
		// TODO Auto-generated method stub
	}

	@Override
	public void afterFindBy(By by, WebElement element, WebDriver driver) {
		// TODO Auto-generated method stub
	}

	@Override
	public void afterNavigateBack(WebDriver driver) {
		// TODO Auto-generated method stub
	}

	@Override
	public void afterNavigateForward(WebDriver driver) {
		// TODO Auto-generated method stub
	}

	@Override
	public void afterNavigateTo(String url, WebDriver driver) {
		// TODO Auto-generated method stub
	}

	@Override
	public void afterScript(String script, WebDriver driver) {
		// TODO Auto-generated method stub
	}

	@Override
	public void beforeChangeValueOf(WebElement element, WebDriver driver) {
		// TODO Auto-generated method stub
	}

	@Override
	public void beforeClickOn(WebElement element, WebDriver driver) {
		// TODO Auto-generated method stub
	}

	@Override
	public void beforeFindBy(By by, WebElement element, WebDriver driver) {
		// TODO Auto-generated method stub
	}

	@Override
	public void beforeNavigateBack(WebDriver driver) {
		// TODO Auto-generated method stub
	}

	@Override
	public void beforeNavigateForward(WebDriver driver) {
		// TODO Auto-generated method stub
	}

	@Override
	public void beforeNavigateTo(String url, WebDriver driver) {
		// TODO Auto-generated method stub
	}

	@Override
	public void beforeScript(String script, WebDriver driver) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onException(Throwable throwable, WebDriver driver) {
		/**
		 * 如果不是NoSuchElementException（页面元素没找到）的异常，则把结果设为fail
		 */
		String noSuchElementException = NoSuchElementException.class.getName();
		String throwableName = throwable.getClass().getName();
		
		if(null != throwableName && !throwableName.equals(noSuchElementException)){
			logger.error(throwableName);
		}
		
		if(!noSuchElementException.equals(throwableName)){
			String name =  throwable.getClass().toString();
			logger.error("an unchecked exception throwed : " + name);
			logger.error("设置结果fail...");
			Assert.fail(name);
			
			//出现alert窗口，关闭窗口
			if(throwableName.equals("org.openqa.selenium.UnhandledAlertException")){
				logger.error("关闭预期外的alert窗口...");
				driver.switchTo().alert().accept();
			}
		}
	}

	@Override
	public void afterNavigateRefresh(WebDriver arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeNavigateRefresh(WebDriver arg0) {
		// TODO Auto-generated method stub
		
	}
}
