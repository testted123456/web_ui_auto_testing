package com.nonobank.apps.utils.data;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.nonobank.apps.utils.db.DBUtils;
import com.nonobank.apps.utils.entity.FinanceAccount;
import com.nonobank.apps.utils.entity.UserBankcardAuth;
import com.nonobank.apps.utils.entity.UserBankcardInfo;
import com.nonobank.apps.utils.entity.UserInfo;

public class UserInfoUtils {
	public final static List<String> MOBILE_OPERATOR_LIST = new ArrayList<String>();
	public final static String NUMBERS = "0123456789";
	public final static List<String> USERNAME_LIST = new ArrayList<String>();
	public static Random random = new Random();
	public final static int USER_NAME_MIN_LENGTH = 6;
	public final static int USER_NAME_MAX_LENGTH = 16;
	private static String newuserId;
	public static Logger logger = LogManager.getLogger(UserInfoUtils.class);

	public static String getNewuserId() {
		return newuserId;
	}

	public void setNewuserId(String userId) {
		newuserId = userId;
	}

	public static int index_limit = 0;
	static {
		MOBILE_OPERATOR_LIST.add("130");
		MOBILE_OPERATOR_LIST.add("131");
		MOBILE_OPERATOR_LIST.add("132");
		MOBILE_OPERATOR_LIST.add("133");
		MOBILE_OPERATOR_LIST.add("134");
		MOBILE_OPERATOR_LIST.add("135");
		MOBILE_OPERATOR_LIST.add("136");
		MOBILE_OPERATOR_LIST.add("137");
		MOBILE_OPERATOR_LIST.add("138");
		MOBILE_OPERATOR_LIST.add("139");

		MOBILE_OPERATOR_LIST.add("150");
		MOBILE_OPERATOR_LIST.add("151");
		MOBILE_OPERATOR_LIST.add("152");
		MOBILE_OPERATOR_LIST.add("153");
		MOBILE_OPERATOR_LIST.add("155");
		MOBILE_OPERATOR_LIST.add("156");
		MOBILE_OPERATOR_LIST.add("157");
		MOBILE_OPERATOR_LIST.add("158");
		MOBILE_OPERATOR_LIST.add("159");

		MOBILE_OPERATOR_LIST.add("180");
		MOBILE_OPERATOR_LIST.add("181");
		MOBILE_OPERATOR_LIST.add("182");
		MOBILE_OPERATOR_LIST.add("183");
		MOBILE_OPERATOR_LIST.add("184");
		MOBILE_OPERATOR_LIST.add("185");
		MOBILE_OPERATOR_LIST.add("186");
		MOBILE_OPERATOR_LIST.add("187");
		MOBILE_OPERATOR_LIST.add("188");
		MOBILE_OPERATOR_LIST.add("189");

		USERNAME_LIST.add("0");
		USERNAME_LIST.add("1");
		USERNAME_LIST.add("2");
		USERNAME_LIST.add("3");
		USERNAME_LIST.add("4");
		USERNAME_LIST.add("5");
		USERNAME_LIST.add("6");
		USERNAME_LIST.add("7");
		USERNAME_LIST.add("8");
		USERNAME_LIST.add("9");

		USERNAME_LIST.add("A");
		USERNAME_LIST.add("B");
		USERNAME_LIST.add("C");
		USERNAME_LIST.add("D");
		USERNAME_LIST.add("E");
		USERNAME_LIST.add("F");
		USERNAME_LIST.add("G");
		USERNAME_LIST.add("H");
		USERNAME_LIST.add("I");
		USERNAME_LIST.add("J");
		USERNAME_LIST.add("K");
		USERNAME_LIST.add("L");
		USERNAME_LIST.add("M");
		USERNAME_LIST.add("N");
		USERNAME_LIST.add("O");
		USERNAME_LIST.add("P");
		USERNAME_LIST.add("Q");
		USERNAME_LIST.add("R");
		USERNAME_LIST.add("S");
		USERNAME_LIST.add("T");
		USERNAME_LIST.add("U");
		USERNAME_LIST.add("V");
		USERNAME_LIST.add("W");
		USERNAME_LIST.add("X");
		USERNAME_LIST.add("Y");
		USERNAME_LIST.add("Z");

		USERNAME_LIST.add("a");
		USERNAME_LIST.add("b");
		USERNAME_LIST.add("c");
		USERNAME_LIST.add("d");
		USERNAME_LIST.add("e");
		USERNAME_LIST.add("f");
		USERNAME_LIST.add("g");
		USERNAME_LIST.add("h");
		USERNAME_LIST.add("i");
		USERNAME_LIST.add("j");
		USERNAME_LIST.add("k");
		USERNAME_LIST.add("l");
		USERNAME_LIST.add("m");
		USERNAME_LIST.add("n");
		USERNAME_LIST.add("o");
		USERNAME_LIST.add("p");
		USERNAME_LIST.add("q");
		USERNAME_LIST.add("r");
		USERNAME_LIST.add("s");
		USERNAME_LIST.add("t");
		USERNAME_LIST.add("u");
		USERNAME_LIST.add("v");
		USERNAME_LIST.add("w");
		USERNAME_LIST.add("x");
		USERNAME_LIST.add("y");
		USERNAME_LIST.add("z");

		USERNAME_LIST.add("_");
	}

