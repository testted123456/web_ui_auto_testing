package com.nonobank.apps.testcase.base;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.TreeMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import com.csvreader.CsvWriter;
import com.nonobank.apps.utils.data.StringUtils;
import com.nonobank.apps.utils.driver.WebDriverUtils;
import com.nonobank.apps.utils.file.ParseProperties;
import com.nonobank.apps.utils.file.ParseXLSX;
import com.nonobank.apps.utils.page.PageUtils;

public class BaseCase {
	// 配置文件config.xml
	public static Properties prop = ParseProperties.getInstance();

	public WebDriver driver;

	// 测试数据文件，保存在recourse/TestData下
	public String testfile;
	public static List<List<String>> lst = new ArrayList<List<String>>();
	protected static Logger logger = LogManager.getLogger(BaseCase.class);

	// 保存测试结果的map
	public static TreeMap<Long, Integer> resultsMap;
	public String caseName = "请配置测试名称";
	public String caseDescription = "请配置测试描述";
	public String inputParams = "请配置输入参数";
	public static String actualResult = "成功";
	public static String errorMessage = "无错误信息";
	public static int passCount = 0;
	public static int failCount = 0;

	public String getCaseName() {
		return caseName;
	}

	public void setCaseName(String caseName) {
		this.caseName = caseName;
	}

	public String getCaseDescription() {
		return caseDescription;
	}

	public void setCaseDescription(String caseDescription) {
		this.caseDescription = caseDescription;
	}

	public String getInputParams() {
		return inputParams;
	}

	public void setInputParams(String inputParams) {
		this.inputParams = inputParams;
	}

	public String getActualResult() {
		return actualResult;
	}

	public void setActualResult(String newactualResult) {
		actualResult = newactualResult;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String newerrorMessage) {
		errorMessage = newerrorMessage;
	}

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
		String url = ParseProperties.getInstance().getProperty("url");
		PageUtils.openPage(url);
		PageUtils.waitForPageLoad();
		logger.info(PageUtils.getUrl());
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

	@AfterMethod
	public void addData() {
		if (actualResult.equals("成功")) {
			passCount++;
		} else {
			failCount++;
		}
		List<String> newLst = new ArrayList<>();
		newLst.add(getCaseName());
		newLst.add(getCaseDescription());
		newLst.add("用户手机号:" + inputParams);
		newLst.add(actualResult);
		newLst.add(errorMessage);
		lst.add(newLst);
		String url = ParseProperties.getInstance().getProperty("url") + "/Login/logout";
		PageUtils.openPage(url);
		PageUtils.waitForPageLoad();
		logger.info(PageUtils.getUrl());
	}

	@AfterTest
	public void saveCSV() {
		try {
			String[] strs = caseName.split("-");
			String fileName = strs[1];
			if (fileName.equals("贷款") || fileName.equals("还款")) {
				fileName = "贷还款";
			} else {
				fileName = "理财";
			}
			OutputStream os = new FileOutputStream("./web" + fileName + ".csv");
			OutputStreamWriter fw = new OutputStreamWriter(os, "GBK");
			fw.write("sep=;\n");
			CsvWriter writer = new CsvWriter(fw, ';');
			String[] contents = { "case名称", "描述", "入参", "结果", "错误日志" };
			writer.writeRecord(contents);
			for (List<String> rowsData : lst) {
				List<String> listData = new ArrayList<String>();
				for (String rowData : rowsData) {
					listData.add(rowData);
				}
				writer.writeRecord(listData.toArray(new String[listData.size()]));
			}
			String passTotal = StringUtils.formatDouble(((double) passCount / (passCount + failCount)) * 100);
			String pass = passTotal + "%";
			String failTotal = StringUtils.formatDouble((1 - ((double) passCount / (passCount + failCount))) * 100);
			String fail = failTotal + "%";
			String[] text = { "", "" };
			writer.writeRecord(text);
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
			String strDate = df.format(new Date());
			String[] endsTitle = { "最近一次运行时间", "成功case", "失败case", "成功率", "失败率" };
			writer.writeRecord(endsTitle);
			String[] endsValue = { strDate, passCount + "个", failCount + "个", pass, fail };
			writer.writeRecord(endsValue);
			writer.close();
			System.out.println(writer.toString());
			// 重新清理数据
			lst = new ArrayList<List<String>>();
			passCount = 0;
			failCount = 0;
		} catch (IOException e) {
			e.printStackTrace();
		}

	}


}
