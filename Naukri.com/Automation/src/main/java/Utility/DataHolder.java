package Utility;

import java.util.ArrayList;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class DataHolder {

	public static WebDriver driver;
	private static ArrayList<String> allWindows = new ArrayList<String>();
	private static int windowIndex = -1;
	public static WebDriverWait wait;

	public static void InitializeData() {
		windowIndex = -1;
		allWindows.clear();
	}

	public static WebDriver getDriver() {
		return driver;
	}

	public static void setDriver(WebDriver driver) {
		DataHolder.driver = driver;
	}

	public static ArrayList<String> getAllWindows() {
		return allWindows;
	}

	public static String getLastWindow() {
		return allWindows.get(windowIndex);
	}

	public static void addNewWindow(String newWindow) {
		windowIndex++;
		allWindows.add(newWindow);
	}

	public static void removeWindow() {
		allWindows.remove(windowIndex);
		windowIndex--;
	}

}
