package commonFunctions;

import org.openqa.selenium.support.PageFactory;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public abstract class Base extends Config implements WaitToLoad {

	public Base() {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);

	}

}