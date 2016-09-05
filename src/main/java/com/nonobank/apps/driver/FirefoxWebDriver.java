package com.nonobank.apps.driver;

import java.io.File;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import com.nonobank.apps.listener.*;
import com.nonobank.apps.utils.file.ParseProperties;

public class FirefoxWebDriver {
	
	private static final Logger logger = LogManager.getLogger(FirefoxWebDriver.class);

	private WebDriver driver;
	
	public  WebDriver getInstance(){
		Properties prop = ParseProperties.getInstance();
		
		logger.info("获取webdriver...");
		
		String profileLoc;
		
		String firefoxLoc;
		
		FirefoxProfile profile;
		
		profileLoc = prop.getProperty("firefoxprofileLoc");
		
		firefoxLoc = prop.getProperty("firefoxLoc"); 
		
		profile = new FirefoxProfile(new File(profileLoc));
		
		driver = new FirefoxDriver(new FirefoxBinary(new File(firefoxLoc)), profile);

		return new EventFiringWebDriver(driver).register(new WebDriverEventListenerImpl());
	}
}
