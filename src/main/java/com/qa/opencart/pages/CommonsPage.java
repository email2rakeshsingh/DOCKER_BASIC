package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class CommonsPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	private By searchBox = By.xpath("//input[@placeholder='Search']");
	private By searchIcon = By.xpath("//i[@class='fa fa-search']");

	public CommonsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(this.driver);
	}

	public SearchResultPage doSearch(String productName) {
		WebElement searchEle = eleUtil.waitForElementVisible(searchBox, Constants.DEFAULT_ELEMENT_WAIT_OUT);
		searchEle.clear();
		searchEle.sendKeys(productName);
		eleUtil.doActionsClick(searchIcon);
		return new SearchResultPage(driver);
	}
}
