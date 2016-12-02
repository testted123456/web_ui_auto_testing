package com.nonobank.apps.business.user;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.nonobank.apps.page.user.Page_Profile;
import com.nonobank.apps.utils.webintegration.Info;

public class Biz_Profile {
	public static Logger logger = LogManager.getLogger(Biz_Profile.class);
	public Page_Profile page_Profile = new Page_Profile();

	@Info(name = "nagivate_to_login", desc = "跳转到登录页面", dependency = "", isDisabled = false)
	public void nagivate_to_profile() {
		logger.info("跳转到个人设置页面............");
		page_Profile.nagivate_to_profile();
	}

	// 设置支付密码
	public void setPayPassword(String payPassword, String payPassword1) {
		logger.info("设置支付密码...");
		nagivate_to_profile();
		page_Profile.click_passwordSetting();
		page_Profile.click_paypasswordSetting();
		page_Profile.input_payPassword(payPassword);
		page_Profile.input_payPassword_again(payPassword1);
		page_Profile.setPasswordbtn();
		page_Profile.closeAlert();
	}
}
