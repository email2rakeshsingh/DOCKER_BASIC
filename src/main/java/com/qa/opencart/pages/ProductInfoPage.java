package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class ProductInfoPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	private By mainProductName = By.cssSelector("div#content h1");
	private By productImages = By.cssSelector("ul.thumbnails li");
	private By productDescPage = By.cssSelector("div[id='tab-description'] div");
	private By productMetaData = By.xpath("(//div[@class='col-sm-4']//ul[@class='list-unstyled'])[position()=1]/li");
	private By productPriceData = By.xpath("(//div[@class='col-sm-4']//ul[@class='list-unstyled'])[position()=2]/li");

	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(this.driver);
	}

	public String getmainProductName() {
		return eleUtil.doGetElementText(mainProductName);
	}

	public int getProductsImagesCount() {
		return eleUtil.waitForElementsVisible(productImages, Constants.DEFAULT_ELEMENT_WAIT_OUT).size();

	}

	public String getProductDec() {
		return eleUtil.doGetElementText(productDescPage);
	}

	public Map<String, String> getProductInfo() {

		Map<String, String> productInfoMap = new HashMap<String, String>();
		productInfoMap.put("name", getmainProductName());

		List<WebElement> metsList = eleUtil.getElements(productMetaData);

		for (WebElement e : metsList) {
			String metaData = e.getText();
			String metakey = metaData.split(":")[0].trim();
			String metaVal = metaData.split(":")[1].trim();

			productInfoMap.put(metakey, metaVal);

		}
		List<WebElement> metaPriceList = eleUtil.getElements(productMetaData);
		String price = metaPriceList.get(0).getText().trim();
		String exTax = metaPriceList.get(1).getText().trim();
		productInfoMap.put("price", price);
		productInfoMap.put("exTax", exTax);
		
		return productInfoMap;

	}

}
