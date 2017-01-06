package com.nonobank.apps.utils.sql;

import java.sql.Connection;

import com.nonobank.apps.utils.db.DBUtils;

public class SqlUtils {
	// 视频录制
	public static void recordVideo(String mobile) {
		Connection con = DBUtils.getConnection("nono");
		String sql = "insert into db_nono.borrows_archive(bo_id,keyword,bo_ext_param,content,"
				+ "is_confirm,is_audit,am_audit_id,am_audit_time,am_op_time) select db_nono.borrows.id,"
				+ "'borrow_video_audit','7225627','2016-07-28 20:14:07：video_sub_mark 用户提交视频签约。',"
				+ "3,3,'1059','2016-07-29 13:49:38','2016-07-29 13:50:11' from db_nono.borrows,"
				+ "db_nono.user_info where db_nono.user_info.id=db_nono.borrows.user_id "
				+ "and db_nono.user_info.mobile_num=" + "'" + mobile + "'";
		DBUtils.update(con, sql);
		DBUtils.closeConnection();
	}

	// 初审通过
	public static void firstAuditPass(String mobile){
//		Connection con =DBUtils.getConnection("nono");
//		String sql2="update borrows set bo_sign_result=0,bo_finish_price=0,bo_finish_rate=0,bo_can_success=3,bo_is_success=0,bo_is_finish='0' where id="+boId";
//		DBUtils.update(con, sql);
//		DBUtils.closeConnection();
	}

	// 初审回退
	public static void firstAuditRollback(String mobile) {
		Connection con = DBUtils.getConnection("nono");
		String sql = "insert into db_nono.borrows_archive(bo_id,keyword,bo_ext_param,content,"
				+ "is_confirm,is_audit,am_audit_id,am_audit_time,am_op_time) select db_nono.borrows.id,"
				+ "'borrow_video_audit','7225627','2016-07-28 20:14:07：video_sub_mark 用户提交视频签约。',"
				+ "3,4,'1059','2016-07-29 13:49:38','2016-07-29 13:50:11' from db_nono.borrows,"
				+ "db_nono.user_info where db_nono.user_info.id=db_nono.borrows.user_id "
				+ "and db_nono.user_info.mobile_num=" + "'" + mobile + "'";
		DBUtils.update(con, sql);
		DBUtils.closeConnection();
	}

	// 初审拒绝
	public static void firstAuditReject(String mobile) {
		Connection con = DBUtils.getConnection("nono");
		String sql = "insert into db_nono.borrows_archive(bo_id,keyword,bo_ext_param,content,"
				+ "is_confirm,is_audit,am_audit_id,am_audit_time,am_op_time) select db_nono.borrows.id,"
				+ "'borrow_video_audit','7225627','2016-07-28 20:14:07：video_sub_mark 用户提交视频签约。',"
				+ "3,2,'1059','2016-07-29 13:49:38','2016-07-29 13:50:11' from db_nono.borrows,"
				+ "db_nono.user_info where db_nono.user_info.id=db_nono.borrows.user_id "
				+ "and db_nono.user_info.mobile_num=" + "'" + mobile + "'";
		DBUtils.update(con, sql);
		DBUtils.closeConnection();
	}

	// 初审取消
	public static void firstAuditCancel(String mobile) {
		Connection con = DBUtils.getConnection("nono");
		String sql = "insert into db_nono.borrows_archive(bo_id,keyword,bo_ext_param,content,"
				+ "is_confirm,is_audit,am_audit_id,am_audit_time,am_op_time) select db_nono.borrows.id,"
				+ "'borrow_video_audit','7225627','2016-07-28 20:14:07：video_sub_mark 用户提交视频签约。',"
				+ "3,5,'1059','2016-07-29 13:49:38','2016-07-29 13:50:11' from db_nono.borrows,"
				+ "db_nono.user_info where db_nono.user_info.id=db_nono.borrows.user_id "
				+ "and db_nono.user_info.mobile_num=" + "'" + mobile + "'";
		DBUtils.update(con, sql);
		DBUtils.closeConnection();
	}

	// 终审通过
	public static void lastAuditPass(String mobile) {
		Connection con = DBUtils.getConnection("nono");
		String sql = "insert into db_nono.borrows_archive(bo_id,keyword,bo_ext_param,content,"
				+ "is_confirm,is_audit,am_audit_id,am_audit_time,am_op_time) select db_nono.borrows.id,"
				+ "'borrow_video_audit','7225627','2016-07-28 20:14:07：video_sub_mark 用户提交视频签约。',"
				+ "1,1,'1059','2016-07-29 13:49:38','2016-07-29 13:50:11' from db_nono.borrows,"
				+ "db_nono.user_info where db_nono.user_info.id=db_nono.borrows.user_id "
				+ "and db_nono.user_info.mobile_num=" + "'" + mobile + "'";
		DBUtils.update(con, sql);
		DBUtils.closeConnection();
	}

	// 终审回退
	public static void lastAuditRollback(String mobile) {
		Connection con = DBUtils.getConnection("nono");
		String sql = "insert into db_nono.borrows_archive(bo_id,keyword,bo_ext_param,content,"
				+ "is_confirm,is_audit,am_audit_id,am_audit_time,am_op_time) select db_nono.borrows.id,"
				+ "'borrow_video_audit','7225627','2016-07-28 20:14:07：video_sub_mark 用户提交视频签约。',"
				+ "4,1,'1059','2016-07-29 13:49:38','2016-07-29 13:50:11' from db_nono.borrows,"
				+ "db_nono.user_info where db_nono.user_info.id=db_nono.borrows.user_id "
				+ "and db_nono.user_info.mobile_num=" + "'" + mobile + "'";
		DBUtils.update(con, sql);
		DBUtils.closeConnection();
	}

	// 终审拒绝
	public static void lastAuditReject(String mobile) {
		Connection con = DBUtils.getConnection("nono");
		String sql = "insert into db_nono.borrows_archive(bo_id,keyword,bo_ext_param,content,"
				+ "is_confirm,is_audit,am_audit_id,am_audit_time,am_op_time) select db_nono.borrows.id,"
				+ "'borrow_video_audit','7225627','2016-07-28 20:14:07：video_sub_mark 用户提交视频签约。',"
				+ "2,1,'1059','2016-07-29 13:49:38','2016-07-29 13:50:11' from db_nono.borrows,"
				+ "db_nono.user_info where db_nono.user_info.id=db_nono.borrows.user_id "
				+ "and db_nono.user_info.mobile_num=" + "'" + mobile + "'";
		DBUtils.update(con, sql);
		DBUtils.closeConnection();
	}

	// 终审取消
	public static void lastAuditCancel(String mobile) {
		Connection con = DBUtils.getConnection("nono");
		String sql = "insert into db_nono.borrows_archive(bo_id,keyword,bo_ext_param,content,"
				+ "is_confirm,is_audit,am_audit_id,am_audit_time,am_op_time) select db_nono.borrows.id,"
				+ "'borrow_video_audit','7225627','2016-07-28 20:14:07：video_sub_mark 用户提交视频签约。',"
				+ "5,1,'1059','2016-07-29 13:49:38','2016-07-29 13:50:11' from db_nono.borrows,"
				+ "db_nono.user_info where db_nono.user_info.id=db_nono.borrows.user_id "
				+ "and db_nono.user_info.mobile_num=" + "'" + mobile + "'";
		DBUtils.update(con, sql);
		DBUtils.closeConnection();
	}

}
