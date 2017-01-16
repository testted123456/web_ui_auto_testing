package com.nonobank.apps.page.portal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.nonobank.apps.objectRepository.WebButton;
import com.nonobank.apps.objectRepository.WebInput;
import com.nonobank.apps.page.base.BasePage;
import com.nonobank.apps.utils.page.PageUtils;

public class Page_Login extends BasePage {

	public static Logger logger = LogManager.getLogger(Page_Login.class);

	/**
	 * 输入用户名
	 * 
	 * @param usernmae
	 *            用户名
	 * @param param
	 */
	public void input_username(String username) {
		logger.info("输入用户名......");
		WebInput input_username = objectFactory.getWebInput("loginname");
		input_username.clearAndInput(username);
	}

	/**
	 * 输入密码
	 * 
	 * @param password
	 *            密码
	 */
	public void input_password(String password) {
		logger.info("输入密码......");
		WebInput input_password = objectFactory.getWebInput("loginpwd");
		input_password.clearAndInput(password);
	}

	/**
	 * 输入验证码
	 */
	public void input_checkCode(String checkCode) {
		logger.info("输入安全码......");
		WebInput input_checkCode = objectFactory.getWebInput("checkCode");
		input_checkCode.clearAndInput(checkCode);
	}

	/**
	 * 提交
	 */
	public void submit() {
		logger.info("点击提交......");
		WebButton button = objectFactory.getWebButton("btnlogin");
		button.click();
		PageUtils.waitForPageLoad();
	}

}
