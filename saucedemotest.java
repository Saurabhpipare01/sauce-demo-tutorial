package Saucedemotest;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class saucedemotest {

	
	 WebDriver driver;

	    @BeforeClass
	    public void setUp() {
	       
	        driver = new ChromeDriver();
	        driver.manage().window().maximize();
	        driver.get("https://www.saucedemo.com/");
	    }

	    @Test
	    public void testShoppingProcess() throws InterruptedException {
	        // Step 1- Login
	        driver.findElement(By.id("user-name")).sendKeys("standard_user");
	        driver.findElement(By.id("password")).sendKeys("secret_sauce");
	        driver.findElement(By.id("login-button")).click();

	        // Step 2- Search for "Sauce Labs Fleece Jacket"
	        WebElement product = driver.findElement(By.xpath("//div[text()='Sauce Labs Fleece Jacket']"));
	        product.click();

	        // Step 3- Add to Cart
	        driver.findElement(By.xpath("//button[@id='add-to-cart']")).click();

	        // Step 4- Go to Cart
	        driver.findElement(By.className("shopping_cart_link")).click();
	        driver.findElement(By.xpath("//button[@id='checkout']")).click();

	        // Step 5- Enter Checkout Information
	        driver.findElement(By.xpath("//input[@id='first-name']")).sendKeys("John");
	        driver.findElement(By.xpath("//input[@id='last-name']")).sendKeys("Doe");
	        driver.findElement(By.xpath("//input[@id='postal-code']")).sendKeys("123455");
	        
	        //Actions act = new Actions(driver);
	        WebElement scrolltill=driver.findElement(By.id("//input[@id='continue']"));
	       // act.moveToElement(scrolltill).click().build().perform();
	        JavascriptExecutor js = (JavascriptExecutor) driver;
	        js.executeScript("arguments[0].scrollIntoView(true);", scrolltill);

	        //Thread.sleep(2000);
	        // Click on the "Continue" button after scrolling
	        scrolltill.click();

	        // Step 6- Print Payment Information and Total Price
	        String paymentInfo = driver.findElement(By.className("summary_info_label")).getText();
	        String totalPrice = driver.findElement(By.className("summary_total_label")).getText();

	        System.out.println("Payment Information: " + paymentInfo);
	        System.out.println("Total Price: " + totalPrice);

	        // Step 7- Finish
	        driver.findElement(By.id("finish")).click();

	        // Verify completion message
	        WebElement completionMessage = driver.findElement(By.className("complete-header"));
	        Assert.assertEquals(completionMessage.getText(), "THANK YOU FOR YOUR ORDER");
	    }

		
		  @AfterClass public void tearDown() { 
			  if (driver != null) {
				  driver.quit(); }
			  }
		 
}
