package com.nonobank.apps.business.admin;

import java.util.List;
import com.nonobank.apps.page.admin.Page_Debt;
import com.nonobank.apps.utils.db.DBUtils;

public class Biz_Debt {
	Page_Debt page_Debt = new Page_Debt();
	public static String bo_id;
	public static final double LOCK_NUM = 0;
	public static final double RESIDUE_NUM = 0;
	public static final double HOLD_NUM = 0;

	public void debt(String username, String code, String targetFpid, String targetVip) {
		page_Debt.input_username(username);
		page_Debt.select_fpId(code);
		page_Debt.click_query();
		page_Debt.select_targetFpid(targetFpid);
		page_Debt.select_targetVip(targetVip);
	}

	// 债转成功时进行的操作
	public void debt(String search_username) {
		if (search_username.equals("random")) {
			search_username = DBUtils.getOneLineValues("nono",
					"SELECT  DISTINCT ui.user_name FROM  borrows_accept ba  LEFT JOIN user_info ui on ui.id = ba.user_id WHERE ba.is_pay =0 and ba.va_id>500  AND ba.price_principal>0  LIMIT 100");
		}
		page_Debt.input_username(search_username);
		debt_common();

	}

	// 债转失败时进行的操作
	public void debt(String search_username, String targetFpid) {
		if (targetFpid.equals("random")) {
			targetFpid = DBUtils.getOneLineValues("nono",
					"SELECT DISTINCT concat(fp.id,'：',fp.title) title FROM  vip_account va  LEFT JOIN  finance_plan fp on fp.id = va.fp_id WHERE  va.is_cash =1 and fp.title is not NULL ORDER BY  fp.id LIMIT  10");

		}
		page_Debt.select_targetFpid(targetFpid);
		debt(search_username);
	}

	public void debt_common() {
		page_Debt.click_query();
		page_Debt.click_debtDetail();
		page_Debt.click_debt();
		if (page_Debt.isAlertExists(3000)) {
			page_Debt.closeAlert();
		}
		if (page_Debt.isAlertExists(3000)) {
			page_Debt.closeAlert();
		}
	}

	public boolean validate_status(String exceptValue) {
		String actualValue = getActualValue("SELECT ds_id from invt_debt_sale_task where bo_id = " + bo_id);
		return actualValue.equals(exceptValue);
	}

	public boolean validate_status(String exceptValue, String task_status) {
		String actualValue = getActualValue(
				"SELECT status from debt_sale where id = (SELECT ds_id from invt_debt_sale_task where `status` = "
						+ task_status + " and bo_id = '" + bo_id + "' limit 1)");
		return actualValue.equals(exceptValue);
	}

	public boolean validate_lockNum(double exceptValue, String task_status) {
		String str = getActualValue(
				"SELECT lock_num from debt_sale where id = (SELECT ds_id from invt_debt_sale_task where `status` = "
						+ task_status + " and bo_id = '" + bo_id + "' limit 1)");
		double actualValue = Double.parseDouble(str);
		return actualValue == exceptValue;
	}

	public boolean validate_residueNum() {
		String str = getActualValue(
				"SELECT residue_num from debt_sale where id = (SELECT ds_id from invt_debt_sale_task where `status` = 5 and bo_id = '"
						+ bo_id + "' limit 1)");
		double except_residueNum = Double.parseDouble(str);
		return except_residueNum == RESIDUE_NUM ? true : false;
	}

	public boolean validate_residueNum_transferNum() {
		String str = getActualValue(
				"SELECT residue_num,transfer_Num from debt_sale where id = (SELECT ds_id from invt_debt_sale_task where `status` = 5 and bo_id = '"
						+ bo_id + "' limit 1)");
		String[] strs = str.split(",");

		return strs[0] == strs[1];
	}

	public boolean validate_price_sumTransAmountAndPayAmount() {
		String str = getActualValue(
				"SELECT price from debt_sale where id = (SELECT ds_id from invt_debt_sale_task where `status` = 5 and bo_id = '"
						+ bo_id + "' limit 1)");
		String str2 = getActualValue(
				"SELECT sum(trans_amount+pay_amount) from debt_sale where id = (SELECT ds_id from invt_debt_sale_task where `status` = 5 and bo_id = '"
						+ bo_id + "' limit 1)");
		double price = Double.parseDouble(str);
		double amount = Double.parseDouble(str2);
		return price == amount;
	}

	public boolean validate_sumAmount_transAmount() {
		String str = getActualValue(
				"SELECT trans_amount from debt_sale where id = (SELECT ds_id from invt_debt_sale_task where `status` = 5 and bo_id = '"
						+ bo_id + "' limit 1)");
		String str2 = getActualValue(
				"SELECT sum(amount) from invt_debt_sale_task_log where task_id = (SELECT id from invt_debt_sale_task where `status` = 5 and bo_id = '"
						+ bo_id + "' limit 1) and `status` = 2");
		double transAmount = Double.parseDouble(str);
		double sumAmount = Double.parseDouble(str2);
		return transAmount == sumAmount;
	}

