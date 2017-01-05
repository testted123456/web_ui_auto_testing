package com.nonobank.apps.utils.data;

import java.sql.Connection;
import com.nonobank.apps.utils.db.DBUtils;

public class ActivityProlocutorCodeUtils {

	public static String genNotexistProlocutorCode() {
		RandomUtils random = RandomUtils.getInstance();
		String prolocutor_code = random.generateRandomValue(6);
		boolean flag = false;
		do {
			flag = isRegister(prolocutor_code);
		} while (flag);
		return prolocutor_code;
	}

	public static boolean isRegister(String prolocutor_code) {
		Connection con = DBUtils.getConnection("nono");
		String sql = "select count(*) from activity_prolocutor_code where prolocutor_code=" + "'" + prolocutor_code
				+ "'";
		String count = DBUtils.getOneObject(con, sql).toString();
		DBUtils.closeConnection();
		if (count.equals("0")) {
			return false;
		} else {
			return true;
		}

	}

	public static String getProlocutorCode() {
		Connection con = DBUtils.getConnection("nono");
		String sql = "SELECT prolocutor_code from activity_prolocutor_code  limit 1";
		String prolocutor_code = DBUtils.getOneObject(con, sql).toString();
		DBUtils.closeConnection();
		return prolocutor_code;
	}
}
