package pagesObjects;

import java.util.List;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import commonFunctions.Base;
import commonFunctions.FileInput;
import commonFunctions.Report;

public class CheckoutPage extends Base {

	FileInput files = new FileInput();
	WebDriverWait wait = new WebDriverWait(driver, 20);

	@Override
	public void waitForPageToLoad() {

		wait.until(ExpectedConditions.visibilityOf(button_Address));
		Report.logWithScreenShot("Address Details:- ");
		button_Address.click();
		wait.until(ExpectedConditions.visibilityOf(button_Continue));
		Report.logWithScreenShot("Delivery Date Details:- ");
	}

	// Verify CheckOut Payments

	public CheckoutPage verifyCheckOutPage() {
		try {
			waitForPageToLoad();
			Report.AssertTrueWithScreenshot(button_Continue.isDisplayed(),
					"Verify CheckOut page is loaded successfully");
		} catch (NoSuchElementException e) {
			Assert.fail("Unable to load CheckOut page");
		}
		return this;
	}

	// Select Bank

	public CheckoutPage selectBank() {
		wait.until(ExpectedConditions.visibilityOf(dropDown_BankName));
		try {
			String bankName = files.BankName(); // Bank name from TestData.xls
			dropDown_BankName.click();
			for (WebElement webElement : list_BankOptions) {
				if (webElement.getText().equalsIgnoreCase(bankName)) {
					webElement.click();
					break;
				}
			}
		} catch (Exception e) {
			Assert.fail("Selecting Bank Name Failed");
		}
		return this;
	}

	// Select Net Banking payment option

	public CheckoutPage clickNetBankingRadioButton() {
		try {

			radio_NetBanking.click();
			Reporter.log("NetBanking option is clicked successfully");
		} catch (Exception e) {
			Assert.fail("Failed to click NetBanking button");
		}
		return this;
	}

	// Click Continue button

	public CheckoutPage clickContinueButton() {
		try {

			button_Continue.click();
			Reporter.log("Continue button is clicked successfully");
		} catch (Exception e) {
			Assert.fail("Failed to click continue button");
		}
		return this;
	}

	// Get selected item name from CheckOut page

	public String getItemNameText() {
		String actualDeviceName = "";
		wait.until(ExpectedConditions.visibilityOf(text_ItemName));
		try {
			actualDeviceName = text_ItemName.getText();
			Reporter.log("Text item name get successfully");
		} catch (Exception e) {
			Assert.fail("Failed to get item name from check out page");
		}
		return actualDeviceName;
	}

	// Compare device names between search results and checkout

	public CheckoutPage compareItemNames(String actualValue, String expectedValue) {
		try {
			Assert.assertEquals(actualValue, expectedValue, "String Comparison failed");
			Reporter.log("Item names are matching successfully");
			Report.logWithScreenShot("CheckOut Page");
		} catch (Exception e) {
			Assert.fail("Failed to compare both values");
		}
		return this;
	}

	/*
	 * System.out.println("Selecting the option from dropdown");
	 * System.out.println("trying to find the Name field");
	 */
	
	
	@FindBy(xpath = "//*[@text='Continue']")
	private WebElement button_Continue;

	@FindBy(xpath = "//*[@resource-id='a-autoid-0-announce']")
	private WebElement button_Address;

	@FindBy(xpath = "//*[@resource-id='net-banking']")
	private WebElement radio_NetBanking;

	@FindBy(xpath = "//*[@resource-id='bank-options-combo']")
	private WebElement dropDown_BankName;

	@FindBy(xpath = "//*[@resource-id='list-banks']")
	private List<WebElement> list_BankOptions;

	@FindBy(xpath = "//*[@resource-id=\"com.amazon.mShop.android.shopping:id/item_title\"]")
	private WebElement text_ItemName;
}
