package BasePackage;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Utility.DataHolder;
import Utility.SeleniumUtils;

public class JobsPortal extends DataHolder {
	static WebDriver driver = DataHolder.getDriver();

	public static void jobList() throws InterruptedException {
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		// Jobs section
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//a[@title='Recommended Jobs']/div[contains(text(),'Jobs')]")))
				.click();
		List<WebElement> jobsList = driver.findElements(By.cssSelector("p.title"));
		for (WebElement job : jobsList) {
			String jobhead = job.getText().toLowerCase();
			System.out.println(jobhead);
			job.click();
			Thread.sleep(2000);
			SeleniumUtils.switchToNewWindow(wait);
			System.out.println("Title : " + driver.getTitle());
			Thread.sleep(1000);
			String experience = driver.findElement(By.cssSelector("section.styles_job-desc-container__txpYf")).getText()
					.toLowerCase();
			System.out.println(experience);
			Thread.sleep(1000);
			if (experience.contains("4") || experience.contains("3") && experience.contains("java")
					&& experience.contains("cucumber") && experience.contains("testng") && experience.contains("rest")
					&& experience.contains("api")) {
				Thread.sleep(5000);
				WebElement element = wait.until(ExpectedConditions.elementToBeClickable(
						By.xpath("//div[@class='styles_jhc__apply-button-container__5Bqnb']/button[2]")));
				SeleniumUtils.javaScriptExecutor(element);

				try {
					WebElement chatbot = wait.until(ExpectedConditions
							.visibilityOfElementLocated(By.cssSelector("div.chatbot_MessageContainer")));
					WebElement answer = driver.findElement(By.cssSelector("div.chatbot_InputContainer"));
					if (chatbot.isDisplayed()) {
						String questions = chatbot.getText();
						if (questions.contains("Notice")) {
							try {
								List<WebElement> options = driver
										.findElements(By.xpath("//div[@class='ssrc__radio-btn-container']"));
								for (int i = 0; i <= options.size(); i++) {
									String option = options.get(i).getText();
									System.out.println(option);
									if (option.contains("3 Months")) {
										options.get(i).click();
										driver.findElement(By.xpath("//div[@class='sendMsg']")).click();
									} else {
										answer.sendKeys("90 days");
										driver.findElement(By.xpath("//div[@class='sendMsg']")).click();
									}
								}

							} catch (Exception e) {
								e.printStackTrace();
								System.out.println("No option present");
							}

						} else if (questions.contains("experience")) {
							answer.sendKeys("4.2");
							driver.findElement(By.xpath("//div[@class='sendMsg']    ")).click();
						} else if (questions.contains("Thank")) {
							String status1 = wait.until(
									ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.upper-section")))
									.getText();
							System.out.println("Job status :" + status1);
						}
					}

				} catch (Exception e) {
					System.out.println("No chatbot available");
					String status1 = wait
							.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.upper-section")))
							.getText();
					System.out.println("Job status :" + status1);
				}

			}
			SeleniumUtils.switchToPreviousWindow(wait);
			System.out.println(driver.getTitle());

		}

	}

}
