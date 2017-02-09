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

	public static List<String> getnewfpIds(List<String> list) {
		List<String> fpIds = new ArrayList<>();
		Connection con = DBUtils.getConnection("nono");
		InvtIntimateDebtPackage invtIntimateDebtPackage = new InvtIntimateDebtPackage();
		InvtIntimateDebtPackage.setInvtIntimateDebtPackageCondition(invtIntimateDebtPackage);
		InvtIntimateDebtPackage.setInvtIntimateDebtPackageConditions(list);
		String sql = "SELECT fp_id FROM invt_intimate_debt_package " + InvtIntimateDebtPackage.getCondition();
		List<Object[]> objects = DBUtils.getMulLine(con, sql);
		for (Object[] id : objects) {
			fpIds.add(id[0].toString());
		}
		return fpIds;
	}

	public static String getNormalfpId() {
		String userId = UserInfoUtils.getNewuserId();
		System.out.println("*******************userId=" + userId);
		FinancePlan financePlan = new FinancePlan();
		financePlan.setStatus("3");
		financePlan.setScope("11");
		financePlan.setFinishDateOperate(">=");
		financePlan.setFinishDate("DATE(NOW())");
		List<String> fpIds = getfpIds(financePlan);
		List<String> newfpIds = new ArrayList<String>();
		for (String fpId : fpIds) {
			String amount = getVipAccount("amount", userId, fpId);
			String priceMax = getFinancePlan("price_max", fpId);
			if (Double.parseDouble(amount) <= Double.parseDouble(priceMax)) {
				newfpIds.add(fpId);
			}
		}
				return getnewfpIds(fpIds).get(0);
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

	public static void main(String[] args) {
		String fpId = getNormalfpId();
		System.out.println("*************************fpId=" + fpId);
	}

}
