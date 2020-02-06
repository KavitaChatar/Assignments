package assignments.zestMoney.webPages;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

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
	
	public Double getProductPrice() {
		String price = priceTag.getText();
		price = price.substring(1,price.length()); 
		Double actualPrice = Double.parseDouble(price.replace("," , ""));
		return actualPrice;
	}
}
