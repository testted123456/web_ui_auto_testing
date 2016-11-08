package com.nonobank.apps.business.portal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.nonobank.apps.objectRepository.WebElementType;
import com.nonobank.apps.page.portal.Page_Portal;
import com.nonobank.apps.page.portal.Page_Register;

public class Biz_Register {

	public static Logger logger = LogManager.getLogger(Biz_Register.class);

	Page_Portal page_Portal = new Page_Portal();
	Page_Register page_Register = new Page_Register();


	/**
	 * 注册操作
	 * @param mobile 手机号
	 * @param user_name 用户名
	 * @param password 初次输入密码
	 * @param password2 再次输入密码
	 * @param strs
	 */
	public void register(String mobile, String user_name, String password, String password2, String... strs) {
		navigate_to_register();
		logger.info("开始输入注册信息...");
		page_Register.input_mobile(mobile);
		page_Register.input_username(user_name);
		page_Register.input_password(password);
		page_Register.input_password2(password2);
		if (strs.length > 0) {
			page_Register.input_invite(strs[0]);
		}
		page_Register.click_next_step();
		page_Register.is_password_not_consistent();
		page_Register.input_check_code();
		page_Register.click_sms_code();
		page_Register.input_sms_code();
		page_Register.click_reg_over_btn();
	}
	
	/**
	 * 注册两次输入密码不一致
	 * @param mobile 手机号
	 * @param user_name 用户名
	 * @param password 初次输入密码
	 * @param password2 再次输入密码
	 * @param strs
	 */
	public void register_pwd_unconsistent(String mobile, String user_name, String password, String password2, String... strs) {
		navigate_to_register();
		logger.info("开始输入注册信息...");
		page_Register.input_mobile(mobile);
		page_Register.input_username(user_name);
		page_Register.input_password(password);
		page_Register.input_password2(password2);
		if (strs.length > 0) {
			page_Register.input_invite(strs[0]);
		}
		page_Register.click_next_step();
		page_Register.is_password_not_consistent();
	}
	
	/**
	 * 注册用户名非法格式
	 * @param mobile 手机号
	 * @param user_name 用户名
	 * @param password 初次输入密码
	 * @param password2 再次输入密码
	 * @param strs
	 */
	public void register_username_format(String mobile, String user_name, String password, String password2, String... strs) {
		navigate_to_register();
		logger.info("开始输入注册信息...");
		page_Register.input_mobile(mobile);
		page_Register.input_username(user_name);
		page_Register.input_password(password);
		page_Register.input_password2(password2);
		if (strs.length > 0) {
			page_Register.input_invite(strs[0]);
		}
		page_Register.click_next_step();
		page_Register.is_username_not_format();
	}

	/**
	 * 跳转到注册页面
	 */
	public void navigate_to_register() {
		logger.info("跳转到注册页面...");
		page_Portal.click_register();
	}

	/**
	 * 注册成功或者失败
	 * @return true:注册成功 false:注册失败
	 */
	public boolean isRegisterSuccess() {
		boolean flag = page_Register.isElementDisplayed("join", WebElementType.WebButton, 15);

		if (flag == true) {
			logger.info("注册成功...");
		} else {
			logger.error("注册失败...");
		}
		return flag;
	}

}
