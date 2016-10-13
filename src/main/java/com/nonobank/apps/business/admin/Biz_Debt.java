package com.nonobank.apps.business.admin;

import java.util.List;
import com.nonobank.apps.page.admin.Page_Debt;
import com.nonobank.apps.utils.db.DBUtils;

public class Biz_Debt {
	Page_Debt page_Debt = new Page_Debt();
	public static String bo_id = "561313";
	public static String from_id = "207724";
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
	public void debt(String search_username, String debtDetail) {
		if (search_username.equals("random")) {
			search_username = DBUtils.getOneLineValues("nono",
					"SELECT  DISTINCT ui.user_name FROM  borrows_accept ba  LEFT JOIN user_info ui on ui.id = ba.user_id WHERE ba.is_pay =0 and ba.va_id>500  AND ba.price_principal>0  LIMIT 100");
		}
		page_Debt.input_username(search_username);
		page_Debt.click_query();
		debt_common(debtDetail);

	}

	// 债转失败或部分成功时进行的操作
	public void debt(String search_username, String targetFpid, String debtDetail) {
		if (targetFpid.equals("random")) {
			targetFpid = DBUtils.getOneLineValues("nono",
					"SELECT DISTINCT concat(fp.id,'：',fp.title) title FROM  vip_account va  LEFT JOIN  finance_plan fp on fp.id = va.fp_id WHERE  va.is_cash =1 and fp.title is not NULL ORDER BY  fp.id LIMIT  10");

		}
		page_Debt.select_targetFpid(targetFpid);
		debt(search_username, debtDetail);
	}

	public void debt_common(String debtDetail) {
		switch (debtDetail) {
		case "PartSuccess":
			page_Debt.click_debtMain();
			break;

		default:
			page_Debt.click_debtDetail();
			page_Debt.click_debt();
			break;
		}
		if (page_Debt.isAlertExists(3000)) {
			page_Debt.closeAlert();
		}
		if (page_Debt.isAlertExists(3000)) {
			page_Debt.closeAlert();
		}

	}

	public boolean validate_lockNum(double exceptValue, String task_status) {
		String sql = "SELECT ds_id from invt_debt_sale_task where status = " + task_status;
		StringBuffer sb = getSql(sql);
		String str = getActualValue("SELECT lock_num from debt_sale where id = (" + sb.toString() + ")");
		double actualValue = Double.parseDouble(str);
		return actualValue == exceptValue;
	}

	private StringBuffer getSql(String sql) {
		StringBuffer sb = new StringBuffer();
		sb.append(sql);
		if (bo_id != null) {
			sb.append(" and bo_id = '" + bo_id + "'");
		}
		if (from_id != null) {
			sb.append(" and from_id = '" + from_id + "'");
		}
		sb.append(" order by create_time desc limit 1");
		return sb;
	}

	public boolean validate_residueNum() {
		String str = getActualValue(
				"SELECT residue_num from debt_sale where id = (SELECT ds_id from invt_debt_sale_task where status = 5 and bo_id = '"
						+ bo_id + "' and from_id = '" + from_id + "' order by create_time desc limit 1)");
		double except_residueNum = Double.parseDouble(str);
		return except_residueNum == RESIDUE_NUM;
	}

	public boolean validate_residueNum_transferNum() {
		String str = getActualValue(
				"SELECT residue_num,transfer_Num from debt_sale where id = (SELECT ds_id from invt_debt_sale_task where status = 5 and bo_id = '"
						+ bo_id + "' and from_id = '" + from_id + "' order by create_time desc limit 1)");
		String[] strs = str.split(",");

		return Double.parseDouble(strs[0]) == Double.parseDouble(strs[1]);
	}

	public boolean validate_price_sumTransAmountAndPayAmount() {
		String str = getActualValue(
				"SELECT price from debt_sale ds where id = (SELECT ds_id from invt_debt_sale_task where status = 5 and bo_id = '"
						+ bo_id + "' and from_id = '" + from_id
						+ "' order by create_time desc limit 1) order by ds.create_time desc");
		String str2 = getActualValue(
				"SELECT sum(trans_amount+pay_amount) from debt_sale ds where id = (SELECT ds_id from invt_debt_sale_task where status = 5 and bo_id = '"
						+ bo_id + "' and from_id = '" + from_id
						+ "' order by create_time desc limit 1) order by ds.create_time desc");
		return Double.parseDouble(str) == Double.parseDouble(str2);
	}

