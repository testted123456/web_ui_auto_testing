package com.nonobank.apps.testcase.base;

import java.lang.reflect.Field;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
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
		Class<?> clazz = this.getClass();
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
		// 保存测试结果,关闭浏览器
		logger.info("保存测试结果,关闭浏览器...");
		driver.close();
		driver = null;
	}
}
