package com.qa.opencart.tests;

import java.util.ArrayList;
import com.qa.opencart.BaseTest.BaseTest;
import com.qa.opencart.constants.AppConstants;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;



public class ProductPageInfoTest extends BaseTest
{
	
	
	ArrayList<String> expProdListInCart;
	
	//5053
	
	
	
	@BeforeClass
	public void productInfoPageSetup() {
		account = login.login(prop.getProperty("username").trim(), prop.getProperty("password").trim());
	}
	
	
	@DataProvider
	public Object[][] getProductImagesTestData() {
		return new Object[][] {
			{"Macbook", "MacBook Pro", 4},
			{"iMac", "iMac", 3},
			{"Apple", "Apple Cinema 30\"", 6},
			{"Samsung", "Samsung SyncMaster 941BW", 1},
		};
	}
	
	
	@Test(dataProvider = "getProductImagesTestData")
	public void productImagesCountTest(String searchKey, String productName, int imagesCount) {
		search = account.performSearch(searchKey);
		productinfo = search.selectProduct(productName);
		int actImagesCount = productinfo.getProductImagesCount();
		Assert.assertEquals(actImagesCount, imagesCount);
	}
	
	
	
	@Test
	public void productInfoTest() {
		search = account.performSearch("MacBook");
		productinfo = search.selectProduct("MacBook Pro");
		Map<String, String> actProductInfoMap = productinfo.getProductInfo();
		softAssert.assertEquals(actProductInfoMap.get("Brand"), "Apple");
		softAssert.assertEquals(actProductInfoMap.get("Product Code"), "Product 18");
		softAssert.assertEquals(actProductInfoMap.get("productname"), "MacBook Pro");
		softAssert.assertEquals(actProductInfoMap.get("productprice"), "$2,000.00");
		
		softAssert.assertAll();
	}
	
	//assert vs verify(soft assertion)
	
	
	@DataProvider
	public Object[][] getCartTestData() {
		return new Object[][] {
			{"Macbook", "MacBook Pro", 1},
			{"iMac", "iMac", 2},
		};
	}
	
	
	//@Test(dataProvider = "getCartTestData")
	public void addtToCartTest(String searchKey, String productName, int quantity) {
		search = account.performSearch(searchKey);
		productinfo = search.selectProduct(productName);
		productinfo.enterQuantity(quantity);
		String actCartMesg = productinfo.addProductToCart();
		//Success: You have added MacBook Pro to your shopping cart!
		softAssert.assertTrue(actCartMesg.indexOf("Success")>=0);
		softAssert.assertTrue(actCartMesg.indexOf(productName)>=0);
		softAssert.assertEquals(actCartMesg, "Success: You have added "+productName+" to your shopping cart!");
		
	
		
		//new code: checking cart details as well:
	/*	viewCartPopUpPage = productinfo.openCart();
		List<String> cartProdList = viewCartPopUpPage.getProductsValueListInCart();
		
		Object[][] data = getCartTestData();
		expProdListInCart = new ArrayList<String>();
		for(int i=0; i< data.length; i++) {
			expProdListInCart.add(data[i][1].toString());
		}
		
		System.out.println(expProdListInCart);
		softAssert.assertTrue(expProdListInCart.containsAll(cartProdList));*/
		
		
		softAssert.assertAll();

	}
	

}
