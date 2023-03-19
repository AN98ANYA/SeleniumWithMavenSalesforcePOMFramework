package com.jan23.home;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.jan23.pages.base.BasePageSalesforce;

public class HomePageSalesforce extends BasePageSalesforce{

	@FindBy(xpath= "//*[@id=\"home_Tab\"]/a") WebElement homeScreen;
	@FindBy(id= "header") WebElement checkEmail;
	@FindBy(xpath= "//*[@id=\"userNavButton\"]") WebElement usernameBut;
	
	
	public HomePageSalesforce(WebDriver driver) {            
		super(driver);
	}
	
	public String getTextFromHomeScreenFormElement() {
		WaitUntilElementIsVisible(homeScreen, "form test element");
		return getTextFormWebElement(homeScreen, "form test element");
	}
	public String getTextFromEmailScreenFormElement() {
		WaitUntilElementIsVisible(checkEmail, "email form test element");
		return getTextFormWebElement(checkEmail, "email form test element");
	}
	public void usernameButOption() {
//		WaitUntilElementIsVisible(usernameBut, "usernameBut test element");
		clickElement(usernameBut, "usernameBut element");
	}
	
}