	public boolean validate_sumBuyNum_transferNum() {
		String str = getActualValue(
				"SELECT transfer_num from debt_sale where id = (SELECT ds_id from invt_debt_sale_task where `status` = 5 and bo_id = '"
						+ bo_id + "' limit 1)");
		String str2 = getActualValue(
				"SELECT sum(buy_num) from invt_debt_sale_task_log where task_id = (SELECT id from invt_debt_sale_task where `status` = 5 and bo_id = '"
						+ bo_id + "' limit 1) and `status` = 2");
		double transferNum = Double.parseDouble(str);
		double sumBuyNum = Double.parseDouble(str2);
		return transferNum == sumBuyNum;
	}

	public boolean validate_sumBuyNum_transferNum2() {
		String str = getActualValue(
				"SELECT sum(buy_num) from debt_buy_log where ds_id  = (SELECT ds_id from invt_debt_sale_task where `status` = 5 and bo_id = '"
						+ bo_id + "' limit 1) and status = 1");
		String str2 = getActualValue(
				"SELECT transfer_num from debt_sale where id = (SELECT ds_id from invt_debt_sale_task where `status` = 5 and bo_id = '"
						+ bo_id + "' limit 1) and status = 1");
		double sumBuyNum = Double.parseDouble(str);
		double transferNum = Double.parseDouble(str2);
		System.out.println("****sumBuyNum=" + sumBuyNum + "****transferNum=" + transferNum);
		return sumBuyNum == transferNum;
	}

	public boolean validate_invtDebtSaleTaskLogCount_invtProofCount(String task_status, String status) {
		String str = getActualValue(
				"SELECT count(*) from invt_debt_sale_task_log where task_id = (SELECT id from invt_debt_sale_task where `status` = "
						+ task_status + " and bo_id = '" + bo_id + "' limit 1) and `status` = " + status);
		String str2 = getActualValue(
				"SELECT count(*) from invt_proof where biz_type = 1 and status = 1 and biz_id = (SELECT id from invt_debt_sale_task_log where task_id = (SELECT id from invt_debt_sale_task where `status` = "
						+ task_status + " and bo_id = '" + bo_id + "' limit 1) and `status` = " + status + ")");
		double invtDebtSaleTaskLogCount_ = Double.parseDouble(str);
		double invtProofCount = Double.parseDouble(str2);
		return invtDebtSaleTaskLogCount_ == invtProofCount;
	}

	public boolean validate_sumPriceIn_transAmount() {
		String str = getActualValue(
				"SELECT sum(price_in) from debt_buy_log where ds_id  = (SELECT ds_id from invt_debt_sale_task where `status` = 5 and bo_id = '"
						+ bo_id + "' limit 1) and status = 1");
		String str2 = getActualValue(
				"SELECT trans_amount from debt_sale where id = (SELECT ds_id from invt_debt_sale_task where `status` = 5 and bo_id = '"
						+ bo_id + "' limit 1) and status = 1");
		double sumPriceIn = Double.parseDouble(str);
		double transAmount = Double.parseDouble(str2);
		return sumPriceIn == transAmount;
	}

	public boolean validate_debtBuyLogCount_invtTrdOrderCount() {
		String str = getActualValue(
				"SELECT count(*) from debt_buy_log where ds_id = (SELECT ds_id from invt_debt_sale_task where `status` = 5 and bo_id = '"
						+ bo_id + "' limit 1) and status = 1");
		String str2 = getActualValue(
				"SELECT count(*) from invt_trd_order where status= 2 and  trans_id = (SELECT trans_id from debt_buy_log where ds_id = (SELECT ds_id from invt_debt_sale_task where `status` = 5 and bo_id = '"
						+ bo_id + "' limit 1) and status = 1)");
		double debtBuyLogCount = Double.parseDouble(str);
		double invtTrdOrderCount = Double.parseDouble(str2);
		return debtBuyLogCount == invtTrdOrderCount;
	}

