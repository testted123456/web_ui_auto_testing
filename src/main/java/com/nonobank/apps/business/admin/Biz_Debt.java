package com.nonobank.apps.business.admin;

import java.text.DecimalFormat;
import java.util.List;
import com.nonobank.apps.page.admin.Page_Debt;
import com.nonobank.apps.utils.data.Assertion;
import com.nonobank.apps.utils.db.DBUtils;
import com.nonobank.apps.utils.webintegration.Info;
import com.nonobank.apps.utils.webintegration.Params;
@Info(desc="债务页面",dependency="com.nonobank.apps.business.admin.Biz_Home",isDisabled=true)
public class Biz_Debt {
	Page_Debt page_Debt = new Page_Debt();
	public static String bo_id = "983616";
	public static String from_id = "93660";
	public static String partSuccessTargetFpid;
	public static double amount;

	@Info(desc="债转页面",dependency="",isDisabled=true)
	@Params(type={"String","String","String"},name={"debtType","fpId","targetFpid"},desc={"债务类型","债务id号","目标id号"})
	public void debt(String debtType, String fpId, String targetFpid) {
		page_Debt.input_fpId(fpId);
		page_Debt.click_query();
		switch (debtType) {
		case "PartSuccess":
			page_Debt.handle_debtMain(null);
			page_Debt.select_targetFpid(partSuccessTargetFpid);
			page_Debt.click_debtMain();
			break;

		case "Success":
			page_Debt.click_debtDetail();
			page_Debt.click_debt();
			break;
		case "Fail":

			page_Debt.select_targetFpid(targetFpid);
			page_Debt.click_debtDetail();
			page_Debt.click_debt();
			break;
		}
		if (page_Debt.isAlertExists(6000)) {
			page_Debt.acceptAlert();
		}
		System.out.println("**************bo_id=" + bo_id + "**************from_id=" + from_id);
	}

	@Info(desc="获取sql",dependency="debt()",isDisabled=true)
	@Params(type={"String","String"},name={"sql","task_status"},desc={"sql","任务状态"})
	private StringBuffer getSql(String sql, String task_status) {
		String str = " order by update_time desc limit 1";
		StringBuffer sb = new StringBuffer();
		sb.append(sql);
		if (from_id != null) {
			sb.append(" and from_id = '" + from_id + "'");
		}
		if (bo_id != null) {
			sb.append(" and status =" + task_status);
			sb.append(" and bo_id = '" + bo_id + "'");
		} else {
			sb.append(" and ds_id IN (SELECT invt_debt_sale_task.ds_id FROM invt_debt_sale_task where status ="
					+ task_status + ") group by ds_id HAVING count(1)=1");
			str = " order by update_time desc";
		}
		sb.append(str);
		return sb;
	}

	@Info(desc="验证锁数目",dependency="getSql()",isDisabled=true)
	@Params(type={"double","String","String"},name={"exceptValue","task_status","sale_status"},desc={"期望值","任务状态","销售状态"})
	public void validate_lockNum(double exceptValue, String task_status, String sale_status) {
		String sql = "SELECT ds_id from invt_debt_sale_task where 1=1";
		try {
			sql = getSql(sql, task_status).toString();
			List<Object> dsIds = DBUtils.getMulLineValues("nono", sql);
			for (Object dsId : dsIds) {
				sql = "SELECT lock_num from debt_sale where status = " + sale_status + " and id =" + dsId;
				String str = DBUtils.getOneLineValues("nono", sql);
				Assertion.assertEquals(exceptValue, Double.parseDouble(str), Biz_Debt.class, sql);
			}
		} catch (Exception e) {
			Assertion.assertEquals(Biz_Debt.class, sql);
		}
	}

