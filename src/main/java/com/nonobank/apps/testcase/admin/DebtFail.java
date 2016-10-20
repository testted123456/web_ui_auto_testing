package com.nonobank.apps.testcase.admin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.nonobank.apps.business.admin.Biz_Debt;
import com.nonobank.apps.business.admin.Biz_Home;
import com.nonobank.apps.business.admin.Biz_Login;
import com.nonobank.apps.testcase.base.BaseCase;

public class DebtFail extends BaseCase {
	public static Logger logger = LogManager.getLogger(DebtFail.class);
	Biz_Login biz_Login = new Biz_Login();
	Biz_Home biz_Home = new Biz_Home();
	Biz_Debt biz_Debt = new Biz_Debt();
	public static final String STATUS = "99";

	@Test(dataProvider = "dataSource")
	public void test(String username, String password, String search_username, String targetFpid,String comments) {
		biz_Login.login(username, password);
		biz_Home.navigate_to_financePlanProfit();
		biz_Debt.debt("Fail", search_username, targetFpid);
		System.out.println("**************bo_id=" + Biz_Debt.bo_id + "**************from_id=" + Biz_Debt.from_id);

		// 校验lock_num=0
		boolean result_lockNum = biz_Debt.validate_lockNum(0.0, STATUS, "3");
		Assert.assertEquals(true, result_lockNum);
		// 校验invt_debt_sale_task_log记录=invt_proof记录
		boolean result_CountInvtDebtSaleTaskLog_CountInvtProof = biz_Debt
				.validate_countInvtDebtSaleTaskLog_countInvtProof(STATUS, "3");
		Assert.assertEquals(true, result_CountInvtDebtSaleTaskLog_CountInvtProof);
		// 校验price_principal=price-pay_amount
		boolean result_subPriceAndPayAmount_sumPricePrincipal = biz_Debt
				.validate_subPriceAndPayAmount_sumPricePrincipal();
		Assert.assertEquals(true, result_subPriceAndPayAmount_sumPricePrincipal);

		// 校验hold_num=transfer_num
		boolean result_holdNum_transferNum = biz_Debt.validate_holdNum_transferNum();
		Assert.assertEquals(true, result_holdNum_transferNum);

	}
}
