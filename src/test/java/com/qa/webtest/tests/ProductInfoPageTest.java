package com.qa.webtest.tests;

import com.qa.webtest.utils.Constants;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Map;

public class ProductInfoPageTest extends BaseTest{
    @BeforeClass
    public void productInfoSetup() {
        System.out.println("Username=" + prop.getProperty("username") + "Password=" + prop.getProperty("password"));
        accountsPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    @Test(priority = 1)
    public void productHeaderTest()
    {
        searchResultPage = accountsPage.doSearch("MacBook");
        productInfoPage = searchResultPage.selectProduct("MacBook Pro");
        String actual = productInfoPage.getProductHeader();
        Assert.assertEquals(actual, "MacBook Pro");
    }

    @Test(priority = 2)
    public void productImageCountTest()
    {
        searchResultPage = accountsPage.doSearch("iMac");
        productInfoPage = searchResultPage.selectProduct("iMac");
        String actual = productInfoPage.getProductHeader();
        Assert.assertEquals(productInfoPage.getImageCount(), Constants.IMAC_IMAGE_COUNT);
    }


    @Test(priority = 3)
    public void productInfoTest()
    {
        searchResultPage = accountsPage.doSearch("MacBook");
        productInfoPage = searchResultPage.selectProduct("MacBook Pro");
       Map<String,String> actualProductInfoMap= productInfoPage.getProductInfo();
        actualProductInfoMap.forEach((k,v)->System.out.println(k+""+v));
        softAssert.assertEquals(actualProductInfoMap.get("Name"),"MacBook Pro");
        softAssert.assertEquals(actualProductInfoMap.get("Brand"),"Apple");
        softAssert.assertEquals(actualProductInfoMap.get("Price"),"$2,000.00");
        softAssert.assertAll();


        }


}
