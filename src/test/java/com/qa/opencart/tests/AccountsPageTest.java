package com.qa.opencart.tests;

import java.util.List;

import com.qa.opencart.BaseTest.BaseTest;
import com.qa.opencart.constants.AppConstants;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class AccountsPageTest extends BaseTest
{
@BeforeClass
public void accountPageSetup()
{
	account=login.login(prop.getProperty("username"),prop.getProperty("password"));
	
}
@Test
public void getTitle()
{
	String title=account.getTitle();
	Assert.assertEquals(title,AppConstants.Account_TITLE);
}

@Test
public void isLogoutDisplayed()
{
	Assert.assertTrue(account.verifyLogoutLink());
	
}
@Test
public void verifyCurrentUrl()
{
	String url=account.getCurrentUrl1();
	Assert.assertTrue(url.contains(AppConstants.ACCOUNT_URL_FRACTION));
}

@Test
public void getAccountHeaderListCount()
{
	List<String>HeaderList=account.getAccountPageHeaderList();
	Assert.assertEquals(HeaderList.size(),AppConstants.HEADER_COUNT);
}

@Test
public void verifyAccountList()
{
	List<String>HeaderList=account.getAccountPageHeaderList();
	Assert.assertEquals(HeaderList, AppConstants.ExpectedHeaderList);
}

@DataProvider
public Object[][] getProductData()
{
	return new Object[][]
{
		{"macbook"},
		{"iMac"},
		{"Apple"},
		{"Samsung"}
			
		
};
}
@Test(dataProvider = "getProductData",priority = 1)
public void searchCountTest(String searchKey)
{
	search=account.performSearch(searchKey);
	Assert.assertTrue(search.getSearchProductsCount()>0);
}

@DataProvider
public Object[][] getProductTestData() {
	return new Object[][] { { "Macbook", "MacBook Pro" },
	{ "Macbook", "MacBook Air" }, { "iMac", "iMac" },
	
			{ "Apple", "Apple Cinema 30\"" }, { "Samsung", "Samsung SyncMaster 941BW" },
			{ "Samsung", "Samsung Galaxy Tab 10.1" }
			};
}

@Test(dataProvider = "getProductTestData",priority =2)
public void searchProductTest(String searchkey,String productkey)
{
	search=account.performSearch(searchkey);
	productinfo=search.selectProduct(productkey);
	String headerValue=productinfo.getProductHeaderValue();
	Assert.assertEquals(headerValue,productkey);
}









}
