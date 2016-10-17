package com.nonobank.apps.page.admin;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import com.nonobank.apps.business.admin.Biz_Debt;
import com.nonobank.apps.objectRepository.WebCommon;
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
		logger.info("查询债转产品......");
		WebInput input_query = objectFactory.getWebInput("查询");
		input_query.click();
	}

	public void click_debtDetail() {
		logger.info("查询债转详情......");
		switch_to_frameSet();
		WebCommon common_uname = objectFactory.getWebCommon("uname");
		common_uname.click();
	}

	public WebElement get_debtMain() {
		logger.info("点击大债转......");
		List<WebElement> lstElements = objectFactory.getWebElements("//table[@id='table_1']/tbody/tr/td[13]/span");
		for (int i = 0; i < lstElements.size(); i++) {
			int endIndex = lstElements.get(i).getText().indexOf("(");
			String text = lstElements.get(i).getText().substring(0, endIndex);
			Biz_Debt.amount = Double.parseDouble(text);
			if (!text.equals("0.00")) {
				WebElement web = objectFactory
						.getWebElement("//table[@id='table_1']/tbody/tr[" + (i + 1) * 2 + "]/td[17]/span[1]/a");
				return web;
			}
		}
		return null;
	}

	public void click_debtMain() {
		WebElement web = get_debtMain();
		web.click();
	}

	public void click_debt() {
		logger.info("点击小债转......");
		WebInput input_vaId = objectFactory.getWebInput("vaId");
		Biz_Debt.from_id = input_vaId.getValue();
		List<WebElement> lstElements = objectFactory.getWebElements("//table[@id='table_1']//table//tr/td[9]//a");
		while (lstElements.size() == 0) {
			try {
				Thread.sleep(3000);
				lstElements = objectFactory.getWebElements("//table[@id='table_1']//table//tr/td[9]//a");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		do {
			lstElements = objectFactory.getWebElements("//table[@id='table_1']//table//tr/td[9]//a");
			try {
				Thread.sleep(3000);
				lstElements = objectFactory.getWebElements("//table[@id='table_1']//table//tr/td[9]//a");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} while (lstElements.size() == 0);
		for (int i = 0; i < lstElements.size(); i++) {
			if (lstElements.get(i).getText().equals("债转")) {
				WebElement web = objectFactory
						.getWebElement("//table[@id='table_1']//table//tr[" + (i + 2) + "]/td[1]//a");
				String text = web.getText();
				int endIndex = text.indexOf("】");
				Biz_Debt.bo_id = text.substring(1, endIndex);
				lstElements.get(i).click();
				break;
			}
		}

	}
}
