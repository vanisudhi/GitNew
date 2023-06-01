package vanishreelearning.tests;

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

import io.github.bonigarcia.wdm.WebDriverManager;
import vanishreelearning.pageobjects.LandingPage;

public class StandAloneTest {

	public static void main(String[] args) {
		String prodname="ZARA COAT 3";
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client");
		driver.findElement(By.id("userEmail")).sendKeys("vanitest@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Testframe@1");
		driver.findElement(By.id("login")).click();
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".mb-3")));
		List<WebElement> products=driver.findElements(By.cssSelector(".mb-3"));
		WebElement prod=products.stream().filter(product->product.findElement(By.cssSelector("b")).getText().equals(prodname)).findFirst().orElse(null);
		prod.findElement(By.cssSelector(".btn:last-of-type")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
		driver.findElement(By.cssSelector("button[routerlink*='cart']")).click();
		List<WebElement> cartProd=driver.findElements(By.cssSelector(".cartSection h3"));
		boolean match=cartProd.stream().anyMatch(item->item.getText().equalsIgnoreCase(prodname));
		Assert.assertTrue(match);
		driver.findElement(By.cssSelector(".totalRow button")).click();
		Actions a=new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector("input[placeholder='Select Country']")), "india").build().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
		driver.findElement(By.cssSelector(".action__submit")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".hero-primary")));
		 String confirmMessage=driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		driver.quit();
		

	}

}
