package com.nonobank.apps.testcase.portal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import com.nonobank.apps.business.portal.Biz_Login;
import com.nonobank.apps.testcase.base.BaseCase;

public class LoginValidateLoginpwdNull extends BaseCase {

	Biz_Login biz_Login;
	public static Logger logger = LogManager.getLogger(LoginValidateLoginpwdNull.class);

	@Test(dataProvider = "dataSource")
	public void test(String loginName, String password, String checkCode) {
		logger.info("*******************************************start test*******************************************");
		biz_Login.login(loginName, password, checkCode, "请输入您的登录密码！");
		logger.info("*******************************************end test*******************************************");
	}
}
