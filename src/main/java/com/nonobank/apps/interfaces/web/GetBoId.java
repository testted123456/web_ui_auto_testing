package com.nonobank.apps.interfaces.web;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.nonobank.apps.utils.db.DBUtils;

public class GetBoId {
	public static Logger logger = LogManager.getLogger(GetBoId.class);

	public static String getBoId(String mobile) {
		String sql = "SELECT id from borrows where user_id = (select id from user_info where mobile_num = " + mobile
				+ ")";
		String str = DBUtils.getOneLineValues("nono", sql);
		return str;
	}

}
