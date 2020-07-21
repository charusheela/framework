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
import commonFunctions.Report;

public class SearchPage extends Base {

	WebDriverWait wait = new WebDriverWait(driver, 20);

	@Override
	public void waitForPageToLoad() {

		wait.until(ExpectedConditions.visibilityOf(label_SearchResults));
	}

	// Verify Search Result page loaded

	public SearchPage verifySearchResultPage() {

		try {
			waitForPageToLoad();
			Report.AssertTrueWithScreenshot(label_SearchResults.isDisplayed(),
					"Verify Search Result page is loaded successfully");
		} catch (NoSuchElementException e) {
			Assert.fail("Failed to load Search Result page");
		}
		return this;
	}

	// item name from search

	public String getItemName() {
		String deviceName = "";
		try {
			deviceName = list_resultItem.get(2).getText();
			;
			Reporter.log(" Item name from search results page");
		} catch (Exception e) {
			Assert.fail("Failed to get item name from search results page");
		}
		return deviceName;
	}

	@FindBy(xpath = "//*[@resource-id=\"com.amazon.mShop.android.shopping:id/item_title\"]")
	private List<WebElement> list_resultItem;

	@FindBy(xpath = "//*[@resource-id='com.amazon.mShop.android.shopping:id/rs_corrections_mixed_quartz']")
	private WebElement label_SearchResults;
}
