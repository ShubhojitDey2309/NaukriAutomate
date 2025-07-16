package BaseClass;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import PropertiesFile.PropertiesClass;
import Utility.DataHolder;

public class InitializeDriver {

	public WebDriver Initialize() throws IOException {
		System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\utility\\chromedriver.exe");
		ChromeOptions option = new ChromeOptions();
		// option.addArguments("--headless");
		option.addArguments("--disable-gpu");
		WebDriver driver = new ChromeDriver(option);
		driver.get(PropertiesClass.propertyValue("$url"));
		DataHolder.setDriver(driver);
		driver.manage().window().maximize();
		return driver;
	}

	/*
	 * public static void TearDown(WebDriver driver) { driver.quit(); }
	 */

}
