package com.jan23.pages.login;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.jan23.pages.base.BasePageSalesforce;

public class LoginPageSalesforce extends BasePageSalesforce{

	@FindBy(id= "username") WebElement username;
	@FindBy(id= "password") WebElement password;
	@FindBy(id = "Login") WebElement loginButton;
	@FindBy(id= "error") WebElement errorMessage;
	@FindBy(id= "rememberUn") WebElement rememberMe;
	@FindBy(id= "forgot_password_link") WebElement forgotPassword;
	@FindBy(id= "un") WebElement forgotUsername;
	@FindBy(id= "continue") WebElement forgotContinue;
	
	
	public LoginPageSalesforce(WebDriver driver) {
		super(driver);
	}
	public void enterUsername(String data) {
		WaitUntilElementIsVisible(username, "username element");
		loginCredentials(username, data, "username element");
	}
	public void enterPassword(String data) {
		loginCredentials(password, data, "password element");
	}
	public WebDriver clickLogin() {
		clickElement(loginButton, "login button element");
		return driver;
	}
	public String errorMessaging() {
		return getTextFormWebElement(errorMessage, "error message element");
	}
	public void rememberMeOption() {
		clickElement(rememberMe, "remember me element");
	}
	public void forgotPasswordOption() {
		clickElement(forgotPassword, "forgotPassword element");
	}
	public void forgotUsernameFill(String data) {
		fillformMethod(forgotUsername, data, "forgotUsername element");
	}
	public void forgotContinueOption() {
		clickElement(forgotContinue, "forgotContinue element");
	}
	public String getTextFromAlert() {
		Alert alert = switchToAlert();
		return getAlertText(alert);
	}
}
