package com.nonobank.apps.testcase.admin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
	public static final String TASK_STATUS = "6";
	public static final String SALE_STATUS = "3";
	public static final String LOG_STATUS = "2";
	public static final String PROOF_STATUS = "1";
	public static final String BUY_STATUS = "1";
	public static final String ORDER_STATUS = "2";
	public static final String FROM_TYPE = "1";
	public static final String IS_PAY = "0";
	public static final String BIZ_TYPE = "1";

	@Test(dataProvider = "dataSource")
	public void test(String username, String password, String search_username, String targetFpid) {
		// biz_Login.login(username, password);
		// biz_Home.navigate_to_financePlanProfit();
		// biz_Debt.debt("PartSuccess", search_username, targetFpid);
		System.out.println("**************bo_id=" + Biz_Debt.bo_id + "**************from_id=" + Biz_Debt.from_id);

		// 校验lock_num=0,测试第1点
		biz_Debt.validate_lockNum(0, null, "1");

		// 校验residue_num=Transfer_num-sum(buy_num),测试第2点
		biz_Debt.validate_residueNum_subTransferNumSumBuyNum(null, LOG_STATUS);

		// 校验amount值,测试第3点
		biz_Debt.validate_pricePrincipal(null, LOG_STATUS, IS_PAY);

		// 校验sum(amount)=trans_amount,测试第4点
		biz_Debt.validate_sumAmount_transAmount(null, LOG_STATUS);

		// 校验sum(amount)=trans_amount,测试第5点
		biz_Debt.validate_countInvtDebtSaleTaskLog_countInvtProof(null, LOG_STATUS, PROOF_STATUS, BIZ_TYPE);

		// 校验sum(price_in)=trans_amount,测试第6点
		biz_Debt.validate_sumPriceIn_transAmount(null, BUY_STATUS);

		// 校验sum(buy_num)=transfer_num-residue_num,测试第7点
		biz_Debt.validate_sumBuyNum_subTransferNumAndResidueNum(null, BUY_STATUS);

		// 校验debt_buy_log表记录=invt_trd_order表记录,测试第8点
		biz_Debt.validate_countdebtBuyLog_countInvtTrdOrder(null, BUY_STATUS, ORDER_STATUS);

		// 校验debt_buy_log表记录=invt_proof表记录
		biz_Debt.validate_countDebtBuyLog_countInvtProof(null, BUY_STATUS, "2", PROOF_STATUS);
		// 校验amount,测试第10点
		biz_Debt.validate_amount(null, LOG_STATUS, FROM_TYPE, IS_PAY);

		// 校验residue_num=sum(hold_hum),测试第11点
		biz_Debt.validate_residueNum_sumHoldNum(null);

	}
}
