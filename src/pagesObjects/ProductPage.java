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
import commonFunctions.Utility;

public class ProductPage extends Base {

	WebDriverWait wait = new WebDriverWait(driver, 20);

	@Override
	public void waitForPageToLoad() {

		wait.until(ExpectedConditions.visibilityOf(label_ProductTitle));
	}

	// Verify Product Detail page loaded successfully

	public ProductPage verifyProductDetailsPage() {

		try {
			waitForPageToLoad();
			Report.AssertTrueWithScreenshot(button_BuyNow.isDisplayed(),
					"Verify Product Details page is loaded successfully");
		} catch (NoSuchElementException e) {
			Assert.fail("Failed to load Product Details page");
		}
		return this;
	}

	// Buy Now button click on Product details page

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
