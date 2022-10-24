package com.qa.webtest.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginPageNegativeTest extends BaseTest
{

    @DataProvider
    public Object[][] loginWrongTestData()
    {
       return new Object[][]
               {
                       {"teswewt1@gmail.com","test123"},
                       {"teseewt2@gmail.com","test423"},
                       {"233we234","321312"}
               };
    }


    @Test(dataProvider ="loginWrongTestData" )
    public void LoginPageNegativeTest(String un, String pwd)
    {
        Assert.assertFalse(loginPage.doLoginwithWrongCredentials(un,pwd));
    }
}
