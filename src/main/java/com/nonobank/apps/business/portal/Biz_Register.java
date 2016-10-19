package com.nonobank.apps.business.portal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.nonobank.apps.objectRepository.WebElementType;
import com.nonobank.apps.page.portal.Page_Portal;
import com.nonobank.apps.page.portal.Page_Register;
import com.nonobank.apps.utils.data.ActivityProlocutorCodeUtils;

public class Biz_Register {

	public static Logger logger = LogManager.getLogger(Biz_Register.class);

	Page_Portal page_Portal = new Page_Portal();
	Page_Register page_Register = new Page_Register();

	// 注册操作
	public void register(String mobile, String user_name, String password, String password2, String check_code,
			String sms_code, String... strs) {
		navigate_to_register();
		logger.info("开始输入注册信息...");
		page_Register.input_mobile(mobile);
		page_Register.input_username(user_name);
		page_Register.input_password(password);
		page_Register.input_password2(password2);
		if (strs.length > 0) {
			if (strs[0].equals("random_exist")) {
				strs[0] = ActivityProlocutorCodeUtils.getProlocutorCode();
			} else if (strs[0].equals("random_notexist")) {
				strs[0] = ActivityProlocutorCodeUtils.genNotexistProlocutorCode();
			}
			page_Register.input_invite(strs[0]);
		}
		page_Register.click_next_step();
		page_Register.input_check_code(check_code);
		page_Register.click_sms_code();
		page_Register.input_sms_code(sms_code);
		page_Register.click_reg_over_btn();
	}

	// 跳转到注册页
	public void navigate_to_register() {
		logger.info("跳转到注册页面...");
		page_Portal.click_register();
	}

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
