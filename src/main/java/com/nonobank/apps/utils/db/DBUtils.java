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

	public static Connection connection;

	// 插入一行sql
	public static Object[] insertOneObject(Connection con, String sql) {
		QueryRunner qr = new QueryRunner();
		Object[] objArr = null;
		System.out.println("开始执行insert语句");
		System.out.println("sql为：" + sql);
		try {
			objArr = qr.insert(con, sql, new ArrayHandler());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("执行insert语句成功");
		return objArr;
	}
	// 更新一行sql
	public static int updateOneObject(Connection con, String sql){
		QueryRunner qr = new QueryRunner();
		int objArr = 0;
		System.out.println("开始执行update语句");
		System.out.println("sql为："+sql);
		try {
			objArr = qr.update(con, sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("执行update语句成功");
		return objArr;
		
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
		Connection conn = null;
		if (conn == null) {
			conn = getConnection(dbname);
		}
		Object obj = null;

		do {
			obj = getOneLine(conn, sql);
			if (obj == null) {
				try {
					Thread.sleep(6000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		} while (obj == null);

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

	public static List<Object> getMulLineValues(String dbname, String sql) {
		Connection conn = getConnection(dbname);
		if (conn == null) {
			conn = getConnection(dbname);
		}
		List<Object[]> obj = null;
		do {
			obj = getMulLine(conn, sql);
			if (obj == null || obj.size() == 0) {
				try {
					Thread.sleep(6000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		} while (obj == null || obj.size() == 0);
		List<Object> lst = new ArrayList<>();
		for (Object[] objects : obj) {
			Object[] newObjects = objects;
			StringBuffer sb = new StringBuffer();
			for (Object object : newObjects) {
				sb.append(object + ",");
			}
			lst.add(sb.substring(0, sb.length() - 1));
		}
		return lst;
	}

	public static void main(String[] args) {
		List<Object> lst = getMulLineValues("nono", "SELECT bo_id,ds_id from invt_debt_sale_task where `status` =6");
		for (Object object : lst) {
			System.out.println("*******************object=" + object);
		}
	}
}
