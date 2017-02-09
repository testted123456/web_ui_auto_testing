package com.nonobank.apps.testcase.test;

import org.testng.annotations.Test;

import com.nonobank.apps.testcase.base.BaseCase;
import com.nonobank.apps.utils.data.Assertion;

public class FunctionTest extends BaseCase {
	@Test
	public void testOpenPage() {
		for (int i = 0; i < 10; i++) {
//			Assertion.assertEquals(1, 0, SystemTest.class, "functiontest比较值");
		}
		System.out.println("this is test FunctionTest");
	}
}
