package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.pages.CommonsPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.SearchResultPage;
import com.qa.opencart.utils.PageDescpText;

import net.bytebuddy.asm.Advice.OffsetMapping.Sort;

public class SearchResultPageTest extends BaseTest {

	@DataProvider
	public Object[][] getProductName() {
		return new Object[][] { { "MacBook" }, { "Imac" }, { "Apple" }, { "samsung" }

		};

	}

	@Test(dataProvider = "getProductName")
	public void doSearch(String productName) {
		commPage = new CommonsPage(driver);
		searchResultsPage = commPage.doSearch(productName);
		String searchResultPageHeader = searchResultsPage.isSearchResultHeader();
		Assert.assertTrue(searchResultPageHeader.contains(productName));
	}

	@DataProvider(name = "getMainProductName")
	public Object[][] getMainProductName() {
		return new Object[][] { { "MacBook", "MacBook Pro" }, { "iMac", "iMac" }, { "Apple", "Apple Cinema 30\"" },
				{ "Samsung", "Samsung SyncMaster 941BW" } };
	}

	@Test(dataProvider = "getMainProductName")
	public void productInfoTest(String productName, String mainProductName) {
		// Initialize necessary pages
		CommonsPage commPage = new CommonsPage(driver);

		// Perform search
		SearchResultPage searchResultPage = commPage.doSearch(productName);
		String searchResultPageHeader = searchResultPage.isSearchResultHeader();

		// Select the product from search results
		ProductInfoPage productInfoPage = searchResultPage.selectProductName(mainProductName);

		// Get the main product name from the product info page
		String mainProductValue = productInfoPage.getmainProductName();

		// Print and assert the main product name
		System.out.println("Expected Main Product Name: " + mainProductName);
		System.out.println("Actual Main Product Name: " + mainProductValue);
		Assert.assertEquals(mainProductValue, mainProductName);
	}

	@DataProvider
	public Object[][] getProductImagesName() {
		return new Object[][] { { "MacBook", "MacBook Pro", 4 }, { "iMac", "iMac", 3 },
				{ "Apple", "Apple Cinema 30\"", 6 }, { "Samsung", "Samsung SyncMaster 941BW", 1 } };
	}

	@Test(dataProvider = "getProductImagesName")
	public void imagesCountTest(String searchKey, String productName, int productCount) {

		commPage = new CommonsPage(driver);
		productinfoPage = new ProductInfoPage(driver);
		searchResultsPage = commPage.doSearch(searchKey);
		productinfoPage = searchResultsPage.selectProductName(productName);
		Assert.assertEquals(productinfoPage.getProductsImagesCount(), productCount);
	}

	@Test
	public void ProductDesc() {

		commPage = new CommonsPage(driver);
		productinfoPage = new ProductInfoPage(driver);
		searchResultsPage = commPage.doSearch("MacBook");
		productinfoPage = searchResultsPage.selectProductName("MacBook Air");
		String productDescrip = productinfoPage.getProductDec();
		System.out.println("Product descrip :" + productDescrip);

		softAssert.assertTrue(productDescrip != null);
		softAssert.assertFalse(productDescrip.isEmpty());
		softAssert.assertTrue(productDescrip.contains("MacBook Air"));
		softAssert.assertTrue(productDescrip.contains("MacBook Air"));
		softAssert.assertTrue(productDescrip.contains(PageDescpText.MACBOOK_DECSCIPTION_AIR));
		softAssert.assertAll();

	}

	@Test
	public void productDtaTest() {
		commPage = new CommonsPage(driver);
		productinfoPage = new ProductInfoPage(driver);
		searchResultsPage = commPage.doSearch("MacBook");
		productinfoPage = searchResultsPage.selectProductName("MacBook Air");
		Map<String, String> actProductInfoMap = productinfoPage.getProductInfo();
		actProductInfoMap.forEach((k, v) -> System.out.println(k + ":" + v));

		softAssert.assertEquals(actProductInfoMap.get("Brand"), "Apple");
		softAssert.assertEquals(actProductInfoMap.get("Availability"), "Out Of Stock");
		softAssert.assertEquals(actProductInfoMap.get("name"), "MacBook Air");
		softAssert.assertEquals(actProductInfoMap.get("Reward Points"), "700");
		softAssert.assertAll();
	}

//	Brand:Apple
//	Availability:Out Of Stock
//	price:Brand: Apple
//	name:MacBook Air
//	Product Code:Product 17
//	Reward Points:700
//	exTax:Product Code: Product 17
//	PASSED: productDtaTest
}
