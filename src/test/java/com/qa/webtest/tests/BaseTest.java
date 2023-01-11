package com.qa.webtest.tests;

import com.qa.webtest.factory.Driverfactory;
import com.qa.webtest.pages.*;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
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

    @Parameters({"browser","browserversion"})
    @BeforeTest
    public void setup(String browser,String browserVersion)
    {
        df=new Driverfactory();
        prop=df.init_prop();

        if(browser!=null)
        {
            prop.setProperty("browser",browser);
            prop.setProperty("browserversion",browserVersion);
        }
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
