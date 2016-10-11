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
	public static final String STATUS = "5";

	@Test(dataProvider = "dataSource")
	public void test(String username, String password, String search_username) {
		// biz_Login.login(username, password);
		// biz_Home.navigate_to_financePlanProfit();
		// biz_Debt.debt(search_username);
		System.out.println("**************bo_id=" + Biz_Debt.bo_id);

		// 校验lock_num=0
		boolean result_lockNum = biz_Debt.validate_lockNum(0, STATUS);
		Assert.assertEquals(true, result_lockNum);

		// 校验residue_num字段
		boolean result_residueNum = biz_Debt.validate_residueNum();
		Assert.assertEquals(true, result_residueNum);

		// 校验sum(trans_amout+pay_amount)=price
		boolean result_price_sumTransAmountAndPayAmount = biz_Debt.validate_price_sumTransAmountAndPayAmount();
		Assert.assertEquals(true, result_price_sumTransAmountAndPayAmount);

		// 校验trans_amount=sum(amount)
		boolean result_sumAmount_transAmount = biz_Debt.validate_sumAmount_transAmount();
		Assert.assertEquals(true, result_sumAmount_transAmount);

		// 校验transfer_num=sum(buy_num)
		boolean result_sumBuyNum_transferNum = biz_Debt.validate_sumBuyNum_transferNum();
		Assert.assertEquals(true, result_sumBuyNum_transferNum);

		// 校验invt_debt_sale_task_log记录=invt_proof记录
		boolean result_invtDebtSaleTaskLogCount_invtProofCount = biz_Debt
				.validate_invtDebtSaleTaskLogCount_invtProofCount(STATUS, "2");
		Assert.assertEquals(true, result_invtDebtSaleTaskLogCount_invtProofCount);

		// 校验sum(price_in)=trans_amount
		boolean result_sumPriceIn_transAmount = biz_Debt.validate_sumPriceIn_transAmount();
		Assert.assertEquals(true, result_sumPriceIn_transAmount);

		// 校验sum(buy_num)=transfer_num
		boolean result_sumBuyNum_transferNum2 = biz_Debt.validate_sumBuyNum_transferNum2();
		Assert.assertEquals(true, result_sumBuyNum_transferNum2);

		// 校验debt_buy_log记录=invt_trd_order记录
		boolean result_debtBuyLogCount_invtTrdOrderCount = biz_Debt.validate_debtBuyLogCount_invtTrdOrderCount();
		Assert.assertEquals(true, result_debtBuyLogCount_invtTrdOrderCount);

		// 校验debt_buy_log记录=invt_proof记录
		boolean result_debtBuyLogCount_invtProofCount = biz_Debt.validate_debtBuyLogCount_invtProofCount();
		Assert.assertEquals(true, result_debtBuyLogCount_invtProofCount);

		// 校验amount
		boolean result_amount = biz_Debt.validate_amount();
		Assert.assertEquals(true, result_amount);

		// 校验hold_num=0
		boolean result_holdNum = biz_Debt.validate_holdNum();
		Assert.assertEquals(true, result_holdNum);

		// 校验sum(price_principal)=sum(price_interest)=sum(price)=0
		boolean result_sumPrice_sumPriceInterest_sumPricePrincipal = biz_Debt
				.validate_sumPrice_sumPriceInterest_sumPricePrincipal();
		Assert.assertEquals(true, result_sumPrice_sumPriceInterest_sumPricePrincipal);

	}
}
