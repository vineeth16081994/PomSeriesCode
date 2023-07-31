package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.BaseTest.BaseTest;
import com.qa.opencart.constants.AppConstants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Login Epic")
@Story("Login functionality verfication")
public class LoginPageTest extends BaseTest
{
@Severity(SeverityLevel.TRIVIAL)
@Description("verifying title of the page")
@Test
public void verifyLoginTitle()
{
	String title=login.getLoginPageTitle();
	Assert.assertEquals(title,AppConstants.LOGIN_TITLE);
}
@Severity(SeverityLevel.TRIVIAL)
@Description("verifying url of the page")
@Test()
public void verifyUrl()
{
	String url=login.getCurrentUrl();
	System.out.println(url);
	Assert.assertTrue(url.contains(AppConstants.LOGIN_URL_FRACTION));
	
}
@Severity(SeverityLevel.CRITICAL)
@Description("verifying title is dispalyed")
@Test
public void forgotPasswordLinkIsDisplayed()
{
	
	Assert.assertTrue(login.isForgotLinkExist());
}
@Severity(SeverityLevel.CRITICAL)
@Description("verifying login with username password")
@Test(priority = 1)
public void verifyLogin()
{
	account=login.login(prop.getProperty("username"),prop.getProperty("password"));
	Assert.assertTrue(account.verifyLogoutLink());
}
}
