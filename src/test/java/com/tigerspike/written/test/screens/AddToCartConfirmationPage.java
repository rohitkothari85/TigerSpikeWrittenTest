package com.tigerspike.written.test.screens;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddToCartConfirmationPage {

	public WebDriver driver;

	public AddToCartConfirmationPage(WebDriver driver) throws Exception {
		this.driver = driver;
		PageFactory.initElements(driver, this);

		if (!proceedToCheckOutButton.isDisplayed()) {
			throw new Exception("Login poge not found");
		}
	}

	@FindBy(className = "a-button-inner")
	WebElement proceedToCheckOutButton;

	@FindBy(xpath = "//*[@id='huc-v2-order-row-confirm-text']/h1")
	WebElement addToCartLabel;

	public String getAddToCartLabel(){
		return addToCartLabel.getText();
	}
	
}
