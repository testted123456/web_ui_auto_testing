package com.nonobank.apps.utils.data;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.nonobank.apps.utils.db.DBUtils;
import com.nonobank.apps.utils.entity.UserBankcardInfo;
import com.nonobank.apps.utils.entity.UserInfo;
import com.nonobank.apps.utils.entity.UserLoginInfo;

public class UserInfoUtils {
	public final static List<String> MOBILE_OPERATOR_LIST = new ArrayList<String>();
	public final static String NUMBERS = "0123456789";
	public final static List<String> USERNAME_LIST = new ArrayList<String>();
	public static Random random = new Random();
	public final static int USER_NAME_MIN_LENGTH = 6;
	public final static int USER_NAME_MAX_LENGTH = 16;
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

	public static String getUnUsedUser(String fieldName) {
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
			}
			List<String> userInfos = getUserInfos(userInfo, ">", "0", fieldName);
			if (userInfos.size() == 0) {
				return loginName;
			}
		}
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

	public static String getSpecialUser(String fieldName) {
		index_limit = 0;
		String loginName = null;
		while (true) {
			UserInfo userInfo = new UserInfo();
			userInfo.setPassword(ConstantUtils.CORRECT_LOGIN_PASSWORD);
			UserLoginInfo userLoginInfo = new UserLoginInfo();
			userLoginInfo.setErrorCount("0");
			List<String> userInfos = getUserInfos(userInfo, ">", "1", "mobile_num");
			int index = userInfos.size();
			List<String> userLoginInfos = getUserLoginInfos(userLoginInfo, userInfos);
			userInfos.retainAll(userLoginInfos);
			for (String userId : userInfos) {
				loginName = getCorrectUserInfo(fieldName, userId);
				if (loginName != null) {
					return loginName;
				}
			}
			index_limit += index;
		}
	}

	public static String getNormalUser(String fieldName) {
		index_limit = 0;
		String loginName = null;
		while (true) {
			UserInfo userInfo = new UserInfo();
			UserLoginInfo userLoginInfo = new UserLoginInfo();
			userInfo.setPassword(ConstantUtils.CORRECT_LOGIN_PASSWORD);
			userLoginInfo.setErrorCount("0");
			List<String> userInfos = getUserInfos(userInfo, "=", "1", "mobile_num");
			int index = userInfos.size();
			List<String> userLoginInfos = getUserLoginInfos(userLoginInfo, userInfos);
			userInfos.retainAll(userLoginInfos);
			for (String userId : userInfos) {
				loginName = getCorrectUserInfo(fieldName, userId);
				if (loginName != null) {
					return loginName;
				}
			}
			index_limit += index;
		}
	}

	public static String getBankUser(String bankCode) {
		index_limit = 0;
		String loginName = null;
		while (true) {
			UserInfo userInfo = new UserInfo();
			userInfo.setPassword(ConstantUtils.CORRECT_LOGIN_PASSWORD);
			userInfo.setIsCard("1");
			List<String> userInfos = getUserInfos(userInfo, "=", "1", "mobile_num");
			int index = userInfos.size();
			UserBankcardInfo userBankcardInfo = new UserBankcardInfo();
			userBankcardInfo.setBankCode(bankCode);
			List<String> userBankcardInfos = getUserBankcardInfos(userBankcardInfo, userInfos);
			userInfos.retainAll(userBankcardInfos);
			for (String userId : userInfos) {
				loginName = getCorrectUserInfo("mobile_num", userId);
				if (loginName != null) {
					return loginName;
				}
			}
			index_limit += index;
		}
	}

	public static List<String> getUserInfos(UserInfo userInfo, String operatorType, String operatorValue,
			String fieldName) {
		List<String> userInfos = new ArrayList<>();
		try {
			Connection con = DBUtils.getConnection("nono");
			UserInfo.setUserInfoCondition(userInfo);
			UserInfo.setUserInfoGroupBy(fieldName, operatorType, operatorValue);
			UserInfo.setLimit(index_limit, ConstantUtils.max_limit);
			String sql = "select id from user_info " + UserInfo.getCondition();
			System.out.println("*************************sql=" + sql);
			List<Object[]> lst = DBUtils.getMulLine(con, sql);
			for (Object[] objects : lst) {
				userInfos.add(objects[0].toString());
			}
		} catch (Exception e) {
		}
		return userInfos;
	}

	public static List<String> getUserLoginInfos(UserLoginInfo userLoginInfo, List<String> userInfos) {
		List<String> userLoginInfos = new ArrayList<>();
		Connection con = DBUtils.getConnection("nono");
		UserLoginInfo.setUserLoginInfoCondition(userLoginInfo);
		UserLoginInfo.setUserLoginInfoConditions(userInfos);
		String sql = "select user_id from user_login_info " + UserLoginInfo.getCondition();
		List<Object[]> objects = DBUtils.getMulLine(con, sql);
		for (Object[] user_id : objects) {
			userLoginInfos.add(user_id[0].toString());
		}
		return userLoginInfos;
	}

	public static List<String> getUserBankcardInfos(UserBankcardInfo userBankcardInfo, List<String> userInfos) {
		List<String> userLoginInfos = new ArrayList<>();
		Connection con = DBUtils.getConnection("nono");
		UserBankcardInfo.setUserInfoCondition(userBankcardInfo);
		UserBankcardInfo.setUserLoginInfoConditions(userInfos);
		String sql = "select user_id from user_bankcard_info " + UserBankcardInfo.getCondition();
		List<Object[]> objects = DBUtils.getMulLine(con, sql);
		for (Object[] user_id : objects) {
			userLoginInfos.add(user_id[0].toString());
		}
		return userLoginInfos;
	}

	public static String getCorrectUserInfo(String filedName, String userId) {
		String loginName = null;
		try {
			Connection con = DBUtils.getConnection("nono");
			String sql = "select " + filedName + " from user_info where id = " + userId;
			loginName = DBUtils.getOneLine(con, sql)[0].toString();
			return loginName;
		} catch (Exception e) {
		}
		return loginName;
	}

	public static String getCorrectUserBankcardInfo(String filedName, String userId) {
		String loginName = null;
		try {
			Connection con = DBUtils.getConnection("nono");
			String sql = "select " + filedName + " from user_bankcard_info where user_id =" + userId;
			String value = DBUtils.getOneLine(con, sql)[0].toString();
			if (value != null) {
				return value;
			}
		} catch (Exception e) {
		}
		return loginName;
	}

	public static String getBankCardByMobile(String mobileNum) {
		UserInfo userInfo = new UserInfo();
		userInfo.setMobileNum(mobileNum);
		List<String> userInfos = getUserInfos(userInfo, ">", "0", "mobile_num");
		String userId = userInfos.get(0);
		return getCorrectUserBankcardInfo("bank_card_no", userId);
	}

	public static void main(String[] args) {

		System.out.println(getNormalMobileNum());
		System.out.println(getNormalUserName());
		System.out.println(getUnUsedMobileNum());
		System.out.println(getUnUsedUserName());
		System.out.println(getSpecifalMobileNum());
		System.out.println(getSpecifalUserName());

	}

}
