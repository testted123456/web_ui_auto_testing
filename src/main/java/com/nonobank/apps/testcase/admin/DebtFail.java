package com.nonobank.apps.testcase.admin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import com.nonobank.apps.business.admin.Biz_Debt;
import com.nonobank.apps.business.admin.Biz_Home;
import com.nonobank.apps.business.admin.Biz_Login;
import com.nonobank.apps.testcase.base.BaseCase;

public class DebtFail extends BaseCase {
	public static Logger logger = LogManager.getLogger(DebtFail.class);
	Biz_Login biz_Login;
	Biz_Home biz_Home;
	Biz_Debt biz_Debt;
	public static final String TASK_STATUS = "99";
	public static final String IS_PAY = "0";
	public static final String SALE_STATUS = "3";
	public static final String FROM_TYPE = "1";

	@Test(dataProvider = "dataSource")
	public void test(String username, String password, String search_username, String targetFpid) {
		biz_Login.login(username, password);
		biz_Home.navigate_to_financePlanProfit();
		biz_Debt.debt("Fail", search_username, targetFpid);

		// 1.校验debt_sale表中lock_num=0
		biz_Debt.validate_lockNum(0.0, TASK_STATUS, SALE_STATUS);
		// 2.校验debt_sale表中residue_num=transfer_num
		biz_Debt.validate_residueNum_transferNum(TASK_STATUS);
		// 3.校验borrows_accept ba,debt_sale
		// ds表中,sum(ba.price_principal)=ds.price-ds.pay_amount
		biz_Debt.validate_subPriceAndPayAmount_sumPricePrincipal(TASK_STATUS, IS_PAY, FROM_TYPE);

		// 4.校验debt_exchange_account dea,invt_debt_sale_task
		// idst表中,其中dea.hold_num=ds.transfer_num
		biz_Debt.validate_holdNum_transferNum(TASK_STATUS, FROM_TYPE);

	}

}
