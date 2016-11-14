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

		// 1.校验debt_sale表中lock_num=0
		biz_Debt.validate_lockNum(0, TASK_STATUS, SALE_STATUS);

		// 2.校验invt_debt_sale_task_log idstl和debt_sale
		// ds表,其中sum(idstl.amount)=ds.trans_amount
		biz_Debt.validate_sumAmount_transAmount(TASK_STATUS, LOG_STATUS);

		// 3.校验debt_buy_log dbl和debt_sale ds表,其中(dbl.price_in)=ds.trans_amount
		biz_Debt.validate_sumPriceIn_transAmount(TASK_STATUS, BUY_STATUS);

		// 4.校验debt_exchange_account dea,invt_debt_sale_task_log
		// tl,borrows_accept
		// ba表,其中(tl.buy_num/dea.hold_num)*dea.hold_num=tl.buy_num
		biz_Debt.validate_amount(TASK_STATUS, LOG_STATUS, FROM_TYPE, IS_PAY);

		// 5.校验invt_debt_sale_task_log idstl,debt_exchange_account
		// dea,borrows_accept
		// ba表,其中sum(idstl.amount)/sum(idstl.buy_num)*dea.hold_num
		// price=sum(ba.price_principal)
		biz_Debt.validate_pricePrincipal(TASK_STATUS, LOG_STATUS, IS_PAY);

		// 6.校验debt_sale ds,invt_debt_sale_task_log idstl表中,ds.transfer_num-
		// sum(idstl.buy_num)=ds.residue_num
		biz_Debt.validate_residueNum_subTransferNumSumBuyNum(TASK_STATUS, LOG_STATUS);

		// 7.校验debt_buy_log dbl,debt_sale
		// ds表中,其中sum(dbl.buy_num)=ds.transfer_num-ds.residue_num
		biz_Debt.validate_sumBuyNum_subTransferNumAndResidueNum(TASK_STATUS, BUY_STATUS);

		// 8.校验debt_exchange_account dea,debt_sale
		// ds表中,其中=dea.hold_num,ds.residue_num
		biz_Debt.validate_residueNum_sumHoldNum(TASK_STATUS);

		// 校验sum(amount)=trans_amount,测试第5点
		biz_Debt.validate_countInvtDebtSaleTaskLog_countInvtProof(TASK_STATUS, LOG_STATUS, PROOF_STATUS, BIZ_TYPE);

		// 校验debt_buy_log表记录=invt_trd_order表记录,测试第8点
		biz_Debt.validate_countdebtBuyLog_countInvtTrdOrder(TASK_STATUS, BUY_STATUS, ORDER_STATUS);

		// 校验debt_buy_log表记录=invt_proof表记录
		biz_Debt.validate_countDebtBuyLog_countInvtProof(TASK_STATUS, BUY_STATUS, "2", PROOF_STATUS);

	}
}
