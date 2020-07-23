package pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import utils.Base;
import utils.FileInput;
import utils.Utility;

public class HomePage extends Utility {

	FileInput files = new FileInput();

	
	
	public HomePage(AndroidDriver<AndroidElement> driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void waitForPageToLoad() {

		try {
			waitForElement(driver, button_CancelLanguage);
			button_CancelLanguage.click();
			System.out.print("Clicked on close language selection successfully");
		} catch (Exception e) {
			Assert.fail("Failed to load Home Page");
		}
	}

	/**
	 * Verify Home page loaded successfully
	 * 
	 * @return
	 */
	public HomePage verifyHomePage() {

		try {
			waitForPageToLoad();
			Utility.AssertTrueWithScreenshot(search_Box.isDisplayed(), "Verify Home page is loaded successfully");
		} catch (NoSuchElementException e) {
			Assert.fail("Failed to load Home Page");
		}
		return this;
	}

	/**
	 * search box and select value from auto suggestion
	 * 
	 * @return
	 */
	public HomePage EnterKeyword_SearchItem() {
		try {
			String searchItemName = files.SearchItem(); // item name from TestData.xls

			new TouchAction(driver).press(search_Box).release().perform();
			search_Box.sendKeys(searchItemName);
			Utility.logWithScreenShot("Searching... ");
			waitForElement(driver, select_Product);
			select_Product.click(); // Selecting product from auto suggest
			Utility.logWithScreenShot("Search Result ");
		} catch (Exception e) {
			Assert.fail("Search process failed");
		}
		return this;
	}

	@FindBy(xpath = "//*[@resource-id='icp-btn-close-announce']")
	private WebElement button_CancelLanguage;

	@FindBy(xpath = "//*[@resource-id=\"com.amazon.mShop.android.shopping:id/action_bar_home_logo\"]")
	private WebElement logo_Amazon;

	@FindBy(xpath = "//*[@resource-id=\"com.amazon.mShop.android.shopping:id/rs_search_src_text\"]")
	private WebElement search_Box;

	@FindBy(xpath = "//*[@resource-id=\"com.amazon.mShop.android.shopping:id/iss_search_dropdown_item_query_builder\"]")
	private WebElement button_Search;

	@FindBy(xpath = "//android.view.View[@resource-id=\"title_feature_div\"]")
	private WebElement select_Product;
}