	public static String generateMobilePhoneNumber() {
		return generateMobilePhoneNumber(11);
	}

	public static String generateMobilePhoneNumber(int digital) {
		int index = random.nextInt(MOBILE_OPERATOR_LIST.size());
		StringBuffer sb = new StringBuffer();
		String mobile_prefix = MOBILE_OPERATOR_LIST.get(index);
		sb.append(mobile_prefix);
		for (int i = 0; i < digital - 3; i++) {
			sb.append(NUMBERS.charAt(random.nextInt(NUMBERS.length())));
		}
		return sb.toString();
	}

	public static String generateUserName() {
		int max = USER_NAME_MAX_LENGTH;
		int min = USER_NAME_MIN_LENGTH;
		int length = random.nextInt(max) % (max - min + 1) + min;

		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			sb.append(USERNAME_LIST.get(random.nextInt(USERNAME_LIST.size())));
		}
		return sb.toString();
	}

	public static String getUnUsedUserName() {
		return getUnUsedUser("user_name");
	}

	public static String getUnUsedMobileNum() {
		return getUnUsedUser("mobile_num");
	}

	public static String getUsedUserName() {
		return getUsedUser("user_name");
	}

	public static String getUsedMobileNum() {
		return getUsedUser("mobile_num");
	}

	public static String getSpecifalUserName() {
		return getSpecialUser("user_name");
	}

	public static String getSpecifalMobileNum() {
		return getSpecialUser("mobile_num");
	}

	public static String getNormalUserName() {
		return getNormalUser("user_name");
	}

	public static String getNormalMobileNum() {
		return getNormalUser("mobile_num");
	}

	public static String getLockMobileNum() {
		return getLockUser("mobile_num");
	}

	public static String getLockUserName() {
		return getLockUser("user_name");
	}

	public static boolean isMobileNO(String mobile) {
		Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0-9]))\\d{8}$");
		Matcher m = p.matcher(mobile);
		return m.matches();
	}

	public static boolean isUserName(String userName) {
		Pattern p = Pattern.compile("^[0-9a-zA-Z_]{6,16}$");
		Matcher m = p.matcher(userName);
		return m.matches();

	}

	public static String getUnUsedUser(String fieldName) {
		Date startdate = new Date();
		index_limit = -1;
		String loginName = null;
		UserInfo userInfo = new UserInfo();
		while (true) {
			switch (fieldName) {
			case "mobile_num":
				loginName = generateMobilePhoneNumber();
				userInfo.setMobileNum(loginName);
				break;
			case "user_name":
				loginName = generateUserName();
				userInfo.setUserName(loginName);
				break;
			case "id_num":
				IdCardGenerator idCardGenerator = new IdCardGenerator();
				loginName = idCardGenerator.getIDCard();
				userInfo.setIdNum(loginName);
				break;
			}
			new UserInfoUtils().setNewuserId(null);
			StringBuffer sb = new StringBuffer();
			String sql = "select id from user_info ui where 1 = 1";
			UserInfo.setUserInfoCondition(userInfo, sb);
			List<String> userInfos = getUserInfos(sql, sb);
			if (userInfos.size() == 0) {
				Date enddate = new Date();
				int seconds = getSeconds(startdate, enddate);
				logger.debug("********************************查询所用时间为seconds=" + seconds + "秒");
				return loginName;
			}
		}
	}

	public static String getUsedUser(String fieldName) {
		int index = 0;
		Date startdate = new Date();
		index_limit = 0;
		String loginName = null;
		while (true) {
			StringBuffer sb = new StringBuffer();
			String sql = "select id from user_info ui where 1 = 1";
			UserInfo.setLimit(index_limit, ConstantUtils.max_limit, sb);
			List<String> userInfos = getUserInfos(sql, sb);
			index = userInfos.size();
			for (String userId : userInfos) {
				UserInfo userInfo = new UserInfo();
				userInfo.setUserId(userId);
				loginName = getUserInfo(fieldName, userId);
				if (loginName != null) {
					Date enddate = new Date();
					int seconds = getSeconds(startdate, enddate);
					logger.debug("********************************查询所用时间为seconds=" + seconds + "秒");
					return loginName;
				}
				index_limit += index;
			}
		}
	}

	public static String getSpecialUser(String fieldName) {
		int index = 0;
		Date startdate = new Date();
		index_limit = 0;
		String loginName = null;
		while (true) {
			StringBuffer sb = new StringBuffer();
			String sql = "select id from user_info ui where 1 = 1";
			UserInfo.setUserInfoGroupBy("mobile_num", ">", "1", sb);
			UserInfo.setLimit(index_limit, ConstantUtils.max_limit, sb);
			List<String> userInfos = getUserInfos(sql, sb);
			index = userInfos.size();

			sb = new StringBuffer();
			UserInfo userInfo = new UserInfo();
			userInfo.setStatus("1");
			userInfo.setPassword(ConstantUtils.CORRECT_LOGIN_PASSWORD);
			UserInfo.setUserInfoCondition(userInfo, sb);
			UserInfo.setUserInfoConditions(userInfos, sb);
			List<String> newUserInfos = getUserInfos(sql, sb);

			for (String userId : newUserInfos) {
				loginName = getUserInfo(fieldName, userId);
				if (loginName != null) {
					Date enddate = new Date();
					int seconds = getSeconds(startdate, enddate);
					logger.debug("********************************查询所用时间为seconds=" + seconds + "秒");
					return loginName;
				}
			}
			index_limit += index;
		}
	}

	public static String getNormalUser(String fieldName) {
		int index = 0;
		Date startdate = new Date();
		index_limit = 0;
		String loginName = null;
		while (true) {
			StringBuffer sb = new StringBuffer();
			String sql = "select id from user_info ui where 1 = 1";
			UserInfo.setUserInfoGroupBy("mobile_num", "=", "1", sb);
			UserInfo.setLimit(index_limit, ConstantUtils.max_limit, sb);
			List<String> userInfos = getUserInfos(sql, sb);
			index = userInfos.size();

			sb = new StringBuffer();
			UserInfo userInfo = new UserInfo();
			userInfo.setPassword(ConstantUtils.CORRECT_LOGIN_PASSWORD);
			userInfo.setStatus("1");
			UserInfo.setUserInfoCondition(userInfo, sb);
			UserInfo.setUserInfoConditions(userInfos, sb);
			List<String> newUserInfos = getUserInfos(sql, sb);

			for (String userId : newUserInfos) {
				loginName = getUserInfo(fieldName, userId);
				if (loginName != null) {
					Date enddate = new Date();
					int seconds = getSeconds(startdate, enddate);
					logger.debug("********************************查询所用时间为seconds=" + seconds + "秒");
					return loginName;
				}
			}
			index_limit += index;
		}
	}

	public static String getLockUser(String fieldName) {
		int index = 0;
		Date startdate = new Date();
		index_limit = 0;
		String loginName = null;
		while (true) {
			StringBuffer sb = new StringBuffer();
			String sql = "select id from user_info ui where 1 = 1";
			UserInfo.setUserInfoGroupBy("mobile_num", "=", "1", sb);
			UserInfo.setLimit(index_limit, ConstantUtils.max_limit, sb);
			List<String> userInfos = getUserInfos(sql, sb);
			index = userInfos.size();

			sb = new StringBuffer();
			UserInfo userInfo = new UserInfo();
			userInfo.setPassword(ConstantUtils.CORRECT_LOGIN_PASSWORD);
			userInfo.setStatus("0");
			UserInfo.setUserInfoCondition(userInfo, sb);
			UserInfo.setUserInfoConditions(userInfos, sb);
			List<String> newUserInfos = getUserInfos(sql, sb);

			for (String userId : newUserInfos) {
				loginName = getUserInfo(fieldName, userId);
				if (loginName != null) {
					Date enddate = new Date();
					int seconds = getSeconds(startdate, enddate);
					logger.debug("********************************查询所用时间为seconds=" + seconds + "秒");
					return loginName;
				}
			}
			index_limit += index;
		}
	}

	public static String getBankUser(String bankCode) {
		int index = 0;
		System.out.println("**************************************bankCode=" + bankCode);
		Date startdate = new Date();
		index_limit = 0;
		String loginName = null;
		while (true) {
			StringBuffer sb = new StringBuffer();

			StringBuffer sql = new StringBuffer();
			sql.append(
					"select ui.id from user_info ui,user_bankcard_info ubi,finance_account fa,user_bankcard_auth uba");
			sql.append(" where ui.id=ubi.user_id");
			sql.append(" and ui.id = fa.user_id");
			sql.append(" and ubi.user_id  = fa.user_id");
			sql.append(" and ui.id=uba.user_id");
			sql.append(" and fa.user_id=uba.user_id");
			sql.append(" and ubi.user_id=uba.user_id");
			sql.append(" and ubi.id = uba.bank_card_id");
			sb = new StringBuffer();
			UserInfo userInfo = new UserInfo();
			userInfo.setIsCard("1");
			userInfo.setStatus("1");
			userInfo.setIsDunning("0");
			userInfo.setPassword(ConstantUtils.CORRECT_LOGIN_PASSWORD);
			UserInfo.setUserInfoCondition(userInfo, sb);

			UserBankcardInfo userBankcardInfo = new UserBankcardInfo();
			userBankcardInfo.setBankCode(bankCode);
			userBankcardInfo.setIsDeleted("0");
			UserBankcardInfo.setUserInfoCondition(userBankcardInfo, sb);

			FinanceAccount financeAccount = new FinanceAccount();
			financeAccount.setBalance("0");
			financeAccount.setLocking("0");
			financeAccount.setRoleId("7");
			FinanceAccount.setFinanceAccountCondition(financeAccount, sb);

			UserBankcardAuth userBankcardAuth = new UserBankcardAuth();
			userBankcardAuth.setAuthStatus("1");

			UserInfo.setLimit(index_limit, ConstantUtils.max_limit, sb);
			List<String> list = getUserInfos(sql.toString(), sb);
			index = list.size();
			sb = new StringBuffer();
			String newsql = "select id from user_info ui where 1 = 1";
			UserInfo.setUserInfoConditions(list, sb);
			UserInfo.setUserInfoGroupBy("mobile_num", "=", "1", sb);

			List<String> userInfos = getUserInfos(newsql, sb);

			for (String userId : userInfos) {
				loginName = getUserInfo("mobile_num", userId);
				if (loginName != null) {

					new UserInfoUtils().setNewuserId(userId);
					Date enddate = new Date();
					int seconds = getSeconds(startdate, enddate);
					logger.debug("********************************查询所用时间为seconds=" + seconds + "秒");
					return loginName;
				}
			}
			index_limit += index;

		}
	}

