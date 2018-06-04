package com.tigerspike.written.test.screens;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ItemDetailPage {

	public WebDriver driver;

	public ItemDetailPage(WebDriver driver) throws Exception {
		this.driver = driver;
		PageFactory.initElements(driver, this);

		if (!addToCartButton.isDisplayed()) {
			throw new Exception("Item detail poge not found");
		}
	}

	@FindBy(id = "add-to-cart-button")
	WebElement addToCartButton;

	public AddToCartConfirmationPage clickAddToCartButton() throws Exception {
		addToCartButton.click();
		return new AddToCartConfirmationPage(driver);
	}

}
