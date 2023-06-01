package vanishreelearning.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import vanishreelearning.pageobjects.CartPage;
import vanishreelearning.pageobjects.OrderPage;

public class AbstractComponent
{

	WebDriver driver;
	public AbstractComponent(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="button[routerlink*='cart']")
	WebElement cart;
	
	@FindBy(css="button[routerlink*='myorders']")
	WebElement orders;
	
	
	public void waitForElementToAppear(By findBy)
	{
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(findBy));
	}
	
	public void waitForWebElementToAppear(WebElement findBy)
	{
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(findBy));
	}
	
	public void waitForElementToDisaapear(By findBy)
	{
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(findBy));
	}
	
	public CartPage gotoCartPage()
	{
		cart.click();
		CartPage cartPage=new CartPage(driver);
		return cartPage;
	}
	
	public OrderPage gotoOrderPage()
	{
		orders.click();
		OrderPage orderPage=new OrderPage(driver);
		return orderPage;
	}
}
