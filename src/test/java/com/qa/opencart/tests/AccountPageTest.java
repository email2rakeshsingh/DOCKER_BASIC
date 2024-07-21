package com.qa.opencart.tests;

import java.util.List;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.qa.opencart.base.BaseTest;
import com.qa.opencart.pages.CommonsPage;
import com.qa.opencart.utils.Constants;

public class AccountPageTest extends BaseTest {

    @BeforeClass
    public void accountSetup() {
        String userName = prop.getProperty("userName");
        String password = prop.getProperty("password");

        if (userName == null || password == null) {
            throw new NullPointerException("The 'userName' or 'password' property is missing in the properties file.");
        }

        accPage = login.doLogin(userName.trim(), password.trim());
    }

    @Test
    public void accountTitleTest() {
        Assert.assertEquals(accPage.getAccountPageTitle(), Constants.ACCOUNT_PAGE_TITLE);
    }

    @Test
    public void getPageUrlTest() {
        Assert.assertTrue(accPage.getPageAccUrl().contains(Constants.ACCOUNT_PAGE_URLS));
    }

    @Test
    public void homePageHeader() {
        String homePageHeader = accPage.getHomePageHeader();
        System.out.println("Page header is :" + homePageHeader);
        Assert.assertEquals(homePageHeader, Constants.ACCOUNT_PAGE_HEADER);
    }

    @Test
    public void searchBoxPresent() {
        Assert.assertTrue(accPage.isSearchBoxPresent());
    }

    @Test
    public void addToCartButtonPresent() {
        Assert.assertTrue(accPage.isAddToCartButtonPresent());
    }

    @Test
    public void getContentMainHeaderList() {
        List<String> headerList = accPage.getContentMainHeaderList();
        System.out.println("Actual Header list :" + headerList);
        Assert.assertEquals(headerList, Constants.EXPECTED_SECTION_LIST);
    }

    @Test
    public void footerLinkAllList() {
        List<String> footerList = accPage.getFooterLinkAllList();
        System.out.println("Actual footer list :" + footerList);
        Assert.assertEquals(footerList, Constants.FOOTER_SECTION_LIST);
    }

    @Test
    public void logoutLinkPresent() {
        Assert.assertTrue(accPage.isLogoutLinkPresent());
    }

    @Test
    public void myAccountPresent() {
        Assert.assertTrue(accPage.isMyAccountLinkPresent());
    }

    @Test
    public void userLogOutPage() {
        login = accPage.clickOnLogoutLink();
        Assert.assertEquals(login.logOutMessageDisplayed(), Constants.USER_LOGOUT_MESSGE);
    }

//    @Test
//    public void doSearch() {
//        commPage = new CommonsPage(driver);
//        searchresultpage = commPage.doSearch("MacBook");
//        String searchResultPageHeader = searchresultpage.isSearchResultHeader();
//        Assert.assertTrue(searchResultPageHeader.contains("MacBook"));
//    }
}