	@Info(desc="验证residue数目",dependency="validate_lockNum()",isDisabled=true)
	@Params(type={"double","String"},name={"exceptValue","task_status"},desc={"期望值","任务状态"})
	public void validate_residueNum(double exceptValue, String task_status) {
		String sql = getSql("SELECT ds_id from invt_debt_sale_task where 1=1", task_status).toString();
		try {
			List<Object> dsIds = DBUtils.getMulLineValues("nono", sql);
			for (Object dsId : dsIds) {
				sql = "SELECT residue_num from debt_sale where  id =" + dsId;
				String str = DBUtils.getOneLineValues("nono", sql);
				Assertion.assertEquals(exceptValue, Double.parseDouble(str), Biz_Debt.class, sql);
			}
		} catch (Exception e) {
			Assertion.assertEquals(Biz_Debt.class, sql);
		}
	}

	@Info(desc="验证residue交易数目",dependency="validate_residueNum()",isDisabled=true)
	@Params(type={"String"},name={"task_status"},desc={"任务状态"})
	public void validate_residueNum_transferNum(String task_status) {
		String sql = getSql("SELECT ds_id from invt_debt_sale_task where 1=1", task_status).toString();
		try {
			List<Object> dsIds = DBUtils.getMulLineValues("nono", sql);
			for (Object dsId : dsIds) {
				sql = "SELECT residue_num,transfer_Num from debt_sale where id = " + dsId;
				String[] strs = DBUtils.getOneLineValues("nono", sql).split(",");
				Assertion.assertEquals(Double.parseDouble(strs[0]), Double.parseDouble(strs[1]), Biz_Debt.class, sql);
			}
		} catch (Exception e) {
			Assertion.assertEquals(Biz_Debt.class, sql);
		}
	}

	public void validate_price_sumTransAmountAndPayAmount(String task_status) {
		String sql = getSql("SELECT ds_id from invt_debt_sale_task where 1 =1", task_status).toString();
		try {
			List<Object> dsIds = DBUtils.getMulLineValues("nono", sql);
			for (Object dsId : dsIds) {
				sql = "SELECT price,sum(trans_amount+pay_amount) from debt_sale where  id =" + dsId;
				String[] strs = DBUtils.getOneLineValues("nono", sql).split(",");
				Assertion.assertEquals(Double.parseDouble(strs[0]), Double.parseDouble(strs[1]), Biz_Debt.class, sql);
			}
		} catch (Exception e) {
			Assertion.assertEquals(Biz_Debt.class, sql);
		}
	}

	public void validate_sumAmount_transAmount(String task_status, String log_status) {
		String sql = getSql("SELECT ds_id from invt_debt_sale_task where 1=1", task_status).toString();
		try {
			List<Object> dsIds = DBUtils.getMulLineValues("nono", sql);
			for (Object dsId : dsIds) {
				sql = "SELECT sum(idstl.amount),ds.trans_amount FROM invt_debt_sale_task_log idstl LEFT JOIN debt_sale ds on ds.id = idstl.ds_id WHERE idstl.status = "
						+ log_status + " and ds.id = " + dsId;
				String[] strs = DBUtils.getOneLineValues("nono", sql).split(",");
				Assertion.assertEquals(Double.parseDouble(strs[0]), Double.parseDouble(strs[1]), Biz_Debt.class, sql);
			}
		} catch (Exception e) {
			Assertion.assertEquals(Biz_Debt.class, sql);
		}
	}

	public void validate_sumBuyNum_transferNum(String task_status, String log_status) {
		String sql = getSql("SELECT ds_id from invt_debt_sale_task where 1 = 1", task_status).toString();
		try {
			List<Object> dsIds = DBUtils.getMulLineValues("nono", sql);
			for (Object dsId : dsIds) {
				sql = "SELECT sum(idstl.buy_num),ds.transfer_num FROM invt_debt_sale_task_log idstl LEFT JOIN  debt_sale ds on ds.id = idstl.ds_id WHERE  idstl.status = "
						+ log_status + " and ds.id =" + dsId;
				String[] strs = DBUtils.getOneLineValues("nono", sql).split(",");
				Assertion.assertEquals(Double.parseDouble(strs[0]), Double.parseDouble(strs[1]), Biz_Debt.class, sql);
			}
		} catch (Exception e) {
			Assertion.assertEquals(Biz_Debt.class, sql);
		}
	}

