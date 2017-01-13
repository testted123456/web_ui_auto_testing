package com.nonobank.apps.business.recharge;

import java.util.HashMap;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.nonobank.apps.page.account.Page_Account;
import com.nonobank.apps.page.recharge.Page_User_Recharge;

public class Biz_User_Recharge {

	public static Logger logger = LogManager.getLogger(Biz_User_Recharge.class);

	Page_User_Recharge page_User_Recharge = new Page_User_Recharge();
	Page_Account page_Account = new Page_Account();
	public static HashMap<String, String> mapBank = new HashMap<String, String>();
	static {
		mapBank.put("建设银行", "CCB");
		mapBank.put("中信银行", "CITIC");
		mapBank.put("华夏银行", "HXB");
		mapBank.put("广发银行", "GDB");
		mapBank.put("平安银行", "PAB");

		mapBank.put("农业银行", "ABC");
		mapBank.put("中国银行", "BOC");
		mapBank.put("招商银行", "CMB");
		mapBank.put("光大银行", "CEB");
		mapBank.put("交通银行", "BCOM");

		mapBank.put("民生银行", "CMBC");
		mapBank.put("浦发银行", "SPDB");
		mapBank.put("工商银行", "ICBC");
		mapBank.put("兴业银行", "CIB");
		mapBank.put("邮政储蓄", "PSBC");
	}

	/**
	 * 根据卡的尾号选择银行卡
	 * 
	 * @param cardno
	 */
	public void recharge(String bankName) {
		logger.info("选择银行卡......");
		page_User_Recharge.click_otherBank();
		String bankCode = mapBank.get(bankName);
		WebElement elementUl = page_User_Recharge.getObjectFactory().getWebElement(By.id("hasbind_card_list"));
		List<WebElement> elementLi = elementUl.findElements(By.xpath("//span[@class='bankIcon " + bankCode + "']"));
		for (WebElement webElement : elementLi) {
			String bankAllName = webElement.getText();
			if (bankAllName.contains(bankName)) {
				webElement.click();
				page_User_Recharge.nextStep();
				break;
			}
		}
	}
}
