package com.jan23.pages.base;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.jan23.utility.Constants;
import com.jan23.utility.ExtentReportsUtility;

public class BasePageSalesforce {

	protected static WebDriverWait wait = null;
	protected static WebDriver driver = null;
	protected static Logger logger = LogManager.getLogger(BasePageSalesforce.class.getName());
	protected static ExtentReportsUtility extentReport = ExtentReportsUtility.getInstance();
	
	public BasePageSalesforce(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public static void loginCredentials(WebElement element, String text, String name) {
		if(element.isDisplayed()) { 
			clearElement(element, name);
			element.sendKeys(text);
			logger.info("text entered in " + name + " field");
			extentReport.logTestPassed("text entered in " + name + " field");
		} else {
			logger.error("fail: "+ name + " element is not displayed");
			extentReport.logTestFailed("fail: "+ name + " element is not displayed");
		}
		driver.getTitle();
	}
	
	public static void clearElement(WebElement element, String objName) {
		if(element.isDisplayed()) {
			element.clear();
			logger.info("pass: " + objName + " element cleared");
			extentReport.logTestPassed("pass: " + objName + " element cleared");
		} else {
			logger.error("fail: " + objName + " element not displayed");
			extentReport.logTestFailed("fail: " + objName + " element not displayed");
		}
	}
	public static void clickElement(WebElement element, String objName) {
		if(element.isDisplayed()) {
			element.click();
			logger.info("pass: " + objName + " element cleared");
			extentReport.logTestPassed("pass: " + objName + " element cleared");
		} else {
			logger.error("fail: " + objName + " element not displayed");
			extentReport.logTestFailed("fail: " + objName + " element not displayed");
		}
	}
	
	public void refreshPage() {
		driver.navigate().refresh();
	}
	
	public void waitUntilPageLoads() {
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
	}
	
	
	public static void enterText(WebElement element, String textname, String name) {
		String expected = textname;
		String actual = element.getText();
		if(actual.equalsIgnoreCase(expected)) {
			logger.info(name+" script is passed");
			extentReport.logTestPassed(name+" script is passed");
		}
		else {
			logger.error(name+" script is failed");
			extentReport.logTestFailed(name+" script is failed");
		}
	}
	public static String getTextFormWebElement(WebElement element, String name) {
		if(element.isDisplayed()) { 
			return element.getText();
		}
		else {
			logger.info("the "+ name + " web element is not displayed");
			extentReport.logTestInfo("the "+ name + " web element is not displayed");
			return null;
		}
	}
	public static void closeBrowser() {
		logger.info("closing the browser");
		extentReport.logTestInfo("closing the browser");
		driver.close();
	}
	public static void sleepMethod() throws InterruptedException {
		Thread.sleep(5000);
	}
	
	public static void moveToElementAction(WebElement ele, String objName)  {
		Actions action = new Actions(driver);
		action.moveToElement(ele).build().perform();
		System.out.println("cursor moved to web element"+objName);
	}
	
	public void ContextClickAction(WebElement ele, String objName) {
		Actions action = new Actions(driver);
		action.contextClick(ele).build().perform();
		System.out.println("right click persormed on web elemet"+objName);
	}
	
	public void WaitUntilElementIsVisible(WebElement ele, String objName) {
		System.out.println("waiting for an elenment" +objName+" for its visibility");
		wait= new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(ele));
	}
	public static void waitUntilAlertIsPresent() {
		logger.info("waiting for alert to be present");
		extentReport.logTestInfo("waiting for alert to be present");
		wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.alertIsPresent());
	}
	public static void waitUntilElementToBeClickable(By locator, String objName) {
		logger.info("Waiting for an webElement "+objName+"to be clickable");
		extentReport.logTestInfo("Waiting for an webElement "+objName+"to be clickable");
		wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(locator));
	}
	public static void waitFluentForVisibility(WebElement ele, String objName) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(30))
				.pollingEvery(2, TimeUnit.SECONDS)
				.ignoring(NoSuchElementException.class);
		wait.until(ExpectedConditions.visibilityOf(ele));
	}
	
	public static Alert switchToAlert() {
		Alert alert = driver.switchTo().alert();
		logger.info("switched to alert");
		extentReport.logTestInfo("switched to alert");
		return alert;
	}
	public static void AcceptAlert(Alert alert) {
		logger.info("Alert accepted");
		extentReport.logTestInfo("Alert accepted");
		alert.accept();
	}
	
	public String getAlertText(Alert alert) {
		System.out.println("extracting text in the alert");
		return alert.getText();
	}
	public static void dismisAlert() {
		waitUntilAlertIsPresent();
		Alert alert = switchToAlert();
		alert.dismiss();
		logger.info("Alert dismissed");
		extentReport.logTestInfo("Alert dismissed");
	}
	
	public void switchToWindowOpened(String mainWindowHandle) {
		Set<String> allWindowHandles = driver.getWindowHandles();
		for(String handle : allWindowHandles) {
			if(!mainWindowHandle.equalsIgnoreCase(handle))
				driver.switchTo().window(handle);
		}
		System.out.println("switched to new window");
	}
	
	public static void selectMethod(WebElement element, String text, String objName) {
		Select ob = new Select(element);
		ob.selectByVisibleText(text);
		logger.info(objName + " selected "+text);
		extentReport.logTestInfo(objName + " selected "+text);
	}
	public static void windowPopupHandle() throws InterruptedException {
		for(String winhandle: driver.getWindowHandles()) {
			driver.switchTo().window(winhandle);
			logger.info("Window Switch");
			extentReport.logTestInfo("Window Switch");
			Thread.sleep(2000);
			
			driver.findElement(By.id("tryLexDialogX")).click();
		}
	}
	public static void fillformMethod(WebElement element, String text, String name) {
		if(element.isDisplayed()) { 
			clearElement(element, name);
			element.sendKeys(text);
			logger.info("text entered in " + name + " field");
			extentReport.logTestPassed("text entered in " + name + " field");
		} else {
			logger.error("fail: "+ name + " element is not displayed");
			extentReport.logTestFailed("fail: "+ name + " element is not displayed");
		}
	}
	public static void getScreenshotOfThePage() { 
	String date = new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss").format(new Date());
	String curDir = System.getProperty("user.dir");
	TakesScreenshot screenShot = (TakesScreenshot)driver;
	File imgFile = screenShot.getScreenshotAs(OutputType.FILE);
	File destFile = new File(Constants.SCREENSHOTS_DIRECTORY_PATH+date+" .png");
	try {
		org.openqa.selenium.io.FileHandler.copy(imgFile, destFile);
	} catch (IOException e1) {
		e1.printStackTrace();
	}
}
}