	public void validate_sumBuyNum_transferNum2(String task_status, String buy_log, String sale_status) {
		String sql = getSql("SELECT ds_id from invt_debt_sale_task where 1 =1", task_status).toString();
		try {
			List<Object> dsIds = DBUtils.getMulLineValues("nono", sql);
			for (Object dsId : dsIds) {
				sql = "SELECT sum(dbl.buy_num),ds.transfer_num from debt_buy_log dbl,debt_sale ds  where dbl.ds_id  = ds.id and dbl.ds_id  = "
						+ dsId;
				String[] strs = DBUtils.getOneLineValues("nono", sql).split(",");
				Assertion.assertEquals(Double.parseDouble(strs[0]), Double.parseDouble(strs[1]), Biz_Debt.class, sql);
			}
		} catch (Exception e) {
			Assertion.assertEquals(Biz_Debt.class, sql);
		}
	}

	public void validate_countInvtDebtSaleTaskLog_countInvtProof(String task_status, String log_status,
			String proof_status, String biz_type) {
		String sql = getSql("SELECT bo_id from invt_debt_sale_task where 1=1", task_status).toString();
		try {
			List<Object> boIds = DBUtils.getMulLineValues("nono", sql);
			for (Object boId : boIds) {
				sql = "SELECT count(*) from invt_debt_sale_task_log idstl  where status= " + log_status
						+ " and task_id = " + boId;
				String str = DBUtils.getOneLineValues("nono", sql);
				sql = "SELECT count(*) from invt_proof ip where biz_type = " + biz_type + " and status = "
						+ proof_status + " and biz_id in (SELECT id from invt_debt_sale_task_log where status = "
						+ log_status + " and task_id = " + boId + ")";
				String str2 = DBUtils.getOneLineValues("nono", sql);
				Assertion.assertEquals(Double.parseDouble(str), Double.parseDouble(str2), Biz_Debt.class, sql);
			}
		} catch (Exception e) {
			Assertion.assertEquals(Biz_Debt.class, sql);
		}
	}

	public void validate_sumPriceIn_transAmount(String task_status, String buy_status) {
		String sql = getSql("SELECT ds_id from invt_debt_sale_task where 1=1", task_status).toString();
		try {
			List<Object> ids = DBUtils.getMulLineValues("nono", sql);
			for (Object dsId : ids) {
				sql = "SELECT sum(dbl.price_in),ds.trans_amount FROM debt_buy_log dbl LEFT JOIN  debt_sale ds on ds.id = dbl.ds_id  WHERE  ds.id = "
						+ dsId + " and dbl.status = " + buy_status;
				String[] strs = DBUtils.getOneLineValues("nono", sql).split(",");
				Assertion.assertEquals(Double.parseDouble(strs[0]), Double.parseDouble(strs[1]), Biz_Debt.class, sql);
			}
		} catch (Exception e) {
			Assertion.assertEquals(Biz_Debt.class, sql);
		}
	}

	public void validate_countDebtBuyLog_countInvtTrdOrder(String task_status, String buy_status, String order_status) {
		String sql = getSql("SELECT ds_id from invt_debt_sale_task where 1=1", task_status).toString();
		try {
			List<Object> dsIds = DBUtils.getMulLineValues("nono", sql);
			for (Object dsId : dsIds) {
				sql = "SELECT count(*) from debt_buy_log dbl where  status = " + buy_status + " and ds_id = " + dsId;
				String str = DBUtils.getOneLineValues("nono", sql);
				sql = "SELECT count(*) from invt_trd_order ito where status= " + order_status
						+ " and  trans_id in (SELECT trans_id from debt_buy_log where ds_id = " + dsId + ")";
				String str2 = DBUtils.getOneLineValues("nono", sql);
				Assertion.assertEquals(Double.parseDouble(str), Double.parseDouble(str2), Biz_Debt.class, sql);
			}
		} catch (Exception e) {
			Assertion.assertEquals(Biz_Debt.class, sql);
		}
	}

