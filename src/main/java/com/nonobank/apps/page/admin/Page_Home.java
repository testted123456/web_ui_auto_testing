package com.nonobank.apps.page.admin;

import java.sql.Connection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import com.nonobank.apps.objectRepository.WebCommon;
import com.nonobank.apps.objectRepository.WebInput;
import com.nonobank.apps.objectRepository.WebLink;
import com.nonobank.apps.page.base.BasePage;
import com.nonobank.apps.utils.db.DBUtils;
import com.nonobank.apps.utils.page.PageUtils;

public class Page_Home extends BasePage {

	public static Logger logger = LogManager.getLogger(Page_Home.class);

	public void switch_to_frameSet() {
		driver.switchTo().defaultContent();
		driver.switchTo().frame(driver.findElement(By.id("mainFrame")));
	}

	// 视频签约终审列表
	public void switch_to_lastAuditLists() {
		WebCommon div = objectFactory.getWebCommon("视频签约终审列表");
		div.click();
	}

	// 点击视频签约
	public void click_VideoAuditTsrial() {
		WebLink link_VideoAuditTsrial = objectFactory.getWebLink("视频签约");
		link_VideoAuditTsrial.click();
	}

	// 点击投资计划收益列表
	public void click_financePlanProfit() {
		switch_to_frameSet();
//		try {
//			Thread.sleep(60000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		WebLink link_financePlanProfit = objectFactory.getWebLink("financePlanProfit");
		link_financePlanProfit.click();
	}

	// 视频签约初审列表，输入手机号码
	public void input_mobile(String mobile_no) {
		WebInput input_mobile = objectFactory.getWebInput("手机号码");
		input_mobile.input(mobile_no);
	}

	// 视频签约初审列表，点击“查询”按钮
	public void click_query() {
		WebInput input_query = objectFactory.getWebInput("查询");
		input_query.click();
	}

	// 视频签约初审列表，点击“视频验证”按钮
	public void click_videoAduditView(String username, String audit) {
		Connection con = DBUtils.getNonoConnection();

		String sql = "SELECT id FROM user_info WHERE user_name='" + username + "'";

		String user_id = DBUtils.getOneObject(con, sql).toString();

		if (null == user_id) {
			logger.error("获取userid失败...");
			Assert.fail("获取userid失败...");
		}

		sql = "SELECT id FROM borrows WHERE user_id='" + user_id + "' ORDER BY id DESC LIMIT 1";

		String borrows_id = DBUtils.getOneObject(con, sql).toString();

		if (null == borrows_id) {
			logger.info("获取borrows id失败...");
			Assert.fail("获取borrows id失败...");
		}

		String path = "/Admin/Audit/VideoAuditView/" + user_id + "/" + borrows_id;

		if (audit.equals("初审通过")) {
			path = path + "/1";
		}

		String xpath = "//a[@href='" + path + "']";
		WebElement link_videoAuditView = objectFactory.getWebElement(By.xpath(xpath));
		link_videoAuditView.click();
		PageUtils.waitForPageLoad();
	}
}
