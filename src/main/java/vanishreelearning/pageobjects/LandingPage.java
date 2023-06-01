package vanishreelearning.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import vanishreelearning.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent
{
	WebDriver driver;
	public LandingPage( WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}	
		
		@FindBy(id="userEmail")
		WebElement userEmail;
		
		@FindBy(id="userPassword")
		WebElement userPassword;
		
		@FindBy(id="login")
		WebElement submit;
		
		@FindBy(css=".ng-star-inserted")
		WebElement errorMessage;
		
		public void goTo()
		{
			driver.get("https://rahulshettyacademy.com/client");
		}
		
		public ProductCatalog loginApplication(String email, String password)
		{
			userEmail.sendKeys(email);
			userPassword.sendKeys(password);
			submit.click();
			ProductCatalog productCatalog=new ProductCatalog(driver);
			return productCatalog;
		}
		
		public String getErrorMessage()
		{
			waitForWebElementToAppear(errorMessage);
			return errorMessage.getText();
		}
		
		
	}


