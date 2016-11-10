package com.nonobank.apps.page.admin;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import com.nonobank.apps.business.admin.Biz_Debt;
import com.nonobank.apps.objectRepository.WebInput;
import com.nonobank.apps.objectRepository.WebSelect;
import com.nonobank.apps.page.base.BasePage;
import com.nonobank.apps.utils.db.DBUtils;

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
		handle_debtMain("/td[4]/span");
	}

	public void click_debtMain() {
		logger.info("点击大债转......");
		handle_debtMain("/td[17]/span[1]/a");
	}

	public void click_debt() {
		logger.info("点击小债转......");
		List<WebElement> lstElements = null;
		do {
			lstElements = objectFactory.getWebElements("//table[@id='table_1']//table//tr/td[9]");
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} while (lstElements.size() == 0);

		for (int i = 0; i < lstElements.size(); i++) {
			if (lstElements.get(i).getText().equals("债转")) {
				System.out.println("****************************************************");
				WebElement web = objectFactory
						.getWebElement("//table[@id='table_1']//table//tr[" + (i + 2) + "]/td[1]//a");
				String text = web.getText();
				int endIndex = text.indexOf("】");
				Biz_Debt.bo_id = text.substring(1, endIndex);
				WebElement debt = objectFactory
						.getWebElement("//table[@id='table_1']//table//tr[" + (i + 2) + "]/td[9]/span");
				debt.click();
				return;
			}
		}

	}

	public void handle_debtMain(String xpath) {
		// 得到所有的[待收债权(个数)]
		List<WebElement> lstElements = objectFactory.getWebElements("//table[@id='table_1']/tbody/tr/td[13]/span");
		for (int i = 0; i < lstElements.size(); i++) {
			String string = lstElements.get(i).getText();
			int endIndex = string.indexOf("(");
			String text = string.substring(0, endIndex).replace(",", "");
			Biz_Debt.amount = Double.parseDouble(text);
			String debtCountString = string.substring(endIndex);
			// 判断[待收债权(个数)]符合条件
			if (!debtCountString.equals("(0/0)") && Biz_Debt.amount != 0) {
				WebElement webFromId = objectFactory
						.getWebElement("//table[@id='table_1']/tbody/tr[" + (i + 1) * 2 + "]/td[1]");
				Biz_Debt.from_id = webFromId.getText();
				if (xpath != null) {
					WebElement web = objectFactory
							.getWebElement("//table[@id='table_1']/tbody/tr[" + (i + 1) * 2 + "]" + xpath);
					web.click();
					return;
				} else {
					Biz_Debt.PartSuccessTargetFpid = DBUtils.getOneLineValues("nono",
							"SELECT title  FROM ( SELECT concat(fp.id,'：',fp.title)  title ,sum(fa.balance-fa.locking) amount FROM user_info ui LEFT JOIN finance_account fa on fa.user_id = ui.id LEFT JOIN  vip_account va on va.id = fa.owner_id LEFT JOIN  vip_autobidder vab on vab.va_id = va.id  LEFT JOIN finance_plan fp on fp.id = vab.fp_id WHERE  fa.role_id = 13 and date_sub(vab.deadline, INTERVAL 3 DAY) > date(now() ) and va.is_cash =0   and fa.balance-fa.locking >100 AND fp.title is NOT NULL GROUP BY fp.id) a WHERE  amount<"
									+ Biz_Debt.amount + "  ORDER BY amount DESC LIMIT  1");
					if (Biz_Debt.PartSuccessTargetFpid != null) {
						return;
					}
				}
			}
		}
	}

}
