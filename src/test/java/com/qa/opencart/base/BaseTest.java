package com.qa.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.CommonsPage;
import com.qa.opencart.pages.ForgotYourPassword;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.RegisterPage;
import com.qa.opencart.pages.SearchResultPage;

public class BaseTest {

	DriverFactory df;
	protected Properties prop;
	public WebDriver driver;

	protected LoginPage loginPage;
	protected AccountsPage accPage;
	protected ForgotYourPassword forgotPage;;
	public CommonsPage commPage;
	protected SearchResultPage searchresultpage;
	protected ProductInfoPage productInfopage;
	protected SoftAssert softAssert;
	protected RegisterPage registerPage;

	@BeforeTest
	public void setup() {
		df = new DriverFactory();
		prop = df.init_prop();
		driver = df.init_driver(prop);
		loginPage = new LoginPage(driver);
		softAssert = new SoftAssert();
	}

//	@AfterClass
//	public void tearDown() {
//		driver.quit();
//	}
}
