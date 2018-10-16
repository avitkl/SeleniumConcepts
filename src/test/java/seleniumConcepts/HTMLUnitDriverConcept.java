package seleniumConcepts;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class HTMLUnitDriverConcept {

	static WebDriver driver;
	public static void main(String[] args) {
		
		System.setProperty("webdriver.chrome.driver","F:\\SeleniumDrivers\\chromedriver.exe");
		
		//htmlunitdriver is not available in Selenium 3.x version
		//htmlunitdriver -- to use this concept, we have download htmlunitdriver JAR file from Github
		// htmldriver is also called "Ghost driver" or "Headless Browser"
		    // htmlUnitDriver -- Java
		    // PhantomJS -- JavaScript
		
		//advantages
		//1. Testing is happening behind the scene -- no browser is launched
		//2. Very fast -- execution of test cases -- performances of the script
		
		//Disadvantages
		//1. Not suitable for Actions class --user actions -- mousemovement,doubleClick ,drag and drop
		 driver =new HtmlUnitDriver();
		 
		 driver.manage().window().maximize();
	 		driver.manage().deleteAllCookies();
	 		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
	 		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	 		
	 		driver.get("https://www.freecrm.com");
	 		
	 		System.out.println("Before login title is :===" +driver.getTitle());
	 		
	 		driver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys("avitkl");
			driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("avitkl@123");
			driver.findElement(By.xpath("//input[@type='submit']")).click();
			
			System.out.println("After login title is :===" +driver.getTitle());
	}

}