	public boolean validate_sumAmount_transAmount() {
		String str = getActualValue(
				"SELECT trans_amount from debt_sale ds where id = (SELECT ds_id from invt_debt_sale_task where status = 5 and bo_id = '"
						+ bo_id + "' and from_id = '" + from_id
						+ "' order by create_time desc limit 1)  order by ds.create_time desc");
		String str2 = getActualValue(
				"SELECT sum(amount) from invt_debt_sale_task_log idstl where status = 2 and task_id = (SELECT id from invt_debt_sale_task where status = 5 and bo_id = '"
						+ bo_id + "' and from_id = '" + from_id
						+ "' order by create_time desc limit 1)  order by idstl.create_time desc");
		return Double.parseDouble(str) == Double.parseDouble(str2);
	}

	public boolean validate_sumBuyNum_transferNum() {
		String str = getActualValue(
				"SELECT transfer_num from debt_sale ds where id = (SELECT ds_id from invt_debt_sale_task where status = 5 and bo_id = '"
						+ bo_id + "' and from_id = '" + from_id
						+ "' order by create_time desc limit 1) order by ds.create_time desc");
		String str2 = getActualValue(
				"SELECT sum(buy_num) from invt_debt_sale_task_log idstl where status = 2 and task_id = (SELECT id from invt_debt_sale_task where status = 5 and bo_id = '"
						+ bo_id + "' and from_id = '" + from_id
						+ "' order by create_time desc limit 1)  order by idstl.create_time desc");
		return Double.parseDouble(str) == Double.parseDouble(str2);
	}

	public boolean validate_sumBuyNum_transferNum2() {
		String str = getActualValue(
				"SELECT sum(buy_num) from debt_buy_log dbl where status = 1 and ds_id  = (SELECT ds_id from invt_debt_sale_task where status = 5 and bo_id = '"
						+ bo_id + "' and from_id = '" + from_id
						+ "' order by create_time desc limit 1)  order by dbl.create_time desc");
		String str2 = getActualValue(
				"SELECT transfer_num from debt_sale ds where status = 1 and id = (SELECT ds_id from invt_debt_sale_task where status = 5 and bo_id = '"
						+ bo_id + "' and from_id = '" + from_id
						+ "' order by create_time desc limit 1) order by ds.create_time desc");
		return Double.parseDouble(str) == Double.parseDouble(str2);
	}

	public boolean validate_CountInvtDebtSaleTaskLog_CountInvtProof(String task_status, String status) {
		String str = getActualValue("SELECT count(*) from invt_debt_sale_task_log idstl where status= " + status
				+ " and task_id = (SELECT id from invt_debt_sale_task where status = " + task_status + " and bo_id = '"
				+ bo_id + "' and from_id = '" + from_id
				+ "' order by create_time desc limit 1)  order by idstl.create_time desc");
		String str2 = getActualValue(
				"SELECT count(*) from invt_proof ip where biz_type = 1 and status = 1 and biz_id in (SELECT id from invt_debt_sale_task_log where status = "
						+ status + " and task_id = (SELECT id from invt_debt_sale_task where status = " + task_status
						+ " and bo_id = '" + bo_id + "' and from_id = '" + from_id
						+ "' order by create_time desc limit 1)) order by ip.create_time desc");
		return Double.parseDouble(str) == Double.parseDouble(str2);
	}

	public boolean validate_sumPriceIn_transAmount() {
		String str = getActualValue(
				"SELECT sum(price_in) from debt_buy_log dbl where status = 1 and ds_id  = (SELECT ds_id from invt_debt_sale_task where status = 5 and bo_id = '"
						+ bo_id + "' and from_id = '" + from_id
						+ "' order by create_time desc limit 1)  order by dbl.create_time desc");
		String str2 = getActualValue(
				"SELECT trans_amount from debt_sale ds where status = 1 and id = (SELECT ds_id from invt_debt_sale_task where status = 5 and bo_id = '"
						+ bo_id + "' and from_id = '" + from_id
						+ "' order by create_time desc limit 1) order by ds.create_time desc");
		return Double.parseDouble(str) == Double.parseDouble(str2);
	}

