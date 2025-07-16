package Utility;

import java.util.ArrayList;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumUtils {
	static WebDriver driver = DataHolder.getDriver();

	public static void javaScriptExecutor(WebElement element) {
		JavascriptExecutor jsc = (JavascriptExecutor) driver;
		jsc.executeScript("arguments[0].click();", element);

	}

	public static void switchToNewWindow(WebDriverWait wait) {
		try {
			driver.switchTo().defaultContent();
			ArrayList<String> allwindows = DataHolder.getAllWindows();
			//waitForWindow(allwindows.size() + 1);
			Set<String> windowHandles = driver.getWindowHandles();
			for (String childWindow : windowHandles) {
				if (!allwindows.contains(childWindow)) {
					driver.switchTo().window(childWindow);
					DataHolder.addNewWindow(childWindow);
					break;
				}
			}
			System.out.println("Switch to new window");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void switchToPreviousWindow(WebDriverWait wait) {
		try {
			driver.switchTo().defaultContent();
			ArrayList<String> allwindows = DataHolder.getAllWindows();
			//waitForWindow(allwindows.size());
			driver.switchTo().window(DataHolder.getLastWindow());
			System.out.println("Switch To Previous Window");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void waitForWindow(int numOfWindows) {
		int size = 0;
		try {
			while (size != numOfWindows) {
				try {
					size = driver.getWindowHandles().size();
				} catch (UnhandledAlertException e) {
					System.out.println(e.getMessage());
					Alert alert = driver.switchTo().alert();
					alert.accept();

				}
				Thread.sleep(1000);
				break;
			}
		} catch (InterruptedException ue) {
			System.out.println("Exception in waitForWindow");
			ue.printStackTrace();

		}
	}
}
