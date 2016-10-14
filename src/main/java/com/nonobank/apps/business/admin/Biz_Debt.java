package com.nonobank.apps.business.admin;

import java.util.List;
import com.nonobank.apps.page.admin.Page_Debt;
import com.nonobank.apps.utils.db.DBUtils;

public class Biz_Debt {
	Page_Debt page_Debt = new Page_Debt();
	public static String bo_id;
	public static String from_id = "43276";
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

	private StringBuffer getSql(String sql) {
		String str = " order by create_time desc limit 1";
		StringBuffer sb = new StringBuffer();
		sb.append(sql);
		if (bo_id != null) {
			sb.append(" and bo_id = '" + bo_id + "'");
		}
		if (from_id != null) {
			sb.append(" and from_id = '" + from_id + "'");
		}
		if (bo_id == null) {
			str = " order by create_time desc";
		}
		sb.append(str);
		return sb;
	}

	public boolean validate_lockNum(double exceptValue, String task_status, String status) {
		String sql = "SELECT ds_id from invt_debt_sale_task where status = " + task_status;
		StringBuffer sb = getSql(sql);
		List<Object> list = DBUtils.getMulLineValues("nono", sb.toString());
		for (Object dsId : list) {
			String str = getActualValue("SELECT lock_num from debt_sale where status = " + status + " and id =" + dsId);
			double actualValue = Double.parseDouble(str);
			System.out.println("validate_lockNum********str1=" + actualValue + "********str2=" + exceptValue);
			if (actualValue != exceptValue) {
				return false;
			}
		}
		return true;
	}

	public boolean validate_residueNum() {
		String str = getActualValue(
				"SELECT residue_num from debt_sale where id = (SELECT ds_id from invt_debt_sale_task where status = 5 and bo_id = '"
						+ bo_id + "' and from_id = '" + from_id + "' order by create_time desc limit 1)");
		double except_residueNum = Double.parseDouble(str);
		System.out.println("validate_residueNum********str1=" + except_residueNum + "********str2=" + RESIDUE_NUM);
		return except_residueNum == RESIDUE_NUM;
	}

	public boolean validate_residueNum_transferNum() {
		String str = getActualValue(
				"SELECT residue_num,transfer_Num from debt_sale where id = (SELECT ds_id from invt_debt_sale_task where status = 5 and bo_id = '"
						+ bo_id + "' and from_id = '" + from_id + "' order by create_time desc limit 1)");
		String[] strs = str.split(",");
		System.out.println("validate_residueNum_transferNum********str1=" + strs[0] + "********str2="
				+ Double.parseDouble(strs[1]));
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
		System.out.println("validate_price_sumTransAmountAndPayAmount********str1=" + Double.parseDouble(str)
				+ "********str2=" + Double.parseDouble(str2));
		return Double.parseDouble(str) == Double.parseDouble(str2);
	}

	public boolean validate_sumAmount_transAmount(String status) {
		String sql = "SELECT id from invt_debt_sale_task where status = " + status;
		StringBuffer sb = getSql(sql);
		List<Object> lst = DBUtils.getMulLineValues("nono", sb.toString());
		for (Object id : lst) {
			String str = getActualValue(
					"SELECT sum(idstl.amount),ds.trans_amount FROM invt_debt_sale_task_log idstl LEFT JOIN  invt_debt_sale_task idst on idst.id = idstl.task_id LEFT JOIN debt_sale ds on ds.id = idst.ds_id WHERE idst.id = "
							+ id + " and idstl.status = 2");
			String[] strs = str.split(",");
			System.out.println("validate_sumAmount_transAmount********str1=" + Double.parseDouble(strs[0])
					+ "********str2=" + Double.parseDouble(strs[1]));
			if (Double.parseDouble(strs[0]) != Double.parseDouble(strs[1])) {
				return false;
			}
		}
		return true;
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
		System.out.println("validate_sumBuyNum_transferNum********str1=" + Double.parseDouble(str) + "********str2="
				+ Double.parseDouble(str2));
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
		System.out.println("validate_sumBuyNum_transferNum2********str1=" + Double.parseDouble(str) + "********str2="
				+ Double.parseDouble(str2));
		return Double.parseDouble(str) == Double.parseDouble(str2);
	}

