package com.nonobank.apps.testcase.recharge;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import com.nonobank.apps.business.portal.Biz_Login;
import com.nonobank.apps.business.portal.Biz_Portal;
import com.nonobank.apps.business.recharge.Biz_User_Recharge;
import com.nonobank.apps.business.recharge.Biz_User_RechargeConfirm;
import com.nonobank.apps.testcase.base.BaseCase;

public class RechargeCheck extends BaseCase {

	public static Logger logger = LogManager.getLogger(RechargeCheck.class);

	@Test(dataProvider = "dataSource")
	public void test(String mobile, String password, String cardno, String pay_password) {
		logger.info("begin to test...");

//		Biz_Login biz_Login = new Biz_Login();
//		Biz_Portal biz_Portal = new Biz_Portal();
//		Biz_User_Recharge biz_User_Recharge1 = new Biz_User_Recharge();
//		Biz_User_RechargeConfirm biz_User_RechargeConfirm = new Biz_User_RechargeConfirm();
//		biz_Login.login(mobile, password, "mobile_num");
//		biz_Portal.close_dialog();
//		biz_Portal.navigate_to_myaccount();
//		biz_User_Recharge1.recharge(cardno, mobile);
//		biz_User_RechargeConfirm.checkMoney();
//		biz_User_RechargeConfirm.checkPayPassword(pay_password);
	}
}
