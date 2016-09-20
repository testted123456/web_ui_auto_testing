package com.nonobank.apps.page.admin;

import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.nonobank.apps.objectRepository.WebElementType;
import com.nonobank.apps.objectRepository.WebInput;
import com.nonobank.apps.page.base.BasePage;
import com.nonobank.apps.utils.file.ParseProperties;
import com.nonobank.apps.utils.page.PageUtils;

public class Page_Admin_Login extends BasePage{

	public static Logger logger = LogManager.getLogger(Page_Admin_Login.class);
	
	/**
	 * 跳转到后台Admin页面
	 */
	public void nagivate_to_adminpage(){
		logger.info("跳转到后台系统...");
		
		Properties prop = ParseProperties.getInstance();
		String url_admin = prop.getProperty("url") + "/Admin";
		driver.navigate().to(url_admin);
	}
	
	public void input_username(String username){
		logger.info("输入用户名...");
		
		WebInput input_username = objectFactory.getWebInput("username");
		input_username.clearAndInput(username);
	}
	
	public void input_password(String password){
		logger.info("输入密码...");
		
		WebInput input_username = objectFactory.getWebInput("password");
		input_username.clearAndInput(password);
	}
	
	public void login(){
		logger.info("点击登录按钮...");
		
		WebInput input_login = objectFactory.getWebInput("login");
		input_login.click();
		
		for(int i=0;i<10;i++){
			driver.switchTo().frame("topFrame");
			if(isElementExists("logout", WebElementType.WebLink, 15)){
				logger.info("登录后台成功1...");
				break;
			}
			if(isElementExists("login", WebElementType.WebInput, 15)){
				logger.info("登录后台失败...");
				input_login = objectFactory.getWebInput("login");
				if(input_login.isDisplayed()){
					input_login.click();
					sleep(1000);
				}else{
					break;
				}
			}else{
				logger.info("登录后台成功...");
				break;
			}
		}
	}
	
	public void login_admin(){
		if(isElementExists(By.xpath("//input[@name='username']"), 15)){
			
			Properties prop = ParseProperties.getInstance();
			String username = prop.getProperty("admin_user");
			String password = prop.getProperty("admin_password");
			
			WebElement input_username = objectFactory.getWebElement(By.xpath("//input[@name='username']"));
			
			WebElement input_password = objectFactory.getWebElement(By.xpath("//input[@name='password']"));
			
			WebElement input_submit = objectFactory.getWebElement(By.xpath("//input[@name='submit']"));
			
			input_username.clear();
			
			input_username.sendKeys(username);

			input_password.clear();
			
			input_password.sendKeys(password);
			
			input_submit.click();
			
			PageUtils.waitForPageLoad();
		}
	}
}
