package com.nonobank.apps.page.admin;

import java.sql.Connection;
import java.util.Set;

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
	
	// 点击投资计划收益列表
	public void click_financePlanProfit() {
		switch_to_frameSet();
		WebLink link_financePlanProfit = objectFactory.getWebLink("financePlanProfit");
		link_financePlanProfit.click();
	}
	
	// 点击视频签约
	public void click_VideoAuditTsrial() {
		WebLink link_VideoAuditTsrial = objectFactory.getWebLink("视频签约");
		link_VideoAuditTsrial.click();
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
	//点击视频签约终审列表
	public void click_videoLastCheckList(){
		driver.switchTo().defaultContent();
		driver.switchTo().frame("mainFrame");
		WebCommon click_videoLastCheckList=objectFactory.getWebCommon("视频签约终审列表");
		click_videoLastCheckList.click();
		driver.switchTo().defaultContent();
	}
	//点击计划任务
	public void click_taskScheduler(){
		logger.info("点击计划任务。。。。。。。。");
		WebLink click_taskScheduler=objectFactory.getWebLink("计划任务");
		click_taskScheduler.click();
	}
	//点击名校贷非V3自动匹配
	public void click_V3(){
		logger.info("点击名校贷非V3自动匹配。。。。。。。。");
		WebInput click_V3=objectFactory.getWebInput("名校贷非v3自动匹配");
		click_V3.click();
	}
	//切换到后台管理系统页面
	public void switch_adminHome(){
		//将页面上所有的windowshandle放在入set集合当中
		Set<String> handles = driver.getWindowHandles();
		for (String handle : handles) {
			try {
				String handleId=driver.switchTo().window("后台管理系统").getWindowHandle();
				String title = driver.switchTo().window(handle).getTitle();
				if (!title.equals("后台管理系统")) {
					driver.switchTo().window(handle).close();
					PageUtils.sleep(2000);
					driver.switchTo().window(handleId);
					break;
				}
			} catch (Exception e) {
				if (e.getClass().getName().equals("org.openqa.selenium.TimeoutException")) {
					sleep(30000);
					driver.switchTo().window(handle);
//					PageUtils.refreshPage();
				}
			}
		}
	}
	//点击菜单tab回到主菜单页面
	public void click_menu(){
		logger.info("点击菜单tab回到主菜单页面。。。。。。。。");
		WebLink click_menu=objectFactory.getWebLink("菜单");
		click_menu.click();
	}
	//
	
	
	
}
