package com.qa.webtest.tests;

import com.qa.webtest.pages.SearchResultPage;
import com.qa.webtest.utils.Constants;
import com.qa.webtest.utils.Errors;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

public class AccountPageTest extends BaseTest {

    @BeforeClass
    public void accPageSetup() {
        System.out.println("Username=" + prop.getProperty("username") + "Password=" + prop.getProperty("password"));
        accountsPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    @Test(priority = 1)
    public void accounPageTitle() {
        String actualTitle = accountsPage.getAccountsPageTitle();
        System.out.println("Account Page Title" + actualTitle);
        Assert.assertEquals(actualTitle, Constants.ACCOUNTS_PAGE_TITLE);

    }

    @Test(priority = 2)
    public void accounPageHeader() {
        String actualHeader = accountsPage.getAccountsPageHeader();
        System.out.println("Account Page Header" + actualHeader);
        Assert.assertEquals(actualHeader, Constants.ACCOUNTS_PAGE_HEADER, Errors.ACC_PAGE_HEADER_NOT_FOUND_ERROR_MESSG);
    }


    @Test(priority = 3)
    public void isLogoutExit() {
        Assert.assertTrue(accountsPage.isLogoutlinkExist());
    }

    @Test(priority = 4)
    public void accountPageSectionTest() {
        List<String> actualAccountSectionList = accountsPage.getAccountSectionList();
        Assert.assertEquals(actualAccountSectionList, Constants.getExpectedSectList());
    }

    @DataProvider
    public Object[][] productData() {
        return new Object[][]{
                {"MacBook Pro"},
                {"Apple"},
                {"Samsung"}
        };
    }

    @Test(priority = 5, dataProvider = "productData")
    public void searchProduct(String productName) {

        searchResultPage = accountsPage.doSearch(productName);
        Assert.assertTrue(searchResultPage.getProductListCount() > 0);
    }


    @DataProvider
    public Object[][] productSelectionData() {
        return new Object[][]{
                {"MacBook", "MacBook Pro"},
                {"iMac", "iMac"},
                {"Samsung", "Samsung SyncMaster 941BW"},
                {"Apple", "Apple Cinema 30\""}
        };
    }

    @Test(priority = 6, dataProvider = "productSelectionData")
    public void selectProductTest(String productName, String mainProductName) {
        searchResultPage = accountsPage.doSearch(productName);
        productInfoPage = searchResultPage.selectProduct(mainProductName);
        String actual = productInfoPage.getProductHeader();
        Assert.assertEquals(actual, mainProductName);

    }
}
