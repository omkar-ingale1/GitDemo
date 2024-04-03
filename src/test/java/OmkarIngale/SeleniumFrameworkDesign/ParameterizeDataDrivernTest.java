package OmkarIngale.SeleniumFrameworkDesign;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import OmkarIngale.PageObjects.CartPage;
import OmkarIngale.PageObjects.CheckOutPage;
import OmkarIngale.PageObjects.LoginPage;
import OmkarIngale.PageObjects.OrdersPage;
import OmkarIngale.PageObjects.ProductCatalogPage;
import OmkarIngale.PageObjects.ThankUPage;
import OmkarIngale.TestComponents.BaseTest;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ParameterizeDataDrivernTest extends BaseTest {

	//String productName = "ADIDAS ORIGINAL";
	
	@Test (dataProvider = "getData",groups ="purchase")
	public void eCommerceProject(String email, String password, String productName) throws IOException, InterruptedException
	{

        LoginPage.login(email, password);

		// catalog page class
		ProductCatalogPage productCatalogPage = new ProductCatalogPage(driver);
		productCatalogPage.getProductList();
		productCatalogPage.addProductToCart(productName);
		// clicking on cart option
		productCatalogPage.goToCartPage();
		
		// checking the required product is present in the cart on not by using java
		CartPage cartPage = new CartPage(driver);
		boolean match = cartPage.verifyCartItems(productName);

		System.out.println(match);
		// Asserting
		Assert.assertTrue(match);

		// clicking on checkout button
		cartPage.checkoutButtonClick();

		CheckOutPage checkOutPage = new CheckOutPage(driver);
		// entering text into country text box
		checkOutPage.enterCountryValue();
		// getting all drop down values and selecting required value
		checkOutPage.selectCountry();

		// clicking on place order button
		checkOutPage.pageScrollingDown();
		checkOutPage.waitForElementClickable(By.cssSelector(".action__submit"));
		checkOutPage.clickOnPlaceOrder();

		// Grabbing order id.
		ThankUPage thankUPage = new ThankUPage(driver);
		thankUPage.orderidmessage();
		
		// asserting final thank you message
		Assert.assertEquals("THANKYOU FOR THE ORDER.",thankUPage.getConfirmationMessage());

	}
		
//	@Test (dependsOnMethods= {"eCommerceProject"})
//	public void verifyProductName()
//	{
//        LoginPage.login("omkar98@gmail.com", "Omkar@123");
//		OrdersPage orderPage = new OrdersPage(driver);
//		orderPage.goTOProductPage();
//		orderPage.verifyProductNames(productName);
//		boolean match = orderPage.verifyProductNames(productName);
//		Assert.assertTrue(match);
//	 }
	@DataProvider
	public Object[][] getData()
	{
		return new Object [][] { {"omkar98@gmail.com","Omkar@123","ADIDAS ORIGINAL"},{"omkar98@gmail.com","Omkar@123","ZARA COAT 3"} };
	}
	
	
	
	
	
	
	
	
	
	
}
