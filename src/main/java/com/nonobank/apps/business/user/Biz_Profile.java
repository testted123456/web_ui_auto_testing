package com.nonobank.apps.business.user;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.nonobank.apps.page.user.Page_Profile;

public class Biz_Profile {
	public static Logger logger = LogManager.getLogger(Biz_Profile.class);
	public Page_Profile page_Profile = new Page_Profile();

	// 设置支付密码
	public void setPayPassword(String payPassword, String payPassword1) {
		logger.info("设置支付密码...");
		page_Profile.click_passwordSetting();
		page_Profile.click_paypasswordSetting();
		page_Profile.input_payPassword(payPassword);
		page_Profile.input_payPassword_again(payPassword1);
		page_Profile.setPasswordbtn();
		page_Profile.sleep(3000);
		if (page_Profile.isAlertExists(10000)) {
			page_Profile.closeAlert();
			page_Profile.sleep(3000);
		}
	}
}