	public void validate_amount(String task_status, String log_status, String from_type, String is_pay) {
		String sql = getSql("SELECT id from invt_debt_sale_task where 1=1", task_status).toString();
		try {
			List<Object> ids = DBUtils.getMulLineValues("nono", sql);
			for (Object id : ids) {
				List<Object> logIds = DBUtils.getMulLineValues("nono",
						"SELECT id from invt_debt_sale_task_log idstl where status = " + log_status + " and task_id = "
								+ id);
				for (Object logId : logIds) {
					sql = "SELECT dea.hold_num,tl.buy_num FROM debt_exchange_account dea LEFT JOIN invt_debt_sale_task_log tl on dea.va_id= tl.from_id  LEFT JOIN debt_sale ds on ds.id = tl.ds_id WHERE  tl.status = "
							+ log_status + " and tl.from_type =" + from_type + " and tl.id = " + logId
							+ " AND dea.bo_id = ds.bo_id";
					String str = DBUtils.getOneLineValues("nono", sql);
					String[] strs = str.split(",");
					sql = "SELECT sum(ba.price_principal),tl.amount FROM borrows_accept ba LEFT JOIN invt_debt_sale_task_log tl on tl.from_id = ba.va_id LEFT JOIN debt_sale ds on tl.ds_id = ds.id WHERE  ba.bo_id = ds.bo_id and ba.is_pay = "
							+ is_pay + " and tl.id = " + logId;
					String str2 = DBUtils.getOneLineValues("nono", sql);
					String[] strs2 = str2.split(",");
					DecimalFormat df = new DecimalFormat("#.00");
					str = df.format(Double.parseDouble(strs2[1]));
					str2 = df.format(
							(Double.parseDouble(strs[1]) / Double.parseDouble(strs[0])) * Double.parseDouble(strs2[0]));
					Assertion.assertEquals(str, str2, Biz_Debt.class, sql);
				}
			}
		} catch (Exception e) {
			Assertion.assertEquals(Biz_Debt.class, sql);
		}
	}

	public void validate_pricePrincipal(String task_status, String log_status, String is_pay) {
		String sql = getSql("SELECT id from invt_debt_sale_task where 1=1", task_status).toString();
		try {
			List<Object> ids = DBUtils.getMulLineValues("nono", sql);
			for (Object id : ids) {
				sql = "SELECT sum(idstl.amount)/sum(idstl.buy_num)*dea.hold_num price,  idstl.from_id vaid ,dea.bo_id boid FROM invt_debt_sale_task_log idstl  INNER JOIN debt_sale ds on ds.id = idstl.ds_id  INNER JOIN debt_exchange_account dea on dea.bo_id = ds.bo_id  WHERE idstl.status = "
						+ log_status + " AND idstl.task_id = " + id
						+ " and dea.va_id = idstl.from_id GROUP BY  idstl.from_id,idstl.ds_id";
				String str = DBUtils.getOneLineValues("nono", sql);
				String[] strs = str.split(",");
				sql = "SELECT sum(price_principal) FROM borrows_accept WHERE va_id =" + Double.parseDouble(strs[1])
						+ " and bo_id=" + Double.parseDouble(strs[2]) + " and borrows_accept.is_pay =" + is_pay;
				String str2 = DBUtils.getOneLineValues("nono", sql);
				Assertion.assertEquals(Double.parseDouble(strs[0]), Double.parseDouble(str2), Biz_Debt.class, sql);
			}
		} catch (Exception e) {
			Assertion.assertEquals(Biz_Debt.class, sql);
		}
	}

	public void validate_holdNum_transferNum(String task_status, String from_type) {
		String sql = getSql("SELECT ds_id from invt_debt_sale_task where 1=1", task_status).toString();
		try {
			List<Object> dsIds = DBUtils.getMulLineValues("nono", sql);
			for (Object dsId : dsIds) {
				sql = "SELECT dea.hold_num, ds.transfer_num FROM debt_exchange_account dea LEFT JOIN  invt_debt_sale_task idst on idst.from_id= dea.va_id LEFT JOIN debt_sale ds on ds.id = idst.ds_id WHERE  idst.from_type="
						+ from_type + " and dea.bo_id = ds.bo_id AND idst.ds_id = " + dsId;
				String[] strs = DBUtils.getOneLineValues("nono", sql).split(",");
				Assertion.assertEquals(Double.parseDouble(strs[0]), Double.parseDouble(strs[1]), Biz_Debt.class, sql);
			}
		} catch (Exception e) {
			Assertion.assertEquals(Biz_Debt.class, sql);
		}
	}