	public boolean validate_amount() {
		boolean flag = false;
		List<Object> lst = DBUtils.geMulLineValues("nono",
				"SELECT id from invt_debt_sale_task_log where `status` = 2 and task_id = (SELECT id from invt_debt_sale_task where `status` = 5 and bo_id = '"
						+ bo_id + "' limit 1)");
		for (Object object : lst) {
			String str = getActualValue(
					"SELECT dea.hold_num,tl.buy_num FROM debt_exchange_account dea LEFT JOIN invt_debt_sale_task_log tl on dea.va_id= tl.from_id  LEFT JOIN debt_sale ds on ds.id = tl.ds_id WHERE  tl.status = 2 and tl.from_type = 1 and tl.id = "
							+ object + " AND dea.bo_id = ds.bo_id");
			String[] strs = str.split(",");
			double[] values = new double[2];
			for (int i = 0; i < strs.length; i++) {
				values[i] = Double.parseDouble(strs[i]);
			}
			String str2 = getActualValue(
					"SELECT sum(ba.price_principal),tl.amount FROM borrows_accept ba LEFT JOIN invt_debt_sale_task_log tl on tl.from_id = ba.va_id LEFT JOIN debt_sale ds on tl.ds_id = ds.id WHERE  ba.bo_id = ds.bo_id and ba.is_pay = 0 and tl.id = "
							+ object);
			String[] strs2 = str2.split(",");
			double[] values2 = new double[2];
			for (int i = 0; i < strs2.length; i++) {
				values2[i] = Double.parseDouble(strs2[i]);
			}
			flag = values2[1] == (values[1] / values[0]) * values2[0] ? true : false;
			if (flag == false) {
				return false;
			}
		}
		return true;
	}

	public boolean validate_holdNum_transferNum() {
		String str = getActualValue(
				"SELECT dea.hold_num, ds.transfer_num FROM debt_exchange_account dea LEFT JOIN  invt_debt_sale_task idst on idst.from_id= dea.va_id LEFT JOIN debt_sale ds on ds.id = idst.ds_id WHERE  idst.from_type=1 and dea.bo_id = ds.bo_id AND idst.id = (SELECT id from invt_debt_sale_task where `status` = 99 and bo_id = "
						+ bo_id + " limit 1)");
		String[] strs = str.split(",");
		return Double.parseDouble(strs[1]) == Double.parseDouble(strs[0]);
	}

	public boolean validate_debtBuyLogCount_invtProofCount() {
		String str = getActualValue(
				"SELECT count(*) from debt_buy_log where ds_id = (SELECT ds_id from invt_debt_sale_task where `status` = 5 and bo_id = '"
						+ bo_id + "' limit 1) and status = 1");
		String str2 = getActualValue(
				"SELECT count(*) from invt_proof where biz_type = 2 and status = 1 and biz_id = (SELECT id from debt_buy_log where ds_id = (SELECT ds_id from invt_debt_sale_task where `status` = 5 and bo_id = '"
						+ bo_id + "' limit 1) and status = 1)");
		double debtBuyLogCount = Double.parseDouble(str);
		double invtProofCount = Double.parseDouble(str2);
		return debtBuyLogCount == invtProofCount;
	}

	public boolean validate_holdNum() {
		String str = getActualValue(
				"SELECT sum(hold_num) from debt_exchange_account where  va_id = (SELECT from_id from invt_debt_sale_task where `status` = 5 and bo_id = '"
						+ bo_id + "' limit 1) and bo_id = " + bo_id);
		double except_holdNum = Double.parseDouble(str);
		return except_holdNum == HOLD_NUM;
	}

	public boolean validate_sumPrice_sumPriceInterest_sumPricePrincipal() {
		String str = getActualValue(
				"SELECT sum(ba.price_principal),sum(ba.price_interest),sum(ba.price) FROM borrows_accept ba LEFT JOIN invt_debt_sale_task it ON it.from_id = ba.va_id and ba.bo_id = it.bo_id WHERE  ba.is_pay = 0 AND it.from_type = 1 and it.from_id  = (SELECT from_id from invt_debt_sale_task where `status` = 5 and bo_id = '"
						+ bo_id + "' limit 1) and it.bo_id = " + bo_id);
		String[] strs = str.split(",");
		for (int i = 0; i < strs.length; i++) {
			double value = Double.parseDouble(strs[i]);
			if (value > 0) {
				return false;
			}
		}
		return true;
	}

	public boolean validate_subPriceAndPayAmount_sumPricePrincipal() {
		String str = getActualValue(
				"SELECT sum(ba.price_principal),ds.price-ds.pay_amount as amount FROM borrows_accept ba LEFT JOIN invt_debt_sale_task idst on idst.from_id = ba.va_id LEFT JOIN debt_sale ds on ds.id = idst.ds_id WHERE  idst.from_type=1 and ba.bo_id = ds.bo_id and ba.is_pay =0 and idst.id = (SELECT id from invt_debt_sale_task where `status` = 99 and bo_id = "
						+ bo_id + " limit 1)");
		String[] strs = str.split(",");

		return Double.parseDouble(strs[1]) == Double.parseDouble(strs[0]);
	}

	public String getActualValue(String sql) {
		String str = null;
		do {
			str = DBUtils.getOneLineValues("nono", sql);
			if (str == null) {
				try {
					Thread.sleep(60000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		} while (str == null);
		return str;
	}
}
