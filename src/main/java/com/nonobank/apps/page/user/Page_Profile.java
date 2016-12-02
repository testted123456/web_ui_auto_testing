package com.nonobank.apps.page.user;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.nonobank.apps.objectRepository.WebCommon;
import com.nonobank.apps.objectRepository.WebInput;
import com.nonobank.apps.page.account.Page_Account;
import com.nonobank.apps.page.base.BasePage;
import com.nonobank.apps.utils.file.ParseProperties;
import com.nonobank.apps.utils.page.PageUtils;

public class Page_Profile extends BasePage {
	public static Logger logger = LogManager.getLogger(Page_Account.class);

	/**
	 * 跳转到个人认证页面
	 */
	public void nagivate_to_profile() {
		String url = ParseProperties.getInstance().getProperty("url") + "/User/Profile";
		PageUtils.openPage(url);
		PageUtils.waitForPageLoad();
		logger.info(PageUtils.getUrl());
	}

	// 点击密码设置
	public void click_passwordSetting() {
		WebCommon li_passwordSetting = objectFactory.getWebCommon("passwordSetting");
		li_passwordSetting.click();
	}

	// 点击支付 密码设置
	public void click_paypasswordSetting() {
		logger.info("点击支付密码设置...");
		WebCommon li_payPasswordSetting = objectFactory.getWebCommon("payPasswordSetting");
		li_payPasswordSetting.click();
	}

	// 输入支付密码
	public void input_payPassword(String payPassword) {
		logger.info("输入支付密码设置...");
		WebInput input_payPayassword = objectFactory.getWebInput("newZFPwd");
		input_payPayassword.clearAndInput(payPassword);
	}

	// 输入支付密码
	public void input_payPassword_again(String payPassword) {
		logger.info("再次输入支付密码设置...");
		WebInput input_payPassword_again = objectFactory.getWebInput("newZFPassword");
		input_payPassword_again.clearAndInput(payPassword);
	}

	// 确认修改密码
	public void setPasswordbtn() {
		logger.info("点击保存...");
		WebInput setPasswordbtn = objectFactory.getWebInput("setPasswordbtn");
		setPasswordbtn.click();
	}
}
