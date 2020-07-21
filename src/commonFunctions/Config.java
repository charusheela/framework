package commonFunctions;

import java.util.Properties;

import org.apache.log4j.Logger;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.ExtentTestInterruptedException;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class Config {

	protected static AppiumDriver driver;
	protected static Properties props = new Properties();
	protected static Logger log = Logger.getLogger("nativeApplicationLogger");

	public static ExtentReports extent;
	public static ExtentTest test;
	public static ExtentTestInterruptedException testexception;

	public static String testCaseIteration;
	public static String testCaseName;

	public static Properties getProperties() {
		return props;
	}

	public static AppiumDriver getDriver() {
		return driver;
	}

	public static Logger getLogger() {
		return log;
	}
}