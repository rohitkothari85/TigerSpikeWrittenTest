package com.tigerspike.written.test.screens;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ShoppingCartPage {

	public WebDriver driver;

	public ShoppingCartPage(WebDriver driver) throws Exception {
		this.driver = driver;
		PageFactory.initElements(driver, this);

//		if (!proceedToCheckOutButton.isDisplayed()) {
//			throw new Exception("Shopping cart poge not found");
//		}
	}

	@FindBy(name = "proceedToCheckout")
	WebElement proceedToCheckOutButton;

	public ConfirmDeliveryAddressPage clickCartLink() throws Exception {
		proceedToCheckOutButton.click();
		return new ConfirmDeliveryAddressPage(driver);
	}
}
