package assignments.zestMoney.webPages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

public class amazonHomePage {
	
	WebDriver driver;
	
	@FindBy(id="twotabsearchtextbox") WebElement searchInputBox;
	@FindBy(xpath="//input[@type=\"submit\"]") WebElement searchButton;
	
	public amazonHomePage(WebDriver driver) {
		this.driver = driver;
		driver.get("https://www.amazon.in");
		driver.manage().window().maximize(); 
	}
	
	public void searchProduct(String searchKey) {
		searchInputBox.sendKeys(searchKey);
		searchButton.click();
	}
}
