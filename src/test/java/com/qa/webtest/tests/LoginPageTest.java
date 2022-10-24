package com.qa.webtest.tests;

import com.qa.webtest.listeners.TestAllureListener;
import com.qa.webtest.utils.Constants;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Epic("EPIC-100: Design open cart webtest-Login Page")
@Story("US 101: Open cart login testcase with multiple features")
@Listeners(TestAllureListener.class)
public class LoginPageTest extends BaseTest{

    @Description("Login Page Title Test")
    @Severity(SeverityLevel.MINOR)
    @Test(priority = 1)
    public void loginPageTitle()
    {
        String actualTitle=loginPage.getLoginPageTitle();
        Assert.assertEquals("Page Title",Constants.LOGIN_PAGE_TITLE, actualTitle);
    }

    @Description("Login Page URL Test")
    @Severity(SeverityLevel.NORMAL)
    @Test(priority = 2)
    public void loginPageURL()
    {
        Assert.assertTrue( loginPage.getLoginPageURL());
    }

    @Description("Login Page Forgot Password Link Test")
    @Severity(SeverityLevel.CRITICAL)
    @Test (priority = 3)
    public void forgotPwdLink()
    {
      Assert.assertTrue(loginPage.isForgotPwdLinkExist());
    }

    @Description("Login Page RegistrationLink Test")
    @Severity(SeverityLevel.BLOCKER)
    @Test(priority = 4)
    public void registerLinkTest()
    {
        Assert.assertTrue(loginPage.isRegisterLinkExit());
    }

    @Description("Login Page Login Test")
    @Severity(SeverityLevel.BLOCKER)
    @Test(priority = 5)
    public void loginTest()
    {
      loginPage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
    }


}
