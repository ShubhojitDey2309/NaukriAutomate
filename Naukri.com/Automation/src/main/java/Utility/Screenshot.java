package Utility;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Screenshot {
	static WebDriver driver = DataHolder.getDriver();

	public static void TakeScreenshot(String comment) throws IOException {
		String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		//String folderPath = "NaukriShots";
		//new File(folderPath).mkdir();
		File dest = new File("screenshots_" + comment + "_" + timestamp + ".png");
		FileUtils.copyFile(src, dest);

	}

}
