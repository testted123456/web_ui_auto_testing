package com.nonobank.apps.testcase.base;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;

public class BaseTest {

	public BaseTest() {
		System.out.println("************************************调用BaseTest()构造方法");
	}

	@BeforeClass
	public void init() {
		System.out.println("@BeforeClass111111111111111111111111调用init()方法");
	}

	@BeforeClass
	public void init2() {
		System.out.println("@BeforeClass000000000000000000000000调用init()方法");
	}

	@AfterClass
	public void closeDriver() {
		System.out.println("@AfterClass2222222222222222222222222调用closeDriver()方法");

	}

	@AfterTest
	public void after() {
		System.out.println("@AfterTest&&&&&&&&&&&&&&&&&&&&&&&&调用closeDriver()方法");

	}

	@DataProvider(name = "dataSource")
	public Object[][] dataSource() {
		Object[][] testdata = { { "1", "2", "3" }, { "4", "5", "6" } };
		return testdata;
	}

	@AfterMethod
	public void addData() {
		System.out.println("********************************adddata");
	}

	@AfterSuite
	public void saveCSV() {
		System.out.println("++++++++++++++++++++++++++++++++结束++++++++++++++++++++++++++++++++++++++");
	}

}
