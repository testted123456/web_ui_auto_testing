package com.nonobank.apps.utils.sql;

import java.sql.Connection;

import com.nonobank.apps.utils.db.DBUtils;

public class SqlUtils {

	public static void recordVideo(String mobile){
		Connection con = DBUtils.getNonoConnection();
		String sql = "insert into db_nono.borrows_archive(bo_id,keyword,bo_ext_param,content,"
				+ "is_confirm,is_audit,am_audit_id,am_audit_time,am_op_time) select db_nono.borrows.id,"
				+ "'borrow_video_audit','7225627','2016-07-28 20:14:07：video_sub_mark 用户提交视频签约。',"
				+ "3,3,'1059','2016-07-29 13:49:38','2016-07-29 13:50:11' from db_nono.borrows,"
				+ "db_nono.user_info where db_nono.user_info.id=db_nono.borrows.user_id "
				+ "and db_nono.user_info.mobile_num=" 
				+ "'" + mobile + "'";
		DBUtils.update(con, sql);
		DBUtils.closeConnection();
	}
}
