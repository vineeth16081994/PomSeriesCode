package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class AccountsPage {
WebDriver driver;
ElementUtil eleUtil;
private By lnk_logout=By.xpath("//a[text()='Logout' and @class='list-group-item']");
private By headerList=By.cssSelector("div#content h2");
private By searchIcon = By.cssSelector("#search button");
private By search = By.name("search");

	
   public AccountsPage(WebDriver driver) 
	{
		this.driver=driver;
		eleUtil=new ElementUtil(driver);
	
	}
	
	public String getTitle()
	{
		System.out.println("title");
		return eleUtil.waitForTitleIsAndFetch(AppConstants.DEFAULT_TIMEOUT,AppConstants.Account_TITLE);
	}
	
	public boolean verifyLogoutLink()
	{
		
		return eleUtil.doElementIsDisplayed(lnk_logout);
	}
	
	public String getCurrentUrl1()
	{
		
		return eleUtil.waitForURLContainsAndFetch(AppConstants.DEFAULT_TIMEOUT,"index.php?route=account/account");
	}
    public List<String> getAccountPageHeaderList()
    {
    	
    	List<WebElement>accHeaderList=eleUtil.waitForElementsVisible(headerList, AppConstants.DEFAULT_TIMEOUT);
    	List<String>accHeaderListValues=new ArrayList<String>();
    	for(WebElement e:accHeaderList)
    	{
    		accHeaderListValues.add(e.getText());
    	}
    	return accHeaderListValues;
    	
    }
    public boolean isSearchExist() {
		return eleUtil.waitForElementVisible(search, AppConstants.DEFAULT_TIMEOUT).isDisplayed();
	}

public SearchPage performSearch(String searchKey) {
		
		if(isSearchExist()) {
			eleUtil.doSendKeys(search, searchKey);
			eleUtil.doClick(searchIcon);
			return new SearchPage(driver);
		}
		else {
			System.out.println("Search field is not present on the page....");
			return null;
		}
}
}
