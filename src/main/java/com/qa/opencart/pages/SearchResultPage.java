package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class SearchResultPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	private By searchResultHeader = By.cssSelector("div[id='content'] h1");

	/**
	 * @param driver
	 */
	public SearchResultPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(this.driver);
	}

	public String isSearchResultHeader() {
		return eleUtil.doGetElementText(searchResultHeader);
	}

	public ProductInfoPage selectProductName(String mainProductName) {
		WebElement mainProductEle = eleUtil.waitForElementVisible(By.linkText(mainProductName),
				Constants.DEFAULT_ELEMENT_WAIT_OUT);

		mainProductEle.click();
		
		return new ProductInfoPage(this.driver);

	}

}
