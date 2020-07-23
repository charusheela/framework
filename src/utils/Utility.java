package utils;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import com.google.common.io.Files;
import com.relevantcodes.extentreports.LogStatus;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidElement;

public class Utility extends Base {
	
	public static void takeScreenshot(String filename) {
		System.out.println(" SS '" + filename + "' page ");
		Reporter.log("SS '" + filename + "' page ");

		File srcFiler = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			Files.copy(srcFiler, new File("screenshots/" + filename + ".jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("SS Captured");
		Reporter.log("SS Captured");
	}


	public static String getScreenshot(String filename) {
		takeScreenshot(filename);
		return System.getProperty("user.dir") + "/screenshots/" + filename + ".jpg";
	}
	
	public static void swipeVeritcal(AppiumDriver<AndroidElement> driver, double startPercentage, double finalPercentage,
			int duration) {
		Dimension size = driver.manage().window().getSize();
		int width = (int) (size.width / 2);
		int startPoint = (int) (size.getHeight() * startPercentage);
		int endPoint = (int) (size.getHeight() * finalPercentage);

		new TouchAction(driver).press(width, startPoint).waitAction(Duration.ofMillis(duration)).moveTo(width, endPoint)
				.release().perform();
	}
	
	/**
	 * This method checks for the visibility of the element
	 */
	public void waitForElement(AppiumDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Integer.parseInt(props.getProperty("app.explicitWait")));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public static void log(String msg) {
		Reporter.log(msg);
		Config.getLogger().info(msg);
		Config.test.log(LogStatus.INFO, msg, "");
	}

	public static void logWithScreenShot(String msg) {
		Reporter.log(msg);
		Config.getLogger().info(msg);
		String image = Config.test.addScreenCapture(Utility.getScreenshot(msg));
		Config.test.log(LogStatus.INFO, msg, image);
	}

	public static void AssertTrueWithScreenshot(boolean condition, String msg) {
		try {
			Assert.assertTrue(condition, msg + "Failed");
			Config.test.log(LogStatus.PASS, msg + "Passed", Utility.getScreenshot(msg));
		} catch (Exception e) {
			String image = Config.test.addScreenCapture(Utility.getScreenshot(msg));
			Config.test.log(LogStatus.FAIL, msg + "Failed", image);
		}
	}

}