package vanishreelearning.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import vanishreelearning.AbstractComponents.AbstractComponent;

public class ConfirmationPage extends AbstractComponent
{
	public WebDriver driver;
	
	public ConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
		
	}
	@FindBy(css=".hero-primary")
	WebElement confirmation;
	
	
	
	public String getConfirmation()
	{
		waitForWebElementToAppear(confirmation);
		String confirmMessage=confirmation.getText();
		return confirmMessage;
	}
	 
	
	

}
