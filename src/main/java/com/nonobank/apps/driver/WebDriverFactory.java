package com.nonobank.apps.driver;

import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class WebDriverFactory {
	
	private static final Logger logger = LogManager.getLogger(WebDriverFactory.class);
	
	public static WebDriver getWebDriver(WebDriverType driverType){
		
		WebDriverType type = driverType;
		
		if(driverType.equals(WebDriverType.random)){
			Random random = new Random();
			
			int r = random.nextInt(3);
			
			switch (r) {
			case 0:
				type = WebDriverType.firefox;
				break;
			case 1:
				type = WebDriverType.chrome;
				break;
			case 2:
				type = WebDriverType.ie;
				break;
			default:
				type = WebDriverType.firefox;
				break;
			}
		}else if(type.equals(WebDriverType.firefox)){
			FirefoxWebDriver firefoxWebDriver = new FirefoxWebDriver();
			WebDriver driver = firefoxWebDriver.getInstance();
			return driver;
		}else if(type.equals(WebDriverType.chrome)){
			return new ChromeWebDriver().getInstance();
		}else if(type.equals(WebDriverType.ie)){
			return new IEWebDriver().getInstance();
		}
		
		logger.info("Can not get any web driver!");
		
		return null;
	}
}
