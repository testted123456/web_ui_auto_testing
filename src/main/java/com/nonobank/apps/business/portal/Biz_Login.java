package com.nonobank.apps.business.portal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.nonobank.apps.page.portal.Page_Login;
import com.nonobank.apps.utils.data.Assertion;
import com.nonobank.apps.utils.file.ParseProperties;
import com.nonobank.apps.utils.page.PageUtils;
import com.nonobank.apps.utils.webintegration.Info;
import com.nonobank.apps.utils.webintegration.Params;
import com.nonobank.apps.utils.webintegration.Return;

@Info(name = "Biz_Login", desc = "登录页面", dependency = "", isDisabled = false)
public class Biz_Login {

	public static Logger logger = LogManager.getLogger(Biz_Login.class);

	Page_Login page_Login = new Page_Login();

	@Return(desc = "", type = "void")
	@Params(type = { "String", "String", "String", "String" }, desc = { "用户名/手机号/麦子通行证", "登录密码", "安全码",
			"预期结果" }, name = { "username", "password", "checkCode", "expectMessage" })
	@Info(name = "login", desc = "登录", dependency = "nagivate_to_login()", isDisabled = false)
	public void login(String username, String password, String checkCode, String expectMessage) {
		logger.info("登录...");
		page_Login.input_username(username);
		page_Login.input_password(password);
		page_Login.input_checkCode(checkCode);
		page_Login.submit();
		PageUtils.waitForPageLoad();
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
			String actualUrl = PageUtils.getUrl();
			
			String [] actualUrlArray = actualUrl.split("://");
			if(actualUrlArray.length == 2){
				actualUrl = actualUrlArray[1];
			}
			String expectUrl = ParseProperties.getInstance().getProperty("url") + "/Account";
			String [] expectUrlArray = expectUrl.split("://");
			if(expectUrlArray.length == 2){
				expectUrl = expectUrlArray[1];
			}
			Assertion.assertEquals(actualUrl, expectUrl, Biz_Login.class, "正例-登录成功");
			break;
		}
	}
}
