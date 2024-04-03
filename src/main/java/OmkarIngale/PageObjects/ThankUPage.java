package OmkarIngale.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import OmkarIngale.AbstractComponent.AbstractComponent;

public class ThankUPage extends AbstractComponent {

	WebDriver driver;
	public ThankUPage(WebDriver driver)
	{
		super (driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	// order id
	@FindBy (css = ("tr[class='ng-star-inserted'] label"))
	WebElement  orderId;
	//confirmation message
	@FindBy (css = "[class=\"hero-primary\"]")
	WebElement confirmationMessage;
	
	public String orderID()
	{
		return	orderId.getText();
	}
	public void orderidmessage()
	{
		System.out.println(orderID());
	}
	
	public String getConfirmationMessage()
	{
		return confirmationMessage.getText();
	}
}
