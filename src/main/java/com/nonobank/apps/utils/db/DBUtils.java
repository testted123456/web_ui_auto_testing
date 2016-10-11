package com.nonobank.apps.utils.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.commons.dbutils.DbUtils;
import com.nonobank.apps.utils.file.ParseProperties;

public class DBUtils {

	public static Logger logger = LogManager.getLogger(DBUtils.class);

	public static Connection connection_nono;

	public static Connection connection_pay;

	/**
	 * 函数说明：返回db_nono的数据库连接
	 * 
	 * @return Connection
	 */
	public static Connection getNonoConnection() {
		closeConnection();
		if (connection_nono != null) {
			return connection_nono;
		} else {
			connection_nono = getConnection("nono");
			return connection_nono;
		}
	}

	/**
	 * 函数说明：返回db_nono_pay的数据库连接
	 * 
	 * @return
	 */
	public static Connection getPayConnection() {
		if (connection_pay != null) {
			return connection_pay;
		} else {
			connection_pay = getConnection("pay");
			return connection_pay;
		}
	}

	/**
	 * @param dbname
	 * @return
	 */
	public static Connection getConnection(String dbname) {
		String driver = ParseProperties.getInstance().getProperty("mySql_driver");
		String url = ParseProperties.getInstance().getProperty("mySql_url_" + dbname);
		String name = ParseProperties.getInstance().getProperty("db_name");
		String password = ParseProperties.getInstance().getProperty("db_password");

		Connection con = null;

		try {
			try {
				Class.forName(driver);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			con = DriverManager.getConnection(url, name, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}

	/**
	 * 返回一个字段值
	 * 
	 * @param con
	 * @param sql
	 * @return
	 */
	public static Object getOneObject(Connection con, String sql) {
		Object[] objs = getOneLine(con, sql);

		if (objs.length == 0) {
			return null;
		}

		if (objs.length == 1) {
			return objs[0];
		} else {
			logger.info("get more than one objects.");
			return null;
		}
	}

	/**
	 * 返回一行记录
	 * 
	 * @param con
	 * @param sql
	 * @return
	 */
	public static Object[] getOneLine(Connection con, String sql) {
		QueryRunner qr = new QueryRunner();
		Object[] objArr = null;

		try {
			objArr = qr.query(con, sql, new ArrayHandler());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return objArr;
	}

	public static List<Object[]> getMulLine(Connection con, String sql) {
		QueryRunner qr = new QueryRunner();
		List<Object[]> objArr = null;

		try {
			objArr = qr.query(con, sql, new ArrayListHandler());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return objArr;
	}

	public static void update(Connection con, String sql) {
		QueryRunner qr = new QueryRunner();

		try {
			qr.update(con, sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void closeConnection() {
		if (null != connection_nono) {
			try {
				DbUtils.close(connection_nono);
				connection_nono = null;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (null != connection_pay) {
			try {
				DbUtils.close(connection_pay);
				connection_pay = null;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static String getOneLineValues(String dbname, String sql) {
		Connection con = getConnection(dbname);
		Object obj = getOneLine(con, sql);
		String str = null;
		if (obj instanceof Object[]) {
			Object[] objs = (Object[]) obj;
			for (int i = 0; i < objs.length; i++) {
				if (i == 0) {
					str = objs[i].toString();
				} else {
					str = str + "," + objs[i];
				}
			}
		}
		return str;
	}

	public static List<Object> geMulLineValues(String dbname, String sql) {
		Connection con = getConnection(dbname);
		List<Object[]> obj = getMulLine(con, sql);
		List<Object> lst = new ArrayList<>();
		for (Object[] objects : obj) {
			lst.add(objects[0]);
		}
		return lst;
	}

}
