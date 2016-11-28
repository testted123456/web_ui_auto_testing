package com.nonobank.apps.testcase.base;

import java.lang.reflect.Field;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import com.nonobank.apps.utils.file.ParseProperties;

public class RemoteBaseCase {
	// 配置文件config.xml
	public static Properties prop = ParseProperties.getInstance();

	public WebDriver driver;
	
	protected static Logger logger = LogManager.getLogger(RemoteBaseCase.class);

	@BeforeClass
	public void init() {
		logger.info("========================================================================================");
		logger.info("开始执行: " + this.getClass().getName());
	}

	@BeforeClass
	public void init_fields() {
		logger.info("初始化business...");
		Class clazz = this.getClass();
		Field[] fields = clazz.getDeclaredFields();

		for (Field f : fields) {
			String type = f.getType().getName();
			if (type.startsWith("com.nonobank.apps.business")) {
				f.setAccessible(true);
				try {
					f.set(this, Class.forName(type).newInstance());
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

//	@AfterClass
//	public void closeDriver() {
//		// 保存测试结果
//		logger.info("保存测试结果...");
//		if (testfile != null && !resultsMap.isEmpty()) {
//			ParseXLSX.saveResults(testfile, resultsMap);
//		}
//		// 关闭浏览器
//		logger.info("关闭浏览器...");
//		driver.quit();
//		//每个testcase执行完成后把webdriver置空
//		WebDriverUtils.destoryWebDriver();
//	}
}