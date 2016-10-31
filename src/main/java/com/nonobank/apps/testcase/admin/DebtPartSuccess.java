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
		 biz_Debt.debt("PartSuccess",search_username, targetFpid);
		System.out.println("**************bo_id=" + Biz_Debt.bo_id + "**************from_id=" + Biz_Debt.from_id);

		// 校验lock_num=0,测试第1点
		boolean result_lockNum = biz_Debt.validate_lockNum(0, STATUS, "3");
		Assert.assertEquals(true, result_lockNum);

		// 校验residue_num=Transfer_num-sum(buy_num),测试第2点
		boolean result_residueNum_subTransferNumSumBuyNum = biz_Debt.validate_residueNum_subTransferNumSumBuyNum("6");
		Assert.assertEquals(true, result_residueNum_subTransferNumSumBuyNum);

		// 校验amount值,测试第3点
		boolean result_pricePrincipal = biz_Debt.validate_pricePrincipal("6");
		Assert.assertEquals(true, result_pricePrincipal);

		// 校验sum(amount)=trans_amount,测试第4点
		boolean result_sumAmount_transAmount = biz_Debt.validate_sumAmount_transAmount(STATUS);
		Assert.assertEquals(true, result_sumAmount_transAmount);

		// 校验sum(amount)=trans_amount,测试第5点
		boolean result_CountInvtDebtSaleTaskLog_CountInvtProof = biz_Debt
				.validate_countInvtDebtSaleTaskLog_countInvtProof(STATUS, "2");
		Assert.assertEquals(true, result_CountInvtDebtSaleTaskLog_CountInvtProof);

		// 校验sum(price_in)=trans_amount,测试第6点
		boolean result_sumPriceIn_transAmount = biz_Debt.validate_sumPriceIn_transAmount("6");
		Assert.assertEquals(true, result_sumPriceIn_transAmount);

		// 校验sum(buy_num)=transfer_num-residue_num,测试第7点
		boolean result_sumBuyNum_subTransferNumAndResidueNum = biz_Debt
				.validate_sumBuyNum_subTransferNumAndResidueNum("6");
		Assert.assertEquals(true, result_sumBuyNum_subTransferNumAndResidueNum);

		// 校验debt_buy_log表记录=invt_trd_order表记录,测试第8点
		boolean result_CountdebtBuyLog_CountInvtTrdOrder = biz_Debt.validate_countdebtBuyLog_countInvtTrdOrder("6");
		Assert.assertEquals(true, result_CountdebtBuyLog_CountInvtTrdOrder);

		// 校验debt_buy_log表记录=invt_proof表记录
		boolean result_countDebtBuyLog_countInvtProof = biz_Debt.validate_countDebtBuyLog_countInvtProof("6");
		Assert.assertEquals(true, result_countDebtBuyLog_countInvtProof);
		// 校验amount,测试第10点
		boolean result_amount = biz_Debt.validate_amount("6");
		Assert.assertEquals(true, result_amount);

		// 校验residue_num=sum(hold_hum),测试第11点
		boolean result_residueNum_sumHoldNum = biz_Debt.validate_residueNum_sumHoldNum();
		Assert.assertEquals(true, result_residueNum_sumHoldNum);

	}
}
