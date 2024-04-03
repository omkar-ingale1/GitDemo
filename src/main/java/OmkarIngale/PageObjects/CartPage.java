package OmkarIngale.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import OmkarIngale.AbstractComponent.AbstractComponent;

public class CartPage extends AbstractComponent {
	WebDriver driver;
	
	public CartPage (WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	// grabbing all cart products
	@FindBy (xpath ="//*[@class=\"cartSection\"]/h3" )
	List<WebElement> cartProducts;
	
	// checkout button
	@FindBy(css = "button[type=\"button\"]:nth-child(1)")
	WebElement checkoutButton;
	
	public List<WebElement> cartProductList()
	{
		return cartProducts;
	}
	
	public Boolean verifyCartItems(String productName)
	{
	Boolean match =  cartProductList().stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
	return match; 
	}
	
	public void checkoutButtonClick ()
	{
		checkoutButton.click();
		
	}
}
