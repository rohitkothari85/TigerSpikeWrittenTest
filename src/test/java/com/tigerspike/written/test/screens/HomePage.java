package com.tigerspike.written.test.screens;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

	public WebDriver driver;

	ItemDetailPage itemDetailPage;
	AddToCartConfirmationPage addToCartConfirmationPage;

	public HomePage(WebDriver driver) throws Exception {
		this.driver = driver;
		PageFactory.initElements(driver, this);

		if (!searchTextBox.isDisplayed()) {
			throw new Exception("Home poge not found");
		}
	}

	@FindBy(className = "nav-action-button")
	WebElement signInButton;

	@FindBy(className = "nav-input")
	WebElement searchButton;

	@FindBy(id = "twotabsearchtextbox")
	WebElement searchTextBox;

	@FindBy(id = "nav-link-yourAccount")
	WebElement yourOrdersLabel;

	@FindBy(xpath = "//*[@id='result_0']//img")
	WebElement firstSearchResult;

	@FindBy(className = "nav-cart-icon")
	WebElement cartLink;

	@FindBy(id = "issDiv0")
	WebElement firstAutoSuggest;

	public LoginPage clickSignInButton() throws Exception {
		Thread.sleep(1000);
		Actions action = new Actions(driver);
		action.moveToElement(yourOrdersLabel).perform();
		action.moveToElement(signInButton);
		action.click();
		action.build().perform();
		return new LoginPage(driver);
	}

	public void search(String searchItem) throws InterruptedException {
		searchTextBox.sendKeys(searchItem);
		firstAutoSuggest.click();
		searchButton.click();
	}

	public void clearSearchTextBox() {
		searchTextBox.clear();
	}

	public void addFirstSearchResultToCart(String item) throws Exception {
		String mainWindow = getWindowHandle(driver);
		firstSearchResult.click();

		Set<String> allWindowHandles = driver.getWindowHandles();

		if (allWindowHandles.size() == 1) {
			itemDetailPage = new ItemDetailPage(driver);
			addToCartConfirmationPage = itemDetailPage.clickAddToCartButton();

		} else {
			Iterator<String> i1 = allWindowHandles.iterator();

			while (i1.hasNext()) {

				String childWindow = i1.next();

				if (!mainWindow.equalsIgnoreCase(childWindow)) {
					driver.switchTo().window(childWindow);
					itemDetailPage = new ItemDetailPage(driver);
					addToCartConfirmationPage = itemDetailPage.clickAddToCartButton();
					driver.close();
				}
			}
		}

		driver.switchTo().window(mainWindow);

	}

	public String getWindowHandle(WebDriver driver) {
		return driver.getWindowHandle();
	}

	public ShoppingCartPage clickCartLink() throws Exception {
		cartLink.click();
		return new ShoppingCartPage(driver);
	}

}
