package vanishreelearning.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import vanishreelearning.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent
{
	public WebDriver driver;
	public CartPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(css=".totalRow button")
	WebElement checkOut;
	
	@FindBy(css=".cartSection h3")
	List<WebElement> cartProd;
	
	public boolean matchCart(String prodname)
	{
		boolean match=cartProd.stream().anyMatch(item->item.getText().equalsIgnoreCase(prodname));
		return match;
	}
	
	public PaymentPage proceedCheckOut()
	{
		checkOut.click();
		PaymentPage paymentPage=new PaymentPage(driver);
		return paymentPage;
		
	}
	
	
}
