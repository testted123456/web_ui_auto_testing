package com.nonobank.apps.utils.data;

import java.sql.Connection;

import com.nonobank.apps.utils.db.DBUtils;

public class QQUtils {
	
	public static String genUnRegisterQQ(){
		String qq = RandomUtils.getInstance().generateQQNumber();
		Connection con = DBUtils.getNonoConnection();
		String sql = "select count(*) from user_info_detail where qq='" + qq + "'";
		Object o = DBUtils.getOneObject(con, sql);
		
		while(!o.toString().equals("0")){
			qq = RandomUtils.getInstance().generateQQNumber();
			sql = "select count(*) from user_info_detail where qq='" + qq + "'";
			o = DBUtils.getOneObject(con, sql);
		}
		
		return qq;
	}
	
	public static String genRegisterQQ(){
		Connection con = DBUtils.getNonoConnection();
		String sql = "select qq from user_info_detail where qq is not null limit 1";
		String qq = DBUtils.getOneObject(con, sql).toString();
		
		return qq;
	}

}
