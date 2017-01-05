package com.nonobank.apps.page.admin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import com.nonobank.apps.objectRepository.WebLink;
import com.nonobank.apps.objectRepository.WebSelect;
import com.nonobank.apps.page.base.BasePage;

public class Page_DfDebt extends BasePage {
	public static Logger logger = LogManager.getLogger(Page_DfDebt.class);

	public void click_dfdDebtList() {
		logger.info("点击大房东债券列表......");
		switch_to_frameSet();
		WebLink link_list = objectFactory.getWebLink("大房东债权列表");
		link_list.click();
	}

	public void switch_to_frameSet() {
		driver.switchTo().defaultContent();
		driver.switchTo().frame(driver.findElement(By.id("mainFrame")));
	}

	// 选择匹配精选产品
	public void select_targetFpid(String targetFpid) {
		logger.info("选择匹配精选产品......");
		WebSelect select_targetFpid = objectFactory.getWebSelect("targetFpid");
		select_targetFpid.selectByExactValue(targetFpid);
	}
}