	public boolean validate_CountInvtDebtSaleTaskLog_CountInvtProof(String task_status, String status) {
		String sql = "SELECT bo_id from invt_debt_sale_task where status = " + status;
		StringBuffer sb = getSql(sql);
		List<Object> lst = DBUtils.getMulLineValues("nono", sb.toString());
		for (Object boId : lst) {
			String str = getActualValue("SELECT count(*) from invt_debt_sale_task_log idstl where status= " + status
					+ " and task_id = (SELECT id from invt_debt_sale_task where status = " + task_status
					+ " and bo_id = '" + boId + "' and from_id = '" + from_id
					+ "' order by create_time desc limit 1)  order by idstl.create_time desc");
			String str2 = getActualValue(
					"SELECT count(*) from invt_proof ip where biz_type = 1 and status = 1 and biz_id in (SELECT id from invt_debt_sale_task_log where status = "
							+ status + " and task_id = (SELECT id from invt_debt_sale_task where status = "
							+ task_status + " and bo_id = '" + boId + "' and from_id = '" + from_id
							+ "' order by create_time desc limit 1)) order by ip.create_time desc");
			System.out.println("validate_CountInvtDebtSaleTaskLog_CountInvtProof********str1=" + Double.parseDouble(str)
					+ "********str2=" + Double.parseDouble(str2));
			if (Double.parseDouble(str) != Double.parseDouble(str2)) {
				return false;
			}
		}
		return true;

	}

	public boolean validate_sumPriceIn_transAmount(String status) {
		String sql = "SELECT id from invt_debt_sale_task where status = " + status;
		StringBuffer sb = getSql(sql);
		List<Object> lst = DBUtils.getMulLineValues("nono", sb.toString());
		for (Object taskId : lst) {
			String str = getActualValue(
					"SELECT sum(dbl.price_in),ds.trans_amount FROM debt_buy_log dbl LEFT JOIN  debt_sale ds on ds.id = dbl.ds_id LEFT JOIN  invt_debt_sale_task idst on idst.ds_id = ds.id WHERE  idst.id = "
							+ taskId + " and dbl.status = 1");
			String[] strs = str.split(",");
			if (Double.parseDouble(strs[0]) != Double.parseDouble(strs[1])) {
				return false;
			}
		}
		return true;
	}

	public boolean validate_CountdebtBuyLog_CountInvtTrdOrder() {
		String str = getActualValue(
				"SELECT count(*) from debt_buy_log dbl where  status = 1 and ds_id = (SELECT ds_id from invt_debt_sale_task where status = 5 and bo_id = '"
						+ bo_id + "' and from_id = '" + from_id
						+ "' order by create_time desc limit 1)  order by dbl.create_time desc");
		String str2 = getActualValue(
				"SELECT count(*) from invt_trd_order ito where status= 2 and  trans_id in (SELECT trans_id from debt_buy_log where ds_id = (SELECT ds_id from invt_debt_sale_task where status = 5 and bo_id = '"
						+ bo_id + "' and from_id = '" + from_id
						+ "' order by create_time desc limit 1)) order by ito.create_time desc");
		System.out.println("validate_CountdebtBuyLog_CountInvtTrdOrder********str1=" + Double.parseDouble(str)
				+ "********str2=" + Double.parseDouble(str2));
		return Double.parseDouble(str) == Double.parseDouble(str2);
	}

	public boolean validate_amount() {
		List<Object> lst = DBUtils.getMulLineValues("nono",
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
			System.out.println("validate_amount********str1=" + values2[1] + "********str2="
					+ (values[1] / values[0]) * values2[0]);

			if (values2[1] != (values[1] / values[0]) * values2[0]) {
				return false;
			}
		}
		return true;
	}

