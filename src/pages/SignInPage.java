package pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import utils.Base;
import utils.Utility;

public class SignInPage extends Utility {

	
	public SignInPage(AndroidDriver<AndroidElement> driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void waitForPageToLoad() {

		waitForElement(driver,button_SignIn);
	}

	/**
	 * Verify Sign In page loaded
	 * 
	 * @return
	 */
	public SignInPage verifySignInPage() {

		try {
			waitForPageToLoad();
			Utility.AssertTrueWithScreenshot(button_SignIn.isDisplayed(), "Verify Sign in page is loaded successfully");
		} catch (NoSuchElementException e) {
			Assert.fail("Failed to load SignIn Page");
		}
		return this;
	}

	/**
	 * Click Sign In button
	 * 
	 * @return
	 */
	public SignInPage clickSignIn() {
		try {
			button_SignIn.click();
			Reporter.log("Sign in button is clickable");
		} catch (Exception e) {
			Assert.fail("Sign in button is not clickable");
		}
		return this;
	}

	@FindBy(id = "sso_splash_logo")
	private WebElement header_Amazon;

	@FindBy(xpath = "//*[@resource-id=\"com.amazon.mShop.android.shopping:id/sign_in_button\"]")
	private WebElement button_SignIn;

	@FindBy(id = "new_user")
	private WebElement button_CreateAccount;

	@FindBy(id = "skip_sign_in_button")
	private WebElement button_SkipSingIn;
}
