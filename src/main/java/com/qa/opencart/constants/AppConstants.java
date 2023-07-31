package com.qa.opencart.constants;

import java.util.Arrays;
import java.util.List;

public class AppConstants 
{
public static final int DEFAULT_TIMEOUT=10;
public static final String LOGIN_TITLE="Account Login";
public static final String Account_TITLE="My Account";
public static final String LOGIN_URL_FRACTION="ndex.php?route=account/login";
public static final String ACCOUNT_URL_FRACTION="index.php?route=account/account";
public static final int HEADER_COUNT = 4;
public static final List<String> ExpectedHeaderList= Arrays.asList("My Account","My Orders","My Affiliate Account","Newsletter");



}
