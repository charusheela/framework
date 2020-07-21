package commonFunctions;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Reporter;

import com.google.common.io.Files;

public class Screenshot extends CapInitialise {
	File srcFiler = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

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

}
