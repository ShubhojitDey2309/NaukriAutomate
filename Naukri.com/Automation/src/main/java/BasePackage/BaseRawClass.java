package BasePackage;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import BaseClass.InitializeDriver;
import PropertiesFile.PropertiesClass;
import Utility.DataHolder;
import Utility.Screenshot;
import Utility.SeleniumUtils;

public class BaseRawClass extends DataHolder {
	static WebDriver driver;

	public static void main(String[] args) throws InterruptedException, IOException {

		InitializeDriver drive = new InitializeDriver();
		driver = drive.Initialize();

		Thread.sleep(3000);

		loginPage login = new loginPage();
		login.login();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1000));

		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[@class='view-profile-wrapper']/a")).click();
		// Screenshot.TakeScreenshot("Profile Page");
		wait = new WebDriverWait(driver, Duration.ofSeconds(10000));

		// upload CV attachment
		File pdfDocs = new File(PropertiesClass.propertyValue("$pdfpath"));
		String pdfDocpath = pdfDocs.getCanonicalPath();
		try {
			WebElement delete = driver.findElement(By.xpath("//i[@title='Click here to delete your resume']"));
			if (delete.isDisplayed()) {
				System.out.println("Existing Resume is there");
				delete.click();
				List<WebElement> deletes = driver.findElements(By.xpath("//div[@class='action right-align']/button"));
				deletes.get(1).click();
				Thread.sleep(6000);
				WebElement fileUpload = wait
						.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='attachCV']")));
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("arguments[0].style.display='block';", fileUpload);
				fileUpload.sendKeys(pdfDocpath);
				Thread.sleep(10000);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@class='collection']/li[11]")))
						.click();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		Thread.sleep(4000);

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[@class='edit icon'])[5]"))).click();

		// Screenshot.TakeScreenshot("Career info");
		for (int i = 1; i <= 4; i++) {
			WebElement location = wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("//input[@placeholder='Tell us your location preferences to work']")));
			Thread.sleep(3000);
			location.sendKeys(PropertiesClass.propertyValue("$location" + i + ""));
			Thread.sleep(6000);
			System.out.println("Location selected :" + PropertiesClass.propertyValue("$location" + i + ""));
			WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='Sbtn']")));
			Thread.sleep(6000);
			SeleniumUtils.javaScriptExecutor(element);
		}
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#saveDesiredProfile"))).click();
		String status = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@class='msg']")))
				.getText();
		System.out.println(status);
		// Screenshot.TakeScreenshot("Saved career page info");
		JavascriptExecutor js1 = (JavascriptExecutor) driver;
		js1.executeScript("window.scrollBy(0,-4000)");
		JobsPortal portal = new JobsPortal();
		portal.jobList();
		login.teardown();

	}

}
