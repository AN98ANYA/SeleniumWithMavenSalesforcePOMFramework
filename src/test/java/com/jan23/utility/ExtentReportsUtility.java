package com.jan23.utility;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportsUtility {
	public static ExtentReports report;
	public static ExtentSparkReporter sparkReporter;
	public static ExtentTest testLogger;
	private static ExtentReportsUtility extentObject;
	
	private ExtentReportsUtility() {
		
	}
	
	public static ExtentReportsUtility getInstance() {
		if(extentObject==null) {
			System.out.println("creating textent utility object");
			extentObject = new ExtentReportsUtility();
		}
		return extentObject;
	}
	
	public void startExtentReporter() {
		sparkReporter = new ExtentSparkReporter(Constants.SPARKS_HTML_REPORT_PATH);
		report = new ExtentReports();
		report.attachReporter(sparkReporter);
		
		report.setSystemInfo("Host name", "Tekarch");
		report.setSystemInfo("Environment", "Automation Testing");
		report.setSystemInfo("User Name", "Admin");
		
		sparkReporter.config().setDocumentTitle("Test Execution Report");
		sparkReporter.config().setReportName("firebase regression tests");
		sparkReporter.config().setTheme(Theme.DARK);
	}
	
	public void startSingleTestReport(String testScript_Name) {
		testLogger = report.createTest(testScript_Name);
	}

	public void logTestInfo(String text) {
		testLogger.info(text);
	}
	
	public void logTestPassed(String testcaseName) {
		testLogger.pass(MarkupHelper.createLabel(testcaseName + "is passTest", ExtentColor.GREEN));
	}
	
	public void logTestFailed(String testcaseName) {
		testLogger.fail(MarkupHelper.createLabel(testcaseName + "is failed", ExtentColor.RED));
	}
	
	public void logTestFailedWithException(Throwable e) {
		testLogger.fail(e);
	}
	
	public void logTestScreenshotBase64(String path) {
		testLogger.addScreenCaptureFromBase64String(path);
	}
	
	public void endReport() {
		report.flush();
	}
}
