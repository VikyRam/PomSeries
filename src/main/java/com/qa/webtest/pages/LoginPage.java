package com.qa.webtest.pages;

import com.qa.webtest.utils.Constants;
import com.qa.webtest.utils.ElementUtil;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    private WebDriver driver;
    private ElementUtil eleUtil;

    public  LoginPage (WebDriver driver)
    {
        this.driver=driver;
        eleUtil= new ElementUtil(driver);

    }

    private By emailID=By.id("input-email");
    private By password=By.id("input-password");
    private By loginBtn=By.xpath("//input[@type='submit']");
    private By registerLink=By.linkText("Register");

    private By forgotPwdLink=By.linkText("Forgotten Password");
    private By loginErrorMsg=By.cssSelector("div.alert.alert-danger.alert-dismissible");

    @Step("Getting login page tittle")
    public String getLoginPageTitle(){
        return eleUtil.doGetTitle(Constants.LOGIN_PAGE_TITLE,Constants.DEFAULT_TIME_OUT);
    }

    @Step("Getting login page URL")
    public boolean getLoginPageURL()
    {
        return eleUtil.waitForURLToContain(Constants.LOGIN_PAGE_URL,Constants.DEFAULT_TIME_OUT);
    }

    @Step("Check Forgot password link exist")
    public boolean isForgotPwdLinkExist()
    {
        return eleUtil.doIsDisplayed(forgotPwdLink);
    }

    @Step("Check Register link exist")
    public boolean isRegisterLinkExit()
    {
        return eleUtil.doIsDisplayed(registerLink);
    }

    @Step("Do login with Username:{0} and Password:{1}")
    public AccountsPage doLogin(String un, String pwd)
    {
        eleUtil.doSendKeys(emailID,un);
        eleUtil.doSendKeys(password,pwd);
        eleUtil.doClick(loginBtn);
        return new AccountsPage(driver);
    }

    @Step("Do login with wrong Username:{0} and Password:{1}")
    public boolean doLoginwithWrongCredentials(String un, String pwd) {
        System.out.println("Username- " + un + "Password- " + pwd);
        eleUtil.doSendKeys(emailID, un);
        eleUtil.doSendKeys(password, pwd);
        eleUtil.doClick(loginBtn);
        String errorMsg = eleUtil.doGetText(loginErrorMsg);
        System.out.println(errorMsg);
        if (errorMsg.contains(Constants.ERROR_MESG_TEXT))
        {
            System.out.println("Login is not done");
            return true;
        }
        return false;
    }

    @Step("Navigating to Registration page URL")
    public RegistrationPage goToRegistrationPage()
    {
        eleUtil.doClick(registerLink);
        return new RegistrationPage(driver);
    }
}

