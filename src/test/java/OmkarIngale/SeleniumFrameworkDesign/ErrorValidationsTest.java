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
import org.testng.annotations.Test;

import OmkarIngale.PageObjects.CartPage;
import OmkarIngale.PageObjects.CheckOutPage;
import OmkarIngale.PageObjects.LoginPage;
import OmkarIngale.PageObjects.ProductCatalogPage;
import OmkarIngale.PageObjects.ThankUPage;
import OmkarIngale.TestComponents.BaseTest;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ErrorValidationsTest extends BaseTest {

	@Test
	public void loginErrorValidation() throws IOException, InterruptedException
	{
		LoginPage.login("omkar98@gmail.com", "Omkar123");
		LoginPage.getErrorMessage();
		System.out.println(LoginPage.getErrorMessage());
		Assert.assertEquals( "Incorrect  or password." , LoginPage.getErrorMessage());
		

	}
	
	@Test (groups = "ErrorHandling")
	public void productErrorValidation() throws IOException, InterruptedException
	{

        LoginPage.login("omkar98@gmail.com", "Omkar@123");

		String productName = "ADIDAS ORIGINAL";
		// catalog page class
		ProductCatalogPage productCatalogPage = new ProductCatalogPage(driver);
		productCatalogPage.getProductList();
		productCatalogPage.addProductToCart(productName);
		// clicking on cart option
		productCatalogPage.goToCartPage();

		// checking the required product is present in the cart on not by using java
		CartPage cartPage = new CartPage(driver);
		boolean match = cartPage.verifyCartItems("ADIDAS ORIGINAL 1");

		System.out.println(match);
		// Asserting
		Assert.assertFalse(match);
}
	
}
