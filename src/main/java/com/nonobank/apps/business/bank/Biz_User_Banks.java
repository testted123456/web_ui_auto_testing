package com.nonobank.apps.business.bank;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import com.nonobank.apps.page.bank.Page_User_Banks;
import com.nonobank.apps.page.base.BasePage;
import com.nonobank.apps.testcase.bindcard.LoginBindCard;
import com.nonobank.apps.utils.data.Assertion;
import com.nonobank.apps.utils.page.PageUtils;

public class Biz_User_Banks extends BasePage {
	public static Logger logger = LogManager.getLogger(Biz_User_Banks.class);

	Page_User_Banks page_User_Banks = new Page_User_Banks();

	/**
	 * 添加银行卡
	 */
	public void navigate_to_bindCard1(String expectResult) {
		if (expectResult.equals("true")) {
			boolean flag = page_User_Banks.isElementExists(By.xpath("//a[@href='/User/BindCard1']"), 3);
			Assertion.assertEquals(flag, false, Biz_User_Banks.class, "反例-用户只能绑定一张银行卡");
			LoginBindCard.is_bindcard = true;
			return;
		}
		logger.info("添加银行卡...");
		page_User_Banks.click_add_bankcard();
		PageUtils.waitForPageLoad();
	}

	public void verifyNameID(String realName, String idNo) {
		logger.info("实名认证...");
		page_User_Banks.verifyNameID(realName, idNo);
	}
}
