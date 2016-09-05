package com.nonobank.apps.utils.driver;

import java.util.Random;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import com.nonobank.apps.driver.ChromeWebDriver;
import com.nonobank.apps.driver.FirefoxWebDriver;
import com.nonobank.apps.driver.IEWebDriver;
import com.nonobank.apps.driver.WebDriverType;
import com.nonobank.apps.utils.file.ParseProperties;

public class WebDriverUtils {

	public static Logger logger = LogManager.getLogger(WebDriverUtils.class);

	private static WebDriver driver;
	
	public static void destoryWebDriver(){
		logger.info("设置webdriver为空...");
		driver = null;
	}
	
	public static WebDriver getWebDriver() {
		if (null == driver) {
			String browser = ParseProperties.getInstance().getProperty("browser");
			WebDriverType driverType = WebDriverType.firefox;

			if (browser.equals("ie")) {
				driverType = WebDriverType.ie;
			} else if (browser.equals("chrome")) {
				driverType = WebDriverType.chrome;
			} else if (browser.equals("firefox")) {
				driverType = WebDriverType.firefox;
			} else if (browser.equals("random")) {
				Random random = new Random();
				int r = random.nextInt(3);

				switch (r) {
				case 0:
					driverType = WebDriverType.firefox;
					break;
				case 1:
					driverType = WebDriverType.chrome;
					break;
				case 2:
					driverType = WebDriverType.ie;
					break;
				default:
					driverType = WebDriverType.firefox;
					break;
				}
			}

			if (driverType.equals(WebDriverType.firefox)) {
				FirefoxWebDriver firefoxWebDriver = new FirefoxWebDriver();
				driver = firefoxWebDriver.getInstance();
			} else if (driverType.equals(WebDriverType.chrome)) {
				driver = new ChromeWebDriver().getInstance();
			} else if (driverType.equals(WebDriverType.ie)) {
				driver = new IEWebDriver().getInstance();
			}
		}
		return driver;
	}
}
