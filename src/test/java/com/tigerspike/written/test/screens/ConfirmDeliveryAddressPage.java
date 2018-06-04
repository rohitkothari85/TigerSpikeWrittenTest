package com.tigerspike.written.test.screens;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ConfirmDeliveryAddressPage {

	public WebDriver driver;

	public ConfirmDeliveryAddressPage(WebDriver driver) throws Exception {
		this.driver = driver;
		PageFactory.initElements(driver, this);

		if (!selectADeliveryAddressLabel.isDisplayed()) {
			throw new Exception("Confirm delivery address poge not found");
		}
	}

	@FindBy(className = "a-spacing-base")
	WebElement selectADeliveryAddressLabel;

	public String getSelectADeliveryAddressLabel() {
		return selectADeliveryAddressLabel.getText();
	}
}
