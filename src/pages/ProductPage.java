package pages;

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
import utils.Utility;

public class ProductPage extends Utility {

	
	public ProductPage(AndroidDriver<AndroidElement> driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void waitForPageToLoad() {

		waitForElement(driver,label_ProductTitle);
	}

	/**
	 * Verify Product Detail page loaded successfully
	 * 
	 * @return
	 */
	public ProductPage verifyProductDetailsPage() {

		try {
			waitForPageToLoad();
			Utility.AssertTrueWithScreenshot(button_BuyNow.isDisplayed(),
					"Verify Product Details page is loaded successfully");
		} catch (NoSuchElementException e) {
			Assert.fail("Failed to load Product Details page");
		}
		return this;
	}

	/**
	 * Buy Now button click on Product details page
	 * 
	 * @return
	 */
	public ProductPage clickBuyNow() {
		try {
			while (!(button_BuyNow.isDisplayed())) {
				Utility.swipeVeritcal(driver, 0.9, 0.2, 2); // Scroll Down till Buy Now button displayed
			}
			button_BuyNow.click();
			Reporter.log("Buy Now button is clicked successfully");
		} catch (Exception e) {
			Assert.fail("Failed to click Buy Now button");
		}
		return this;
	}

	@FindBy(xpath = "//*[@resource-id='titleExpander']")
	private WebElement label_ProductTitle;

	@FindBy(xpath = "//*[@resource-id='buyNowCheckout']")
	private WebElement button_BuyNow;

}
