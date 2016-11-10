package com.nonobank.apps.utils.data;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;

public class Assertion {
	public static Logger logger;

	public static void assertEquals(Object expected, Object actual, Class clazz, String msg) {
		logger = LogManager.getLogger(clazz);

		try {
			Assert.assertEquals(expected, actual);
			logger.info("检查点：" + msg + "-----------成功");
		} catch (Error e) {
			logger.error("检查点：" + msg + "-----------失败，预期值:" + expected + ",实际值:" + actual);
			Assert.fail();
		}
	}

	public static void assertEquals(Class clazz, String msg) {
		logger = LogManager.getLogger(clazz);
		logger.error(msg + "-----------执行返回为空值");
		Assert.fail();
	}

}
