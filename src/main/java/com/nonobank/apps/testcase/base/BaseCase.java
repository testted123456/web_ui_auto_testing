package com.nonobank.apps.testcase.base;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Properties;
import java.util.TreeMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import com.nonobank.apps.utils.driver.WebDriverUtils;
import com.nonobank.apps.utils.file.ParseProperties;
import com.nonobank.apps.utils.file.ParseXLSX;

public class BaseCase {
	// 配置文件config.xml
	public static Properties prop = ParseProperties.getInstance();

	public WebDriver driver;

	// 测试数据文件，保存在recourse/TestData下
	public String testfile;

	protected static Logger logger = LogManager.getLogger(BaseCase.class);

	// 保存测试结果的map
	public static TreeMap<Long, Integer> resultsMap;

	public BaseCase() {
		logger.info("初始化类:" + this.getClass().getName());

		// 测试数据文件，文件名字必须和testcase的类名相同
		testfile = "resources" + File.separator + "TestData" + File.separator
				+ this.getClass().getName().replace(".", File.separator) + ".xlsx";
		resultsMap = new TreeMap<Long, Integer>();
	}

	// 测试数据保存到二维数组testdata，sheet的名字必须和testng的测试方法相同
	@DataProvider(name = "dataSource")
	public Object[][] dataSource(Method method) {
		logger.info("开始准备测试数据...");

		Object[][] testdata = null;
		// 测试方法默认为test
		testdata = ParseXLSX.getDataValue(testfile, "test");
		return testdata;
	}

	@BeforeClass
	public void init() {
		logger.info("========================================================================================");
		logger.info("开始执行: " + this.getClass().getName());
		driver = WebDriverUtils.getWebDriver();
		PageFactory.initElements(driver, this);
	}

	@BeforeClass
	public void init_fields() {
		logger.info("初始化business...");
		Class<? extends BaseCase> clazz = this.getClass();
		Field[] fields = clazz.getDeclaredFields();

		for (Field f : fields) {
			String type = f.getType().getName();
			if (type.startsWith("com.nonobank.apps.business")) {
				f.setAccessible(true);
				try {
					f.set(this, Class.forName(type).newInstance());
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@AfterClass
	public void closeDriver() {
		// 保存测试结果
		logger.info("保存测试结果...");
		if (testfile != null && !resultsMap.isEmpty()) {
			ParseXLSX.saveResults(testfile, resultsMap);
		}
		// 关闭浏览器
		logger.info("关闭浏览器...");
		driver.quit();
		// 每个testcase执行完成后把webdriver置空
		WebDriverUtils.destoryWebDriver();
		logger.info("========================================================================================");
	}

}
