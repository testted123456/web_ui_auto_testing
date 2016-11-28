package com.nonobank.apps;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class TestMain {
	
	public static void main(String [] args){
		DesiredCapabilities dc = DesiredCapabilities.chrome();
		WebDriver driver = null;
		
		try {
			driver = new RemoteWebDriver(new URL("http://127.0.0.1:4444/wd/hub"), dc);
		} catch (MalformedURLException e) {	
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		driver.get("http://baidu.com");
	}
}
