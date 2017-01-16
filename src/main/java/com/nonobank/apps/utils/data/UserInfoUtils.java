package com.nonobank.apps.utils.data;

import java.sql.Connection;
import com.nonobank.apps.utils.db.DBUtils;

public class UserInfoUtils {

	public static String getUnregisterMobile() {
		RandomUtils random = RandomUtils.getInstance();
		String mobile = random.generateMobilePhoneNumber();
		boolean flag = false;
		do {
			flag = isRegister(mobile);
		} while (flag);
		DBUtils.closeConnection();
		return mobile;
	}

	public static boolean isRegister(String mobile) {
		Connection con = DBUtils.getConnection("nono");
		String sql = "select count(*) from user_info where mobile_num=" + "'" + mobile + "'";
		String count = DBUtils.getOneObject(con, sql).toString();
		DBUtils.closeConnection();
		if (count.equals("0")) {
			return false;
		} else {
			return true;
		}
	}

	public static String getBindedCardUser(String filedName) {
		return correctUser(filedName, "4");
	}

	public static String getBankUser(String bank_code) {
		return correctUser("mobile_num", bank_code);
	}

	public static String correctUser(String filedName, String bank_code) {
		String password = StringUtils.md5("it789123");
		String pay_password = StringUtils.md5("it7891234");
		Connection con = DBUtils.getConnection("nono");
		String sql = "select " + filedName + " from user_info ui, user_bankcard_info ubi,user_bankcard_auth uba where password = '" + password
				+ "' and pay_password = '" + pay_password + "'  and bank_code=" + bank_code
				+ " and auth_status =1 and  ui.id=ubi.user_id and ui.id=uba.user_id and ubi.id =uba.bank_card_id  ORDER BY ui.id desc  limit 1";
		String mobile_num = DBUtils.getOneObject(con, sql).toString();
		DBUtils.closeConnection();
		return mobile_num;
	}

	public static String getBindedCardMobileExceptThisBank(String bank_name) {
		String prefix = null;

		if (bank_name.equals("工商银行")) {
			prefix = "622202100112";
		} else if (bank_name.equals("建设银行")) {
			prefix = "436742121737";
		}

		Connection con = DBUtils.getConnection("nono");
		String sql = "select ui.mobile_num, ubi.bank_card_no, count(ubi.bank_card_no) as amount from user_info ui, user_bankcard_info ubi"
				+ " where ui.id=ubi.user_id and ui.user_name like 'BCS%' " + "group by ui.id having amount=1"
				+ " and ubi.bank_card_no not like " + "'" + prefix + "%'" + " limit 1";
		Object[] obj = DBUtils.getOneLine(con, sql);
		String mobile_num = null;

		if (null != obj) {
			mobile_num = obj[0].toString();
		}
		DBUtils.closeConnection();
		return mobile_num;
	}

	public static String getUsername(String mobile) {
		Connection con = DBUtils.getConnection("nono");
		String sql = "select user_name from user_info where mobile_num=" + "'" + mobile + "'";
		String user_name = DBUtils.getOneObject(con, sql).toString();
		DBUtils.closeConnection();
		return user_name;
	}

	public static String getUnUsedUserName() {
		Connection con = DBUtils.getConnection("nono");
		RandomUtils randomUtils = new RandomUtils();
		while (true) {
			String user_name = randomUtils.generateUserName();
			String sql = "select count(*) from user_info where user_name=" + "'" + user_name + "'";
			String count = DBUtils.getOneObject(con, sql).toString();

			if (count.equals("0")) {
				return user_name;
			}
		}
	}

}