//	public static String getIsDunningBankUser(String bankCode) {
//		int index = 0;
//		Date startdate = new Date();
//		index_limit = 0;
//		String loginName = null;
//		while (true) {
//
//			List<String> userInfos = getUserInfos("=", "1", "mobile_num");
//			index = userInfos.size();
//
//			StringBuffer sql = new StringBuffer();
//			UserInfo userInfo = new UserInfo();
//			userInfo.setIsCard("1");
//			userInfo.setStatus("1");
//			userInfo.setIsDunning("1");
//			userInfo.setPassword(ConstantUtils.CORRECT_LOGIN_PASSWORD);
//			userInfos = getUserInfos(userInfo, userInfos);
//
//			UserBankcardInfo userBankcardInfo = new UserBankcardInfo();
//			userBankcardInfo.setBankCode(bankCode);
//			List<String> userBankcardInfos = getUserBankcardInfos(userBankcardInfo, userInfos);
//			userInfos.retainAll(userBankcardInfos);
//			for (String userId : userInfos) {
//				loginName = getUserInfo("mobile_num", userId);
//				if (loginName != null) {
//
//					new UserInfoUtils().setNewuserId(userId);
//					Date enddate = new Date();
//					int seconds = getSeconds(startdate, enddate);
//					logger.debug("********************************查询所用时间为seconds=" + seconds + "秒");
//					return loginName;
//				}
//			}
//			index_limit += index;
//		}
//	}

	public static List<String> getUserInfos(String sql, StringBuffer sbConditon) {
		List<String> newUserInfos = new ArrayList<>();
		Connection con = DBUtils.getConnection("nono");
		System.out.println("*********************** sql + sbConditon=" + sql + sbConditon);
		List<Object[]> objects = DBUtils.getMulLine(con, sql + sbConditon);
		for (Object[] user_id : objects) {
			newUserInfos.add(user_id[0].toString());
		}
		return newUserInfos;
	}

	// 精确查询user_info表，通过user_id
	public static String getUserInfo(String filedName, String userId) {
		String filedValue = null;
		Connection con = DBUtils.getConnection("nono");
		String sql = "select " + filedName + " from user_info where id = " + userId;
		// System.out.println("**************************精确查询用户信息sql=" + sql);
		Object[] objects = DBUtils.getOneLine(con, sql);
		if (objects.length == 0) {
			return filedValue;
		}
		filedValue = objects[0].toString();
		switch (filedName) {
		case "id_num":
			if (IDCardVerify.verify(filedValue)) {
				return filedValue;
			}
			break;
		case "mobile_num":
			if (isMobileNO(filedValue)) {
				return filedValue;
			}
			break;
		case "user_name":
			if (isUserName(filedValue)) {
				return filedValue;
			}
			break;
		default:
			return filedValue;
		}
		return null;
	}

	public static String getUserBankcardInfo(String filedName, String userId) {
		String loginName = null;
		Connection con = DBUtils.getConnection("nono");
		String sql = "select " + filedName + " from user_bankcard_info where user_id =" + userId;
		Object[] objects = DBUtils.getOneLine(con, sql);
		if (objects.length == 0) {
			return loginName;
		}
		loginName = objects[0].toString();
		return loginName;
	}

	public static void main(String[] args) {
		// System.out.println(getUnUsedMobileNum());
		// System.out.println(getUnUsedUserName());

		// System.out.println(getUsedMobileNum());
		// System.out.println(getUsedUserName());
		//
		// System.out.println(getSpecifalMobileNum());
		// System.out.println(getSpecifalUserName());
		//
		//
		// System.out.println(getLockMobileNum());
		// System.out.println(getLockUserName());

		// System.out.println(getNormalMobileNum());
		// System.out.println(getNormalUserName());

		System.out.println(getBankUser("4"));

	}

	/**
	 * 取得enddate 之间 startdate的秒
	 * 
	 * @param startdate
	 *            Date
	 * @param enddate
	 *            Date
	 * @return int
	 */
	public static int getSeconds(Date startdate, Date enddate) {
		long time = enddate.getTime() - startdate.getTime();
		int totalS = new Long(time / 1000).intValue();
		return totalS;
	}
}
