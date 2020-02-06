package assignments.zestMoney.testScripts;

import static io.github.bonigarcia.wdm.DriverManagerType.CHROME;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Test;
import assignments.zestMoney.webPages.*;
import io.github.bonigarcia.wdm.WebDriverManager;

public class comparePrice {
	
	WebDriver driver;
	public static final String searchProduct = "iPhone XR (64GB) - Yellow";
	public amazonSearchPage amazon;
	public flipkartSearchPage flipkart;
	public List<String> chromeTabs;
	
	/*  Initialize the driver with chrome browser and create objects of Amazon and Flipkart WebPages, 
	 *  open two separate tabs for each website and perform operations on them */
	public comparePrice() {
		WebDriverManager.getInstance(CHROME).setup();
		driver = new ChromeDriver(); 
		amazon = PageFactory.initElements(driver, amazonSearchPage.class);
		flipkart = PageFactory.initElements(driver, flipkartSearchPage.class);
		chromeTabs = new ArrayList<String> (driver.getWindowHandles());
	}
	
	/* Search the product in Amazon Website */
	@Test(priority=1)
	public void searchProductinAmazon() {	
		driver.switchTo().window(chromeTabs.get(0));
		amazon.searchProduct(searchProduct);
	}
	
	/* Check if there are products available in Amazon, if no products are available exit the test case */
	@Test(priority=2)
	public void checkProductisAvailableAmazon() {
		Assert.assertTrue(amazon.checkProductValidity(), "No results found for : "+searchProduct);
	}
	
	/* Search the product in Flipkart Website */
	@Test(priority=3)
	public void searchProductinFlipkart() {	
		driver.switchTo().window(chromeTabs.get(1));
		flipkart.searchProduct(searchProduct);
	}
	
	/*  Check if there are products available in Flipkart, if no products are available exit the test case  */
	@Test(priority=4)
	public void checkProductisAvailableFlipkart() {	
		Assert.assertTrue(flipkart.checkProductValidity(), "No results found for : "+searchProduct);
	}
	
	/* Get the pricing details from both the website and comapare */
	@Test(priority=5)
	public void getPrice() {
		
		driver.switchTo().window(chromeTabs.get(0));
		Double amazonPrice = amazon.getProductPrice();
		
		driver.switchTo().window(chromeTabs.get(1));
		Double flipkartPrice = flipkart.getProductPrice(driver);
		
		String minimumPriceResult = (amazonPrice < flipkartPrice ? "\n\n Amazon has lesser value for the iPhone with --> "+amazonPrice : "Flipkart has lesser value for the iPhone with --> "+flipkartPrice);
		System.out.print(minimumPriceResult+ "/- Rs. \n\n");
	} 
	
	/*  Close the browser after running the tests */
	@AfterSuite
	public void closeBrowser() {
		driver.quit();
	}
}
