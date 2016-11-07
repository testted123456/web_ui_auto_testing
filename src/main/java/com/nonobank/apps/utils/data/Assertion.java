package com.nonobank.apps.utils.data;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;

public class Assertion {
	public static Logger logger = LogManager.getLogger(Assertion.class);

	public static void fail(String msg, Object expected, Object actual) {
		try {
			Assert.assertEquals(expected, actual);
		} catch (Error e) {
			logger.error(msg + "预期值:" + expected + ",实际值:" + actual);
		}
	}

	public static void main(String[] args) {
		fail("aaa", 1, 1);
	}
}
