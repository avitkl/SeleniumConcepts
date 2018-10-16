package seleniumConcepts;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BrokenLinkTest {

	static WebDriver driver;
	public static void main(String[] args) throws MalformedURLException, IOException {

		System.setProperty("webdriver.chrome.driver","F:\\SeleniumDrivers\\chromedriver.exe");
 		driver=new ChromeDriver();
 		driver.manage().window().maximize();
 		driver.manage().deleteAllCookies();
 		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
 		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
 		
 		driver.get("https://www.freecrm.com");
 		
 		driver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys("avitkl");
		driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("avitkl@123");
		
		By loadingImage = By.id("preloader");
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(loadingImage));
		
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		
		driver.switchTo().frame("mainpanel");
		
		//links -- /a
		//image -- /img
		
		//1. Get the list of all the links and images
		List<WebElement> linksList=driver.findElements(By.tagName("a"));
		linksList.addAll(driver.findElements(By.tagName("img")));
		
		System.out.println("Full Size of links and images -->" + linksList.size());
		
		List<WebElement> activeLinks = new ArrayList<WebElement>();
		
		//2. Iterate linksList : excluse all the links/images doesnot have any href attribute
		for(int i=0;i<linksList.size();i++)
		{
			System.out.println(linksList.get(i).getAttribute("href"));
			if(linksList.get(i).getAttribute("href")!=null && (!linksList.get(i).getAttribute("href").contains("javascript")))
			{
				activeLinks.add(linksList.get(i));
			}
		}
		
		//get the size of active links list
		System.out.println("Size of active links and images -->" + activeLinks.size());
		
		//3. Check the href url with http connection api:
		for(int j=0;j<activeLinks.size();j++)
		{
			HttpURLConnection connection= (HttpURLConnection)new URL(activeLinks.get(j).getAttribute("href")).openConnection();
			
			connection.connect();
			String response=connection.getResponseMessage(); //If connection is success then return "OK"
			connection.disconnect();
			System.out.println(activeLinks.get(j).getAttribute("href") + "-->" +response);
		}
		
		driver.quit();
	}

}
