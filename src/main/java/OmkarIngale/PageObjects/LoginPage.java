package OmkarIngale.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import OmkarIngale.AbstractComponent.AbstractComponent;
import net.bytebuddy.implementation.bind.annotation.Super;

public class LoginPage extends AbstractComponent{

	WebDriver driver;
	
	// Constructor 
	public LoginPage (WebDriver driver)
	{
		super (driver);
		// initialization
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	// Normal way to write web element locators
	// WebElement userEmail = driver.findElement(By.id("userEmail"));
	 //WebElement password = driver.findElement(By.id("userPassword"));
	 
	 // writing locators by using Page factory
	 @FindBy(id ="userEmail")
	 WebElement  userEmail;
	 
	 @FindBy(id ="userPassword")
	 WebElement  passwordEle;
	  
	 @FindBy(id ="login")
	 WebElement  loginButton;
	 
	 @FindBy(css = "[class*= 'flyInOut ']") WebElement errorMessage;
	 
	 public void login(String Email, String  password )
	 {
		 userEmail.sendKeys(Email);
		 passwordEle.sendKeys(password);
		 loginButton.click();
	 }
	 
	 public void goTo()
	 {
			driver.get("https://rahulshettyacademy.com/client");
	 }
	 
	 public String getErrorMessage()
	 {
		 waitForWebElementAppear(errorMessage);
		 return errorMessage.getText();
	 }
	 
	 
	 
	 
}
