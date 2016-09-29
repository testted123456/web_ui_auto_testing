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

		// 校验lock_num=0
		boolean result_lockNum = biz_Debt.validate_lockNum();
		Assert.assertEquals(true, result_lockNum);

		// 校验residue_num字段
		boolean result_residueNum = biz_Debt.validate_lockNum();
		Assert.assertEquals(true, result_residueNum);

		// 校验sum(trans_amout+pay_amount)=price
		boolean result_price_sumTransAmountAndPayAmount = biz_Debt.validate_price_sumTransAmountAndPayAmount();
		Assert.assertEquals(true, result_price_sumTransAmountAndPayAmount);

		// 校验trans_amount=sum(amount)
		boolean result_transAmount_sumAmount = biz_Debt.validate_transAmount_sumAmount();
		Assert.assertEquals(true, result_transAmount_sumAmount);

		// 校验transfer_num=sum(buy_num)
		boolean result_transferNum_sumBuyNum = biz_Debt.validate_transferNum_sumBuyNum();
		Assert.assertEquals(true, result_transferNum_sumBuyNum);

		// 校验invt_debt_sale_task_log记录=invt_proof
		boolean result_invtDebtSaleTaskLogCount_invtProofCount = biz_Debt
				.validate_invtDebtSaleTaskLogCount_invtProofCount();
		Assert.assertEquals(true, result_invtDebtSaleTaskLogCount_invtProofCount);

		// 校验sum(price_in)=trans_amount
		boolean result_sumPriceIn_transAmount = biz_Debt.validate_sumPriceIn_transAmount();
		Assert.assertEquals(true, result_sumPriceIn_transAmount);

		// 校验sum(buy_num)=transfer_num
		boolean result_sumBuyNum_transferNum = biz_Debt.validate_sumBuyNum_transferNum();
		Assert.assertEquals(true, result_sumBuyNum_transferNum);

	}
}
