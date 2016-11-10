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
	Biz_Login biz_Login = new Biz_Login();
	Biz_Home biz_Home = new Biz_Home();
	Biz_Debt biz_Debt = new Biz_Debt();
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
		biz_Login.login(username, password);
		biz_Home.navigate_to_financePlanProfit();
		biz_Debt.debt("Success", search_username, null);
		System.out.println("**************bo_id=" + Biz_Debt.bo_id + "**************from_id=" + Biz_Debt.from_id);

		// 1.校验lock_num=0
		biz_Debt.validate_lockNum(0, TASK_STATUS, SALE_STATUS);

		// 2.校验residue_num字段
		biz_Debt.validate_residueNum(0, TASK_STATUS);

		// 3.校验sum(trans_amout+pay_amount)=price
		biz_Debt.validate_price_sumTransAmountAndPayAmount(TASK_STATUS);

		// 4.校验trans_amount=sum(amount)
		biz_Debt.validate_sumAmount_transAmount(TASK_STATUS, LOG_STATUS);

		// 5.校验transfer_num=sum(buy_num)
		biz_Debt.validate_sumBuyNum_transferNum(TASK_STATUS, LOG_STATUS);

		// 6.校验invt_debt_sale_task_log记录=invt_proof记录
		biz_Debt.validate_countInvtDebtSaleTaskLog_countInvtProof(TASK_STATUS, LOG_STATUS, PROOF_STATUS, BUY_STATUS);

		// 7.校验sum(price_in)=trans_amount
		biz_Debt.validate_sumPriceIn_transAmount(TASK_STATUS, BUY_STATUS);

		// 8.校验sum(buy_num)=transfer_num
		biz_Debt.validate_sumBuyNum_transferNum2(TASK_STATUS, BUY_STATUS, SALE_STATUS);

		// 9.校验debt_buy_log记录=invt_trd_order记录
		biz_Debt.validate_countdebtBuyLog_countInvtTrdOrder(TASK_STATUS, BUY_STATUS, ORDER_STATUS);

		// 10.校验debt_buy_log记录=invt_proof记录
		biz_Debt.validate_countDebtBuyLog_countInvtProof(TASK_STATUS, BUY_STATUS, BIZ_TYPE, PROOF_STATUS);

		// 11.校验amount
		biz_Debt.validate_amount(TASK_STATUS, LOG_STATUS, FROM_TYPE, IS_PAY);

		// 12.校验hold_num=0
		biz_Debt.validate_sumHoldNum(0);

		// 13.校验sum(price_principal)=0
		biz_Debt.validate_sumPricePrincipal(0, IS_PAY, FROM_TYPE);

		// 14.校验sum(price_interest)=0
		biz_Debt.validate_sumPriceInterest(0, IS_PAY, FROM_TYPE);

		// 15.校验sum(price)=0
		biz_Debt.validate_sumPrice(0, IS_PAY, FROM_TYPE);

	}
}
