package com.qa.opencart.tests;

import org.testng.Assert;


import org.testng.annotations.Test;
import com.qa.opencart.base.BaseTest;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.utils.Constants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import io.qameta.allure.Story;

@Epic("User Management")
@Feature("Login")
public class LoginPageTest extends BaseTest {
	@Story("Valid User Login")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Test to verify that a valid user can log in successfully")
	@Test
	public void verifyLoginPageTitle() {
		String actualTitle = loginPage.getPageTitle();
		Assert.assertEquals(actualTitle, Constants.LOGIN_PAGE_TITLE, "Login page title doesn't match expected.");
	}
  
	@Test
	public void verifyFailTest() {
		 loginPage.getPageTitle();
		String actualTitle = "Rakesh";
		Assert.assertEquals(actualTitle, "Opencart", "Login page title doesn't match expected.");
	}

	@Test
	public void verifyLoginPageUrl() {
		String pageUrl = loginPage.getPageUrl();
		Assert.assertTrue(pageUrl.contains(Constants.LOGIN_PAGE_URLS_FRACTION),
				"Current URL doesn't contain expected URL fraction.");
	}

	@Test
	public void verifySearchBoxIsPresent() {
		Assert.assertTrue(loginPage.isSearchBoxPresent(), "Search box is not present on the login page.");
	}

	@Test
	public void verifyLoginLinkIsPresent() {
		Assert.assertTrue(loginPage.isLoginLinkPresent(), "Login link is not present on the login page.");
	}

	@Test
	public void verifyEmailAddressFieldIsPresent() {
		Assert.assertTrue(loginPage.isEmailAddressPresent(), "Email address field is not present on the login page.");
	}

	@Test
	
	public void verifyRegisterFieldIsPresent() {
		Assert.assertTrue(loginPage.isRagisterLinkPresent(), "Password field is not present on the login page.");
	}

	@Test
	public void verifyPasswordFieldIsPresent() {
		Assert.assertTrue(loginPage.isPasswordPresent(), "Password field is not present on the login page.");
	}

	@Test
	public void verifyReturningCustomerTextIsPresent() {
		Assert.assertTrue(loginPage.isReturningCustomerTextPresent(),
				"Returning customer text is not present on the login page.");
	}

	@Test
	@Step("Enter Credentials: {username} / {password}")
	public void verifySuccessfulLogin() {
		// Assuming 'userName' and 'password' are properties fetched from a
		// configuration
		String userName = prop.getProperty("userName").trim();
		String password = prop.getProperty("password").trim();

		// Perform login and get the AccountsPage object
		AccountsPage accountsPage = loginPage.doLogin(userName, password);

		// Verify Account Page title
		String accountPageTitle = accountsPage.getAccountPageTitle();
		System.out.println("Account Page Title: " + accountPageTitle);

		// Assertions
		Assert.assertEquals(accountPageTitle, Constants.ACCOUNT_PAGE_TITLE,
				"Account page title doesn't match expected.");
		Assert.assertTrue(accountsPage.isLogoutLinkPresent(), "Logout link is not present after successful login.");
		Assert.assertTrue(accountsPage.isMyAccountLinkPresent(),
				"My Account section is not present after successful login.");
	}
}
