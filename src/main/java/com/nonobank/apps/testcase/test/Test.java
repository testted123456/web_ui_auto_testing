//package com.nonobank.apps.testcase.test;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//public class Test {
//	public static String POSSIBLE_CHARS = "0123456789";
//	public static StringBuilder sb;
//	public static String bankType = "CCB";
//	public static String bankName = "中国建设银行";
//	public static String bankCode = "105";
//
//	public static final Map<String, String> areaCode = new HashMap<String, String>();
//	static {
//		areaCode.put("北京市", "110000");
//	}
//
//	public static boolean luhmCheck(String bankno) {
//		Long lastNum = Long.parseLong(bankno.substring(bankno.length() - 1, bankno.length()));// 取出最后一位（与luhm进行比较）
//		String first15Num = bankno.substring(0, bankno.length() - 1);// 前15或18位
//		List<Long> newArr = new ArrayList<Long>();
//
//		for (int i = first15Num.length() - 1; i > -1; i--) { // 前15或18位倒序存进数组
//			newArr.add(Long.parseLong(first15Num.substring(i, i + 1)));
//		}
//		List<Long> arrJiShu = new ArrayList<Long>(); // 奇数位*2的积 <9
//		List<Long> arrJiShu2 = new ArrayList<Long>(); // 奇数位*2的积 >9
//
//		List<Long> arrOuShu = new ArrayList<Long>(); // 偶数位数组
//		for (int j = 0; j < newArr.size(); j++) {
//			if ((j + 1) % 2 == 1) {// 奇数位
//				if (newArr.get(j) * 2 < 9)
//					arrJiShu.add(newArr.get(j) * 2);
//				else
//					arrJiShu2.add(newArr.get(j) * 2);
//			} else // 偶数位
//				arrOuShu.add(newArr.get(j));
//		}
//
//		List<Long> jishu_child1 = new ArrayList<Long>();// 奇数位*2 >9
//														// 的分割之后的数组个位数
//		List<Long> jishu_child2 = new ArrayList<Long>();// 奇数位*2 >9
//														// 的分割之后的数组十位数
//		for (int h = 0; h < arrJiShu2.size(); h++) {
//			jishu_child1.add(arrJiShu2.get(h) % 10);
//			jishu_child2.add(arrJiShu2.get(h) / 10);
//		}
//
//		long sumJiShu = 0; // 奇数位*2 < 9 的数组之和
//		long sumOuShu = 0; // 偶数位数组之和
//		long sumJiShuChild1 = 0; // 奇数位*2 >9 的分割之后的数组个位数之和
//		long sumJiShuChild2 = 0; // 奇数位*2 >9 的分割之后的数组十位数之和
//		long sumTotal = 0;
//		for (int m = 0; m < arrJiShu.size(); m++) {
//			sumJiShu = sumJiShu + arrJiShu.get(m);
//		}
//
//		for (int n = 0; n < arrOuShu.size(); n++) {
//			sumOuShu = sumOuShu + arrOuShu.get(n);
//		}
//
//		for (int p = 0; p < jishu_child1.size(); p++) {
//			sumJiShuChild1 = sumJiShuChild1 + jishu_child1.get(p);
//			sumJiShuChild2 = sumJiShuChild2 + jishu_child2.get(p);
//		}
//		// 计算总和
//		sumTotal = sumJiShu + sumOuShu + sumJiShuChild1 + sumJiShuChild2;
//
//		// 计算Luhm值
//		long k = sumTotal % 10 == 0 ? 10 : sumTotal % 10;
//		long luhm = 10 - k;
//
//		if (lastNum == luhm) {
//			return true;
//		} else {
//			return false;
//		}
//	}
//
//	/** 随机生成一个卡号 */
//	public static String randomCreateBankID() {
//		String cardNo = "";
//		for (int i = 0; i < 1; i++) {
//			if (bankType.equals("ICBC") || bankType.equals("CCB") || bankType.equals("ABC") || bankType.equals("PSBC")
//					|| bankType.equals("BCOM") || bankType.equals("GDB") || bankType.equals("BOC")) {
//				sb = new StringBuilder(13);
//
//				for (int j = 0; j < 13; j++) {
//					sb.append((int) (Math.random() * 10));
//				}
//
//			} else {
//				sb = new StringBuilder(10);
//
//				for (int k = 0; k < 10; k++) {
//					sb.append((int) (Math.random() * 10));
//				}
//			}
//
//			switch (bankType) {
//			// 建设银行
//			case "CCB":
//				cardNo = "621700" + sb;
//				bankCode = "105";
//				break;
//			// 民生银行
//			case "CMBC":
//				cardNo = "621691" + sb;
//				bankCode = "305";
//				break;
//			// 农业银行
//			case "ABC":
//				cardNo = "622827" + sb;
//				bankCode = "103";
//				break;
//			// 交通银行
//			case "BCOM":
//				cardNo = "622262" + sb;
//				bankCode = "301";
//				break;
//			// 招商银行
//			case "CMB":
//				cardNo = "621486" + sb;
//				bankCode = "308";
//				break;
//			// 浦发银行
//			case "SPDB":
//				cardNo = "622521" + sb;
//				bankCode = "310";
//				break;
//			// 广发银行
//			case "GDB":
//				cardNo = "622568" + sb;
//				bankCode = "306";
//				break;
//			// 华夏银行
//			case "HXB":
//				cardNo = "622632" + sb;
//				bankCode = "304";
//				break;
//			// 平安银行
//			case "PAB":
//				cardNo = "622298" + sb;
//				bankCode = "783";
//				break;
//			// 中信银行
//			case "CITIC":
//				cardNo = "622696" + sb;
//				bankCode = "302";
//				break;
//			// 工商银行
//			case "ICBC":
//				cardNo = "620058" + sb;
//				bankCode = "102";
//				break;
//			// 中国银行
//			case "BOC":
//				cardNo = "620061" + sb;
//				bankCode = "104";
//				break;
//			// 兴业银行
//			case "CIB":
//				cardNo = "622908" + sb;
//				bankCode = "309";
//				break;
//			// 光大银行
//			case "CEB":
//				cardNo = "622660" + sb;
//				bankCode = "303";
//				break;
//			// 储蓄银行
//			case "PSBC":
//				cardNo = "621799" + sb;
//				bankCode = "403";
//				break;
//			default:
//				cardNo = "621700" + sb;
//				bankCode = "105";
//			}
//
//			if (luhmCheck(cardNo)) {
//				return cardNo;
//			}
//		}
//		return cardNo;
//	}
//
//	public static void main(String[] args) {
//		String str = randomCreateBankID();
//		String str2 = _RandomCreateID();
//		System.out.println("****************************str=" + str);
//		System.out.println("****************************str2=" + str2);
//	}
//
//	/** 随机生成一个身份证 */
//	public static boolean randomCreateID() {
//
//		boolean ff = false;
//		int ct = 0;
//		while (!ff) {
//			ct++;
//			ff = getCheckID(_RandomCreateID());
//			if (ct == 200) {
//				return (ff);
//			}
//		}
//		ct = 0;
//		return (ff);
//	}
//
//	public static boolean getCheckID(String _pid) {
//		Object[] arrVerifyCode = new Object[] { 1, 0, "X", 9, 8, 7, 6, 5, 4, 3, 2 };
//		int[] wi = new int[] { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 };
//		if (_pid.length() != 15 && _pid.length() != 17)
//			return (false);
//		String ai = (_pid.length() == 17) ? _pid : _pid.substring(0, 6) + "19" + _pid.substring(6);
//		String pattern = "/^\\d+$/";
//		Pattern r = Pattern.compile(pattern);
//		Matcher m = r.matcher(ai);
//		if (m.matches()) {
//			return false;
//		}
//		int yyyy = Integer.parseInt(ai.substring(6, 10));
//		int mm = Integer.parseInt(ai.substring(10, 12)) - 1;
//		int dd = Integer.parseInt(ai.substring(12, 2));
//		Date d = new Date(yyyy, mm, dd);
//		int year = d.getYear();
//		int mon = d.getMonth();
//		int day = d.getDate();
//		Date now = new Date();
//		if (year != yyyy || mon != mm || day != dd || d.getDate() > now.getDate() || now.getYear() - year > 140)
//			return (false);
//		for (int i = 0, ret = 0; i < 17; i++) {
//			ret += ai.charAt(i) * wi[i];
//			ai += arrVerifyCode[ret %= 11];
//		}
//		return getCheckID(ai);
//	}
//
//	public static String _RandomCreateID() {
//		String aid = "0";
//		String ac = null;
//		int yyyy = 0;
//		int mm = 0;
//		int dd = 0;
//		String rnd = "0";
//		String[] Code = new String[] { "11", "12", "13", "14", "15", "21", "22", "23", "31", "32", "33", "34", "35",
//				"36", "37", "41", "42", "43", "44", "45", "46", "50", "51", "52", "53", "54", "61", "62", "63", "64",
//				"65" };
//
//		if (aid.equals("")) {
//			int l = aid.length();
//			for (int i = 0; i < 6 - l; i++) {
//				aid = "1" + aid;
//			}
//		} else {
//			aid = "" + Code[fRandomBy(0, 31)] + fRandomBy(0, 9) + fRandomBy(0, 9) + fRandomBy(0, 9) + fRandomBy(0, 9);
//		}
//		yyyy = fRandomBy(1960, 1990);
//		mm = fRandomBy(1, 12);
//		dd = fRandomBy(1, 31);
//		rnd = "" + fRandomBy(0, 9) + fRandomBy(0, 9) + fRandomBy(0, 9);
//		if ((mm == 2) && (dd > 28)) {
//			dd = fRandomBy(1, 28);
//		} else if (((mm == 4) || (mm == 6) || (mm == 9) || (mm == 11)) && (dd == 31)) {
//			dd = dd - 1;
//		}
//		String newmm = (mm < 10) ? ("0" + mm) : String.valueOf(mm);
//		String newdd = (dd < 10) ? ("0" + dd) : String.valueOf(dd);
//		return ("" + aid + yyyy + newmm + newdd + rnd);
//	}
//
//	public static int fRandomBy(int under, int over) {
//		int aa = 9;
//		switch (aa) {
//		case 1:
//			return (int) (Math.random() * under + 1);
//		case 2:
//			return (int) (Math.random() * (over - under + 1) + under);
//		default:
//			return 0;
//		}
//	}
//
//	public static String ParseID(String pId) {
//		char[] arrVerifyCode = new char[] { 1, 0, 'x', 9, 8, 7, 6, 5, 4, 3, 2 };
//		char[] wi = new char[] { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 };
//		if (pId.length() != 15 && pId.length() != 18)
//			return "身份证号码只能是15位或18位!";
//
//		String ai = (pId.length() == 18) ? pId.substring(0, 17) : pId.substring(0, 6) + "19" + pId.substring(0, 6);
//		String pattern = "/^\\d+$/";
//		Pattern r = Pattern.compile(pattern);
//		Matcher m = r.matcher(ai);
//		if (!m.matches()) {
//			return "身份证除最后一位外，必须为数字！";
//		}
//		int yyyy = Integer.parseInt(ai.substring(6, 10));
//		int mm = Integer.parseInt(ai.substring(10, 12)) - 1;
//		int dd = Integer.parseInt(ai.substring(12, 12));
//		Date d = new Date(yyyy, mm, dd);
//		int year = d.getYear(), mon = d.getMonth(), day = d.getDate();
//		Date now = new Date();
//		if (year != yyyy || mon != mm || day != dd || d.getDate() > now.getDate() || now.getYear() - year > 140)
//			return ("身份证出生年月日输入错误！");
//		int ret = 0;
//		for (int i = 0; i < 17; i++)
//			ret += ai.charAt(i) * wi[i];
//		ai += arrVerifyCode[ret %= 11];
//		return ((pId.length() == 18 && pId.toLowerCase() != ai) ? "身份证输入错误，正确的为\n" + ai + "！" : ai);
//	}
//}
