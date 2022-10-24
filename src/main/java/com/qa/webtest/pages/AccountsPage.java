package com.qa.webtest.pages;

import com.qa.webtest.utils.Constants;
import com.qa.webtest.utils.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class AccountsPage {

    public WebDriver driver;
    public ElementUtil eleUtil;

    private By header= By.xpath("//div[@id='logo']");
    private By accountsSection= By.xpath("//div[@id='content']/h2");
    private By searchfield=By.xpath("//input[@name='search']");
    private By searchButton=By.xpath("//i[@class='fa fa-search']");
    private By logoutLink=By.linkText("Logout");

    public AccountsPage(WebDriver driver)
    {
        this.driver=driver;
        eleUtil= new ElementUtil(driver);
    }


    public String getAccountsPageTitle()
    {
        return eleUtil.doGetTitle(Constants.ACCOUNTS_PAGE_TITLE,Constants.DEFAULT_TIME_OUT);
    }

    public String getAccountsPageHeader()
    {
        return eleUtil.getElement(header).getText();
    }

    public  boolean isLogoutlinkExist()
    {
        return eleUtil.doIsDisplayed(logoutLink);
    }

    public void logout()
    {
        if(isLogoutlinkExist())
        {
            eleUtil.doClick(logoutLink);
        }
    }

    public List<String> getAccountSectionList()
    {
        List<WebElement>accountsectionlist=eleUtil.waitForElementsToBeVisible(accountsSection,10);
        List<String>getAccountSectionValueList=new ArrayList<String>();
        for(WebElement e:accountsectionlist)
        {
            String text=e.getText();
            getAccountSectionValueList.add(text);
        }
        return getAccountSectionValueList;
    }


    public boolean isSearchFieldExits()
    {
        return eleUtil.doIsDisplayed(searchfield);
    }


    public SearchResultPage doSearch(String productName){
        System.out.println("Searching the product"+productName);
        eleUtil.doSendKeys(searchfield,productName);
        eleUtil.doClick(searchButton);

        return new SearchResultPage(driver);
    }
}
