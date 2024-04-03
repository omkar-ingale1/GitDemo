package OmkarIngale.SeleniumFrameworkDesign;

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

import OmkarIngale.PageObjects.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneProject {

	public static void main(String[] args) {
		// TODO Auto-generated method stub1
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		driver.get("https://rahulshettyacademy.com/client");
		
		// creating class to use driver scope in loginPage locators class
				LoginPage LoginPage = new LoginPage(driver);
		driver.findElement(By.id("userEmail")).sendKeys("omkar98@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Omkar@123");
		driver.findElement(By.id("login")).click();
		
		// grabbing all products element into one variable
		String productName = "ADIDAS ORIGINAL";
		List <WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
//		for (int i =0; i<=products.size(); i++)
//		{
//			String productText =products.get(i).getText();
//			System.out.println(productText);
//			if (productText.contains("ADIDAS ORIGINAL"))
//			{
//				products.get(i).findElement(By.xpath("//button[@class = \"btn w-10 rounded\"]")).click();
//				//.findElement(By.xpath("//button[@class = \"btn w-10 rounded\"] [1]")).click();
//			}
//		}
		
		// by using java streams
	WebElement prod= 	products.stream().filter(product -> 
	product.findElement(By.tagName("b")).getText().equals(productName)).findFirst().orElse(null);
	prod.findElement(By.cssSelector(".card-body button:last-child")).click();
	
	//toast message
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
	
	//waiting for buffer
	wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
	
	//clicking on cart button
	driver.findElement(By.cssSelector("button[routerlink=\"/dashboard/cart\"]")).click();
	
	// grabbing all products from cart
	List <WebElement> cartProducts = driver.findElements(By.xpath("//*[@class=\"cartSection\"]/h3"));
	
	// checking the required product is present in the cart on not by using java stream
	Boolean match = cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
	System.out.println(match);
	//Asserting 
	Assert.assertTrue(match);
	
	// clicking on checkout button
	driver.findElement(By.cssSelector("button[type=\"button\"]:nth-child(1)")).click();	
	
	// entering text into country text box
	driver.findElement(By.cssSelector("*[placeholder=\"Select Country\"]")).sendKeys("ind");
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
	
	// grabbing all drop down values
	List <WebElement> dropDownValue = driver.findElements(By.cssSelector(".ta-results span"));
	WebElement value = dropDownValue.stream().filter(ddValue->ddValue.getText().equals("India")).findFirst().orElse(null);
	value.click();
	 // another way to select value from dropdown
	Actions a = new Actions (driver);
//	a.sendKeys(driver.findElement(By.cssSelector("*[placeholder=\"Select Country\"]")),"india").build().perform();
//	wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".ta-results")));
//	driver.findElement(By.xpath("(//button[contains(@class, 'ta-item')])[2]")).click();
	
	
	//clicking on place order button
	JavascriptExecutor js = (JavascriptExecutor) driver; // scrolling page because facing element is not interceted error.
	js.executeScript("window.scrollBy(0,500)");// scrolling webPage
	wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".action__submit")));
	driver.findElement(By.cssSelector(".action__submit")).click();
	
	// Grabbing order id.
	String productId= driver.findElement(By.cssSelector("tr[class='ng-star-inserted'] label")).getText();
	System.out.println(productId);
	
	// asserting final thank you message
	Assert.assertEquals("THANKYOU FOR THE ORDER.", driver.findElement(By.cssSelector("[class=\"hero-primary\"]")).getText());
	
	}
}
