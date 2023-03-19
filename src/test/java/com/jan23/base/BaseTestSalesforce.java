package com.jan23.base;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.jan23.utility.Constants;
import com.jan23.utility.ExtentReportsUtility;
import com.jan23.utility.PropertiesUtility;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTestSalesforce {
	protected static WebDriver driver = null;
	protected static Logger logger = null;
	protected static ExtentReportsUtility extentReport = ExtentReportsUtility.getInstance();
	
	
	
	@BeforeTest
	public void setUpBeforeTest() {
		System.out.println("inside @BeforeTest method");
		logger = LogManager.getLogger(BaseTestSalesforce.class.getName());
	}
	
	@BeforeMethod
	@Parameters("browsername")
	public void setUpBeforeTestMethod(@Optional("chrome") String browserName, Method method) {
		logger.info("started testscript name = "+method.getName());
		PropertiesUtility propertiesUtility =new PropertiesUtility();
		propertiesUtility.loadFile("applicationDataProperties");
		String url = propertiesUtility.getPropertyValue("url");
		getDriverInstances(browserName);
		getUrl(url);
	}
	
	@AfterMethod
	public void tearDownAfterTestMethod() {
		driver.quit();
	}
	
	public static void getUrl(String url) {
		driver.get(url);
}
	
	public static void getDriverInstances(String browserName) {
		switch(browserName) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			break;
		case "safari":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			break;
		default: 
			System.out.println("not entered proper browser name");
		}
		
	}
	
	public String getScreenshotBase64(WebDriver driver) {
		// random value + date()+testcase name -> filename 
		TakesScreenshot screenShot = (TakesScreenshot)driver;
		System.out.println("driver ="+driver);
		String imgFile = screenShot.getScreenshotAs(OutputType.BASE64);
		
		return imgFile;
	}

}
