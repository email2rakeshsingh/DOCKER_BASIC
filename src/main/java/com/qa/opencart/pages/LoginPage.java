package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	// Locators
	private By searchBox = By.xpath("//input[@placeholder='Search']");
	private By searchButton = By.xpath("//i[@class='fa-solid fa-magnifying-glass']");
	private By loginLink = By.linkText("Login");
	private By emailAddress = By.id("input-email");
	private By password = By.id("input-password");
	private By loginButton = By.xpath("//input[@value='Login']");
	private By textVerify = By.xpath("//h2[text()='Returning Customer']");
	private By accLogOutMessage = By.cssSelector("div#content h1");
	private By registerLink = By.xpath("//a[@class='list-group-item'][normalize-space()='Register']");



	/**
	 * Constructor to initialize the driver
	 * 
	 * @param driver WebDriver instance
	 */
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(this.driver);
	}

	/**
	 * Get the page title
	 * 
	 * @return the title of the login page
	 */
	public String getPageTitle() {
		String pageTitle = eleUtil.waitForTitleIs(Constants.LOGIN_PAGE_TITLE, Constants.DEFAULT_TIME_OUT);
		System.out.println("Login page title is: " + pageTitle);
		return pageTitle;
	}

	/**
	 * Get the current page URL
	 * 
	 * @return the URL of the current page
	 */
	public String getPageUrl() {
		String pageUrl = eleUtil.waitForUrlContains(Constants.LOGIN_PAGE_URLS_FRACTION, Constants.DEFAULT_TIME_OUT);
		System.out.println("Current page URL is: " + pageUrl);
		return pageUrl;
	}

	/**
	 * Check if the search box is present
	 * 
	 * @return true if the search box is displayed, false otherwise
	 */
	public boolean isSearchBoxPresent() {
		return eleUtil.waitForElementVisible(searchBox, Constants.DEFAULT_ELEMENT_WAIT_OUT).isDisplayed();
	}

	/**
	 * Check if the search button is present
	 * 
	 * @return true if the search button is displayed, false otherwise
	 */
	public boolean isSearchButtonPresent() {
		return eleUtil.waitForElementVisible(searchButton, Constants.DEFAULT_ELEMENT_WAIT_OUT).isDisplayed();
	}

	/**
	 * Check if the login link is present
	 * 
	 * @return true if the login link is displayed, false otherwise
	 */
	@Step("RegisterFieldI Login Form")
	public boolean isLoginLinkPresent() {
		return eleUtil.waitForElementVisible(loginLink, Constants.DEFAULT_ELEMENT_WAIT_OUT).isDisplayed();
	}

	/**
	 * Check if the email address field is present
	 * 
	 * @return true if the email address field is displayed, false otherwise
	 */
	public boolean isEmailAddressPresent() {
		return eleUtil.waitForElementVisible(emailAddress, Constants.DEFAULT_ELEMENT_WAIT_OUT).isDisplayed();
	}

	/**
	 * Check if the password field is present
	 * 
	 * @return true if the password field is displayed, false otherwise
	 */
	public boolean isPasswordPresent() {
		return eleUtil.waitForElementVisible(password, Constants.DEFAULT_ELEMENT_WAIT_OUT).isDisplayed();
	}

	/**
	 * Check if the returning customer text is present
	 * 
	 * @return true if the returning customer text is displayed, false otherwise
	 */
	public boolean isReturningCustomerTextPresent() {
		return eleUtil.waitForElementVisible(textVerify, Constants.DEFAULT_ELEMENT_WAIT_OUT).isDisplayed();
	}

	/**
	 * Perform login action with the given username and password
	 * 
	 * @param username The username to enter
	 * @param pwd      The password to enter
	 * @return AccountsPage object after successful login
	 */
	public AccountsPage doLogin(String username, String pwd) {
		eleUtil.waitForElementVisible(emailAddress, Constants.DEFAULT_ELEMENT_WAIT_OUT).sendKeys(username);
		eleUtil.waitForElementVisible(password, Constants.DEFAULT_ELEMENT_WAIT_OUT).sendKeys(pwd);
		eleUtil.doClick(loginButton);

		return new AccountsPage(driver);
	}

	/**
	 * Perform login action and navigate to ForgotYourPassword page
	 * 
	 * @param username The username to enter
	 * @param pwd      The password to enter
	 * @return ForgotYourPassword object after login attempt
	 */
	@Step("Open Login Page")
	public ForgotYourPassword fdoLogin(String username, String pwd) {
		eleUtil.waitForElementVisible(emailAddress, Constants.DEFAULT_ELEMENT_WAIT_OUT).sendKeys(username);
		eleUtil.waitForElementVisible(password, Constants.DEFAULT_ELEMENT_WAIT_OUT).sendKeys(pwd);
		eleUtil.doClick(loginButton);

		return new ForgotYourPassword(driver);
	}

	/**
	 * Check if logout message is displayed
	 * 
	 * @return Logout message displayed as String
	 */
	public String logOutMessageDisplayed() {
		String logOutMessage = eleUtil.waitForElementVisible(accLogOutMessage, Constants.DEFAULT_ELEMENT_WAIT_OUT)
				.getText();
		System.out.println("Logout Message is displayed: " + logOutMessage);
		return logOutMessage;
	}

	public boolean isRagisterLinkPresent() {
		return eleUtil.waitForElementVisible(registerLink, Constants.DEFAULT_ELEMENT_WAIT_OUT).isDisplayed();
	}

	public RegisterPage navigateToRegisterPage() {
		eleUtil.doClick(registerLink);
		return new RegisterPage(driver);
	}
}
