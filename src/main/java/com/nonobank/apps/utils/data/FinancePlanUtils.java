package com.nonobank.apps.utils.data;

import java.sql.Connection;
import com.nonobank.apps.utils.db.DBUtils;

public class FinancePlanUtils {
	public static String getFinanceId(String scope) {
		Connection con = DBUtils.getNonoConnection();
		String sql = "SELECT id FROM finance_plan WHERE  STATUS =3 and price_finish<finance_plan.price and scope = "
				+ scope + " limit 1";
		String id = DBUtils.getOneObject(con, sql).toString();
		return id;
	}
}
