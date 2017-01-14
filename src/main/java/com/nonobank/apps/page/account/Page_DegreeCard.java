package com.nonobank.apps.page.account;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.nonobank.apps.objectRepository.WebInput;
import com.nonobank.apps.page.base.BasePage;

public class Page_DegreeCard extends BasePage {
	public static Logger logger = LogManager.getLogger(Page_DegreeCard.class);

	// 输入真实姓名
	public void input_name(String myname) {
		logger.info("输入真实姓名....");
		WebInput input_myname = objectFactory.getWebInput("myname");
		input_myname.clearAndInput(myname);
	}

	// 输入身份证号
	public void input_mycard(String mycard) {
		logger.info("输入身份证号....");
		WebInput input_mycard = objectFactory.getWebInput("mycard");
		input_mycard.clearAndInput(mycard);
	}

	// 认证通过
	public void submit() {
		logger.info("点击提交....");
		WebInput submit = objectFactory.getWebInput("ok");
		submit.click();
	}

}
