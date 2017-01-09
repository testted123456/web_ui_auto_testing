package com.nonobank.apps.testcase.repayment;

import java.util.Properties;
import java.net.URL;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import com.nonobank.apps.utils.driver.WebDriverUtils;
import com.nonobank.apps.utils.file.ParseProperties;
import com.nonobank.apps.utils.file.ParseXLSX;

/**
 * @author Coderinfo
 * @Email: coderinfo@163.com
 */
public class UpdateFile {
	private static final String URL = "file:///C:/Users/lishuangshuang/Desktop/update_file.html";

	public static void main(String[] args) {
		WebDriver driver = WebDriverUtils.getWebDriver();
		driver.manage().window().maximize();
		driver.get(URL);
		By bybbb = By.id("updatebbb");
		By byaaa = By.id("updateaaa");
		System.out.println("****************by=" + bybbb);
		WebElement bbb = driver.findElement(bybbb);
		bbb.sendKeys("woshisb");
		WebElement aaa = driver.findElement(byaaa);
//		URL url = ParseXLSX.class.getClassLoader().getResource("id.jpg");
		String file_path = "D:\\project\\web_ui_auto_testing\\target\\classes\\png\\id.jpg";
		String file_path2 = "/D:/project/web_ui_auto_testing/target/classes/png/id.jpg";
		System.out.println("*********************file_path="+file_path2);
		aaa.sendKeys(file_path);
		System.out.println("****************aaa=" + aaa.getAttribute("type"));
		// Use API:sendKeys to update file
		// JavascriptExecutor j = (JavascriptExecutor) driver;
		// j.executeScript("document.getElementById('update').style.display='block';");
		// driver.findElement(By.id("update")).sendKeys("C:/Users/lishuangshuang/Desktop/update_file.html");
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// driver.close();
	}
}