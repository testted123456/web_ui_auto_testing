package com.nonobank.apps.testcase.portal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import com.nonobank.apps.business.portal.Biz_Login;
import com.nonobank.apps.business.portal.Biz_Logout;
import com.nonobank.apps.testcase.base.BaseCase;
import com.nonobank.apps.utils.data.LoginResult;

public class LoginValidateLoginnameNull extends BaseCase {

	Biz_Login biz_Login;
	Biz_Logout biz_Logout;
	public static Logger logger = LogManager.getLogger(LoginValidateLoginnameNull.class);

	@Test(dataProvider = "dataSource")
	public void test(String loginName, String password) {
		logger.info("*******************************************start test*******************************************");
		biz_Login.login(loginName, password, LoginResult.LOGINNAME_NULL);
		biz_Logout.logout();
		logger.info("*******************************************end test*******************************************");
	}
}
