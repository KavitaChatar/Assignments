package assignments.zestMoney.webPages;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class flipkartSearchPage extends flipkartHomePage{
WebDriver driver;
	
	@FindAll(@FindBy(className="DUFPUZ")) List<WebElement> noResultsFound;
	@FindBy(css="div.col.col-5-12._2o7WAb > div > div > div") WebElement priceTag;
	
	public flipkartSearchPage(WebDriver driver) throws Exception {
		super(driver); 
	}
	
	public boolean checkProductValidity() {
		if(noResultsFound.size()>0) {return false;}
		else return true;
	}
	
	/* Get the product's price from flipkart, since we are switching tabs in chrome browser
	 * it takes some time to load the data in second tab, hence will use WebDriverWait to wait
	 * for the element to load on the screen */
	
	public Double getProductPrice(WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver,60);
		wait.until(ExpectedConditions.elementToBeClickable(priceTag));
		
		String price = priceTag.getText();
		price = price.substring(1,price.length()); 
		Double actualPrice = Double.parseDouble(price.replace("," , ""));
		return actualPrice;
	}
}
