package com.nonobank.apps.testcase.admin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
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
	public void test(String username, String password, String search_username) {
		// biz_Login.login(username, password);
		// biz_Home.navigate_to_financePlanProfit();
		// biz_Debt.debt(search_username);

		// 比较数据库字段lock_num字段
		boolean result_lockNum = biz_Debt.validate_lockNum();
		Assert.assertEquals(true, result_lockNum);

		// 比较数据库字段residue_num字段
		boolean result_residueNum = biz_Debt.validate_lockNum();
		Assert.assertEquals(true, result_residueNum);

		//比较trans_amout+pay_amount=price
		boolean result_priceAndAmount = biz_Debt.validate_priceAndAmount();
		Assert.assertEquals(true, result_priceAndAmount);
	}
}
