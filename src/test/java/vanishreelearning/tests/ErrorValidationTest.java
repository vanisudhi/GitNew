package vanishreelearning.tests;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import vanishreelearning.pageobjects.CartPage;
import vanishreelearning.pageobjects.ConfirmationPage;
import vanishreelearning.pageobjects.LandingPage;
import vanishreelearning.pageobjects.PaymentPage;
import vanishreelearning.pageobjects.ProductCatalog;
import vanishreelearning.testComponents.BaseTest;
import vanishreelearning.testComponents.Retry;

public class ErrorValidationTest extends BaseTest {
	
	
	@Test(groups= {"Error"},retryAnalyzer=Retry.class)
	public void LoginErrorValidation()throws IOException {
		
		landingpage.loginApplication("vanitest@gmail.com", "Testame@1");
	
		Assert.assertEquals("Incorrect email or password.",landingpage.getErrorMessage());
		
	}
	
	@Test
	public void ProductErrorValidation()throws IOException {
		String prodname="ZARA COAT 3";
		ProductCatalog productCatalog=landingpage.loginApplication("aastika@gmail.com", "Demo@123");
		List<WebElement> products=productCatalog.getProductList();
		productCatalog.addProductToCart(prodname);
		CartPage cartPage=productCatalog.gotoCartPage();
		boolean match=cartPage.matchCart("ZARA COAT 33");
		Assert.assertFalse(match);
	
		
	}

}
