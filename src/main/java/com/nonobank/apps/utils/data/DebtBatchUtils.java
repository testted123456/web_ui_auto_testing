package com.nonobank.apps.utils.data;

import java.sql.Connection;
import com.nonobank.apps.utils.db.DBUtils;

public class DebtBatchUtils {
	public static String getDebtId() {
		Connection con = DBUtils.getConnection("nono");
		String sql = "SELECT id FROM debt_batch WHERE  db_status=3 limit 1";
		String id = DBUtils.getOneObject(con, sql).toString();
		return id;
	}
}
