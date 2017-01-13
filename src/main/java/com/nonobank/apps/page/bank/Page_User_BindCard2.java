package com.nonobank.apps.page.bank;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.nonobank.apps.objectRepository.WebButton;
import com.nonobank.apps.objectRepository.WebInput;
import com.nonobank.apps.objectRepository.WebLink;
import com.nonobank.apps.page.base.BasePage;
import com.nonobank.apps.utils.page.PageUtils;

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
		PageUtils.sleep(3000);
		link_getValidate = objectFactory.getWebLink("getValidate");
		String text = link_getValidate.getText();
		if (!text.startsWith("重新获取")) {
			click_getValidate();
			logger.error("获取验证码失败.");
		}
		sleep(1000);
	}

	// 输入验证码
	public void input_validateCode(String validation) {
		WebInput input_validateCode = objectFactory.getWebInput("validNo");
		input_validateCode.input(validation);
	}

	// 提交
	public void submit() {
		WebButton button_submit = objectFactory.getWebButton("submit");
		button_submit.click();
	}

	public void close_dialog() {
		WebLink link_getValidate = objectFactory.getWebLink("dialog_close");
		link_getValidate.click();
	}

}
