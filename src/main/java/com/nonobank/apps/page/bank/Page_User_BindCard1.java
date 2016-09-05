package com.nonobank.apps.page.bank;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.nonobank.apps.objectRepository.WebLink;
import com.nonobank.apps.page.base.BasePage;

public class Page_User_BindCard1 extends BasePage {
	public static Logger logger = LogManager.getLogger(Page_User_BindCard1.class);
	
	public static Map<String, String> map = new HashMap<String, String>(){/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

	{
		put("光大银行","03030000");
		put("广发银行","03060000");
		put("民生银行","03050000");
		put("华夏银行","HXB");
		put("建设银行","CCB");
		put("农业银行","ABC");
		put("平安银行","PAB");
		put("浦发银行","SPDB");
		put("中信银行","CITIC");
		put("兴业银行","CIB");
		put("招商银行","CMB");
		put("中国银行","BOC");
		put("工商银行","01020000");
		put("交通银行","BCOM");
		put("邮政储蓄","01000000");
	}};
	
	//选择银行
	public void select_bank(String bank_name){
		String bank_code = map.get(bank_name);
		Set<String> handles = driver.getWindowHandles();
		
		for(String handle : handles){
			String url = driver.switchTo().window(handle).getCurrentUrl();
			if(url.endsWith("User/BindCard1")){
				break;
			}
		}
		
		WebElement span_bank_name = objectFactory.getWebElement(By.xpath("//span[@data-bankcode='" + bank_code + "']"));
		span_bank_name.click();	
	}
	 
	//点击下一步
	public void click_next_step(){
		WebLink link_next_step = objectFactory.getWebLink("next_step");
		link_next_step.click();
	}
	
	//“下一步”按钮的class属性，未选银行class属性为pay_btn grey_btn，已选银行class属性为pay_btn
	public String get_next_step_class(){
		WebLink link_next_step = objectFactory.getWebLink("next_step");
		String clazz = link_next_step.getClzz();
		return clazz;
	}

	//校验选择银行点下一步是否成功
	public void verify_select_bank(String bank_name){
		if(objectFactory.isElementExists(By.xpath("//span[@data-bankname='" + bank_name + "']"))){
			logger.info("选择银行成功.");
		}else{
			logger.error("选择银行失败.");
			Assert.fail("select bank failed.");
		}
	}
}
