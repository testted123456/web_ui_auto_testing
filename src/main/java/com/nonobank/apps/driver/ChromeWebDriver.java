package com.nonobank.apps.driver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.nonobank.apps.listener.WebDriverEventListenerImpl;
import com.nonobank.apps.utils.file.ParseProperties;

public class ChromeWebDriver {

	private static final Logger logger = LogManager.getLogger(ChromeWebDriver.class);

	private ChromeDriver driver;

	public WebDriver getInstance() {

		logger.info("try to get chrome webdriver");

		ChromeOptions options = new ChromeOptions();

		options.addArguments("start-maximized");

		String driver_path = ParseProperties.getInstance().getProperty("chromeDriver");

		System.setProperty("webdriver.chrome.driver", driver_path);

		driver = new ChromeDriver();

		return new EventFiringWebDriver(driver).register(new WebDriverEventListenerImpl());
	}
}