	public boolean validate_pricePrincipal(String status) {
		String sql = "SELECT id from invt_debt_sale_task where status = " + status;
		StringBuffer sb = getSql(sql);
		List<Object> taskIds = DBUtils.getMulLineValues("nono", sb.toString());
		for (Object taskId : taskIds) {
			String str = getActualValue(
					"SELECT sum(idstl.amount)/sum(idstl.buy_num)*dea.hold_num price,  idstl.from_id vaid ,dea.bo_id boid FROM invt_debt_sale_task_log idstl  INNER JOIN debt_sale ds on ds.id = idstl.ds_id  INNER JOIN debt_exchange_account dea on dea.bo_id = ds.bo_id  WHERE idstl.status = 2 AND idstl.task_id = "
							+ taskId + " and dea.va_id = idstl.from_id GROUP BY  idstl.from_id,idstl.ds_id");
			String[] strs = str.split(",");
			double[] values = new double[3];
			for (int i = 0; i < strs.length; i++) {
				values[i] = Double.parseDouble(strs[i]);
			}
			String str2 = getActualValue("SELECT sum(price_principal) FROM borrows_accept WHERE va_id =" + values[1]
					+ " and bo_id=" + values[2] + " and borrows_accept.is_pay =0");
			System.out.println(
					"validate_pricePrincipal********str1=" + values[0] + "********str2=" + Double.parseDouble(str2));
			if (values[0] != Double.parseDouble(str2)) {
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
		System.out.println("validate_holdNum_transferNum********str1=" + Double.parseDouble(strs[0]) + "********str2="
				+ Double.parseDouble(strs[1]));
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
		System.out.println("validate_CountDebtBuyLog_CountInvtProof********str1=" + Double.parseDouble(str)
				+ "********str2=" + Double.parseDouble(str2));
		return Double.parseDouble(str) == Double.parseDouble(str2);
	}

	public boolean validate_sumHoldNum() {
		String str = getActualValue("SELECT sum(hold_num) from debt_exchange_account dea where  va_id = " + from_id
				+ " and bo_id = '" + bo_id + "' order by dea.create_time desc");
		System.out.println("validate_sumHoldNum*****str1=" + HOLD_NUM + "*****str2=" + Double.parseDouble(str));
		return Double.parseDouble(str) == HOLD_NUM;
	}

	public boolean validate_sumPrice(double exceptValue) {
		String str = getActualValue(
				"SELECT sum(ba.price) FROM borrows_accept ba LEFT JOIN invt_debt_sale_task it ON it.from_id = ba.va_id and ba.bo_id = it.bo_id WHERE  ba.is_pay = 0 AND it.from_type = 1 and it.from_id  = "
						+ from_id + " and it.bo_id = '" + bo_id + "' and from_id = '" + from_id
						+ "' order by ba.create_time desc");
		System.out.println("validate_sumPrice*****str1=" + exceptValue + "*****str2=" + Double.parseDouble(str));
		return exceptValue == Double.parseDouble(str);
	}

	public boolean validate_sumPriceInterest(double exceptValue) {
		String str = getActualValue(
				"SELECT sum(ba.price_interest) FROM borrows_accept ba LEFT JOIN invt_debt_sale_task it ON it.from_id = ba.va_id and ba.bo_id = it.bo_id WHERE  ba.is_pay = 0 AND it.from_type = 1 and it.from_id  = "
						+ from_id + " and it.bo_id = '" + bo_id + "' and from_id = '" + from_id
						+ "' order by ba.create_time desc");
		System.out
				.println("validate_sumPriceInterest*****str1=" + exceptValue + "*****str2=" + Double.parseDouble(str));
		return exceptValue == Double.parseDouble(str);
	}

	public boolean validate_sumPricePrincipal(double exceptValue) {
		String str = getActualValue(
				"SELECT sum(ba.price_principal) FROM borrows_accept ba LEFT JOIN invt_debt_sale_task it ON it.from_id = ba.va_id and ba.bo_id = it.bo_id WHERE  ba.is_pay = 0 AND it.from_type = 1 and it.from_id  = "
						+ from_id + " and it.bo_id = '" + bo_id + "' and from_id = '" + from_id
						+ "' order by ba.create_time desc");
		System.out
				.println("validate_sumPricePrincipal*****str1=" + exceptValue + "*****str2=" + Double.parseDouble(str));
		return exceptValue == Double.parseDouble(str);
	}

	public boolean validate_subPriceAndPayAmount_sumPricePrincipal() {
		String str = getActualValue(
				"SELECT sum(ba.price_principal),ds.price-ds.pay_amount as amount FROM borrows_accept ba LEFT JOIN invt_debt_sale_task idst on idst.from_id = ba.va_id LEFT JOIN debt_sale ds on ds.id = idst.ds_id WHERE  idst.from_type=1 and ba.bo_id = ds.bo_id and ba.is_pay =0 and idst.id = (SELECT id from invt_debt_sale_task where status = 99 and bo_id = '"
						+ bo_id + "' and from_id = '" + from_id
						+ "' order by create_time desc limit 1) order by ba.create_time desc");
		String[] strs = str.split(",");
		System.out.println("validate_subPriceAndPayAmount_sumPricePrincipal*****str1=" + Double.parseDouble(strs[1])
				+ "*****str2=" + Double.parseDouble(strs[0]));
		return Double.parseDouble(strs[1]) == Double.parseDouble(strs[0]);
	}

	public boolean validate_residueNum_subTransferNumSumBuyNum(String status) {
		String sql = "SELECT ds_id from invt_debt_sale_task where status = " + status;
		StringBuffer sb = getSql(sql);
		List<Object> values = DBUtils.getMulLineValues("nono", sb.toString());
		for (Object string : values) {
			String str = getActualValue(
					"SELECT ds.transfer_num- sum(idstl.buy_num),ds.residue_num FROM invt_debt_sale_task_log idstl LEFT JOIN  debt_sale ds on ds.id = idstl.ds_id WHERE  idstl.status = 2 and idstl.ds_id = "
							+ string);
			String[] strs = str.split(",");
			System.out.println("validate_residueNum_subTransferNumSumBuyNum*****str1=" + Double.parseDouble(strs[1])
					+ "*****str2=" + Double.parseDouble(strs[0]));
			if (Double.parseDouble(strs[1]) != Double.parseDouble(strs[0])) {
				return false;
			}
		}

		return true;
	}

	public boolean validate_sumBuyNum_subTransferNumAndResidueNum(String status) {
		String sql = "SELECT bo_id from invt_debt_sale_task where status = " + status;
		StringBuffer sb = getSql(sql);
		List<Object> values = DBUtils.getMulLineValues("nono", sb.toString());

		for (Object boId : values) {
			String str = getActualValue(
					"SELECT sum(buy_num) from debt_buy_log dbl where status = 1 and ds_id  = (SELECT ds_id from invt_debt_sale_task where status = 6 and bo_id = "
							+ boId + "  and from_id=" + from_id + ")");
			String str2 = getActualValue(
					"SELECT transfer_num-residue_num from debt_sale ds where id = (SELECT ds_id from invt_debt_sale_task where status = 6 and bo_id = "
							+ boId + "  and from_id=" + from_id + ")");
			System.out.println("validate_sumBuyNum_subTransferNumAndResidueNum********str1=" + Double.parseDouble(str)
					+ "********str2=" + Double.parseDouble(str2));
			if (Double.parseDouble(str) != Double.parseDouble(str2)) {
				return false;
			}
		}
		return true;
	}

	public boolean validate_residueNum_sumHoldNum() {
		List<Object> object = DBUtils.getMulLineValues("nono",
				"SELECT bo_id,ds_id from invt_debt_sale_task where `status` =6  and from_id = " + from_id);
		for (Object oneLineValues : object) {
			String value = oneLineValues.toString();
			String[] strs = value.split(",");
			String str = getActualValue("SELECT sum(hold_num) from debt_exchange_account dea where  va_id =" + from_id
					+ " and bo_id = " + strs[0]);
			String str2 = getActualValue("SELECT residue_num FROM   debt_sale ds WHERE  id = " + strs[1]);
			System.out.println("validate_residueNum_sumHoldNum********str1=" + Double.parseDouble(str) + "********str2="
					+ Double.parseDouble(str2));
			if (Double.parseDouble(str) != Double.parseDouble(str2)) {
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