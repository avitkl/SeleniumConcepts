package seleniumConcepts;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class HandleWindowPopUp {

	static WebDriver driver;
	
	public static void main(String[] args) 
	{
		
		//1. alerts -- Javascript Pop Up -- Alert API(accept,dismiss)   -- Alert alert = driver.switchTo().alert();
		//2. File Upload Pop up -- Browse Button
		//3. Browser Window Popup or Advertisement pop up 
		
		System.setProperty("webdriver.chrome.driver","F:\\SeleniumDrivers\\chromedriver.exe");
 		driver=new ChromeDriver();
 		driver.manage().window().maximize();
 		driver.manage().deleteAllCookies();
 		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
 		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
 		
 		driver.get("http://www.popuptest.com/goodpopups.html");
 		
 		driver.findElement(By.xpath("//a[@class='black']")).click();
 		
 	    // We will not able to fetch values from handler based on index hence we have to use iterator() method which will point just above the handler object
 		Set<String> handler=driver.getWindowHandles();  
 		
 		// Now "it" object is pointing to first element of handler object
 		Iterator<String> it =handler.iterator();
 		
 		String parentWindowId = it.next();
 		System.out.println("Parent window id --> "+parentWindowId);
 		
 		String childWindowId = it.next();
 		System.out.println("Child window id --> "+childWindowId);
 		
 		driver.switchTo().window(childWindowId);		
 		System.out.println("Child window pop up title is " + driver.getTitle());
 		driver.close();
 		
        driver.switchTo().window(parentWindowId);
        System.out.println("Parent window pop up title is " + driver.getTitle());
        driver.close();
	}

}
