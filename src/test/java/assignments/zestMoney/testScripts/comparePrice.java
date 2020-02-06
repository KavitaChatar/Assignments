package assignments.zestMoney.testScripts;

import static io.github.bonigarcia.wdm.DriverManagerType.CHROME;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import assignments.zestMoney.webPages.*;
import io.github.bonigarcia.wdm.WebDriverManager;

public class comparePrice {
	
	WebDriver driver;
	public static final String searchProduct = "iPhone XR (64GB) - Yellow";
	public amazonSearchPage amazon;
	public flipkartSearchPage flipkart;
	public List<String> chromeTabs;
	
	public comparePrice() {
		WebDriverManager.getInstance(CHROME).setup();
		driver = new ChromeDriver(); 
		amazon = PageFactory.initElements(driver, amazonSearchPage.class);
		flipkart = PageFactory.initElements(driver, flipkartSearchPage.class);
		chromeTabs = new ArrayList<String> (driver.getWindowHandles());
	}
	
	@Test(priority=1)
	public void searchProductinAmazon() {	
		driver.switchTo().window(chromeTabs.get(0));
		amazon.searchProduct(searchProduct);
	}
	
	@Test(priority=2)
	public void checkProductisAvailableAmazon() {
		Assert.assertTrue(amazon.checkProductValidity(), "No results found for : "+searchProduct);
	}
	
	@Test(priority=3)
	public void searchProductinFlipkart() {	
		driver.switchTo().window(chromeTabs.get(1));
		flipkart.searchProduct(searchProduct);
	}
	
	@Test(priority=4)
	public void checkProductisAvailableFlipkart() {	
		flipkart.searchProduct(searchProduct);
	}
	
	@Test(priority=5)
	public void getPrice() throws Exception {
		driver.switchTo().window(chromeTabs.get(0));
		Double amazonPrice = amazon.getProductPrice();
		
		driver.switchTo().window(chromeTabs.get(1));Thread.sleep(100);
		Double flipkartPrice = flipkart.getProductPrice();
		
		String minimumPriceResult = (amazonPrice < flipkartPrice ? "\n\n Amazon has lesser value for the iPhone with --> "+amazonPrice : "Flipkart has lesser value for the iPhone with --> "+flipkartPrice);
		System.out.print(minimumPriceResult+ "/- Rs. \n\n");
	} 
}
