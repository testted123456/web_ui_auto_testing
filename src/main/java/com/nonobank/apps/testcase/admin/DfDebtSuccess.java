package com.nonobank.apps.testcase.admin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import com.nonobank.apps.business.admin.Biz_DfDebt;
import com.nonobank.apps.business.admin.Biz_Home;
import com.nonobank.apps.business.admin.Biz_Login;
import com.nonobank.apps.testcase.base.BaseCase;

public class DfDebtSuccess extends BaseCase {
	public static Logger logger = LogManager.getLogger(DfDebtSuccess.class);
	Biz_Login biz_Login;
	Biz_Home biz_Home;
	Biz_DfDebt biz_DfDebt;

	@Test(dataProvider = "dataSource")
	public void test(String username, String password, String search_username) {
		biz_Login.login(username, password);
		biz_Home.navigate_to_financePlanProfit();
		biz_DfDebt.searchDfdDebtList();
		biz_DfDebt.debt();
		biz_DfDebt.validate_num();
	}
}
