package assignments.zestMoney.webPages;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class flipkartHomePage {
	
	WebDriver driver;
	
	@FindBy(xpath="//input[@type=\"text\"]") WebElement searchInputBox;
	@FindBy(xpath="//button[@type=\"submit\"]") WebElement searchButton;
	@FindBy(xpath="//body") WebElement escapeLoginPopup; // Sending escape command key to avoid flipkart login pop ups	
	
	/* In same chrome driver create new tab and open flipkart website */
	public flipkartHomePage(WebDriver driver) throws InterruptedException {
		this.driver = driver; 
		((JavascriptExecutor)driver).executeScript("window.open()");
		List<String> tabs = new ArrayList<String> (driver.getWindowHandles());
	    driver.switchTo().window(tabs.get(1));
		driver.get("https://www.flipkart.com/");
	}
	
	public void searchProduct(String searchKey) {
		escapeLoginPopup.sendKeys(Keys.ESCAPE);
		searchInputBox.sendKeys(searchKey);
		searchButton.click();
	}	
}
