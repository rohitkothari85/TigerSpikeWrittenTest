package com.tigerspike.written.test;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.tigerspike.written.test.screens.AddToCartConfirmationPage;
import com.tigerspike.written.test.screens.ConfirmDeliveryAddressPage;
import com.tigerspike.written.test.screens.HomePage;
import com.tigerspike.written.test.screens.ItemDetailPage;
import com.tigerspike.written.test.screens.LoginPage;
import com.tigerspike.written.test.screens.ShoppingCartPage;
import com.tigerspike.written.utils.Constant;
import com.tigerspike.written.utils.XMLUtils;

public class TigerSpikeTest {

	public WebDriver driver;
	public LoginPage loginScreen;
	public HomePage homeScreen;
	public ItemDetailPage itemDetailPage;
	public ShoppingCartPage shoppingCartPage;
	public ConfirmDeliveryAddressPage confirmDeliveryAddressPage;

	@Parameters("browser")
	@BeforeTest
	public void beforeTest(String browser) throws Exception {
		if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", "driver/chromedriver");
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.firefox.marionette", "driver/geckodriver");
			driver = new FirefoxDriver();
		} else {
			throw new Exception("Browser is not correct");
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(Constant.URL);
		loginScreen = new HomePage(driver).clickSignInButton();
		loginScreen.login(Constant.Username, Constant.Password);
	}

	@Test(dataProvider = "searchItems")
	public void testAddItemToCart(String item, String quantity) throws Exception {
		System.out.println(item + " " + quantity);
		driver.get(Constant.URL);
		homeScreen = new HomePage(driver);
		homeScreen.search(item);
		homeScreen.addFirstSearchResultToCart(item);
//		assertEquals((new AddToCartConfirmationPage(driver)).getAddToCartLabel(), "Added to Cart");
	}

	@Test
	public void testPaymentFlow() throws Exception {

		homeScreen = new HomePage(driver);
		shoppingCartPage = new ShoppingCartPage(driver);
		shoppingCartPage = homeScreen.clickCartLink();
		confirmDeliveryAddressPage = new ConfirmDeliveryAddressPage(driver);
		confirmDeliveryAddressPage = shoppingCartPage.clickCartLink();

		assertEquals("Select a delivery address", confirmDeliveryAddressPage.getSelectADeliveryAddressLabel());
	}

	@AfterTest
	public void afterTest() {
		driver.quit();
	}

	@DataProvider
	public Object[][] searchItems() throws Exception {
		Object[][] testObjArray = XMLUtils.getDataArray(Constant.Path_TestData + Constant.File_TestData);
		return (testObjArray);
	}

}
