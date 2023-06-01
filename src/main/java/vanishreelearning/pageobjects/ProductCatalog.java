package vanishreelearning.pageobjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import vanishreelearning.AbstractComponents.AbstractComponent;

public class ProductCatalog extends AbstractComponent
{
	public WebDriver driver;
	 
	public ProductCatalog( WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".mb-3")
	List<WebElement> products;
	
	
	
	By productBy=By.cssSelector(".mb-3");
	
	By addToCart=By.cssSelector(".btn:last-of-type");
	
	By toastMessage=By.cssSelector("#toast-container");
	
	By spinner=By.cssSelector(".ng-animating");

	public List<WebElement> getProductList()
	{
		waitForElementToAppear(productBy);
		return products;
	}
	
	public WebElement getProductByName(String productName)
	{
		WebElement prod=getProductList().stream().filter(product->product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		return prod;
	}	
	
	public void addProductToCart(String productName)
	{
		WebElement prod=getProductByName(productName);
		prod.findElement(addToCart).click();
		waitForElementToAppear(toastMessage);
		waitForElementToDisaapear(spinner);
		
	}
	
	
	
	
	
		
	}


