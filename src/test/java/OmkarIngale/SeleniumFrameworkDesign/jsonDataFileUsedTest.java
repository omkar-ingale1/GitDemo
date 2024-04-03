package OmkarIngale.SeleniumFrameworkDesign;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
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

public class jsonDataFileUsedTest extends BaseTest {

	//String productName = "ADIDAS ORIGINAL";
	
	@Test (dataProvider = "getData",groups ="jsonFileUsed")
	public void eCommerceProject(HashMap <String, String> input)  throws IOException, InterruptedException
	{

        LoginPage.login(input.get("email"), input.get("password"));

		// catalog page class
		ProductCatalogPage productCatalogPage = new ProductCatalogPage(driver);
		productCatalogPage.getProductList();
		productCatalogPage.addProductToCart(input.get("productName"));
		// clicking on cart option
		productCatalogPage.goToCartPage();
		
		// checking the required product is present in the cart on not by using java
		CartPage cartPage = new CartPage(driver);
		boolean match = cartPage.verifyCartItems(input.get("productName"));

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
		
// HashMap
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
		List <HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\OmkarIngale\\data\\PurchaseOrderdata.json");
		
		return new Object [][] { {data.get(0)}, {data.get(1) } };
	}
	
	
	
	
	
	
	
	
	
	
	
}