	public void validate_countDebtBuyLog_countInvtProof(String task_status, String buy_status, String biz_type,
			String proof_status) {
		String sql = getSql("SELECT ds_id from invt_debt_sale_task where 1=1", task_status).toString();
		try {
			List<Object> dsIds = DBUtils.getMulLineValues("nono", sql);
			for (Object dsId : dsIds) {
				sql = "SELECT count(*) from debt_buy_log dbl where status = " + buy_status + " and ds_id = " + dsId;
				String str = DBUtils.getOneLineValues("nono", sql);
				sql = "SELECT count(*) from invt_proof ip where biz_type = " + biz_type + " and status = "
						+ proof_status + " and biz_id in (SELECT id from debt_buy_log where  ds_id = " + dsId + ") ";
				String str2 = DBUtils.getOneLineValues("nono", sql);
				Assertion.assertEquals(Double.parseDouble(str), Double.parseDouble(str2), Biz_Debt.class, sql);
			}
		} catch (Exception e) {
			Assertion.assertEquals(Biz_Debt.class, sql);
		}
	}

	public void validate_sumHoldNum(double exceptValue) {
		String sql = "SELECT sum(hold_num) from debt_exchange_account dea where  va_id = " + from_id + " and bo_id = '"
				+ bo_id + "' order by dea.create_time desc";
		try {
			String str = DBUtils.getOneLineValues("nono", sql);
			Assertion.assertEquals(exceptValue, Double.parseDouble(str), Biz_Debt.class, sql);
		} catch (Exception e) {
			Assertion.assertEquals(Biz_Debt.class, sql);
		}
	}

	public void validate_sumPrice(double exceptValue, String is_pay, String from_type) {
		String sql = "SELECT sum(ba.price) FROM borrows_accept ba LEFT JOIN invt_debt_sale_task it ON it.from_id = ba.va_id and ba.bo_id = it.bo_id WHERE  ba.is_pay = "
				+ is_pay + " AND it.from_type = " + from_type + " and it.from_id  = " + from_id + " and it.bo_id = '"
				+ bo_id + "' and from_id = '" + from_id + "' order by ba.create_time desc";
		try {
			String str = DBUtils.getOneLineValues("nono", sql);
			Assertion.assertEquals(exceptValue, Double.parseDouble(str), Biz_Debt.class, sql);
		} catch (Exception e) {
			Assertion.assertEquals(Biz_Debt.class, sql);
		}
	}

	public void validate_sumPriceInterest(double exceptValue, String is_pay, String from_type) {
		String sql = "SELECT sum(ba.price_interest) FROM borrows_accept ba LEFT JOIN invt_debt_sale_task it ON it.from_id = ba.va_id and ba.bo_id = it.bo_id WHERE  ba.is_pay = "
				+ is_pay + " AND it.from_type = " + from_type + " and it.from_id  = " + from_id + " and it.bo_id = '"
				+ bo_id + "' and from_id = '" + from_id + "' order by ba.create_time desc";
		try {
			String str = DBUtils.getOneLineValues("nono", sql);
			Assertion.assertEquals(exceptValue, Double.parseDouble(str), Biz_Debt.class, sql);
		} catch (Exception e) {
			Assertion.assertEquals(Biz_Debt.class, sql);
		}
	}

	public void validate_sumPricePrincipal(double exceptValue, String is_pay, String from_type) {
		String sql = "SELECT sum(ba.price_principal) FROM borrows_accept ba LEFT JOIN invt_debt_sale_task it ON it.from_id = ba.va_id and ba.bo_id = it.bo_id WHERE  ba.is_pay = "
				+ is_pay + " AND it.from_type = " + from_type + " and it.from_id  = " + from_id + " and it.bo_id = '"
				+ bo_id + "' and from_id = '" + from_id + "' order by ba.create_time desc";
		try {
			String str = DBUtils.getOneLineValues("nono", sql);
			Assertion.assertEquals(exceptValue, Double.parseDouble(str), Biz_Debt.class, sql);
		} catch (Exception e) {
			Assertion.assertEquals(Biz_Debt.class, sql);
		}
	}

