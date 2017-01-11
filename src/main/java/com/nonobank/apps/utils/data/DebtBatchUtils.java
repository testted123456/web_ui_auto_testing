package com.nonobank.apps.utils.data;

import java.sql.Connection;
import com.nonobank.apps.utils.db.DBUtils;

public class DebtBatchUtils {
	public static String getFinancePlan() {
		Connection con = DBUtils.getConnection("nono");
		String sql = "select id from finance_plan where STATUS=3 and recommend_scope=3 order by id desc limit 1";
		String id = DBUtils.getOneObject(con, sql).toString();
		return id;
	}
	

}
