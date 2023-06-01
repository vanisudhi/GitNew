package vanishreelearning.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import vanishreelearning.AbstractComponents.AbstractComponent;

public class OrderPage extends  AbstractComponent
{
	WebDriver driver;
	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="tr td:nth-child(3)")
	List<WebElement> ordername;
	
	public boolean verifyOrder(String prodName)
	{
		 boolean match=ordername.stream().anyMatch(order->order.getText().equalsIgnoreCase(prodName));
		 return match;
	}
	

}
