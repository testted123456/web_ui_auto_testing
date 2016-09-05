package com.nonobank.apps.page.admin;

import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nonobank.apps.objectRepository.WebElementType;
import com.nonobank.apps.objectRepository.WebInput;
import com.nonobank.apps.page.base.BasePage;
import com.nonobank.apps.utils.page.PageUtils;

public class Page_Admin_Audit_Video_AuditView extends BasePage {

	public static Logger logger = LogManager.getLogger(Page_Admin_Audit_Video_AuditView.class);

	// 初审通过 初审回退 初审拒绝 初审取消
	public void audit(String audit) {
		Set<String> handles = driver.getWindowHandles();
		for (String handle : handles) {
			try {
				String title = driver.switchTo().window(handle).getTitle();
				if (!title.equals("后台管理系统")) {
					break;
				}
			} catch (Exception e) {
				if (e.getClass().getName().equals("org.openqa.selenium.TimeoutException")) {
					sleep(30000);
					driver.switchTo().window(handle);
					PageUtils.refreshPage();
				}
			}
		}

		for (int i = 0; i < 15; i++) {
			if (objectFactory.isElementExists(audit, WebElementType.WebInput)) {
				break;
			} else {
				sleep(20000);
			}
		}
		WebInput input_audit = objectFactory.getWebInput(audit);
		input_audit.click();
	}

	// 审核页面出现的alert框
	public String accept_alert() {
		if (objectFactory.isAlertExists(5000)) {
			String text = objectFactory.getAlertText();
			objectFactory.closeAlert();
			return text;
		} else {
			return null;
		}
	}

	// 关闭当前页面
	public void close_this_page() {
		String h = null;
		logger.info(driver.getCurrentUrl());
		driver.switchTo().window(h);
	}
}
