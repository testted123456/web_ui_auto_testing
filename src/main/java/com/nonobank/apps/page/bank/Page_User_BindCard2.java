package com.nonobank.apps.page.bank;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.nonobank.apps.objectRepository.WebButton;
import com.nonobank.apps.objectRepository.WebInput;
import com.nonobank.apps.objectRepository.WebLink;
import com.nonobank.apps.page.base.BasePage;

/**
 * 添加银行卡号页面
 */
public class Page_User_BindCard2 extends BasePage {
	public static Logger logger = LogManager.getLogger(Page_User_BindCard2.class);

	// 输入银行卡号
	public void input_bankcard_no(String bankcard_no) {
		WebInput input_bankcard_no = objectFactory.getWebInput("bankCard_Input");
		input_bankcard_no.clearAndInput(bankcard_no);
	}

	// 点击获取验证码
	public void click_getValidate() {
		WebLink link_getValidate = objectFactory.getWebLink("getValidate");
		link_getValidate.click();
		link_getValidate = objectFactory.getWebLink("getValidate");
		String text = link_getValidate.getText();
		if (!text.startsWith("重新获取")) {
			logger.error("获取验证码失败.");
		}
		sleep(2000);
	}

	// 输入验证码
	public void input_validateCode() {
		WebInput input_validateCode = objectFactory.getWebInput("validNo");
		input_validateCode.input("8888");
	}

	// 提交
	public void submit() {
		WebButton button_submit = objectFactory.getWebButton("submit");
		button_submit.click();
	}

}
