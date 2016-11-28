package com.nonobank.apps.listener;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

public class TestngListenerForRemote extends TestListenerAdapter {
	
	private String result;

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	@Override
	public void onTestFailure(ITestResult tr) {
		super.onTestFailure(tr);
		setResult("Failure");
	}

	@Override
	public void onTestSkipped(ITestResult tr) {
		super.onTestSkipped(tr);
		setResult("Skipped");
	}

	@Override
	public void onTestSuccess(ITestResult tr) {
		super.onTestSuccess(tr);
		setResult("Success");
	}

	@Override
	public void onTestStart(ITestResult tr) {
		super.onTestStart(tr);
		setResult("Start");
	}

	@Override
	public void onFinish(ITestContext testContext) {
		super.onFinish(testContext);
		setResult("Finish");
	}
}
