package com.nonobank.apps.business.portal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.nonobank.apps.objectRepository.WebElementType;
import com.nonobank.apps.page.portal.Page_Portal;
import com.nonobank.apps.page.portal.Page_Register;
import com.nonobank.apps.utils.data.Assertion;
import com.nonobank.apps.utils.data.RegisterResult;

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
	public void register(String mobile, String user_name, String password, String password2, RegisterResult result,
			String... invite) {
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
			page_Register.input_check_code(result.getCheckCode());
			page_Register.click_sms_code();
			page_Register.input_sms_code(result.getValidation());
			page_Register.click_reg_over_btn();
			handleResult(result);
		} catch (Error e) {
			handleResult(result);
		}
	}

	private void handleResult(RegisterResult result) {
		switch (result.getCode()) {
		case 1:
			boolean flag = page_Register.isElementDisplayed("join", WebElementType.WebButton, 15);
			Assertion.assertEquals(true, flag, Biz_Register.class, result.getComment());
			break;
		case 2:
		case 3:
			String moblieMessage = page_Register.getElementText("moblieMessage");
			Assertion.assertEquals(result.getMessage(), moblieMessage, Biz_Register.class, result.getComment());
			break;
		case 4:
		case 5:
		case 6:
			String usernameMessage = page_Register.getElementText("usernameMessage");
			Assertion.assertEquals(result.getMessage(), usernameMessage, Biz_Register.class, result.getComment());
			break;
		case 7:
			String passwordMessage = page_Register.getElementText("passwordMessage");
			Assertion.assertEquals(result.getMessage(), passwordMessage, Biz_Register.class, result.getComment());
			break;
		case 8:
			String password2Message = page_Register.getElementText("password2Message");
			Assertion.assertEquals(result.getMessage(), password2Message, Biz_Register.class, result.getComment());
			break;
		case 9:
			String checkCodeMessage = page_Register.getElementText("checkCodeMessage");
			Assertion.assertEquals(result.getMessage(), checkCodeMessage, Biz_Register.class, result.getComment());
			break;
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
