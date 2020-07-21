package commonFunctions;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.google.common.io.Files;
import com.relevantcodes.extentreports.ExtentReports;

import commonFunctions.Config;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.events.EventFiringWebDriverFactory;
import io.appium.java_client.remote.MobileCapabilityType;

public class CapInitialise extends Config {

	DesiredCapabilities cap;

	private static String APPLICATION_PROPERTIES_FILE = "C:\\Users\\hafizlandya\\Desktop\\workspace\\mobileAssignment\\mobileTest\\resources\\application.properties";
	private static String APPIUM_SERVER_URL;
	private static String ANDROID_ID;
	private static String ANDROID_APP;
	private static String ANDROID_PKG_ID;
	private static String ANDROID_ACTIVITY_ID;
	private static String ANDROID_WAIT_ACTIVITY_ID;
	private static String ANDROID_PLATFORM_NAME;
	private static String ANDROID_AUTOMATION_NAME;
	private static String ANDROID_NO_RESET;
	private static String ANDROID_FULL_RESET;
	private static String EXECUTION_TYPE;

	public void AppiumCapabilities() {

		try {

			System.out.print(System.getProperty("user.dir"));

			props.load(new FileReader(new File(APPLICATION_PROPERTIES_FILE)));

			APPIUM_SERVER_URL = props.getProperty("appium.server.url");
			EXECUTION_TYPE = props.getProperty("execution.type");

			if (EXECUTION_TYPE.toUpperCase().trim().equals("ANDROID")) {
				ANDROID_APP = props.getProperty("android.app");
				ANDROID_ID = props.getProperty("android.id");
				ANDROID_PKG_ID = props.getProperty("android.pkg.id");
				ANDROID_ACTIVITY_ID = props.getProperty("android.activity.id");
				ANDROID_WAIT_ACTIVITY_ID = props.getProperty("android.wait.activity.id");
				ANDROID_AUTOMATION_NAME = props.getProperty("android.automation.name");
				ANDROID_NO_RESET = props.getProperty("android.noReset");
				ANDROID_FULL_RESET = props.getProperty("android.fullReset");

			} else {
				// IOS
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void startAppium(String platform) throws MalformedURLException {
		cap = new DesiredCapabilities();
		cap.setCapability("browserName", "");

		switch (platform) {
		case "IOS":
			// IOS
			break;

		case "ANDROID":
			cap.setCapability(MobileCapabilityType.UDID, ANDROID_ID);
			cap.setCapability(MobileCapabilityType.PLATFORM_NAME, ANDROID_PLATFORM_NAME);
			cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, ANDROID_AUTOMATION_NAME);
			cap.setCapability(MobileCapabilityType.NO_RESET, ANDROID_NO_RESET);
			cap.setCapability(MobileCapabilityType.FULL_RESET, ANDROID_FULL_RESET);
			cap.setCapability("printPageSourceOnFindFailure", "true");
			cap.setCapability(MobileCapabilityType.APP, ANDROID_APP);
			cap.setCapability("appPackage", ANDROID_PKG_ID);
			cap.setCapability("appActivity", ANDROID_ACTIVITY_ID);
			cap.setCapability("appWaitActivity", ANDROID_WAIT_ACTIVITY_ID);
			cap.setCapability(MobileCapabilityType.DEVICE_NAME, "Android");
			cap.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "60");

			try {
				log.info("setting capabilities : " + cap);
				driver = new AndroidDriver<AndroidElement>(new URL("http://" + APPIUM_SERVER_URL + "/wd/hub"),
						cap);
				log.info(" driver instance created successfully");
			} catch (Exception e) {
				log.error("not able to create driver instance");
				e.printStackTrace();
			}

			break;

		default:
			break;
		}

		driver.manage().timeouts().implicitlyWait(Integer.parseInt(props.getProperty("driver.timeout")),
				TimeUnit.MILLISECONDS);
		log.info("Driver Initialized successfully.....");
		System.out.println("Driver Initialized successfully.....");
		Reporter.log("Driver Initialized successfully.....");
	}

	@BeforeSuite
	public void beforeSuite(ITestContext context) throws InterruptedException, FileNotFoundException, IOException {
		AppiumCapabilities();

		if (props.getProperty("new.appium.session.between.test").toUpperCase().trim().equals("FALSE")) {
			log.info("Running test case from feature: " + context.getName());
			startAppium(EXECUTION_TYPE.trim().toUpperCase());
		}

		// Report Directory and Report Name
		extent = new ExtentReports(System.getProperty("user.dir") + "/report.html", true);
		extent.loadConfig(new File(System.getProperty("user.dir") + "/extent-config.xml"));

	}

	@BeforeMethod
	public void beforeMethod(ITestContext context, Method method)
			throws InterruptedException, FileNotFoundException, IOException {
		if (props.getProperty("new.appium.session.between.test").toUpperCase().trim().equals("TRUE")) {
			log.info("Running test case from feature: " + context.getName());

			startAppium(EXECUTION_TYPE.trim().toUpperCase());
		} else {
			driver.launchApp();
		}

		test = extent.startTest((this.getClass().getSimpleName() + " :: " + method.getName()), method.getName()); // Test
																													// Case
																													// Start
																													// Here
		test.assignAuthor(props.getProperty("test.author").toUpperCase().trim()); // Test Script Author Name
		test.assignCategory(props.getProperty("test.category").toUpperCase().trim());

	}

	@AfterMethod
	public void afterMethod(ITestResult result) {
		if (props.getProperty("new.appium.session.between.test").toUpperCase().trim().equals("TRUE")) {
			log.info("End of Test Case : " + result.getMethod().getMethodName() + result.toString());
			driver.closeApp();
			log.info("closed application");
			driver.quit();
			log.info("closed appium session");
		} else {
			log.info("End of Test Case : " + result.getMethod().getMethodName() + result.toString());
			driver.resetApp();
			driver.closeApp();
			log.info("closed application");
		}

		extent.endTest(test);
		extent.flush();

	}

	@AfterSuite
	public void afterSuite(ITestContext context) {
		if (props.getProperty("new.appium.session.between.test").toUpperCase().trim().equals("FALSE")) {
			log.info("End of Test Suite.");
			log.info("Passed test cases" + context.getPassedTests());
			log.info("Failed test cases" + context.getFailedTests());
			log.info("Skipped test cases" + context.getSkippedTests());
			driver.closeApp();
			log.info("closed application");
			driver.quit();
			log.info("closed appium session");
		}

		extent.close();
	}

}
