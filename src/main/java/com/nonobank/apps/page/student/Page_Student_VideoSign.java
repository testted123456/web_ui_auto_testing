package com.nonobank.apps.page.student;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import com.nonobank.apps.page.base.BasePage;

public class Page_Student_VideoSign extends BasePage {

	public static Logger logger = LogManager.getLogger(Page_Student_VideoSign.class);

	public boolean is_submit_success() {
		if (objectFactory.isElementExists(By.xpath("//button[@id='sbt'and@onclick='showWeiXinQQ();']"))) {
			logger.info("submit successful and begin to record video...");
			return true;
		} else {
			return false;
		}
	}
}
