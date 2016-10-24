package com.nonobank.apps.utils.data;

import java.sql.Connection;
import java.util.Random;
import com.nonobank.apps.utils.db.DBUtils;

public class BankCardUtils {

	private static String[] prefix = { "622202100112", "436742121737" };

	public static int getOSum(int oSum) {
		oSum = oSum * 2;

		if (oSum < 10) {
			return oSum;
		} else {
			return oSum - 9;
		}
	}

	public static String getBankCard(String prefix) {
		Random random = new Random();
		String str = prefix;

		for (int i = 0; i < 6; i++) {
			str += String.valueOf(random.nextInt(10));
		}

		int sum = 0;

		for (int i = 1; i < 19; i++) {
			int v = Character.getNumericValue(str.charAt(i - 1));

			if (i % 2 == 0) {
				sum += getOSum(v);
			} else {
				sum += v;
			}
		}

		if (sum % 10 == 0) {
			str = str + "0";
		} else {
			str += String.valueOf(10 - sum % 10);
		}
		return str;
	}

	public static String getBankCard() {
		Random random = new Random();
		String str = prefix[random.nextInt(2)];

		for (int i = 0; i < 6; i++) {
			str += String.valueOf(random.nextInt(10));
		}

		int sum = 0;

		for (int i = 1; i < 19; i++) {
			int v = Character.getNumericValue(str.charAt(i - 1));

			if (i % 2 == 0) {
				sum += getOSum(v);
			} else {
				sum += v;
			}
		}

		if (sum % 10 == 0) {
			str = str + "0";
		} else {
			str += String.valueOf(10 - sum % 10);
		}

		return str;
	}

	public static String getBankCardByMobile(String mobile) {
		String sql = "select ubi.bank_card_no from user_info ui, user_bankcard_info ubi where ui.mobile_num='" + mobile
				+ "' and ui.id=ubi.user_id limit 1";
		System.out.println("sql="+sql);
		Connection con = DBUtils.getNonoConnection();
		String cardno = DBUtils.getOneObject(con, sql).toString();
		DBUtils.closeConnection();
		return cardno;
	}

	public static boolean validate(String cardno) {
		int len = cardno.length();
		int sum = 0;

		for (int i = len; i > 0; i--) {
			int v = Character.getNumericValue(cardno.charAt(i - 1));

			if (i % 2 == 0) {
				sum += getOSum(v);
			} else {
				sum += v;
			}
		}

		int res = sum % 10;
		return res == 0;
	}

	public static String getUnUsedBankCard(String prefix) {
		Connection con = DBUtils.getNonoConnection();

		while (true) {
			String bankCard = getBankCard(prefix);
			String sql = "select count(*) from user_bankcard_info WHERE bank_card_no='" + bankCard + "'";
			String count = DBUtils.getOneObject(con, sql).toString();

			if (count.equals("0")) {
				return bankCard;
			}
		}
	}
}
