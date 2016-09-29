package com.nonobank.apps.business.admin;

import com.nonobank.apps.page.admin.Page_Debt;
import com.nonobank.apps.utils.db.DBUtils;

public class Biz_Debt {
	Page_Debt page_Debt = new Page_Debt();
	public static String bo_id = "561313";
	public static final double LOCK_NUM = 0.0;
	public static final double RESIDUE_NUM = 0.0;

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
		return except_lockNum == LOCK_NUM ? true : false;
	}

	public boolean validate_residueNum() {
		String str = getActualValue(
				"SELECT residue_num from debt_sale where id = (SELECT ds_id from invt_debt_sale_task where `status` = 5 and bo_id = '"
						+ bo_id + "')");
		double except_residueNum = Double.parseDouble(str);
		return except_residueNum == RESIDUE_NUM ? true : false;
	}

	public boolean validate_priceAndAmount() {
		String str = getActualValue(
				"SELECT price from debt_sale where id = (SELECT ds_id from invt_debt_sale_task where `status` = 5 and bo_id = '"
						+ bo_id + "')");
		String str2 = getActualValue(
				"SELECT sum(trans_amount+pay_amount) from debt_sale where id = (SELECT ds_id from invt_debt_sale_task where `status` = 5 and bo_id = '"
						+ bo_id + "')");
		double price = Double.parseDouble(str);
		double amount = Double.parseDouble(str2);
		return price == amount ? true : false;
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
