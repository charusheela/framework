package commonFunctions;

import java.time.Duration;

import org.openqa.selenium.Dimension;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.MultiTouchAction;
import io.appium.java_client.TouchAction;

public class Utility extends CapInitialise {

	public static void swipeVeritcal(AppiumDriver<MobileElement> driver, double startPercentage, double finalPercentage,
			int duration) {
		Dimension size = driver.manage().window().getSize();
		int width = (int) (size.width / 2);
		int startPoint = (int) (size.getHeight() * startPercentage);
		int endPoint = (int) (size.getHeight() * finalPercentage);

		new TouchAction(driver).press(width, startPoint).waitAction(Duration.ofMillis(duration)).moveTo(width, endPoint)
				.release().perform();
	}
}
