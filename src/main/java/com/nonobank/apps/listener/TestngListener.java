package com.nonobank.apps.listener;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import com.nonobank.apps.business.portal.Biz_Logout;
import com.nonobank.apps.testcase.base.BaseCase;
import com.nonobank.apps.utils.driver.WebDriverUtils;
import com.nonobank.apps.utils.file.ParseProperties;
import org.apache.logging.log4j.*;

public class TestngListener extends TestListenerAdapter {

	private static Logger logger = LogManager.getLogger(TestngListener.class);

	@Override
	public void onTestFailure(ITestResult tr) {
		super.onTestFailure(tr);
		Throwable throwable = tr.getThrowable();

		if (null != throwable) {
			String throwableName = throwable.getClass().getName();
			if (null != throwableName) {
				logger.error(throwableName);
			}
		}

		BaseCase.resultsMap.put(tr.getStartMillis(), tr.getStatus());
		logger.error(tr.getInstanceName() + " : " + tr.getName() + " Failure...");
		takeScreenShot(tr);

		// 退出登录
		Biz_Logout biz_Logout = new Biz_Logout();
		biz_Logout.logout();
	}

	@Override
	public void onTestSkipped(ITestResult tr) {
		String trName = tr.getName();
		if (trName.equals("comment")) {
			return;
		}
		super.onTestSkipped(tr);
		BaseCase.resultsMap.put(tr.getStartMillis(), tr.getStatus());
		logger.info(tr.getInstanceName() + " : " + trName + " Skipped...");
		takeScreenShot(tr);
	}

	@Override
	public void onTestSuccess(ITestResult tr) {
		super.onTestSuccess(tr);
		BaseCase.resultsMap.put(tr.getStartMillis(), tr.getStatus());
		logger.info(tr.getInstanceName() + " : " + tr.getName() + " Success...");
	}

	@Override
	public void onTestStart(ITestResult tr) {
		super.onTestStart(tr);
		logger.info(tr.getInstanceName() + " : " + tr.getName() + " Start...");
	}

	@Override
	public void onFinish(ITestContext testContext) {
		super.onFinish(testContext);
	}

	/**
	 * 自动截图，保存图片到本地
	 * 
	 * @param tr
	 */
	private void takeScreenShot(ITestResult tr) {

		logger.info("开始截图 : " + tr.getInstanceName() + " - " + tr.getName());

		Date currentTime = new Date();

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss");

		String dateString = formatter.format(currentTime);

		WebDriver driver = WebDriverUtils.getWebDriver();

		String dir = ParseProperties.getInstance().getProperty("screenshot_dir") + File.separator +

				new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + File.separator + tr.getInstanceName()
				+ File.separator;

		File file = new File(dir);

		if (!file.exists()) {
			file.mkdir();
		}
		try {
			File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(srcFile, new File(dir + File.separator + dateString + ".jpg"));
		} catch (Exception e) {
			logger.info("截图失败...");
		}
	}
}
