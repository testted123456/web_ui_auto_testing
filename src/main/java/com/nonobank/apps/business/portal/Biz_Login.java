package com.nonobank.apps.business.portal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.nonobank.apps.objectRepository.WebElementType;
import com.nonobank.apps.page.portal.Page_Login;
import com.nonobank.apps.utils.data.Assertion;
import com.nonobank.apps.utils.webintegration.Info;
import com.nonobank.apps.utils.webintegration.Params;
import com.nonobank.apps.utils.webintegration.Return;

@Info(dependency = "", desc = "", isDisabled = false)
public class Biz_Login {

	public static Logger logger = LogManager.getLogger(Biz_Login.class);

	Page_Login page_Login = new Page_Login();

	/**
	 * nagivate_to_login 跳转到登录页面
	 */
	public void nagivate_to_login() {
		logger.info("跳转到登录页面...");
		page_Login.nagivate_to_login();
	}

	public void login(String username, String password) {
		page_Login.input_username(username);
		page_Login.input_password(password);
		page_Login.submit();
	}

	/**
	 * 
	 * login 登录
	 * 
	 * @param username
	 *            用户名
	 * @param password
	 *            密码
	 * @param param
	 *            参数
	 */
	@Info(dependency = "nagivate_to_login", desc = "跳转到登录界面", isDisabled = false)
	@Return(desc = "", type = "void")
	@Params(type = { "String", "String", "String", "String" }, desc = { "用户名", "密码", "安全码", "预期结果" }, name = {
			"username", "password", "checkCode", "expectMessage" })
	public void login(String username, String password, String checkCode, String expectMessage) {
		logger.info("登录...");
		page_Login.input_username(username);
		page_Login.input_password(password);
		page_Login.input_checkCode(checkCode);
		page_Login.submit();
		switch (expectMessage) {
		case "请输入您的用户名或手机号！":
		case "登录用户名不存在":
		case "请输入您的登录密码！":
		case "请输入安全码！":
		case "验证码错误":
			String actualMessage = page_Login.getElementText("tips_normal");
			Assertion.assertEquals(expectMessage, actualMessage, Biz_Login.class, "反例-登录失败");
			break;
		default:
			boolean flag = page_Login.isElementDisplayed("account_name", WebElementType.WebLink, 15);
			Assertion.assertEquals(true, flag, Biz_Login.class, "反例-登录成功");
			break;
		}
	}
}
