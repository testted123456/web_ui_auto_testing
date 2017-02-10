package com.nonobank.apps.utils.data;

public class ConstantUtils {
	public final static String GREATER_THAN = ">";
	public final static String CORRECT_CHECK_CODE = "8888";
	public final static String ERROR_CHECK_CODE = "1234";
	public final static String CORRECT_VALIDATION = "888888";
	public final static String ERROR_VALIDATION = "1234";
	public final static String CORRECT_LOGIN_PASSWORD = "it789123";
	public final static String ERROR_LOGIN_PASSWORD = "1234";
	public final static String CORRECT_PAY_PASSWORD = "it7891234";
	public final static String ERROR_PAY_PASSWORD = "1234";
	public final static String AUTO_REAL_NAME = "李双双";
	private static boolean is_dunning = false;



	public static boolean isIs_dunning() {
		return is_dunning;
	}

	public static void setIs_dunning(boolean newis_dunning) {
		is_dunning = newis_dunning;
	}

	public static int max_limit = 10000;
	public final static int LIMIT = 10000000;

	public static String getCorrectCheckCode() {
		return CORRECT_CHECK_CODE;
	}

	public static String getCorrectValidation() {
		return CORRECT_VALIDATION;
	}

	public static String getErrorCheckCode() {
		return ERROR_CHECK_CODE;
	}

	public static String getErrorValidation() {
		return ERROR_VALIDATION;
	}

	public static String getCorrectLoginPassword() {
		return CORRECT_LOGIN_PASSWORD;
	}

	public static String getErrorLoginPassword() {
		return ERROR_LOGIN_PASSWORD;
	}

	public static String getCorrectPayPassword() {
		return CORRECT_PAY_PASSWORD;
	}

	public static String getErrorPayPassword() {
		return ERROR_PAY_PASSWORD;
	}

}
