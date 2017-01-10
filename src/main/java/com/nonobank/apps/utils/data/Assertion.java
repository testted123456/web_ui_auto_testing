package com.nonobank.apps.utils.data;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import com.nonobank.apps.testcase.base.BaseCase;

public class Assertion {
	public static Logger logger;
	

	public static void assertEquals(Object expected, Object actual, Class<?> clazz, String msg) {
		logger = LogManager.getLogger(clazz);

		try {
			Assert.assertEquals(expected, actual);
			BaseCase.actualResult = "成功";
			BaseCase.errorMessage = "无错误信息";
			logger.info("检查点：" + msg + "-----------执行成功，预期值:" + expected + ",实际值:" + actual);
		} catch (Error e) {
			BaseCase.actualResult = "失败";
			BaseCase.errorMessage = "检查点：" + msg + "-----------執行失败，预期值:" + expected + ",实际值:" + actual;
			logger.error(BaseCase.errorMessage);
			Assert.fail();
		}
	}

	public static void assertEquals(Class<?> clazz, String msg) {
		logger = LogManager.getLogger(clazz);
		logger.error(msg + "-----------执行返回为空值");
		Assert.fail();
	}

}
