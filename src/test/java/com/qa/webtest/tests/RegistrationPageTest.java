package com.qa.webtest.tests;

import com.qa.webtest.utils.Constants;
import com.qa.webtest.utils.ExcelUtil;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Random;

public class RegistrationPageTest extends BaseTest{

    @BeforeClass
    public void setupRegistrationPage()
    {
        registrationPage=loginPage.goToRegistrationPage();
    }

    public String getRandomEmailID()
    {
        Random randomGenerator=new Random();
        String email="octAutomation"+randomGenerator.nextInt(100)+"@gmail.com";
        return email;
    }
    @DataProvider
    public Object[][] getRegistrationData()
    {
        return ExcelUtil.getTestData(Constants.RESGISTRATION_SHEET_NAME);

    }
    @Test(dataProvider = "getRegistrationData")
    public void userRegistrationTest(String firstName, String lastName, String telephone, String password, String subscribe)
    {
        Assert.assertTrue(registrationPage.accountRegistration( firstName,  lastName,getRandomEmailID(),  telephone,  password,  subscribe));
    }



}
