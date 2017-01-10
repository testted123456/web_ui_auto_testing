package com.nonobank.apps.business.portal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.nonobank.apps.page.portal.Page_Register;
import com.nonobank.apps.utils.data.Assertion;
import com.nonobank.apps.utils.page.PageUtils;
import com.nonobank.apps.utils.webintegration.Info;
import com.nonobank.apps.utils.webintegration.Params;
import com.nonobank.apps.utils.webintegration.Return;

public class Biz_Register {

	public static Logger logger = LogManager.getLogger(Biz_Register.class);

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
	@Return(desc = "", type = "void")
	@Params(type = { "String", "String", "String", "String", "String", "String", "String", "String" }, desc = { "手机号",
			"用户名", "密码", "确认密码", "邀请码", "安全码", "验证码", "预期结果" }, name = { "mobile", "user_name", "password", "password2",
					"checkCode", "validation", "expectMessage", "expectMessage" })
	@Info(name = "login", desc = "登录", dependency = "nagivate_to_login()", isDisabled = false)
	public void register(String mobile, String user_name, String password, String password2, String checkCode,
			String validation, String expectMessage, String... invite) {
		try {
			logger.info("开始输入注册信息...");
			page_Register.input_mobile(mobile);
			page_Register.input_username(user_name);
			page_Register.input_password(password);
			page_Register.input_password2(password2);
			if (invite.length > 0) {
				page_Register.input_invite(invite[0]);
			}
			page_Register.click_next_step();
			PageUtils.waitForPageLoad();
			page_Register.input_check_code(checkCode);
			page_Register.click_sms_code();
			PageUtils.waitForPageLoad();
			page_Register.input_sms_code(validation);
			page_Register.click_reg_over_btn();
			PageUtils.waitForPageLoad();
			handleResult(expectMessage);
		} catch (Error e) {
			handleResult(expectMessage);
		}
	}

	private void handleResult(String expectMessage) {
		switch (expectMessage) {
		case "success":
			String successTitle = page_Register.getElementText("successTitle");
			Assertion.assertEquals(expectMessage, successTitle, Biz_Register.class, "正例-注册成功");
			break;
		case "安全码输入错误":
			String checkCodeMessage = page_Register.getElementText("countdown");
			Assertion.assertEquals(expectMessage, checkCodeMessage, Biz_Register.class, "反例-验证安全码错误");
			break;
		case "验证码输入错误！":
			if (page_Register.isAlertExists(3000)) {
				String validation = page_Register.getAlertText();
				Assertion.assertEquals(expectMessage, validation, Biz_Register.class, "反例-验证短信验证码");
			}
			break;
		default:
			String error_msg = page_Register.getElementText("error");
			Assertion.assertEquals(expectMessage, error_msg, Biz_Register.class, "反例-验证注册失败");
			break;
		}
	}
}
