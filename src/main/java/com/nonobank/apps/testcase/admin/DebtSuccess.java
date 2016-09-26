package com.nonobank.apps.testcase.admin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import com.nonobank.apps.business.admin.Biz_Debt;
import com.nonobank.apps.business.admin.Biz_Home;
import com.nonobank.apps.business.admin.Biz_Login;
import com.nonobank.apps.testcase.base.BaseCase;

public class DebtSuccess extends BaseCase {
	public static Logger logger = LogManager.getLogger(DebtSuccess.class);
	Biz_Login biz_Login = new Biz_Login();
	Biz_Home biz_Home = new Biz_Home();
	Biz_Debt biz_Debt = new Biz_Debt();

	@Test(dataProvider = "dataSource")
	public void test(String username, String password, String cardno, String pay_password) {
		biz_Login.login(username, pay_password);
		biz_Home.navigate_to_financePlanProfit();
		biz_Debt.debt("a219855", "3036：精选计划3036期", "3033：精选计划3033期", "a219852:金额1000");
	}
}
