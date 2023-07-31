package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage 
{
WebDriver driver;
ElementUtil eleUtil;
private By emailId=By.id("input-email");
private By password=By.id("input-password");
private By btn_Login=By.xpath("//input[@value='Login']");
private By btn_ForgotPassword=By.xpath("//a[text()='Forgotten Password']");

public  LoginPage(WebDriver driver)
{
	this.driver=driver;
	eleUtil=new ElementUtil(driver);
}
@Step("used to get title of page")
public String getLoginPageTitle()
{
	String title=eleUtil.waitForTitleIsAndFetch(AppConstants.DEFAULT_TIMEOUT,AppConstants.LOGIN_TITLE);
	return title;
}
@Step("used to get url")
public String getCurrentUrl()
{
	String url=eleUtil.waitForURLContainsAndFetch(AppConstants.DEFAULT_TIMEOUT,AppConstants.LOGIN_URL_FRACTION);
	System.out.println(url);
	return url;
}
@Step("Forgot password present or not")
public Boolean isForgotLinkExist()
{
	Boolean value=eleUtil.doElementIsDisplayed(btn_ForgotPassword);
	return value;
}
@Step("login using username:{0} and password:{1}")
public AccountsPage login(String Username1,String password1)
{
	eleUtil.waitForElementPresence(emailId,AppConstants.DEFAULT_TIMEOUT).sendKeys(Username1);
	eleUtil.doSendKeys(password, password1);
	eleUtil.doClick(btn_Login);
	return new AccountsPage(driver);
}

}
