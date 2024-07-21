package com.qa.opencart.base;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.CommonsPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.RegisterPage;
import com.qa.opencart.pages.SearchResultPage;

/**
 * BaseTest class sets up the WebDriver instance and test environment.
 */
public class BaseTest {

    private static final String SENDER = "email2rakeshsingh@gmail.com";
    private static final String RECIPIENT = "email2rakeshsingh@gmail.com";
    private static final String SUBJECT = "Open Cart Test Execution Report " + LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
    private static final String BODY_TEXT = "Please click on this latest Test Execution Report link:";

    private DriverFactory df;
    protected WebDriver driver;
    protected Properties prop;

    protected LoginPage login;
    protected AccountsPage accPage;
    protected CommonsPage commPage;
    protected SearchResultPage searchResultsPage;
    protected ProductInfoPage productinfoPage;
    protected RegisterPage regPage;

    protected SoftAssert softAssert;

    /**
     * Setup method initializes WebDriver and other resources before each test.
     * 
     * @param browser the browser to use for testing
     * @param browserversion the version of the browser to use
     */
    @Parameters({ "browser", "browserversion" })
    @BeforeTest
    public void setup(String browser, String browserversion) {
        df = new DriverFactory();
        prop = df.initProperties();

        if (browser != null) {
            prop.setProperty("browser", browser);
            prop.setProperty("browserversion", browserversion);
        }

        driver = df.initDriver(prop);
        login = new LoginPage(driver);
        accPage = new AccountsPage(driver);
        commPage = new CommonsPage(driver);
        searchResultsPage = new SearchResultPage(driver);
        productinfoPage = new ProductInfoPage(driver);
        regPage = new RegisterPage(driver);
        softAssert = new SoftAssert();
    }

    /**
     * Teardown method cleans up WebDriver and other resources after each test.
     */
    @AfterTest
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
