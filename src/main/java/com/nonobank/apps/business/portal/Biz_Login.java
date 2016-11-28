package com.nonobank.apps.business.portal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.nonobank.apps.objectRepository.WebElementType;
import com.nonobank.apps.page.portal.Page_Login;
import com.nonobank.apps.utils.data.Assertion;
import com.nonobank.apps.utils.webintegration.Info;
import com.nonobank.apps.utils.webintegration.Params;


@Info(name="Biz_Login",desc="登录页面",dependency="",isDisabled=false)
public class Biz_Login {

	public static Logger logger = LogManager.getLogger(Biz_Login.class);

	Page_Login page_Login = new Page_Login();

	/**
	 * nagivate_to_login 跳转到登录页面
	 */

	@Info(name="nagivate_to_login",desc="跳转到登录页面",dependency="",isDisabled=false)
	public void nagivate_to_login() {
		logger.info("跳转到登录页面...");
		page_Login.nagivate_to_login();
	}
	
	public void login(String username, String password){
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

	@Info(name="login",desc="登录",dependency="nagivate_to_login()",isDisabled=false)
	@Params(type={"String","String","LoginResult"},name={"username","password","loginResult"},desc={"用户名","密码","登录结果"})
	public void login(String username, String password, String checkCode, String checkPoint, String expectMessage) {

		nagivate_to_login();
		logger.info("登录...");
		page_Login.input_username(username);
		page_Login.input_password(password);
		page_Login.input_checkCode(checkCode);
		page_Login.submit();
		switch (checkPoint) {
		case "success":
			boolean flag = page_Login.isElementDisplayed("account_name", WebElementType.WebLink, 15);
			Assertion.assertEquals(true, flag, Biz_Login.class, "login success");
			break;
		case "loginnameNull":
		case "loginnameError":
		case "loginpwdNull":
		case "checkCodeNull":
		case "checkCodeError":
			String actualMessage = page_Login.getElementText("tips_normal");
			Assertion.assertEquals(expectMessage, actualMessage, Biz_Login.class, checkPoint);
			break;
		}
	}
}
