package com.jan23.utility;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.jan23.base.BaseTestSalesforce;
import com.jan23.pages.base.BasePageSalesforce;

public class TestEventListenersUtility implements ITestListener{
	
	protected static ExtentReportsUtility extentReport = null;
	protected WebDriver driver;
	

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		extentReport.startSingleTestReport(result.getMethod().getMethodName());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		extentReport.logTestPassed(result.getMethod().getMethodName()); 
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		extentReport.logTestFailed(result.getMethod().getMethodName());
		BaseTestSalesforce ob = new BaseTestSalesforce();
		String path = ob.getScreenshotBase64(driver);
		System.out.println("driver check =" +driver);
		extentReport.logTestFailedWithException(result.getThrowable());
		extentReport.logTestScreenshotBase64(path);
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		extentReport = ExtentReportsUtility.getInstance();
		extentReport.startExtentReporter();
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		extentReport.endReport();
	}

	
}
