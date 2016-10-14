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
		// biz_Login.login(username, password);
		// biz_Home.navigate_to_financePlanProfit();
		// biz_Debt.debt(search_username, targetFpid, "PartSuccess");
		System.out.println("**************bo_id=" + Biz_Debt.bo_id + "**************from_id=" + Biz_Debt.from_id);

		// 校验lock_num=0
		boolean result_lockNum = biz_Debt.validate_lockNum(0, STATUS, "3");
		System.out.println("************************result_lockNum=" + result_lockNum);
		Assert.assertEquals(true, result_lockNum);

		// 校验residue_num=Transfer_num-sum(buy_num)
		boolean result_residueNum_subTransferNumSumBuyNum = biz_Debt.validate_residueNum_subTransferNumSumBuyNum();
		System.out.println("************************result_residueNum_subTransferNumSumBuyNum="
				+ result_residueNum_subTransferNumSumBuyNum);
		Assert.assertEquals(true, result_residueNum_subTransferNumSumBuyNum);

		// 校验amount值
		boolean result_amount2 = biz_Debt.validate_amount2();
		System.out.println("************************result_amount2=" + result_amount2);
		Assert.assertEquals(true, result_amount2);

		/**
		 * // 校验sum(amount)=trans_amount boolean result_sumAmount_transAmount =
		 * biz_Debt.validate_sumAmount_transAmount(); Assert.assertEquals(true,
		 * result_sumAmount_transAmount);
		 **/

		// 校验sum(amount)=trans_amount
		boolean result_CountInvtDebtSaleTaskLog_CountInvtProof = biz_Debt
				.validate_CountInvtDebtSaleTaskLog_CountInvtProof(STATUS, "2");
		System.out.println("************************result_CountInvtDebtSaleTaskLog_CountInvtProof="
				+ result_CountInvtDebtSaleTaskLog_CountInvtProof);
		Assert.assertEquals(true, result_CountInvtDebtSaleTaskLog_CountInvtProof);

		// 校验sum(price_in)=trans_amount
		boolean result_sumPriceIn_transAmount = biz_Debt.validate_sumPriceIn_transAmount();
		Assert.assertEquals(true, result_sumPriceIn_transAmount);

		// 校验sum(buy_num)=transfer_num-residue_num
		boolean result_sumBuyNum_subTransferNumAndResidueNum = biz_Debt
				.validate_sumBuyNum_subTransferNumAndResidueNum();
		System.out.println("************************result_sumBuyNum_subTransferNumAndResidueNum="
				+ result_sumBuyNum_subTransferNumAndResidueNum);
		Assert.assertEquals(true, result_sumBuyNum_subTransferNumAndResidueNum);

		// 校验debt_buy_log表记录=invt_trd_order表记录
		boolean result_CountdebtBuyLog_CountInvtTrdOrder = biz_Debt.validate_CountdebtBuyLog_CountInvtTrdOrder();
		System.out.println("************************result_CountdebtBuyLog_CountInvtTrdOrder="
				+ result_CountdebtBuyLog_CountInvtTrdOrder);
		Assert.assertEquals(true, result_CountdebtBuyLog_CountInvtTrdOrder);

		boolean result_amounut = biz_Debt.validate_amount();
		System.out.println("************************result_lockNum=" + result_lockNum);
		Assert.assertEquals(true, result_amounut);

		boolean result_residueNum_sumHoldNum = biz_Debt.validate_residueNum_sumHoldNum();
		System.out.println("************************result_residueNum_sumHoldNum=" + result_residueNum_sumHoldNum);
		Assert.assertEquals(true, result_residueNum_sumHoldNum);
	}
}
