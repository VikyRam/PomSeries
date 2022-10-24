package com.qa.webtest.tests;

import com.qa.webtest.factory.Driverfactory;
import com.qa.webtest.pages.*;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import java.util.Properties;

public class BaseTest {

    Driverfactory df;
    WebDriver driver;
    LoginPage loginPage;
    AccountsPage accountsPage;

    SearchResultPage searchResultPage;
    productInfoPage productInfoPage;
    RegistrationPage registrationPage;
    Properties prop;

    SoftAssert softAssert;

    @BeforeTest
    public void setup()
    {
        df=new Driverfactory();
        prop=df.init_prop();
        driver= df.init_driver(prop);

        loginPage=new LoginPage(driver);
        softAssert=new SoftAssert();


    }

    @AfterTest
    public void tearDown()
    {
        driver.quit();
    }
}
