package BasePackage;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import PropertiesFile.PropertiesClass;
import Utility.DataHolder;

public class loginPage {
	static WebDriver driver = DataHolder.getDriver();

	public static void login() throws IOException {
		// login page
		driver.findElement(By.xpath("//input[@id='usernameField']"))
				.sendKeys(PropertiesClass.propertyValue("$username"));
		driver.findElement(By.xpath("//input[@id='passwordField']"))
				.sendKeys(PropertiesClass.propertyValue("$password"));
		driver.findElement(By.xpath("//div[@class='col s12']/button[1]")).click();
		System.out.println("Login Successfully");
	}

	public static void teardown() throws InterruptedException {
		Thread.sleep(500);
		driver.quit();
	}

}
