package com.nonobank.apps.utils.data;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.nonobank.apps.utils.db.DBUtils;
import com.nonobank.apps.utils.entity.UserBankcardInfo;
//-建设银行 ------快钱通道
//-中信银行-连连通道
//-华夏银行-连连通道
//-广发银行 ------快钱通道
//-平安银行 ------快钱通道

// -农业银行 ------块钱通道
// -中国银行-连连通道
// -招商银行-连连通道
// -光大银行-连连通道
// -交通银行-连连通道

// -民生银行-连连通道
// -浦发银行-连连通道
// -工商银行 ------快钱通道
// -兴业银行 ------快钱通道
// -邮政储蓄-连连通道
public class IdBankGenerator {
	public static Map<String, String> map = new HashMap<String, String>() {
		/**
		* 
		*/
		private static final long serialVersionUID = 1L;

		{
			put("建设银行", "CCB");
			put("中信银行", "CITIC");
			put("华夏银行", "HXB");
			put("广发银行", "GDB");
			put("平安银行", "PAB");

			put("农业银行", "ABC");
			put("中国银行", "BOC");
			put("招商银行", "CMB");
			put("光大银行", "CEB");
			put("交通银行", "BCOM");

			put("民生银行", "CMBC");
			put("浦发银行", "SPDB");
			put("工商银行", "ICBC");
			put("兴业银行", "CIB");
			put("邮政储蓄", "PSBC");
		}
	};
	public static StringBuilder sb;
	// public static String bankType = "CCB";
	// public static String bankName = "中国建设银行";
	public static String bankCode = "105";

	/** 随机生成一个卡号 */
	public static String randomCreateBankID(String bankName, boolean flag) {
		String cardNo = "";
		for (int i = 0; i < 1; i++) {
			if (bankName.equals("工商银行") || bankName.equals("建设银行") || bankName.equals("农业银行") || bankName.equals("储蓄银行")
					|| bankName.equals("交通银行") || bankName.equals("广发银行") || bankName.equals("中国银行")) {
				sb = new StringBuilder(13);

				for (int j = 0; j < 13; j++) {
					sb.append((int) (Math.random() * 10));
				}

			} else {
				sb = new StringBuilder(10);

				for (int k = 0; k < 10; k++) {
					sb.append((int) (Math.random() * 10));
				}
			}

			switch (bankName) {
			// 建设银行-CCB
			case "CCB":
				cardNo = "621700" + sb;
				bankCode = "105";
				break;
			// 民生银行-CMBC
			case "CMBC":
				cardNo = "621691" + sb;
				bankCode = "305";
				break;
			// 农业银行-ABC
			case "ABC":
				cardNo = "622827" + sb;
				bankCode = "103";
				break;
			// 交通银行-BCOM
			case "BCOM":
				cardNo = "622262" + sb;
				bankCode = "301";
				break;
			// 招商银行-CMB
			case "CMB":
				cardNo = "621486" + sb;
				bankCode = "308";
				break;
			// 浦发银行-SPDB
			case "SPDB":
				cardNo = "622521" + sb;
				bankCode = "310";
				break;
			// 广发银行-GDB
			case "GDB":
				cardNo = "622568" + sb;
				bankCode = "306";
				break;
			// 华夏银行-HXB
			case "HXB":
				cardNo = "622632" + sb;
				bankCode = "304";
				break;
			// 平安银行-PAB
			case "PAB":
				cardNo = "622298" + sb;
				bankCode = "783";
				break;
			// 中信银行-CITIC
			case "CITIC":
				cardNo = "622696" + sb;
				bankCode = "302";
				break;
			// 工商银行-ICBC
			case "ICBC":
				cardNo = "620058" + sb;
				bankCode = "102";
				break;
			// 中国银行-BOC
			case "BOC":
				cardNo = "620061" + sb;
				bankCode = "104";
				break;
			// 兴业银行-CIB
			case "CIB":
				cardNo = "622908" + sb;
				bankCode = "309";
				break;
			// 光大银行-CEB
			case "CEB":
				cardNo = "622660" + sb;
				bankCode = "303";
				break;
			// 储蓄银行-PSBC
			case "PSBC":
				cardNo = "621799" + sb;
				bankCode = "403";
				break;
			default:
				cardNo = "621700" + sb;
				bankCode = "105";
			}

			if (luhmCheck(cardNo) == flag) {
				return cardNo;
			}
		}
		return randomCreateBankID(bankName, flag);
	}

