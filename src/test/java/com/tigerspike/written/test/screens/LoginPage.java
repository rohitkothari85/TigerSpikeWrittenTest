package com.tigerspike.written.test.screens;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	public WebDriver driver;

	public LoginPage(WebDriver driver) throws Exception {
		this.driver = driver;
		PageFactory.initElements(driver, this);

		if (!userNameTB.isDisplayed()) {
			throw new Exception("Login poge not found");
		}

	}

	@FindBy(id = "ap_email")
	WebElement userNameTB;

	@FindBy(id = "continue")
	WebElement continueButton;

	@FindBy(id = "ap_password")
	WebElement passwordTB;

	@FindBy(id = "signInSubmit")
	WebElement submitButton;

	public HomePage login(String userName, String password) throws Exception {
		userNameTB.sendKeys(userName);
		continueButton.click();
		passwordTB.sendKeys(password);
		submitButton.click();

		return new HomePage(driver);
	}

}
