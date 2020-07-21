package test;

import org.testng.annotations.Test;

import commonFunctions.CapInitialise;
import commonFunctions.FileInput;
import pagesObjects.CheckoutPage;
import pagesObjects.HomePage;
import pagesObjects.LoginPage;
import pagesObjects.ProductPage;
import pagesObjects.SearchPage;
import pagesObjects.SignInPage;;

public class AmazonAPKTest extends CapInitialise {

	FileInput files = new FileInput();

	@Test
	public void testCompare() throws Exception {

		SignInPage sign = new SignInPage();
		sign.verifySignInPage();
		sign.clickSignIn();

		// Login
		LoginPage login = new LoginPage();
		login.verifyLogInPage();
		login.logInUser();

		// search keyword
		HomePage hp = new HomePage();
		hp.verifyHomePage();
		hp.EnterKeyword_SearchItem();

		SearchPage objSearch = new SearchPage();
		objSearch.verifySearchResultPage();
		String expectedItemName = objSearch.getItemName();

		ProductPage pd = new ProductPage();
		pd.verifyProductDetailsPage();
		pd.clickBuyNow();

		CheckoutPage objCheck = new CheckoutPage();
		objCheck.verifyCheckOutPage();
		objCheck.clickNetBankingRadioButton();
		objCheck.selectBank();
		objCheck.clickContinueButton();

		// Comparing the item name
		String actualItemName = objCheck.getItemNameText();
		objCheck.compareItemNames(actualItemName, expectedItemName);
	}
}