	public static boolean luhmCheck(String bankno) {
		Long lastNum = Long.parseLong(bankno.substring(bankno.length() - 1, bankno.length()));// 取出最后一位（与luhm进行比较）
		String first15Num = bankno.substring(0, bankno.length() - 1);// 前15或18位
		List<Long> newArr = new ArrayList<Long>();

		for (int i = first15Num.length() - 1; i > -1; i--) { // 前15或18位倒序存进数组
			newArr.add(Long.parseLong(first15Num.substring(i, i + 1)));
		}
		List<Long> arrJiShu = new ArrayList<Long>(); // 奇数位*2的积 <9
		List<Long> arrJiShu2 = new ArrayList<Long>(); // 奇数位*2的积 >9

		List<Long> arrOuShu = new ArrayList<Long>(); // 偶数位数组
		for (int j = 0; j < newArr.size(); j++) {
			if ((j + 1) % 2 == 1) {// 奇数位
				if (newArr.get(j) * 2 < 9)
					arrJiShu.add(newArr.get(j) * 2);
				else
					arrJiShu2.add(newArr.get(j) * 2);
			} else // 偶数位
				arrOuShu.add(newArr.get(j));
		}

		List<Long> jishu_child1 = new ArrayList<Long>();// 奇数位*2 >9
														// 的分割之后的数组个位数
		List<Long> jishu_child2 = new ArrayList<Long>();// 奇数位*2 >9
														// 的分割之后的数组十位数
		for (int h = 0; h < arrJiShu2.size(); h++) {
			jishu_child1.add(arrJiShu2.get(h) % 10);
			jishu_child2.add(arrJiShu2.get(h) / 10);
		}

		long sumJiShu = 0; // 奇数位*2 < 9 的数组之和
		long sumOuShu = 0; // 偶数位数组之和
		long sumJiShuChild1 = 0; // 奇数位*2 >9 的分割之后的数组个位数之和
		long sumJiShuChild2 = 0; // 奇数位*2 >9 的分割之后的数组十位数之和
		long sumTotal = 0;
		for (int m = 0; m < arrJiShu.size(); m++) {
			sumJiShu = sumJiShu + arrJiShu.get(m);
		}

		for (int n = 0; n < arrOuShu.size(); n++) {
			sumOuShu = sumOuShu + arrOuShu.get(n);
		}

		for (int p = 0; p < jishu_child1.size(); p++) {
			sumJiShuChild1 = sumJiShuChild1 + jishu_child1.get(p);
			sumJiShuChild2 = sumJiShuChild2 + jishu_child2.get(p);
		}
		// 计算总和
		sumTotal = sumJiShu + sumOuShu + sumJiShuChild1 + sumJiShuChild2;

		// 计算Luhm值
		long k = sumTotal % 10 == 0 ? 10 : sumTotal % 10;
		long luhm = 10 - k;

		if (lastNum == luhm) {
			return true;
		} else {
			return false;
		}
	}

	public static String getUnUsedBankCard(String bankName) {
		while (true) {
			String bankCardNo = randomCreateBankID(bankName, true);
			UserBankcardInfo userBankcardInfo = new UserBankcardInfo();
			userBankcardInfo.setBankCardNo(bankCardNo);
			List<String> UserBankcardInfos = getUserBankcardInfos(userBankcardInfo, null, null);
			if (UserBankcardInfos.size() == 0) {
				return bankCardNo;
			}
		}
	}

	public static String getUsedBankCard(String bankCode) {
		while (true) {
			UserBankcardInfo userBankcardInfo = new UserBankcardInfo();
			userBankcardInfo.setBankCode(bankCode);
			List<String> userBankcardInfos = getUserBankcardInfos(userBankcardInfo, null, null);
			if (userBankcardInfos.size() > 0) {
				for (String bankno : userBankcardInfos) {
					if (luhmCheck(bankno)) {
						return bankno;
					}
				}
			}
		}
	}

	public static String getInvalidBankCard(String bankCode) {
		return randomCreateBankID(bankCode, false);
	}

	public static List<String> getUserBankcardInfos(UserBankcardInfo userBankcardInfo, String operatorType,
			String operatorValue) {
		List<String> userInfos = new ArrayList<>();
		try {
			Connection con = DBUtils.getConnection("nono");
			UserBankcardInfo.setUserInfoCondition(userBankcardInfo);
			UserBankcardInfo.setLimit(ConstantUtils.LIMIT);
			String sql = "select id from user_bankcard_info " + UserBankcardInfo.getCondition();
			List<Object[]> lst = DBUtils.getMulLine(con, sql);
			for (Object[] objects : lst) {
				userInfos.add(objects[0].toString());
			}
		} catch (Exception e) {
		}
		return userInfos;
	}

	public static void main(String[] args) {
//		System.out.println(getUnUsedBankCard("4"));
		
		System.out.println(luhmCheck("621700207000149051"));

	}
}