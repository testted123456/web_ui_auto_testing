package com.nonobank.apps.utils.data;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import com.nonobank.apps.utils.db.DBUtils;
import com.nonobank.apps.utils.entity.FinancePlan;

public class FinancePlanUtils {
	public static List<String> getfpIds(FinancePlan financePlan) {
		List<String> fpIds = new ArrayList<>();
		Connection con = DBUtils.getConnection("nono");
		FinancePlan.setFinancePlanCondition(financePlan);
		String sql = "SELECT id FROM finance_plan " + FinancePlan.getCondition();
		List<Object[]> objects = DBUtils.getMulLine(con, sql);
		for (Object[] id : objects) {
			fpIds.add(id[0].toString());
		}
		return fpIds;
	}

	public static String getVipAccount(String filedName, String userId, String fpId) {
		Connection con = DBUtils.getConnection("nono");
		String sql = "SELECT " + filedName + " FROM vip_account WHERE  user_id=" + userId + " and fp_Id=" + fpId;
		Object filedValue = DBUtils.getOneObject(con, sql);
		return filedValue == null ? "0" : filedValue.toString();
	}

	public static String getFinancePlan(String filedName, String fpId) {
		Connection con = DBUtils.getConnection("nono");
		String sql = "SELECT " + filedName + " FROM finance_plan WHERE id=" + fpId;
		String filedValue = DBUtils.getOneObject(con, sql).toString();
		return filedValue;
	}

	public static String getNormalfpId(String userId) {
		FinancePlan financePlan = new FinancePlan();
		financePlan.setStatus("3");
		financePlan.setScope("11");
		financePlan.setFinishDate(">=");
		financePlan.setFinishDateOperate(">=");
		financePlan.setFinishDate("DATE(NOW())");
		List<String> fpIds = getfpIds(financePlan);
		for (String fpId : fpIds) {
			String amount = getVipAccount("amount", userId, fpId);
			String priceMax = getFinancePlan("price_max", fpId);
			System.out.println("*********amount=" + amount + "*******priceMax=" + priceMax + "**********fpId=" + fpId);
			if (Double.parseDouble(amount) != Double.parseDouble(priceMax)) {
				return fpId;
			}
		}
		return null;
	}

	public static void main(String[] args) {
		String fpId = getNormalfpId("53");
		System.out.println("*************************fpId=" + fpId);
	}

	public static String getFinanceId2(String scope) {
		Connection con = DBUtils.getConnection("nono");
		String sql = "SELECT id FROM finance_plan WHERE  STATUS =3 and price_finish<finance_plan.price and scope = "
				+ scope + " limit 1";
		String id = DBUtils.getOneObject(con, sql).toString();
		return id;
	}
}
