package com.nonobank.apps.testcase.admin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import com.nonobank.apps.business.admin.Biz_Debt;
import com.nonobank.apps.business.admin.Biz_Home;
import com.nonobank.apps.business.admin.Biz_Login;
import com.nonobank.apps.testcase.base.BaseCase;

public class DebtSuccess extends BaseCase {
	public static Logger logger = LogManager.getLogger(DebtSuccess.class);
	Biz_Login biz_Login;
	Biz_Home biz_Home;
	Biz_Debt biz_Debt;
	public static final String TASK_STATUS = "5";
	public static final String LOG_STATUS = "2";
	public static final String BUY_STATUS = "1";
	public static final String SALE_STATUS = "1";
	public static final String PROOF_STATUS = "1";
	public static final String ORDER_STATUS = "2";
	public static final String FROM_TYPE = "1";
	public static final String IS_PAY = "0";
	public static final String BIZ_TYPE = "2";

	@Test(dataProvider = "dataSource")
	public void test(String username, String password, String search_username) {
		// biz_Login.login(username, password);
		// biz_Home.navigate_to_financePlanProfit();
		// biz_Debt.debt("Success", search_username, null);

		// 1.校验debt_sale表中lock_num=0
		biz_Debt.validate_lockNum(0, TASK_STATUS, SALE_STATUS);

		// 2.校验debt_sale表中residue_num=0
		biz_Debt.validate_residueNum(0, TASK_STATUS);

		// 3.校验debt_sale表中sum(trans_amout+pay_amount)=price
		biz_Debt.validate_price_sumTransAmountAndPayAmount(TASK_STATUS);

		// 4.校验invt_debt_sale_task_log idstl和debt_sale
		// ds表,其中sum(idstl.amount)=ds.trans_amount
		biz_Debt.validate_sumAmount_transAmount(TASK_STATUS, LOG_STATUS);

		// 5.校验invt_debt_sale_task_log idstl和debt_sale
		// ds表,其中sum(idstl.buy_num)=ds.transfer_num
		biz_Debt.validate_sumBuyNum_transferNum(TASK_STATUS, LOG_STATUS);

		// 6.校验debt_buy_log dbl和debt_sale ds表,其中sum(dbl.buy_num)=ds.transfer_num
		biz_Debt.validate_sumBuyNum_transferNum2(TASK_STATUS, BUY_STATUS, SALE_STATUS);

		// 7.校验sumdebt_buy_log dbl和debt_sale
		// ds表,其中(dbl.price_in)=ds.trans_amount
		biz_Debt.validate_sumPriceIn_transAmount(TASK_STATUS, BUY_STATUS);

		// 8.校验debt_exchange_account dea,invt_debt_sale_task_log
		// tl,borrows_accept
		// ba表,其中(tl.buy_num/dea.hold_num)*dea.hold_num=tl.buy_num
		biz_Debt.validate_amount(TASK_STATUS, LOG_STATUS, FROM_TYPE, IS_PAY);

		// 7.校验invt_debt_sale_task_log记录=invt_proof记录
		biz_Debt.validate_countInvtDebtSaleTaskLog_countInvtProof(TASK_STATUS, LOG_STATUS, PROOF_STATUS, BUY_STATUS);

		// 8.校验debt_exchange_account表中hold_num=0
		biz_Debt.validate_sumHoldNum(0);

		// 9.校验borrows_accept表中sum(price_principal)=0
		biz_Debt.validate_sumPricePrincipal(0, IS_PAY, FROM_TYPE);

		// 10.校验borrows_accept表中sum(price_interest)=0
		biz_Debt.validate_sumPriceInterest(0, IS_PAY, FROM_TYPE);

		// 11.校验borrows_accept表中sum(price)=0
		biz_Debt.validate_sumPrice(0, IS_PAY, FROM_TYPE);

		// 12.校验debt_buy_log记录=invt_trd_order记录
		biz_Debt.validate_countDebtBuyLog_countInvtTrdOrder(TASK_STATUS, BUY_STATUS, ORDER_STATUS);

		// 13.校验debt_buy_log记录=invt_proof记录
		biz_Debt.validate_countDebtBuyLog_countInvtProof(TASK_STATUS, BUY_STATUS, BIZ_TYPE, PROOF_STATUS);

	}
}
