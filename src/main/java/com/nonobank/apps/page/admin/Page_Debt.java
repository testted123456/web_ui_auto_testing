package com.nonobank.apps.page.admin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import com.nonobank.apps.objectRepository.WebInput;
import com.nonobank.apps.objectRepository.WebSelect;
import com.nonobank.apps.page.base.BasePage;

public class Page_Debt extends BasePage {
	public static Logger logger = LogManager.getLogger(Page_Debt.class);

	public void input_username(String username) {
		logger.info("输入用户名......");
		switch_to_frameSet();
		WebInput input_username = objectFactory.getWebInput("username");
		input_username.clearAndInput(username);
	}

	public void switch_to_frameSet() {
		driver.switchTo().defaultContent();
		driver.switchTo().frame(driver.findElement(By.id("mainFrame")));
	}

	// 选择精选产品
	public void select_fpId(String fpId) {
		logger.info("选择精选产品......");
		WebSelect select_fpId = objectFactory.getWebSelect("fpId");
		select_fpId.selectByExactValue(fpId);
	}

	// 选择匹配精选产品
	public void select_targetFpid(String targetFpid) {
		logger.info("选择匹配精选产品......");
		WebSelect select_targetFpid = objectFactory.getWebSelect("targetFpid");
		select_targetFpid.selectByExactValue(targetFpid);
	}

	public void select_targetVip(String targetVip) {
		logger.info("选择匹配账户......");
		WebSelect select_targetVip = objectFactory.getWebSelect("targetVip");
		select_targetVip.selectByExactValue(targetVip);
	}

	// 视频签约初审列表，点击"查询"按钮
	public void click_query() {
		WebInput input_query = objectFactory.getWebInput("查询");
		input_query.click();
	}

}
