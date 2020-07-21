package pagesObjects;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import commonFunctions.Base;
import commonFunctions.FileInput;
import commonFunctions.Report;
import io.appium.java_client.TouchAction;

public class HomePage extends Base {

	FileInput files = new FileInput();
	WebDriverWait wait = new WebDriverWait(driver, 20);

	@Override
	public void waitForPageToLoad() {

		try {
			wait.until(ExpectedConditions.visibilityOf(button_CancelLanguage));
			button_CancelLanguage.click();
			System.out.print("Clicked on close language selection successfully");
		} catch (Exception e) {
			Assert.fail("Failed to load Home Page");
		}
	}

	// Verify Home page loaded successfully

	public HomePage verifyHomePage() {

		try {
			waitForPageToLoad();
			Report.AssertTrueWithScreenshot(search_Box.isDisplayed(), "Verify Home page is loaded successfully");
		} catch (NoSuchElementException e) {
			Assert.fail("Failed to load Home Page");
		}
		return this;
	}

	//  search box and select value from auto suggestion

	public HomePage EnterKeyword_SearchItem() {
		try {
			String searchItemName = files.SearchItem(); //  item name from TestData.xls

			new TouchAction(driver).press(search_Box).release().perform();
			search_Box.sendKeys(searchItemName);
			Report.logWithScreenShot("Searching... ");
			wait.until(ExpectedConditions.visibilityOf(select_Product));
			select_Product.click(); // Selecting product from auto suggest
			Report.logWithScreenShot("Search Result ");
		} catch (Exception e) {
			Assert.fail("Search process failed");
		}
		return this;
	}

	@FindBy(xpath = "//*[@resource-id='icp-btn-close-announce']")
	private WebElement button_CancelLanguage;

	@FindBy(xpath = "//*[@resource-id=\"com.amazon.mShop.android.shopping:id/action_bar_home_logo\"]")
	private WebElement logoAmazon;

	@FindBy(xpath = "//*[@resource-id=\"com.amazon.mShop.android.shopping:id/rs_search_src_text\"]")
	private WebElement search_Box;

	@FindBy(xpath = "//*[@resource-id=\"com.amazon.mShop.android.shopping:id/iss_search_dropdown_item_query_builder\"]")
	private WebElement button_Search;

	@FindBy(xpath = "//android.widget.TextView[@text='65-inch tv'])[2]")
	private WebElement select_Product;
}
