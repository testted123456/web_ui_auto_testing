package com.nonobank.apps.page.portal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.nonobank.apps.objectRepository.WebButton;
import com.nonobank.apps.objectRepository.WebElementType;
import com.nonobank.apps.objectRepository.WebInput;
import com.nonobank.apps.objectRepository.WebLink;
import com.nonobank.apps.page.base.BasePage;
import com.nonobank.apps.utils.page.PageUtils;

public class Page_Register extends BasePage {

	public static Logger logger = LogManager.getLogger(Page_Register.class);

	/**
	 * 输入手机号
	 * 
	 * @param mobile
	 *            手机号码
	 */
	public void input_mobile(String mobile) {
		logger.info("输入手机号......");
		WebInput input_mobile = objectFactory.getWebInput("mobile");
		input_mobile.clearAndInput(mobile);
	}

	/**
	 * 输入用户名
	 * 
	 * @param user_name
	 *            用户名
	 */
	public void input_username(String user_name) {
		logger.info("输入用户名......");
		WebInput input_username = objectFactory.getWebInput("username");
		input_username.clearAndInput(user_name);
	}

	/**
	 * 
	 * @param invite
	 */
	public void input_invite(String invite) {
		logger.info("输入邀请码......");
		WebInput input_invite = objectFactory.getWebInput("invite");
		input_invite.clearAndInput(invite);
	}

	/**
	 * 输入密码
	 * 
	 * @param password
	 *            密码
	 */
	public void input_password(String password) {
		logger.info("输入密码......");
		WebInput input_password = objectFactory.getWebInput("password");
		input_password.clearAndInput(password);
	}

	/**
	 * 再次输入密码
	 * 
	 * @param password2
	 *            密码
	 */
	public void input_password2(String password2) {
		logger.info("再次输入密码......");
		WebInput input_password = objectFactory.getWebInput("password2");
		input_password.clearAndInput(password2);
	}

	/**
	 * 点击下一步
	 */
	public void click_next_step() {
		logger.info("点击下一步......");
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

	/**
	 * 输入安全码
	 */
	public void input_check_code(String checkCode) {
		logger.info("输入安全码......");
		WebInput input_check_code = objectFactory.getWebInput("checkCode");
		input_check_code.clearAndInput(checkCode);
		sleep(5000);
	}

	/**
	 * 获取验证码
	 */
	public void click_sms_code() {
		logger.info("点击获取验证码......");
		WebLink link_sms_code = objectFactory.getWebLink("countdown");
		link_sms_code.click();

	}

	/**
	 * 输入验证码
	 */
	public void input_sms_code(String smsCode) {
		try {
			WebInput input_sms_code = objectFactory.getWebInput("validation");
			logger.info("输入验证码......");
			input_sms_code.click();
			sleep(3000);
			input_sms_code.clearAndInput(smsCode);
		} catch (Error e) {
		}
	}

	/**
	 * 输入下一步
	 */
	public void click_reg_over_btn() {
		logger.info("点击下一步......");
		WebButton button = objectFactory.getWebButton("reg_over_btn");
		button.click();
		PageUtils.waitForPageLoad();
	}

}
