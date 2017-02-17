package com.nonobank.apps.testcase.test;

import java.text.DecimalFormat;

public class Test {
	public static void test1() {
		DecimalFormat df = new DecimalFormat(".00");

		double d1 = 333.23156;
		double d2 = 0.0;
		double d3 = 2.0;
		String aa = df.format(d1);
		System.out.println("******************aa=" + aa);
		df.format(d2);
		df.format(d3);
	}

	public static void main(String[] args) {
		test1();
	}
}
