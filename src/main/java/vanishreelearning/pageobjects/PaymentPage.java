package vanishreelearning.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import vanishreelearning.AbstractComponents.AbstractComponent;

public class PaymentPage extends AbstractComponent
{
	public WebDriver driver;
	public PaymentPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(css="input[placeholder='Select Country']")
	WebElement selectCountry;
	
	@FindBy(xpath="(//button[contains(@class,'ta-item')])[2]")
	WebElement country;
	
	@FindBy(css=".action__submit")
	WebElement submit;
	
	By showDropdown = By.cssSelector(".ta-results");
	
	
	public ConfirmationPage actionSelectCountry(String countryName)
	{
		Actions a=new Actions(driver);
		a.sendKeys(selectCountry, countryName).build().perform();
		waitForElementToAppear(showDropdown);
		country.click();
		submit.click();
		ConfirmationPage confirmationPage=new ConfirmationPage(driver);
		return confirmationPage;
	}
	
	

}
