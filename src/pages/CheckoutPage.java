package pages;

import java.util.List;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import utils.Base;
import utils.FileInput;
import utils.Utility;

public class CheckoutPage extends Utility {

	FileInput files = new FileInput();
	
	
	public CheckoutPage(AndroidDriver<AndroidElement> driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void waitForPageToLoad() {
		waitForElement(driver, button_Address);
		Utility.logWithScreenShot("Address Details:- ");
		button_Address.click();
		waitForElement(driver, button_Continue);
		Utility.logWithScreenShot("Delivery Date Details:- ");
	}

	/**
	 * Verify CheckOut Payments
	 * 
	 * @return
	 */
	public CheckoutPage verifyCheckOutPage() {
		try {
			waitForPageToLoad();
			Utility.AssertTrueWithScreenshot(button_Continue.isDisplayed(),
					"Verify CheckOut page is loaded successfully");
		} catch (NoSuchElementException e) {
			Assert.fail("Unable to load CheckOut page");
		}
		return this;
	}

	/**
	 * Select Bank
	 * 
	 * @return
	 */
	public CheckoutPage selectBank() {
		waitForElement(driver, dropDown_BankName);
		
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

	/**
	 * Select Net Banking payment option
	 * 
	 * @return
	 */
	public CheckoutPage clickNetBankingRadioButton() {
		try {

			radio_NetBanking.click();
			Reporter.log("NetBanking option is clicked successfully");
		} catch (Exception e) {
			Assert.fail("Failed to click NetBanking button");
		}
		return this;
	}

	/**
	 * Click Continue button
	 * 
	 * @return
	 */
	public CheckoutPage clickContinueButton() {
		try {

			button_Continue.click();
			Reporter.log("Continue button is clicked successfully");
		} catch (Exception e) {
			Assert.fail("Failed to click continue button");
		}
		return this;
	}

	/**
	 * Get selected item name from CheckOut page
	 * 
	 * @return
	 */
	public String getItemNameText() {
		String actualDeviceName = "";
		waitForElement(driver, text_ItemName);
		try {
			actualDeviceName = text_ItemName.getText();
			Reporter.log("Text item name get successfully");
		} catch (Exception e) {
			Assert.fail("Failed to get item name from check out page");
		}
		return actualDeviceName;
	}

	/**
	 * Compare device names between search results and checkout
	 * 
	 * @param actualValue
	 * @param expectedValue
	 * @return
	 */
	public CheckoutPage compareItemNames(String actualValue, String expectedValue) {
		try {
			Assert.assertEquals(actualValue, expectedValue, "String Comparison failed");
			Reporter.log("Item names are matching successfully");
			Utility.logWithScreenShot("CheckOut Page");
		} catch (Exception e) {
			Assert.fail("Failed to compare both values");
		}
		return this;
	}

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
