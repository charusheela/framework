package commonFunctions;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import com.relevantcodes.extentreports.LogStatus;
import io.appium.java_client.AppiumDriver;

public class Listener extends TestListenerAdapter {

	AppiumDriver driver;
	private static String fileSeperator = System.getProperty("file.separator");

	@Override
	public void onTestStart(ITestResult result) {
		Config.getLogger().info("Executing test case : " + result.getName());
		System.out.println("Executing test case : " + result.getName());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		Config.getLogger().info("Passed : " + result.getName());
		System.out.println("Passed : " + result.getName());

		Config.getLogger()
				.info("Test Result [" + Config.testCaseName + ": " + Config.testCaseIteration + ":" + "Pass ]");
		System.out.println("Test Result [" + Config.testCaseName + ": " + Config.testCaseIteration + ":" + "Pass ]");

	}

	@Override
	public void onTestSkipped(ITestResult result) {
		Config.getLogger().info("Skipped : " + result.getName());
		System.out.println("Skipped : " + result.getName());
		Config.getLogger()
				.info("Test Result [" + Config.testCaseName + ": " + Config.testCaseIteration + ":" + "Pass ]");
		System.out.println("Test Result [" + Config.testCaseName + ": " + Config.testCaseIteration + ":" + "Pass ]");

	}

	@Override
	public void onTestFailure(ITestResult result) {
		Config.getLogger().info("Failed : " + result.getName());
		System.out.println("Failed " + result.getName());

		driver = CapInitialise.getDriver();
		String testClassName = getTestClassName(result.getTestName()).trim();
		String testMethodName = result.getName().toString().trim();
		String screenShotName = testMethodName + ".jpg";

		if (driver != null) {
			String imagePath = ".." + fileSeperator + "Screenshots" + fileSeperator + "Results" + fileSeperator
					+ testClassName + fileSeperator + takeScreenShot(driver, screenShotName, testClassName);

			Config.test.log(LogStatus.FAIL, "Execution failed..........", imagePath);
			String img = Config.test.addScreenCapture(System.getProperty("user.dir") + "\\screenshots\\Results\\"
					+ testClassName + "\\" + screenShotName);
			Config.test.log(LogStatus.FAIL, "Failed : " + testMethodName, img);

			Config.getLogger()
					.info("Test Result [" + Config.testCaseName + ": " + Config.testCaseIteration + ":" + "Pass ]");
			System.out
					.println("Test Result [" + Config.testCaseName + ": " + Config.testCaseIteration + ":" + "Pass ]");

		}
	}

	public static String takeScreenShot(WebDriver driver, String screenShotName, String testName) {
		try {
			File file = new File("Screenshots" + fileSeperator + "Results");
			if (!file.exists()) {
				System.out.println("File created " + file);
				file.mkdir();
			}

			File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			File targetFile = new File("Screenshots" + fileSeperator + "Results" + fileSeperator + testName,
					screenShotName);
			FileUtils.copyFile(screenshotFile, targetFile);

			return screenShotName;
		} catch (Exception e) {
			System.out.println("An exception occured while taking screenshot " + e.getCause());
			return null;
		}
	}

	public String getTestClassName(String testName) {
		String[] reqTestClassname = testName.split("\\.");
		int i = reqTestClassname.length - 1;
		System.out.println("Required Test Name : " + reqTestClassname[i]);
		return reqTestClassname[i];
	}

}