package com.nonobank.apps.page.admin;

import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import com.nonobank.apps.business.admin.Biz_Debt;
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

	public void input_fpId(String username) {
		logger.info("输入精选产品......");
		switch_to_frameSet();
		WebInput input_username = objectFactory.getWebInput("fpId");
		input_username.clearAndInput(username);
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
		WebElement element = get_debtMain("/td[4]/span");
		element.click();
	}

	public void click_debtMain() {
		logger.info("点击大债转......");
		WebElement web = get_debtMain("/td[17]/span[1]/a");
		web.click();
	}

	public void click_debt() {
		logger.info("点击小债转......");
		List<WebElement> lstElements = objectFactory.getWebElements("//table[@id='table_1']//table//tr/td[9]//a");
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
			System.out.println("*****************lstElements.get(" + i + ").getText()=" + lstElements.get(i).getText());
			if (lstElements.get(i).getText().equals("债转")) {
				WebElement web = objectFactory
						.getWebElement("//table[@id='table_1']//table//tr[" + (i + 2) + "]/td[1]//a");
				System.out.println("************=//table[@id='table_1']//table//tr[" + (i + 2) + "]/td[1]//a");
				String text = web.getText();
				int endIndex = text.indexOf("】");
				Biz_Debt.bo_id = text.substring(1, endIndex);
				lstElements.get(i).click();
				return;
			}
		}

	}

	public WebElement get_debtMain(String xpath) {
		List<WebElement> lstElements = objectFactory.getWebElements("//table[@id='table_1']/tbody/tr/td[13]/span");
		for (int i = 0; i < lstElements.size(); i++) {
			String string = lstElements.get(i).getText();
			int endIndex = string.indexOf("(");
			String text = string.substring(0, endIndex).replace(",", "");
			Biz_Debt.amount = Double.parseDouble(text);
			String debtCountString = string.substring(endIndex);
			if (!debtCountString.equals("(0/0)") && Biz_Debt.amount != 0) {
				WebElement webFromId = objectFactory
						.getWebElement("//table[@id='table_1']/tbody/tr[" + (i + 1) * 2 + "]/td[1]");
				Biz_Debt.from_id = webFromId.getText();
				if (xpath == null) {
					return null;
				} else if (xpath != null) {
					WebElement web = objectFactory
							.getWebElement("//table[@id='table_1']/tbody/tr[" + (i + 1) * 2 + "]" + xpath);
					return web;
				}
			}
		}
		return null;
	}

	public List<String> getFromIds() {
		List<String> lst = new ArrayList<String>();
		List<WebElement> lstElements = objectFactory.getWebElements(By.xpath("//table[@id='table_1']/tbody/tr/td[1]"));
		for (int i = 0; i < lstElements.size(); i++) {
			lst.add(lstElements.get(i).getText());
		}
		return lst;
	}

}
