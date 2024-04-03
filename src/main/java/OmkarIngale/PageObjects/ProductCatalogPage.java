package OmkarIngale.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import OmkarIngale.AbstractComponent.AbstractComponent;
import net.bytebuddy.implementation.bind.annotation.Super;

public class ProductCatalogPage extends AbstractComponent {

	WebDriver driver;
	
	// Constructor
	public ProductCatalogPage (WebDriver driver)
	{
		// initialization
		super (driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	//List <WebElement> products = driver.findElements(By.cssSelector(".mb-3")); // normal way to declare List of web ELEMENTS (plural)
	 
	 // writing locators by using Page factory. writing list keyword becse its plural. 
	 @FindBy(css =".mb-3")
	 List<WebElement>  products;
	 @FindBy(css = ".ng-animating")
	 WebElement buffer;
	 
	 By productsBy = By.cssSelector(".mb-3");
	 By addToCart = By.cssSelector(".card-body button:last-child");
	 By toastMessage = By.cssSelector("#toast-container");
	 
	 
	 public List<WebElement> getProductList()
	 {
		 waitForElementAppear(productsBy);
		 return products;
	 }

	 public WebElement getProductByName(String productName)
	 {
		 WebElement prod= 	getProductList().stream().filter(product -> 
		  product.findElement(By.tagName("b")).getText().equals(productName)).findFirst().orElse(null);
		  return prod;
	 }
	 
	 public void addProductToCart(String productName) throws InterruptedException
	 {
		 	WebElement prod = getProductByName( productName);
			prod.findElement(addToCart).click();
			waitForElementAppear(toastMessage);
			Thread.sleep(1000);
			//waitForElementToDissapear(buffer);

	 }
	 
	 
	 
}
