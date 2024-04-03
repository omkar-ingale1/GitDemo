package OmkarIngale.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import OmkarIngale.AbstractComponent.AbstractComponent;

public class CheckOutPage extends AbstractComponent {
	
	WebDriver driver;
	
	public CheckOutPage (WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	// entering text into country text box
	@FindBy (css ="*[placeholder='Select Country']")
	WebElement countryTextBox;
	
	// country DD values
	@FindBy (css = ".ta-results span")
	List<WebElement> dropDownValue;
	
	// place order button
	@FindBy (css =".action__submit")
	WebElement placeOrder;
	By waitForcountryValuesAppear = By.cssSelector(".ta-results");
		
	public void enterCountryValue()
	{
		countryTextBox.sendKeys("ind");
		waitForElementAppear(waitForcountryValuesAppear);
	}
	
	public List<WebElement> dropDownValue()
	{
		return dropDownValue;
	}
	public void selectCountry()
	{
		WebElement value = dropDownValue().stream().filter(ddValue->ddValue.getText().equals("India")).findFirst().orElse(null);
		value.click();
	}
	
	public void clickOnPlaceOrder()
	{
		placeOrder.click();
	}
	
}
