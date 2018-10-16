package seleniumConcepts;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class LaunchFFWithGeckoDriver {

	public static void main(String[] args) {

		System.setProperty("webdriver.gecko.driver", "F:\\SeleniumDrivers\\geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		
		driver.manage().window().maximize();
		driver.get("https://www.google.com");
		
		driver.quit();

	}

}
