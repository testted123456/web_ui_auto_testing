package com.nonobank.apps.page.admin;

import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nonobank.apps.objectRepository.WebElementType;
import com.nonobank.apps.objectRepository.WebInput;
import com.nonobank.apps.objectRepository.WebSelect;
import com.nonobank.apps.page.base.BasePage;
import com.nonobank.apps.utils.page.PageUtils;

public class Page_Audit_Video_AuditView extends BasePage {

	public static Logger logger = LogManager.getLogger(Page_Audit_Video_AuditView.class);

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
			if (isElementExists(audit, WebElementType.WebInput, 15)) {
				break;
			} else {
				sleep(20000);
			}
		}
		WebInput input_audit = objectFactory.getWebInput(audit);
		input_audit.click();
	}
	//选择CA
	//CA01:初审无问题  CA02:可能存在问题，请终审严审
	public void select_CA(String CACode){
		logger.info("选择CA........");
		WebSelect select_CA=objectFactory.getWebSelect("初审通过选择框");
		select_CA.selectByExactValue(CACode);
	}
	//提交
	public void click_submit(){
		logger.info("提交.......");
		WebInput click_submit=objectFactory.getWebInput("提交");
		click_submit.click();
	}

	
}
