package com.nonobank.apps.testcase.portal;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import com.nonobank.apps.business.portal.Biz_Portal;
import com.nonobank.apps.testcase.base.BaseCase;
import com.nonobank.apps.utils.variable.CodisTool;

public class Register222 extends BaseCase {
	Biz_Portal biz_Portal;

	@Test(dataProvider = "dataSource")

	public void test(String mobile) {
		biz_Portal.navigate_to_register();
		String url = "https://www.sit.nonobank.com/v6/Uuid";
		try {
			// element = driver.findElement(By.tagName("body"));
			// element.sendKeys(Keys.CONTROL + "t");
			driver.get(url);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		WebElement element2 = driver.findElement(By.tagName("body"));
		String uuid = element2.getText();
		System.out.println("********************uuid=" + uuid);
		driver.navigate().back();

		int i = 1;
		String host = "";
		int port = 0;
		if (i == 0) {
			host = "192.168.3.130";
			port = 6379;
		} else {
			host = "192.168.4.53";
			port = 19000;
		}
		CodisTool.init(host, port);
		String imgcode = CodisTool.getValue(uuid);
		System.out.println("***********************8imgcode=" + imgcode);
	}

}
