package com.qa.opencart.BaseTest;

import java.util.Properties;

import org.openqa.selenium.WebDriver;

import org.testng.annotations.AfterTest;

import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.SearchPage;

public class BaseTest 
{
DriverFactory df;	
WebDriver driver;
protected LoginPage login;
protected AccountsPage account;
protected SearchPage search;
protected ProductInfoPage productinfo;
protected Properties prop;
protected SoftAssert softAssert;

@BeforeTest
public void setup()
{
	df=new DriverFactory();
	prop=df.initProp();
	driver=df.initializeDriver(prop);
	login=new LoginPage(driver);
	softAssert=new SoftAssert();
	
}


@AfterTest
public void tearDown()
{
	driver.quit();
	
}
}
