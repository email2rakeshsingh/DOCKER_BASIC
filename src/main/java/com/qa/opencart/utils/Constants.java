package com.qa.opencart.utils;

import java.util.Arrays;
import java.util.List;

public class Constants {

	public static final String LOGIN_PAGE_TITLE = "My Account";
	public static final String LOGIN_PAGE_URLS_FRACTION = "route=account/login";
	public static final String ACCOUNT_PAGE_TITLE = "My Account";
	public static final String ACCOUNT_PAGE_URLS = "route=account/account";
	public static final String ACCOUNT_PAGE_HEADER = "";
	public static final List<String> EXPECTED_SECTION_LIST = Arrays.asList("My Account", "My Orders",
			"My Affiliate Account", "Newsletter");

	public static final List<String> FOOTER_SECTION_LIST = Arrays.asList("About Us", "Delivery Information",
			"Privacy Policy", "Terms & Conditions", "Contact Us", "Returns", "Site Map", "Brands", "Gift Certificates",
			"Affiliate", "Specials", "My Account", "Order History", "Wish List", "Newsletter");

	public static final int DEFAULT_ELEMENT_WAIT_OUT = 15;
	public static final int DEFAULT_TIME_OUT = 5;
	public static final String USER_LOGOUT_MESSGE = "Account Logout";
	public static final String  REGISTER_SUCCESS_MESSAGE = "Your Account Has Been Created!";
	public static final String REGISTER_SHEET_NAME = "register";
	

}