	public boolean validate_CountdebtBuyLog_CountinvtTrdOrder() {
		String str = getActualValue(
				"SELECT count(*) from debt_buy_log dbl where  status = 1 and ds_id = (SELECT ds_id from invt_debt_sale_task where status = 5 and bo_id = '"
						+ bo_id + "' and from_id = '" + from_id
						+ "' order by create_time desc limit 1)  order by dbl.create_time desc");
		String str2 = getActualValue(
				"SELECT count(*) from invt_trd_order ito where status= 2 and  trans_id in (SELECT trans_id from debt_buy_log where ds_id = (SELECT ds_id from invt_debt_sale_task where status = 5 and bo_id = '"
						+ bo_id + "' and from_id = '" + from_id
						+ "' order by create_time desc limit 1)) order by ito.create_time desc");
		return Double.parseDouble(str) == Double.parseDouble(str2);
	}

	public boolean validate_amount() {
		boolean flag = false;
		List<Object> lst = DBUtils.geMulLineValues("nono",
				"SELECT id from invt_debt_sale_task_log idstl where status = 2 and task_id = (SELECT id from invt_debt_sale_task where status = 5 and bo_id = '"
						+ bo_id + "' and from_id = '" + from_id
						+ "' order by create_time desc limit 1) order by idstl.create_time desc");
		for (Object object : lst) {
			String str = getActualValue(
					"SELECT dea.hold_num,tl.buy_num FROM debt_exchange_account dea LEFT JOIN invt_debt_sale_task_log tl on dea.va_id= tl.from_id  LEFT JOIN debt_sale ds on ds.id = tl.ds_id WHERE  tl.status = 2 and tl.from_type = 1 and tl.id = "
							+ object + " AND dea.bo_id = ds.bo_id order by dea.create_time desc");
			String[] strs = str.split(",");
			double[] values = new double[2];
			for (int i = 0; i < strs.length; i++) {
				values[i] = Double.parseDouble(strs[i]);
			}
			String str2 = getActualValue(
					"SELECT sum(ba.price_principal),tl.amount FROM borrows_accept ba LEFT JOIN invt_debt_sale_task_log tl on tl.from_id = ba.va_id LEFT JOIN debt_sale ds on tl.ds_id = ds.id WHERE  ba.bo_id = ds.bo_id and ba.is_pay = 0 and tl.id = "
							+ object + " order by ba.create_time desc");
			String[] strs2 = str2.split(",");
			double[] values2 = new double[2];
			for (int i = 0; i < strs2.length; i++) {
				values2[i] = Double.parseDouble(strs2[i]);
			}
			System.out.println("************object=" + object + "*******str=" + str + "************str2" + str2);
			flag = values2[1] == (values[1] / values[0]) * values2[0];
			if (flag == false) {
				return false;
			}
		}
		return true;
	}

	public boolean validate_holdNum_transferNum() {
		String str = getActualValue(
				"SELECT dea.hold_num, ds.transfer_num FROM debt_exchange_account dea LEFT JOIN  invt_debt_sale_task idst on idst.from_id= dea.va_id LEFT JOIN debt_sale ds on ds.id = idst.ds_id WHERE  idst.from_type=1 and dea.bo_id = ds.bo_id AND idst.id = (SELECT id from invt_debt_sale_task where status = 99 and bo_id = '"
						+ bo_id + "' and from_id = '" + from_id
						+ "' order by create_time desc limit 1) order by dea.create_time desc");
		String[] strs = str.split(",");
		return Double.parseDouble(strs[1]) == Double.parseDouble(strs[0]);
	}

	public boolean validate_CountDebtBuyLog_CountInvtProof() {
		String str = getActualValue(
				"SELECT count(*) from debt_buy_log dbl where status = 1 and ds_id = (SELECT ds_id from invt_debt_sale_task where status = 5 and bo_id = '"
						+ bo_id + "' and from_id = '" + from_id
						+ "' order by create_time desc limit 1)  order by dbl.create_time desc");
		String str2 = getActualValue(
				"SELECT count(*) from invt_proof ip where biz_type = 2 and status = 1 and biz_id in (SELECT id from debt_buy_log where ds_id = (SELECT ds_id from invt_debt_sale_task where status = 5 and bo_id = '"
						+ bo_id + "' and from_id = '" + from_id
						+ "' order by create_time desc limit 1)) order by ip.create_time desc");
		return Double.parseDouble(str) == Double.parseDouble(str2);
	}

