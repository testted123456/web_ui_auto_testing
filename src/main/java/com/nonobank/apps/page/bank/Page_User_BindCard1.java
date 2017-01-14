package com.nonobank.apps.page.bank;

import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import com.nonobank.apps.objectRepository.WebLink;
import com.nonobank.apps.page.base.BasePage;
import com.nonobank.apps.utils.data.IdBankGenerator;

public class Page_User_BindCard1 extends BasePage {
	public static Logger logger = LogManager.getLogger(Page_User_BindCard1.class);

	// 选择银行
	public void select_bank(String bank_name) {
		String bank_code = IdBankGenerator.map.get(bank_name);
		Set<String> handles = driver.getWindowHandles();

		for (String handle : handles) {
			String url = driver.switchTo().window(handle).getCurrentUrl();
			if (url.endsWith("User/BindCard1")) {
				break;
			}
		}
		WebElement span_bank_name = objectFactory
				.getWebElement(By.xpath("//span[@class='bankIcon " + bank_code + "']"));
		System.out.println("***********************span_bank_name=" + span_bank_name.getText());
		span_bank_name.click();
	}

	// 点击下一步
	public void click_next_step() {
		WebLink link_next_step = objectFactory.getWebLink("next_step");
		link_next_step.click();
	}

	// “下一步”按钮的class属性，未选银行class属性为pay_btn grey_btn，已选银行class属性为pay_btn
	public String get_next_step_class() {
		WebLink link_next_step = objectFactory.getWebLink("next_step");
		String clazz = link_next_step.getClzz();
		return clazz;
	}

	// 校验选择银行点下一步是否成功
	public void verify_select_bank(String bank_name) {
		if (isElementExists(By.xpath("//span[@data-bankname='" + bank_name + "']"), 15)) {
			logger.info("选择银行成功.");
		} else {
			logger.error("选择银行失败.");
			Assert.fail("select bank failed.");
		}
	}
}
