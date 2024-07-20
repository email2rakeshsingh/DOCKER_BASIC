package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class RegisterPage {

    private WebDriver driver;
    private ElementUtil eleUtil;

    private By firstName = By.id("input-firstname");
    private By lastName = By.id("input-lastname");
    private By email = By.id("input-email");
    private By telephone = By.id("input-telephone");

    private By password = By.id("input-password");
    private By passwordConfirm = By.id("input-confirm");

    private By subscribeYes = By.xpath("(//input[@name='newsletter'])[1]");
    private By subscribeNo = By.xpath("(//input[@name='newsletter'])[2]");

    private By privacyPolicy = By.xpath("//input[@name='agree']");
    private By submitButton = By.xpath("//input[@value='Continue']");
    private By successMessage = By.cssSelector("div#content h1");

    private By logoutLink = By.xpath("//a[@class='list-group-item'][normalize-space()='Logout']");
    private By registerLink = By.xpath("//a[@class='list-group-item'][normalize-space()='Register']");

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        eleUtil = new ElementUtil(this.driver);
    }

    public boolean registerUser(String firstName, String lastName, String email, String telephone,
                                String password, String subscribe) {

        eleUtil.doSendKeys(this.firstName, firstName);
        eleUtil.doSendKeys(this.lastName, lastName);
        eleUtil.doSendKeys(this.email, email);
        eleUtil.doSendKeys(this.telephone, telephone);
        eleUtil.doSendKeys(this.password, password);
        eleUtil.doSendKeys(this.passwordConfirm, password);

        if (subscribe.equalsIgnoreCase("yes")) {
            eleUtil.doClick(subscribeYes);
        } else {
            eleUtil.doClick(subscribeNo);
        }

        eleUtil.doClick(privacyPolicy);
        eleUtil.doClick(submitButton);

        String successMsg = eleUtil.waitForElementVisible(successMessage, Constants.DEFAULT_ELEMENT_WAIT_OUT).getText();
        System.out.println(successMsg);

        if (successMsg.contains(Constants.REGISTER_SUCCESS_MESSAGE)) {
            eleUtil.doClick(logoutLink);
            eleUtil.doClick(registerLink);
            return true;
        } else {
            return false;
        }
    }
}
