package com.nonobank.apps.business.portal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.nonobank.apps.objectRepository.WebElementType;
import com.nonobank.apps.page.portal.Page_Portal;
import com.nonobank.apps.page.portal.Page_Register;
import com.nonobank.apps.utils.data.Assertion;

public class Biz_Register {

	public static Logger logger = LogManager.getLogger(Biz_Register.class);

	Page_Portal page_Portal = new Page_Portal();
	Page_Register page_Register = new Page_Register();

	/**
	 * 注册操作
	 * 
	 * @param mobile
	 *            手机号
	 * @param user_name
	 *            用户名
	 * @param password
	 *            初次输入密码
	 * @param password2
	 *            再次输入密码
	 * @param success
	 */
	public void register(String mobile, String user_name, String password, String password2, String checkCode,
			String validation, String checkPoint, String expectMessage, String... invite) {
		try {
			navigate_to_register();
			logger.info("开始输入注册信息...");
			page_Register.input_mobile(mobile);
			page_Register.input_username(user_name);
			page_Register.input_password(password);
			page_Register.input_password2(password2);
			if (invite.length > 0) {
				page_Register.input_invite(invite[0]);
			}
			page_Register.click_next_step();
			page_Register.input_check_code(checkCode);
			page_Register.click_sms_code();
			page_Register.input_sms_code(validation);
			page_Register.click_reg_over_btn();
			handleResult(checkPoint, expectMessage);
		} catch (Error e) {
			handleResult(checkPoint, expectMessage);
		}
	}

	private void handleResult(String checkPoint, String expectMessage) {
		switch (checkPoint) {
		case "success":
			boolean flag = page_Register.isElementDisplayed("join", WebElementType.WebButton, 15);
			Assertion.assertEquals(true, flag, Biz_Register.class, "register success");
			break;
		case "moblieError":
		case "moblieExist":
			String moblieMessage = page_Register.getElementText("moblieMessage");
			Assertion.assertEquals(expectMessage, moblieMessage, Biz_Register.class, checkPoint);
			break;
		case "usernameError":
		case "usernameLength":
		case "usernameExist":
			String usernameMessage = page_Register.getElementText("usernameMessage");
			Assertion.assertEquals(expectMessage, usernameMessage, Biz_Register.class, checkPoint);
			break;
		case "passwordError":
			String passwordMessage = page_Register.getElementText("passwordMessage");
			Assertion.assertEquals(expectMessage, passwordMessage, Biz_Register.class, checkPoint);
			break;
		case "password2Error":
			String password2Message = page_Register.getElementText("password2Message");
			Assertion.assertEquals(expectMessage, password2Message, Biz_Register.class, checkPoint);
			break;
		case "checkCodeNull":
			String checkCodeMessage = page_Register.getElementText("checkCodeMessage");
			Assertion.assertEquals(expectMessage, checkCodeMessage, Biz_Register.class, checkPoint);
			break;
		case "checkCodeError":
			checkCodeMessage = page_Register.getAlertText();
			Assertion.assertEquals(expectMessage, checkCodeMessage, Biz_Register.class, checkPoint);
		default:
			break;
		}
	}

	/**
	 * 跳转到注册页面
	 */
	public void navigate_to_register() {
		logger.info("跳转到注册页面...");
		page_Portal.click_register();
	}

}
