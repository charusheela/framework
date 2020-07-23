package tests;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import pages.CheckoutPage;
import pages.HomePage;
import pages.LoginPage;
import pages.ProductPage;
import pages.SearchPage;
import pages.SignInPage;
import utils.Base;
import utils.FileInput;;

public abstract class AmazonAPKTest extends Base {

	FileInput files = new FileInput();
	

	@BeforeTest
	public void Login() throws Exception {

		SignInPage sign = new SignInPage(driver);
		sign.verifySignInPage();
		sign.clickSignIn();

		// Login
		LoginPage login = new LoginPage(driver);
		login.verifyLogInPage();
		login.logInUser();
	}

	@Test
	public void testCompare() throws Exception {

		
		// search keyword
		HomePage hp = new HomePage(driver);
		hp.verifyHomePage();
		hp.EnterKeyword_SearchItem();

		SearchPage objSearch = new SearchPage(driver);
		objSearch.verifySearchResultPage();
		String expectedItemName = objSearch.getItemName();

		ProductPage pd = new ProductPage(driver);
		pd.verifyProductDetailsPage();
		pd.clickBuyNow();

		CheckoutPage objCheck = new CheckoutPage(driver);
		objCheck.verifyCheckOutPage();
		objCheck.clickNetBankingRadioButton();
		objCheck.selectBank();
		objCheck.clickContinueButton();

		// Comparing the item name
		String actualItemName = objCheck.getItemNameText();
		objCheck.compareItemNames(actualItemName, expectedItemName);
	}
}
