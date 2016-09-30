package com.nonobank.apps.business.admin;

import com.nonobank.apps.page.admin.Page_Debt;
import com.nonobank.apps.utils.db.DBUtils;

public class Biz_Debt {
	Page_Debt page_Debt = new Page_Debt();
	public static String bo_id = "561313";
	public static final double LOCK_NUM = 0.0;
	public static final double RESIDUE_NUM = 0.0;
	public static final double HOLD_NUM = 0.0;

	public void debt(String username, String code, String targetFpid, String targetVip) {
		page_Debt.input_username(username);
		page_Debt.select_fpId(code);
		page_Debt.click_query();
		page_Debt.select_targetFpid(targetFpid);
		page_Debt.select_targetVip(targetVip);
	}

	public void debt(String search_username) {
		if (search_username.equals("random")) {
			search_username = DBUtils.getValues("nono",
					"SELECT  DISTINCT ui.user_name FROM  borrows_accept ba  LEFT JOIN user_info ui on ui.id = ba.user_id WHERE ba.is_pay =0 and ba.va_id>500  AND ba.price_principal>0  LIMIT 100");
		}
		page_Debt.input_username(search_username);
		debt_common();

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

	public boolean validate_lockNum() {
		String str = getActualValue(
				"SELECT lock_num from debt_sale where id = (SELECT ds_id from invt_debt_sale_task where `status` = 5 and bo_id = '"
						+ bo_id + "')");
		double except_lockNum = Double.parseDouble(str);
		System.out.println("************except_lockNum=" + except_lockNum + "********LOCK_NUM=" + LOCK_NUM);
		return except_lockNum == LOCK_NUM ? true : false;
	}

	public boolean validate_residueNum() {
		String str = getActualValue(
				"SELECT residue_num from debt_sale where id = (SELECT ds_id from invt_debt_sale_task where `status` = 5 and bo_id = '"
						+ bo_id + "')");
		double except_residueNum = Double.parseDouble(str);
		System.out.println("************except_residueNum=" + except_residueNum + "********RESIDUE_NUM=" + RESIDUE_NUM);
		return except_residueNum == RESIDUE_NUM ? true : false;
	}

	public boolean validate_price_sumTransAmountAndPayAmount() {
		String str = getActualValue(
				"SELECT price from debt_sale where id = (SELECT ds_id from invt_debt_sale_task where `status` = 5 and bo_id = '"
						+ bo_id + "')");
		String str2 = getActualValue(
				"SELECT sum(trans_amount+pay_amount) from debt_sale where id = (SELECT ds_id from invt_debt_sale_task where `status` = 5 and bo_id = '"
						+ bo_id + "')");
		double price = Double.parseDouble(str);
		double amount = Double.parseDouble(str2);
		System.out.println("************price=" + price + "********amount=" + amount);
		return price == amount ? true : false;
	}

	public boolean validate_transAmount_sumAmount() {
		String str = getActualValue(
				"SELECT trans_amount from debt_sale where id = (SELECT ds_id from invt_debt_sale_task where `status` = 5 and bo_id = '"
						+ bo_id + "')");
		String str2 = getActualValue(
				"SELECT sum(amount) from invt_debt_sale_task_log where task_id= (SELECT id from invt_debt_sale_task where `status` = 5 and bo_id = '"
						+ bo_id + "') and `status` = 2");
		double transAmount = Double.parseDouble(str);
		double sumAmount = Double.parseDouble(str2);
		System.out.println("************transAmount=" + transAmount + "********sumAmount=" + sumAmount);
		return transAmount == sumAmount ? true : false;
	}

	public boolean validate_transferNum_sumBuyNum() {
		String str = getActualValue(
				"SELECT transfer_num from debt_sale where id = (SELECT ds_id from invt_debt_sale_task where `status` = 5 and bo_id = '"
						+ bo_id + "')");
		String str2 = getActualValue(
				"SELECT sum(buy_num) from invt_debt_sale_task_log where task_id = (SELECT id from invt_debt_sale_task where `status` = 5 and bo_id = '"
						+ bo_id + "') and `status` = 2");
		double transferNum = Double.parseDouble(str);
		double sumBuyNum = Double.parseDouble(str2);
		System.out.println("************transferNum=" + transferNum + "********sumBuyNum=" + sumBuyNum);
		return transferNum == sumBuyNum ? true : false;
	}

	public boolean validate_invtDebtSaleTaskLogCount_invtProofCount() {
		String str = getActualValue(
				"SELECT count(*) from invt_debt_sale_task_log where task_id= (SELECT id from invt_debt_sale_task where `status` = 5 and bo_id = '"
						+ bo_id + "') and `status` = 2");
		String str2 = getActualValue(
				"SELECT count(*) from invt_proof where biz_type = 1 and status = 1 and biz_id in (SELECT id from invt_debt_sale_task_log where task_id= (SELECT id from invt_debt_sale_task where `status` = 5 and bo_id = '"
						+ bo_id + "') and `status` = 2)");
		double invtDebtSaleTaskLogCount_ = Double.parseDouble(str);
		double invtProofCount = Double.parseDouble(str2);
		System.out.println(
				"invtDebtSaleTaskLogCount_=" + invtDebtSaleTaskLogCount_ + "**invtProofCount=" + invtProofCount);
		return invtDebtSaleTaskLogCount_ == invtProofCount ? true : false;
	}

	public boolean validate_sumPriceIn_transAmount() {
		String str = getActualValue(
				"SELECT sum(price_in) from debt_buy_log where ds_id  = (SELECT ds_id from invt_debt_sale_task where `status` = 5 and bo_id = '"
						+ bo_id + "') and status = 1");
		String str2 = getActualValue(
				"SELECT trans_amount from debt_sale where id = (SELECT ds_id from invt_debt_sale_task where `status` = 5 and bo_id = '"
						+ bo_id + "') and status = 1");
		double sumPriceIn = Double.parseDouble(str);
		double transAmount = Double.parseDouble(str2);
		System.out.println("****sumPriceIn=" + sumPriceIn + "****transAmount=" + transAmount);
		return sumPriceIn == transAmount ? true : false;
	}

	public boolean validate_sumBuyNum_transferNum() {
		String str = getActualValue(
				"SELECT sum(buy_num) from debt_buy_log where ds_id  = (SELECT ds_id from invt_debt_sale_task where `status` = 5 and bo_id = '"
						+ bo_id + "') and status = 1");
		String str2 = getActualValue(
				"SELECT transfer_num from debt_sale where id = (SELECT ds_id from invt_debt_sale_task where `status` = 5 and bo_id = '"
						+ bo_id + "') and status = 1");
		double sumBuyNum = Double.parseDouble(str);
		double transferNum = Double.parseDouble(str2);
		System.out.println("****sumBuyNum=" + sumBuyNum + "****transferNum=" + transferNum);
		return sumBuyNum == transferNum ? true : false;
	}

	public boolean validate_debtBuyLogCount_invtTrdOrderCount() {
		String str = getActualValue(
				"SELECT count(*) from debt_buy_log where ds_id = (SELECT ds_id from invt_debt_sale_task where `status` = 5 and bo_id = '"
						+ bo_id + "') and status = 1");
		String str2 = getActualValue(
				"SELECT count(*) from invt_trd_order where status= 2 and  trans_id in (SELECT trans_id from debt_buy_log where ds_id = (SELECT ds_id from invt_debt_sale_task where `status` = 5 and bo_id = '"
						+ bo_id + "') and status = 1)");
		double debtBuyLogCount = Double.parseDouble(str);
		double invtTrdOrderCount = Double.parseDouble(str2);
		System.out.println("****debtBuyLogCount=" + debtBuyLogCount + "****invtTrdOrderCount=" + invtTrdOrderCount);
		return debtBuyLogCount == invtTrdOrderCount ? true : false;
	}

	public boolean validate_debtBuyLogCount_invtProofCount() {
		String str = getActualValue(
				"SELECT count(*) from debt_buy_log where ds_id = (SELECT ds_id from invt_debt_sale_task where `status` = 5 and bo_id = '"
						+ bo_id + "') and status = 1");
		String str2 = getActualValue(
				"SELECT count(*) from invt_proof where biz_type = 2 and status = 1 and biz_id in (SELECT id from debt_buy_log where ds_id = (SELECT ds_id from invt_debt_sale_task where `status` = 5 and bo_id = '"
						+ bo_id + "') and status = 1)");
		double debtBuyLogCount = Double.parseDouble(str);
		double invtProofCount = Double.parseDouble(str2);
		System.out.println("****debtBuyLogCount=" + debtBuyLogCount + "****invtProofCount=" + invtProofCount);
		return debtBuyLogCount == invtProofCount ? true : false;
	}

	public boolean validate_holdNum() {
		String str = getActualValue(
				"SELECT hold_num from debt_exchange_account where  va_id = (SELECT from_id from invt_debt_sale_task where `status` = 5 and bo_id = '"
						+ bo_id + "')");
		double except_holdNum = Double.parseDouble(str);
		System.out.println("************except_residueNum=" + except_holdNum + "********RESIDUE_NUM=" + RESIDUE_NUM);
		return except_holdNum == HOLD_NUM ? true : false;
	}

	public boolean validate_sumPricePrincipal_sumPriceInterest_sumPrice() {
		String str = getActualValue(
				"SELECT sum(ba.price_principal),sum(ba.price_interest),sum(ba.price) FROM borrows_accept ba LEFT JOIN invt_debt_sale_task it ON it.from_id = ba.va_id and ba.bo_id = it.bo_id WHERE  ba.is_pay = 0 AND it.from_type = 1 and it.from_id =(SELECT from_id from invt_debt_sale_task where `status` = 5 and bo_id = '"
						+ bo_id + "') and it.bo_id = " + bo_id);
		String[] strs = str.split(",");
		for (int i = 0; i < strs.length; i++) {
			double value = Double.parseDouble(strs[i]);
			System.out.println("*****************************value=" + value);
			if (value > 0) {
				return false;
			}
		}
		return true;
	}

	public String getActualValue(String sql) {
		String str = null;
		do {
			str = DBUtils.getValues("nono", sql);
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
