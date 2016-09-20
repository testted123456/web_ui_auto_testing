package com.nonobank.apps.page.portal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.nonobank.apps.objectRepository.WebButton;
import com.nonobank.apps.objectRepository.WebElementType;
import com.nonobank.apps.objectRepository.WebInput;
import com.nonobank.apps.objectRepository.WebLabel;
import com.nonobank.apps.objectRepository.WebLink;
import com.nonobank.apps.page.base.BasePage;
import com.nonobank.apps.utils.data.ActivityProlocutorCodeUtils;
import com.nonobank.apps.utils.data.UserInfoUtils;
import com.nonobank.apps.utils.page.PageUtils;

public class Page_Register extends BasePage {

	public static Logger logger = LogManager.getLogger(Page_Register.class);

	// 输入手机号
	public void input_mobile(String mobile) {
		if (mobile.equals("random_register")) {
			mobile = UserInfoUtils.getBindedCard("mobile_num");
		} else if (mobile.equals("random_unregister")) {
			mobile = UserInfoUtils.getUnregisterMobile();
		} else if (mobile.equals("random_unregister")) {
			mobile = UserInfoUtils.getUnregisterMobile();
		}
		WebInput input_mobile = objectFactory.getWebInput("mobile");
		input_mobile.clearAndInput(mobile);
	}

	// 输入用户名
	public void input_username(String user_name) {
		if (user_name.equals("random_register")) {
			user_name = UserInfoUtils.getBindedCard("user_name");
		}
		if (user_name.equals("random_unregister")) {
			WebInput input_mobile = objectFactory.getWebInput("mobile");
			user_name = input_mobile.getValue();
		}
		WebInput input_username = objectFactory.getWebInput("username");
		input_username.clearAndInput(user_name);
	}

	public void input_invite(String invite) {
		if (invite.equals("random_exist")) {
			invite = ActivityProlocutorCodeUtils.getProlocutorCode();
		}
		if (invite.equals("random_notexist")) {
			invite = ActivityProlocutorCodeUtils.genNotexistProlocutorCode();
		}
		WebInput input_invite = objectFactory.getWebInput("invite");
		input_invite.clearAndInput(invite);
	}

	// 输入错误的号码提示信息
	public boolean is_error_exist() {
		if (isElementExists("error", WebElementType.WebLabel, 15)) {
			WebLabel label_error = objectFactory.getWebLabel("error");
			String text = label_error.getText();
			if (text.equals("请输入有效的手机号码，以便找回密码")) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

	// 输入密码
	public void input_password(String password) {
		WebInput input_password = objectFactory.getWebInput("password");
		input_password.clearAndInput(password);
	}

	// 再次输入密码
	public void input_password2(String password2) {
		WebInput input_password = objectFactory.getWebInput("password2");
		input_password.clearAndInput(password2);
	}

	// 点击下一步
	public void click_next_step() {
		WebButton button_next_step = objectFactory.getWebButton("nextStep");
		button_next_step.click();
		PageUtils.waitForPageLoad();
		sleep(1500);

		for (int i = 0; i < 15; i++) {
			if (isElementExists("checkCode", WebElementType.WebInput, 15)) {
				break;
			} else if (isElementExists("nextStep", WebElementType.WebButton, 15)) {
				button_next_step = objectFactory.getWebButton("nextStep");
				button_next_step.click();
				PageUtils.waitForPageLoad();
			}
		}
	}

	// 输入安全码
	public void input_check_code(String check_code) {
		WebInput input_check_code = objectFactory.getWebInput("checkCode");
		input_check_code.clearAndInput(check_code);
		sleep(5000);
	}

	// 免费获取验证码
	public void click_sms_code() {
		WebLink link_sms_code = objectFactory.getWebLink("countdown");
		link_sms_code.click();

	}

	// 输入验证码
	public void input_sms_code(String sms_code) {
		WebInput input_sms_code = objectFactory.getWebInput("validation");
		sleep(3000);
		input_sms_code.clearAndInput(sms_code);
	}

	// 输入下一步
	public void click_reg_over_btn() {
		WebButton button = objectFactory.getWebButton("reg_over_btn");
		button.click();
		PageUtils.waitForPageLoad();
	}

}
