package Ecommerce.Ecommerce;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.annotations.AfterClass;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BestBuy {
	WebDriver driver;

	@BeforeClass
	public void setup() {

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://www.bestbuy.ca/en-ca/");
		driver.manage().window().maximize();

	}

	@Test(priority = 1)
	public void Check_Ipad_Product() throws InterruptedException, IOException {

		driver.manage().timeouts().implicitlyWait(3000, TimeUnit.SECONDS);
      // type ipad in the search box
		WebElement TextSearch = driver.findElement(By.name("search"));
		TextSearch.sendKeys("ipad");
		
		WebElement ClickSearch=driver.findElement(By.xpath("//button[@type='submit']"));
		ClickSearch.click();
		
		//click on Online Only
		WebElement AddOnlineOnly=driver.findElement(By.xpath("//span[text()='Online Only']"));
		AddOnlineOnly.click();
 
		//choose any product to be added
		WebElement AddProduct=	driver.findElement(By.xpath("//div[text()='$543.49']"));
	    AddProduct.click();
	    
		Thread.sleep(5000);
		
		 //click on Add to Cart
		WebElement AddToCart=driver.findElement(By.xpath("//span[text()='Add to Cart']"));
		AddToCart.click();
		
		Thread.sleep(5000);
		
		//take screenshot 
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File("F:\\testing\\Ecommerce\\screenshots\\task1.png"));
		
		//click on view cart

		WebElement ViewCart=driver.findElement(By.xpath("//span[text()='View Cart']"));
		ViewCart.click();
		
		 //click on Continue to Checkout
		WebElement Checkout=driver.findElement(By.xpath("//span[text()='Continue to Checkout']"));
		Checkout.click();
		
		WebElement ClickContinue=driver.findElement(By.xpath("//span[text()='Continue']"));
		ClickContinue.click();
		
		// fill the details
		driver.findElement(By.id("email")).sendKeys("sallamfcis@gmail.com");
		driver.findElement(By.id("firstName")).sendKeys("sayed");
		driver.findElement(By.id("lastName")).sendKeys("sallam");
		driver.findElement(By.id("addressLine")).sendKeys("11742-1 Place Ville-Marie");
		driver.findElement(By.id("city")).sendKeys("Montréal");
		driver.findElement(By.id("postalCode")).sendKeys(" H3B 3Y1");
		driver.findElement(By.id("phone")).sendKeys("(010) 134–6891");
       //click continue
		driver.findElement(By.xpath("//span[text()='Continue']")).click();
		driver.findElement(By.xpath("//span[text()='Continue']")).click();
		// fill master card
		driver.findElement(By.id("shownCardNumber")).sendKeys("4573764706314705");
		driver.findElement(By.id("cvv")).sendKeys("145");
		driver.findElement(By.xpath("//span[text()='Continue']")).click();
		// check status of card
		String actual = driver.findElement(By.xpath("//*[@id=\"root\"]/div[5]/div/div/div/div/div")).getText();
		String excpect = "The credit card you entered or selected is expired or will expire soon. Please select a different credit card, edit this credit card, or add a new one. (0115)";
		assertEquals(actual, excpect);
		
	}

	@AfterClass
	public void teardown() {

		// driver.quit();

	}
}
