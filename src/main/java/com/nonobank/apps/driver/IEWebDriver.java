package com.nonobank.apps.driver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.nonobank.apps.listener.WebDriverEventListenerImpl;
import com.nonobank.apps.utils.file.ParseProperties;

public class IEWebDriver {
	private static final Logger logger = LogManager.getLogger(IEWebDriver.class);

	private WebDriver driver;
	
	public  WebDriver getInstance(){
		
		Properties prop = ParseProperties.getInstance();
		
		try {
			logger.info("try to get ie webdriver");
			
			
			String driver_path = prop.getProperty("ieDriver");
			
			System.setProperty("webdriver.ie.driver", driver_path);
			
			DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer();
			
		    ieCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
		    
		    driver = new InternetExplorerDriver(ieCapabilities);
		}  catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			prop = null;
		}
		return new EventFiringWebDriver(driver).register(new WebDriverEventListenerImpl());
	}
}
