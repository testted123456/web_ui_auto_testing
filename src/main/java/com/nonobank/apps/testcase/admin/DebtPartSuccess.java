package com.nonobank.apps.testcase.admin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.nonobank.apps.business.admin.Biz_Debt;
import com.nonobank.apps.business.admin.Biz_Home;
import com.nonobank.apps.business.admin.Biz_Login;
import com.nonobank.apps.testcase.base.BaseCase;

public class DebtPartSuccess extends BaseCase {
	public static Logger logger = LogManager.getLogger(DebtPartSuccess.class);
	Biz_Login biz_Login = new Biz_Login();
	Biz_Home biz_Home = new Biz_Home();
	Biz_Debt biz_Debt = new Biz_Debt();
	public static final String STATUS = "6";

	@Test(dataProvider = "dataSource")
	public void test(String username, String password, String search_username, String targetFpid) {
		biz_Login.login(username, password);
		biz_Home.navigate_to_financePlanProfit();
		biz_Debt.debt(search_username, targetFpid, "PartSuccess");
		System.out.println("**************bo_id=" + Biz_Debt.bo_id + "**************from_id=" + Biz_Debt.from_id);

		// 校验lock_num=0
		boolean result_lockNum = biz_Debt.validate_lockNum(0, STATUS);
		Assert.assertEquals(true, result_lockNum);

		// 校验residue_num=Transfer_num-sum(buy_num)
		biz_Debt.validate_residueNum_subTransferNumSumBuyNum();
		
		
	}
}
