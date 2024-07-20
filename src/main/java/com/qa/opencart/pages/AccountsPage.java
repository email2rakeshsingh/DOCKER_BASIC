package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class AccountsPage {

    private WebDriver driver;
    private ElementUtil eleUtil;

    private By homePageHeader = By.xpath("//img[@title='naveenopencart']");
    private By searchBoxPresent = By.xpath("//input[@placeholder='Search']");
    private By addToCartButton = By.xpath("//button[@class='btn btn-inverse btn-block btn-lg dropdown-toggle']");
    private By contentMainHeader = By.cssSelector("div#content h2");
    private By footerLinkAll = By.xpath("//footer//div[@class='container']//li");
    private By logoutLink = By.linkText("Logout");
    private By myAccountLink = By.xpath("//h2[normalize-space()='My Account']");

    /**
     * Constructor for AccountsPage class.
     *
     * @param driver WebDriver instance to initialize the page.
     */
    public AccountsPage(WebDriver driver) {
        this.driver = driver;
        eleUtil = new ElementUtil(this.driver);
    }

    /**
     * Method to get the title of the current page.
     *
     * @return The title of the current page.
     */
    public String getAccountPageTitle() {
        return eleUtil.waitForTitleIs(Constants.ACCOUNT_PAGE_TITLE, Constants.DEFAULT_TIME_OUT);
    }

    /**
     * Method to get the current URL of the page.
     *
     * @return The current URL of the page.
     */
    public String getPageAccUrl() {
        return eleUtil.waitForUrlContains(Constants.ACCOUNT_PAGE_URLS, Constants.DEFAULT_TIME_OUT);
    }

    /**
     * Method to get the text of the home page header.
     *
     * @return The text of the home page header.
     */
    public String getHomePageHeader() {
        return eleUtil.waitForElementVisible(homePageHeader, Constants.DEFAULT_ELEMENT_WAIT_OUT).getText();
    }

    /**
     * Method to check if the search box is present on the page.
     *
     * @return True if search box is present, false otherwise.
     */
    public boolean isSearchBoxPresent() {
        return eleUtil.waitForElementVisible(searchBoxPresent, Constants.DEFAULT_TIME_OUT).isDisplayed();
    }

    /**
     * Method to check if the Add to Cart button is present on the page.
     *
     * @return True if Add to Cart button is present, false otherwise.
     */
    public boolean isAddToCartButtonPresent() {
        return eleUtil.waitForElementVisible(addToCartButton, Constants.DEFAULT_TIME_OUT).isDisplayed();
    }

    /**
     * Method to retrieve a list of text from content main headers.
     *
     * @return List of strings containing text from content main headers.
     */
    public List<String> getContentMainHeaderList() {
        List<WebElement> accSecList = eleUtil.waitForElementsVisible(contentMainHeader,
                Constants.DEFAULT_ELEMENT_WAIT_OUT);

        List<String> headerTexts = new ArrayList<>();
        for (WebElement e : accSecList) {
            String text = e.getText().trim();
            headerTexts.add(text);
        }
        return headerTexts;
    }

    /**
     * Method to retrieve a list of text from footer links.
     *
     * @return List of strings containing text from footer links.
     */
    public List<String> getFooterLinkAllList() {
        List<WebElement> footerLinks = eleUtil.waitForElementsVisible(footerLinkAll, Constants.DEFAULT_ELEMENT_WAIT_OUT);
        List<String> footerTexts = new ArrayList<>();
        for (WebElement e : footerLinks) {
            String linkText = e.getText().trim();
            footerTexts.add(linkText);
        }
        return footerTexts;
    }

    public boolean isLogoutLinkPresent() {
        return eleUtil.waitForElementVisible(logoutLink, Constants.DEFAULT_TIME_OUT).isDisplayed();
    }

    public LoginPage clickOnLogoutLink() {
        if (isLogoutLinkPresent()) {
            eleUtil.doClick(logoutLink);
            return new LoginPage(driver);
        }
        return null;
    }

    public boolean isMyAccountLinkPresent() {
        return eleUtil.waitForElementVisible(myAccountLink, Constants.DEFAULT_TIME_OUT).isDisplayed();
    }

    public void clickOnMyAccountLink() {
        if (isMyAccountLinkPresent()) {
            eleUtil.doClick(myAccountLink);
        }
    }

	
}
