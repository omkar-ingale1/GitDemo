package OmkarIngale.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import OmkarIngale.AbstractComponent.AbstractComponent;

public class OrdersPage extends AbstractComponent {
	WebDriver driver;
	
	public OrdersPage (WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	// grabbing all  products names
	@FindBy (css ="tr td:nth-child(3)" )
	List<WebElement> productsNamesList;
	
	public List<WebElement> productList()
	{
		return productsNamesList;
	}
	
	public Boolean verifyProductNames(String productName)
	{
	Boolean match =  productList().stream().anyMatch(Product->Product.getText().equalsIgnoreCase(productName));
	return match; 
	}
	
	
}
