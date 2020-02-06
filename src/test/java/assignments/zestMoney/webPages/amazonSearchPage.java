package assignments.zestMoney.webPages;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

public class amazonSearchPage extends amazonHomePage {

	WebDriver driver;
	
	@FindAll(@FindBy(className="a-size-medium a-color-base")) List<WebElement> noResultsFound;
	@FindBy(className="a-price-whole") WebElement priceTag;
	
	public amazonSearchPage(WebDriver driver) {
		super(driver); 
	}
	
	public boolean checkProductValidity() {
		if(noResultsFound.size()>0) {return false;}
		else return true;
	}
	
	public Double getProductPrice() {
		String price = priceTag.getText();
		Double actualPrice = Double.parseDouble(price.replace("," , ""));
		return actualPrice;
	}
}
