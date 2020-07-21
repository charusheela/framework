package pagesObjects;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import commonFunctions.Base;
import commonFunctions.FileInput;
import commonFunctions.Report;
import io.appium.java_client.MobileElement;

public class LoginPage extends Base {

	FileInput files = new FileInput();
	WebDriverWait wait = new WebDriverWait(driver, 20);

	@Override
	public void waitForPageToLoad() {
		wait.until(ExpectedConditions.visibilityOf(button_Continue));
	}

	// Verify LogIn In page loaded

	public LoginPage verifyLogInPage() {

		try {
			waitForPageToLoad();
			Report.AssertTrueWithScreenshot(textBox_MobileNumber.isDisplayed(), "Verify Login page is loaded");
		} catch (NoSuchElementException e) {
			Assert.fail("Failed to load LogIn Page");
		}
		return this;
	}

	// User login

	public LoginPage logInUser() {
		try {
			String username = files.Username(); // username from TestData.xls
			String password = files.Password(); // password from TestData.xls
			signIn(username, password);
			Assert.assertTrue(true, "Login Successful");
		} catch (Exception e) {
			Assert.fail("Sign in failed");
		}
		return this;
	}

	// Enter username and password 

	public void signIn(String mobile_no, String pass) {
		try {
			textBox_MobileNumber.sendKeys(mobile_no);
			button_Continue.click();
			wait.until(ExpectedConditions.visibilityOf(textBox_Password));
			textBox_Password.sendKeys(pass);
			Report.logWithScreenShot("Before Login");
			button_Login.click();
			Reporter.log("Successfully  login ");
		} catch (Exception e) {
			Assert.fail("Failed to enter login details and Continue");
		}
	}

	@FindBy(xpath = "//*[@resource-id=\"ap_email_login\"]")
	private MobileElement textBox_MobileNumber;

	@FindBy(xpath = "//*[@resource-id=\"ap_password\"]")
	private MobileElement textBox_Password;

	@FindBy(xpath = "//*[@resource-id=\"signInSubmit\"]")
	private MobileElement button_Login;

	@FindBy(xpath = "//*[@resource-id=\"continue\"]")
	private MobileElement button_Continue;

}
