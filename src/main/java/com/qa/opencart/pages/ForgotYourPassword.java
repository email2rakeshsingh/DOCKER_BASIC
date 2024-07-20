package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ForgotYourPassword {

    private WebDriver driver;

    private By headerOfPage = By.xpath("//h1[normalize-space()='Forgot Your Password?']");
    private By urlOfPage = By.xpath("?route=account/forgotten");
    private By forgottenLink = By.xpath("//a[@class='list-group-item'][normalize-space()='Forgotten Password']");

    public ForgotYourPassword(WebDriver driver) {
        this.driver = driver;
    }

    public String getHeaderOfPage() {
        return driver.findElement(headerOfPage).getText();
    }

    public String getCurrentURL() {
        String pageURL = driver.getCurrentUrl();
        System.out.println("PAGE URL: " + pageURL);
        return pageURL;
    }
    
    public boolean isForgottenLinkDisplayed() {
        return driver.findElement(forgottenLink).isDisplayed();
    }

    public ForgotYourPassword clickForgottenLink() {
        driver.findElement(forgottenLink).click();
        return new ForgotYourPassword(driver);
    }
}
