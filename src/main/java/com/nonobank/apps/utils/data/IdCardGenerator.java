package com.nonobank.apps.utils.data;

import java.util.List;
import com.nonobank.apps.utils.entity.UserInfo;

public class IdCardGenerator {
	// wi =2(n-1)(mod 11)
	final int[] wi = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 }; //

	// verify digit
	final int[] vi = { 1, 0, 'X', 9, 8, 7, 6, 5, 4, 3, 2 }; //

	private int[] ai = new int[18];

	public IdCardGenerator() {
	}
	// verify

	// get verify
	public String getVerify(String eightcardid) { // eightcardid
		int remaining = 0;

		if (eightcardid.length() == 17) {
			int sum = 0;
			for (int i = 0; i < 17; i++) {
				String k = eightcardid.substring(i, i + 1);
				ai[i] = Integer.parseInt(k);
			}

			for (int i = 0; i < 17; i++) {
				sum = sum + wi[i] * ai[i];
			}
			remaining = sum % 11;
			return remaining == 2 ? "X" : String.valueOf(vi[remaining]);

		} else {
			return "出错了!";
		}

	}

	// 用随机数 写出前17位

	public String getSheng() {
		// 第一二位 前两位 01- 64
		// int number = (int)(Math.random()*(tonum-fromnum))+fromnum;
		int number;
		String str = null;
		number = (int) (Math.random() * (64 - 1) + 1);
		if (number < 10) {
			// 转换成字符串

			str = Integer.toString(number);
			str = "0" + number;
			// System.out.println(str);
			return str;
		} else {

			// System.out.println(Integer.toString(number));
			return Integer.toString(number);
		}
	}

	public String getShi() {
		// 第三四位 两位 01-02
		int number;
		number = (int) (Math.random() * 1 + 1);
		String str = null;
		if (number < 10) {
			// 转换成字符串

			str = Integer.toString(number);
			str = "0" + number;
			// System.out.println(str);
			return str;
		} else {

			// System.out.println(Integer.toString(number));
			return Integer.toString(number);
		}
	}

	public String getXian() {
		// 第五六位 两位 01-05
		int number;
		number = (int) (Math.random() * 4 + 1);
		String str = null;
		if (number < 10) {
			// 转换成字符串

			str = Integer.toString(number);
			str = "0" + number;
			// System.out.println(str);
			return str;
		} else {

			// System.out.println(Integer.toString(number));
			return Integer.toString(number);
		}
	}

	public String getYear() {
		// 第 7--10位 年份：1970-1990
		int number;
		number = (int) (Math.random() * (1990 - 1970) + 1970);
		String str = null;
		if (number < 10) {
			// 转换成字符串

			str = Integer.toString(number);
			str = "0" + number;
			// System.out.println(str);
			return str;
		} else {

			// System.out.println(Integer.toString(number));
			return Integer.toString(number);
		}
	}

	public String getMonth() {
		// 第11 12 位 月份：01-12

		int number;
		number = (int) (Math.random() * (12 - 1) + 1);
		String str = null;
		if (number < 10) {
			// 转换成字符串

			str = Integer.toString(number);
			str = "0" + number;
			// System.out.println(str);
			return str;
		} else {

			// System.out.println(Integer.toString(number));
			return Integer.toString(number);
		}
	}

	public String getDay() {
		// 第13 14 位 日期：01-28

		int number;
		number = (int) (Math.random() * (28 - 1) + 1);
		String str = null;
		if (number < 10) {
			// 转换成字符串

			str = Integer.toString(number);
			str = "0" + number;
			// System.out.println(str);
			return str;
		} else {

			// System.out.println(Integer.toString(number));
			return Integer.toString(number);
		}

	}

	public String getSequence() {
		// 第15 16 位 两位：01-99
		int number;
		number = (int) (Math.random() * (99 - 1) + 1);
		String str = null;
		if (number < 10) {
			// 转换成字符串

			str = Integer.toString(number);
			str = "0" + number;
			// System.out.println(str);
			return str;
		} else {

			// System.out.println(Integer.toString(number));
			return Integer.toString(number);
		}
	}

	public String getSex() {
		// 第17 位 一位性别：0-9
		int number;
		number = (int) (Math.random() * (9 - 0) + 0);
		return Integer.toString(number);
	}

	public String get17ID() {
		String str = null;
		str = this.getSheng() + this.getShi() + this.getXian() + this.getYear() + this.getMonth() + this.getDay()
				+ this.getSequence() + this.getSex();
		return str;
	}

	public String getIDCard() {
		String str = null;
		str = this.get17ID();
		str = str + this.getVerify(str);
		// String test = this.get17ID();
		// System.out.println("身份证号前17位："+test);
		// System.out.println("身份证号："+test+this.getVerify(test));
		return str;
	}

	public static void main(String[] args) {

		// IDCard card=new IDCard();

		// String idcard = "41011119820316258";// 41112119821028551
		// 41102219841112602
		// 410111452105122564
		// String idcard = card.get17ID();
		// System.out.println("41124919810612243" + card.getVerify(idcard));
		// System.out.println("身份证的最后一位为：" + card.getVerify(idcard));
		// System.out.println("身份证的最后一位为：" + card.getVerify("你的身份证号前17位"));
		// System.out.println("身份证号前17位为：" + card.get17ID());
		// System.out.println("身份证号为："+card.getIDCard());

		// 411249198106122431
		// 身份证的最后一位为：1
		// 身份证的最后一位为：出错了!
		// 身份证号前17位为：51010119840906487
		// 身份证号为：570104197007104472

		IdCardGenerator card = new IdCardGenerator();

		for (int i = 0; i < 1000; i++) {
			String idcard = card.getIDCard();
			System.out.println(card.getIDCard() + "************************" + IDCardVerify.verify(idcard));
		}
	}

	public static String getUnUsedIDCard() {
		IdCardGenerator card = new IdCardGenerator();
		while (true) {
			String idNum = card.getIDCard();
			boolean flag = IDCardVerify.verify(idNum);
			if (flag) {
				UserInfo userInfo = new UserInfo();
				userInfo.setIdNum(idNum);
				List<String> userInfos = UserInfoUtils.getUserInfos(userInfo, ">", "0", "mobile_num");
				if (userInfos.size() == 0) {
					return idNum;
				}
			}
		}
	}

	public static String getUsedIDCard() {
		IdCardGenerator card = new IdCardGenerator();
		String idNum = null;
		idNum = card.getIDCard();
		UserInfo userInfo = new UserInfo();
		List<String> userInfos = UserInfoUtils.getUserInfos(userInfo, ">", "0", "mobile_num");
		for (String idcard : userInfos) {
			if (IDCardVerify.verify(idcard)) {
				return idcard;
			}
		}
		return idNum;
	}

}