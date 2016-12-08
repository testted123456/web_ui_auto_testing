package com.nonobank.apps.testcase.portal;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import com.nonobank.apps.business.portal.Biz_Portal;
import com.nonobank.apps.testcase.base.BaseCase;

public class Register extends BaseCase {
	Biz_Portal biz_Portal;

	@Test(dataProvider = "dataSource")

	public void test(String mobile) {
		biz_Portal.navigate_to_register();
		WebElement element = null;
		String url = "https://www.sit.nonobank.com/v6/Uuid";

		try {
			element = driver.findElement(By.tagName("body"));
			element.sendKeys(Keys.CONTROL + "t");
			driver.get(url);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		WebElement element2=driver.findElement(By.xpath("//body[]"));
		String uuid=element2.getText();
		System.out.println(uuid);
		
	}

}