	public void validate_subPriceAndPayAmount_sumPricePrincipal(String task_status, String is_pay, String from_type) {
		String sql = getSql("SELECT id from invt_debt_sale_task where 1=1", task_status).toString();
		try {
			List<Object> ids = DBUtils.getMulLineValues("nono", sql);
			for (Object id : ids) {
				sql = "SELECT sum(ba.price_principal),ds.price-ds.pay_amount as amount FROM borrows_accept ba LEFT JOIN invt_debt_sale_task idst on idst.from_id = ba.va_id LEFT JOIN debt_sale ds on ds.id = idst.ds_id WHERE  idst.from_type="
						+ from_type + " and ba.bo_id = ds.bo_id and ba.is_pay =" + is_pay + " and idst.id = " + id;
				String[] strs = DBUtils.getOneLineValues("nono", sql).split(",");
				Assertion.assertEquals(Double.parseDouble(strs[0]), Double.parseDouble(strs[1]), Biz_Debt.class, sql);
			}
		} catch (Exception e) {
			Assertion.assertEquals(Biz_Debt.class, sql);
		}
	}

	public void validate_residueNum_subTransferNumSumBuyNum(String task_status, String log_status) {
		String sql = getSql("SELECT ds_id from invt_debt_sale_task where 1=1", task_status).toString();
		try {
			List<Object> dsIds = DBUtils.getMulLineValues("nono", sql);
			for (Object dsId : dsIds) {
				sql = "SELECT ds.transfer_num- sum(idstl.buy_num),ds.residue_num FROM invt_debt_sale_task_log idstl LEFT JOIN  debt_sale ds on ds.id = idstl.ds_id WHERE  idstl.status = "
						+ log_status + " and idstl.ds_id = " + dsId;
				String[] strs = DBUtils.getOneLineValues("nono", sql).split(",");
				Assertion.assertEquals(Double.parseDouble(strs[0]), Double.parseDouble(strs[1]), Biz_Debt.class, sql);
			}
		} catch (Exception e) {
			Assertion.assertEquals(Biz_Debt.class, sql);
		}
	}

	public void validate_sumBuyNum_subTransferNumAndResidueNum(String task_status, String buy_status) {
		String sql = getSql("SELECT ds_id from invt_debt_sale_task where 1=1", task_status).toString();
		try {
			List<Object> dsIds = DBUtils.getMulLineValues("nono", sql);
			for (Object dsId : dsIds) {
				sql = "SELECT sum(dbl.buy_num),ds.transfer_num-ds.residue_num from debt_buy_log dbl,debt_sale ds where dbl.ds_id=ds.id and dbl.status = "
						+ buy_status + " and ds.id = " + dsId;
				String[] strs = DBUtils.getOneLineValues("nono", sql).split(",");
				Assertion.assertEquals(Double.parseDouble(strs[0]), Double.parseDouble(strs[1]), Biz_Debt.class, sql);
			}
		} catch (Exception e) {
			Assertion.assertEquals(Biz_Debt.class, sql);
		}
	}

	public void validate_residueNum_sumHoldNum(String task_status) {
		String sql = getSql("SELECT ds_id from invt_debt_sale_task where 1=1", task_status).toString();
		try {
			List<Object> dsIds = DBUtils.getMulLineValues("nono", sql);
			for (Object dsId : dsIds) {
				sql = "SELECT dea.hold_num,ds.residue_num FROM debt_sale ds LEFT JOIN  debt_exchange_account dea   on dea.va_id = ds.va_id and ds.bo_id = dea.bo_id WHERE  ds.id = "
						+ dsId;
				String[] strs = DBUtils.getOneLineValues("nono", sql).split(",");
				Assertion.assertEquals(Double.parseDouble(strs[0]), Double.parseDouble(strs[1]), Biz_Debt.class, sql);
			}
		} catch (Exception e) {
			Assertion.assertEquals(Biz_Debt.class, sql);
		}
	}
}
