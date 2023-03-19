package com.jan23.tests;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import java.io.IOException;


import com.jan23.base.BaseTestSalesforce;
import com.jan23.home.HomePageSalesforce;
import com.jan23.pages.login.LoginPageSalesforce;
import com.jan23.utility.PropertiesUtility;

@Listeners(com.jan23.utility.TestEventListenersUtility.class)
public class SalesforceAutomation extends BaseTestSalesforce{

	@Test
	public static void TC01_errorLoginSalesforceOne() throws InterruptedException, IOException {
		PropertiesUtility propertiesUtility =new PropertiesUtility();
		propertiesUtility.loadFile("applicationDataProperties");
		
		String username = propertiesUtility.getPropertyValue("login.valid.username");
		String password = propertiesUtility.getPropertyValue("login.blank.password");
		
		String expected = "Please enter your password.";
		
		Thread.sleep(4000);
		LoginPageSalesforce loginpage = new LoginPageSalesforce(driver);
		loginpage.enterUsername(username);
		loginpage.enterPassword(password);
		driver = loginpage.clickLogin();
		String actual = loginpage.errorMessaging();
		Assert.assertEquals(actual, expected);
	}
	
	@Test
	public static void TC02_successLoginSalesforce() throws InterruptedException, IOException {
		PropertiesUtility propertiesUtility =new PropertiesUtility();
		propertiesUtility.loadFile("applicationDataProperties");
		String username = propertiesUtility.getPropertyValue("login.valid.username");
		String password = propertiesUtility.getPropertyValue("login.valid.password");
		
		String expected = "Home";
		
		Thread.sleep(4000);
		LoginPageSalesforce loginpage = new LoginPageSalesforce(driver);
		loginpage.enterUsername(username);
		loginpage.enterPassword(password);
		driver = loginpage.clickLogin();
		Thread.sleep(4000);
		HomePageSalesforce homepage = new HomePageSalesforce(driver);
		String actual = homepage.getTextFromHomeScreenFormElement();
		Assert.assertEquals(actual, expected);
		extentReport.logTestInfo("method end");
		driver.close();
	}
	
	@Test
	public static void TC03_rememberMeLoginSalesforce() throws InterruptedException, IOException {
		PropertiesUtility propertiesUtility =new PropertiesUtility();
		propertiesUtility.loadFile("applicationDataProperties");
		String username = propertiesUtility.getPropertyValue("login.valid.username");
		String password = propertiesUtility.getPropertyValue("login.valid.password");
		
		String expected = "Home";
		
		Thread.sleep(4000);
		LoginPageSalesforce loginpage = new LoginPageSalesforce(driver);
		loginpage.enterUsername(username);
		loginpage.enterPassword(password);
		loginpage.rememberMeOption();
		driver = loginpage.clickLogin();
		Thread.sleep(4000);
		HomePageSalesforce homepage = new HomePageSalesforce(driver);
		String actual = homepage.getTextFromHomeScreenFormElement();
		Assert.assertEquals(actual, expected);
		extentReport.logTestInfo("method end");
		driver.close();
	}
	
	@Test
	public static void TC04_A_forgotYourPasswordLoginSalesforce() throws InterruptedException, IOException {    
		PropertiesUtility propertiesUtility =new PropertiesUtility();
		propertiesUtility.loadFile("applicationDataProperties");
		String username = propertiesUtility.getPropertyValue("login.valid.username");
		
		propertiesUtility.loadFile("FormFillDataProperties");
		String forgotUsernameFill = propertiesUtility.getPropertyValue("forgotUsernameFillUp");
		
		String expected = "Check Your Email";
		
		Thread.sleep(4000);
		LoginPageSalesforce loginpage = new LoginPageSalesforce(driver);
		loginpage.enterUsername(username);
		loginpage.forgotPasswordOption();
		loginpage.forgotUsernameFill(forgotUsernameFill);
		loginpage.forgotContinueOption();
		Thread.sleep(4000);
		HomePageSalesforce homepage = new HomePageSalesforce(driver);
		String actual = homepage.getTextFromEmailScreenFormElement();
		Assert.assertEquals(actual, expected);
		extentReport.logTestInfo("method end");
		driver.close();
	}
	
	@Test
	public static void TC04_B_errorLoginSalesforceTwo() throws IOException, InterruptedException {
		PropertiesUtility propertiesUtility =new PropertiesUtility();
		propertiesUtility.loadFile("applicationDataProperties");
		String username = propertiesUtility.getPropertyValue("login.valid.username");
		String password = propertiesUtility.getPropertyValue("login.invalid.pssword");
		
		String expected = "Please check your username and password. If you still can't log in, contact your Salesforce administrator.";
		
		Thread.sleep(4000);
		LoginPageSalesforce loginpage = new LoginPageSalesforce(driver);
		loginpage.enterUsername(username);
		loginpage.enterPassword(password);
		driver = loginpage.clickLogin();
		String actual = loginpage.errorMessaging();
		Assert.assertEquals(actual, expected);
		extentReport.logTestInfo("method end");
		driver.close();
	}
	
	@Test
	public static void TC05_User_Menu_Dropdown() throws InterruptedException, IOException {
		PropertiesUtility propertiesUtility =new PropertiesUtility();
		propertiesUtility.loadFile("applicationDataProperties");
		String username = propertiesUtility.getPropertyValue("login.valid.username");
		String password = propertiesUtility.getPropertyValue("login.valid.password");
		
		LoginPageSalesforce loginpage = new LoginPageSalesforce(driver);
		loginpage.enterUsername(username);
		loginpage.enterPassword(password);
		driver = loginpage.clickLogin();
		Thread.sleep(4000);
		HomePageSalesforce homepage = new HomePageSalesforce(driver);
		homepage.usernameButOption();
		System.out.println("method end");
		driver.close();
	}
	
}