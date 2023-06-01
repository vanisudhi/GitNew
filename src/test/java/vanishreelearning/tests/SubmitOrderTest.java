package vanishreelearning.tests;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import vanishreelearning.pageobjects.CartPage;
import vanishreelearning.pageobjects.ConfirmationPage;
import vanishreelearning.pageobjects.LandingPage;
import vanishreelearning.pageobjects.OrderPage;
import vanishreelearning.pageobjects.PaymentPage;
import vanishreelearning.pageobjects.ProductCatalog;
import vanishreelearning.testComponents.BaseTest;

public class SubmitOrderTest extends BaseTest {
	//String prodname="ZARA COAT 3";
	
	@Test(dataProvider="getData",groups= {"purchase"})
	public void submitOrder(HashMap<String,String> input)throws IOException {
		String country="india";
		ProductCatalog productCatalog=landingpage.loginApplication(input.get("email"),input.get("password"));
		List<WebElement> products=productCatalog.getProductList();
		productCatalog.addProductToCart(input.get("prodname"));
		CartPage cartPage=productCatalog.gotoCartPage();
		boolean match=cartPage.matchCart(input.get("prodname"));
		Assert.assertTrue(match);
		PaymentPage paymentPage=cartPage.proceedCheckOut();
		ConfirmationPage confirmationPage=paymentPage.actionSelectCountry(country);
		String confirmMessage=confirmationPage.getConfirmation();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		
	}
	
	@Test(dependsOnMethods= {"submitOrder"},dataProvider="getData",groups= {"purchase"})
	public void verifyOrder(HashMap<String,String> input)
	{
		ProductCatalog productCatalog=landingpage.loginApplication(input.get("email"),input.get("password"));
		OrderPage orderPage = productCatalog.gotoOrderPage();
		Assert.assertTrue(orderPage.verifyOrder(input.get("prodname")));
	}
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
		
		List<HashMap<String,String>> data=getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//vanishreelearning//data//PurchaseOrder.json");
		
		return new Object[][]{{data.get(0)},{data.get(1)}};
	}
	
	
}	
	/*HashMap<String,String> map=new HashMap<String,String>();
	map.put("email", "vanitest@gmail.com");
	map.put("password", "Testframe@1");
	map.put("prodname", "ZARA COAT 3");
	HashMap<String,String> map1=new HashMap<String,String>();
	map1.put("email", "aastika@gmail.com");
	map1.put("password", "Demo@123");
	map1.put("prodname", "ADIDAS ORIGINAL");*/