	public boolean validate_sumHoldNum() {
		String str = getActualValue("SELECT sum(hold_num) from debt_exchange_account dea where  va_id = " + from_id
				+ " and bo_id = '" + bo_id + "' order by dea.create_time desc");
		double except_holdNum = Double.parseDouble(str);
		return except_holdNum == HOLD_NUM;
	}

	public boolean validate_sumPrice(double exceptValue) {
		String str = getActualValue(
				"SELECT sum(ba.price) FROM borrows_accept ba LEFT JOIN invt_debt_sale_task it ON it.from_id = ba.va_id and ba.bo_id = it.bo_id WHERE  ba.is_pay = 0 AND it.from_type = 1 and it.from_id  = "
						+ from_id + " and it.bo_id = '" + bo_id + "' and from_id = '" + from_id
						+ "' order by ba.create_time desc");
		return exceptValue == Double.parseDouble(str);
	}

	public boolean validate_sumPriceInterest(double exceptValue) {
		String str = getActualValue(
				"SELECT sum(ba.price_interest) FROM borrows_accept ba LEFT JOIN invt_debt_sale_task it ON it.from_id = ba.va_id and ba.bo_id = it.bo_id WHERE  ba.is_pay = 0 AND it.from_type = 1 and it.from_id  = "
						+ from_id + " and it.bo_id = '" + bo_id + "' and from_id = '" + from_id
						+ "' order by ba.create_time desc");
		return exceptValue == Double.parseDouble(str);
	}

	public boolean validate_sumPricePrincipal(double exceptValue) {
		String str = getActualValue(
				"SELECT sum(ba.price_principal) FROM borrows_accept ba LEFT JOIN invt_debt_sale_task it ON it.from_id = ba.va_id and ba.bo_id = it.bo_id WHERE  ba.is_pay = 0 AND it.from_type = 1 and it.from_id  = "
						+ from_id + " and it.bo_id = '" + bo_id + "' and from_id = '" + from_id
						+ "' order by ba.create_time desc");
		return exceptValue == Double.parseDouble(str);
	}

	public boolean validate_subPriceAndPayAmount_sumPricePrincipal() {
		String str = getActualValue(
				"SELECT sum(ba.price_principal),ds.price-ds.pay_amount as amount FROM borrows_accept ba LEFT JOIN invt_debt_sale_task idst on idst.from_id = ba.va_id LEFT JOIN debt_sale ds on ds.id = idst.ds_id WHERE  idst.from_type=1 and ba.bo_id = ds.bo_id and ba.is_pay =0 and idst.id = (SELECT id from invt_debt_sale_task where status = 99 and bo_id = '"
						+ bo_id + "' and from_id = '" + from_id
						+ "' order by create_time desc limit 1) order by ba.create_time desc");
		String[] strs = str.split(",");
		return Double.parseDouble(strs[1]) == Double.parseDouble(strs[0]);
	}

	public boolean validate_residueNum_subTransferNumSumBuyNum() {
		String value = getActualValue(
				"SELECT ds_id from invt_debt_sale_task where `status` =6  and from_id = " + from_id);
		String[] values = value.split(",");
		for (String string : values) {
			String str = getActualValue(
					"SELECT ds.transfer_num- sum(idstl.buy_num),ds.residue_num FROM invt_debt_sale_task_log idstl LEFT JOIN  debt_sale ds on ds.id = idstl.ds_id WHERE  idstl.status = 2 and idstl.ds_id = "
							+ string);
			String[] strs = str.split(",");
			boolean flag = Double.parseDouble(strs[1]) == Double.parseDouble(strs[0]);
			if (flag == false) {
				return false;
			}
		}

		return true;
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

	public static void main(String[] args) {
		// ************object=10099907*******str=0.0000,2.7486************str20.00,274.86
		// values2[1] == (values[1] / values[0]) * values2[0]
		System.out.println((2.7486 / 0.0000) * 20.00);
	}
}
