package com.qa.opencart.tests;

import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ExcelUtil;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;


@Epic("User Management")
@Feature("Registration")
public class RegisterTest extends BaseTest {

	@BeforeClass
	 @Story("Valid User Registration")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Test to verify that a valid user can register successfully")
	public void regSetup() {
		regPage = login.navigateToRegisterPage();

	}

	public String getRandomEmailID() {
		Random random = new Random();
		String email = "may2024" + random.nextInt(1000) + "@gmail.com";
		System.out.println("Generated Email ID: " + email); // Display email in console
		return email;
	}

	@DataProvider(name = "getRegisterTestData")
	public Object[][] getRegisterTestData() {
		return ExcelUtil.getTestData(Constants.REGISTER_SHEET_NAME);
	}

	@Test(dataProvider = "getRegisterTestData")
	public void userRegisterTest(String firstName, String lastName, String telephone, String password,
			String subscribe) {
		String email = getRandomEmailID(); // Get random email ID
		// Assuming registerPage is initialized somewhere in your test setup
		// and registerUser method returns a boolean indicating success
		Assert.assertTrue(regPage.registerUser(firstName, lastName, email, telephone, password, subscribe));
	}
}
