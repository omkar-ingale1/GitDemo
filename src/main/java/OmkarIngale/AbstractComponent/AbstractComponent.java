package OmkarIngale.AbstractComponent;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



public class AbstractComponent {
	WebDriver driver;

	public AbstractComponent(WebDriver driver) {
		//super(driver);
		this.driver  = driver;
		PageFactory.initElements(driver, this);
		
	}
	// cart menu 
	@FindBy (css = "button[routerlink=\"/dashboard/cart\"]")
	WebElement cartPage;
	
	// product menu click
	@FindBy(css = "button[routerlink = '/dashboard/myorders']")
	WebElement productPage;
	
	public void waitForElementAppear(By findBy)
	{
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(findBy));
	}
	
	public void waitForWebElementAppear(WebElement findBy)
	{
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	wait.until(ExpectedConditions.visibilityOf(findBy));
	} 
	
	public void waitForElementToDissapear(WebElement element)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(element));
	}
	public void goToCartPage()
	{
		cartPage.click();
		
	}
	
	public void goTOProductPage()
	{
		productPage.click();
	}
	
	public void pageScrollingDown()
	{
		JavascriptExecutor js = (JavascriptExecutor) driver; // scrolling page because facing element is not interceted error.
		js.executeScript("window.scrollBy(0,800)");// scrolling webPage
	}
	
	public void waitForElementClickable (By FindBy)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.elementToBeClickable(FindBy));
	}
	
	
}
