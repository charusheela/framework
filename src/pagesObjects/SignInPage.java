package pagesObjects;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import commonFunctions.Base;
import commonFunctions.Report;

public class SignInPage extends Base {

	WebDriverWait wait = new WebDriverWait(driver, 20);

	@Override
	public void waitForPageToLoad() {

		wait.until(ExpectedConditions.visibilityOf(button_SignIn));
	}

	// Verify Sign In page loaded

	public SignInPage verifySignInPage() {

		try {
			waitForPageToLoad();
			Report.AssertTrueWithScreenshot(button_SignIn.isDisplayed(),
					"Verify Sign in page is loaded successfully");
		} catch (NoSuchElementException e) {
			Assert.fail("Failed to load SignIn Page");
		}
		return this;
	}

	// Click Sign In button

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
