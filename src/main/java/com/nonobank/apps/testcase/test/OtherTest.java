package com.nonobank.apps.testcase.test;

import org.testng.annotations.Test;
import com.nonobank.apps.testcase.base.BaseTest;

public class OtherTest extends BaseTest {

	@Test(dataProvider = "dataSource")
	public void testLogin(String str1, String str2, String str3) {
		for (int i = 0; i < 20; i++) {
			// Assertion.assertEquals(1, 0, OtherTest.class, "othertest比较值");
		}
		System.out.println("this is test OtherTest");
	}

}
