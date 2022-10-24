package com.qa.webtest.pages;

import com.qa.webtest.utils.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SearchResultPage {
    private WebDriver driver;
    private ElementUtil eleUtil;

    private By productList = By.cssSelector("div.caption a");

    public SearchResultPage(WebDriver driver) {
        this.driver = driver;
        eleUtil = new ElementUtil(driver);
    }

    public int getProductListCount() {
        int listCount = eleUtil.waitForElementsToBeVisible(productList, 10, 2000).size();
        System.out.println("The search result count" + listCount);
        return listCount;
    }

    public productInfoPage selectProduct(String mainProductName) {
        System.out.println("Select this product-" + mainProductName);
        List<WebElement> productListresult = eleUtil.waitForElementsToBeVisible(productList, 10, 200);
        for (WebElement e : productListresult) {
            String text = e.getText();
            if (text.equals(mainProductName)) {
                e.click();
                break;
            }

        }
        return new productInfoPage(driver);
    }
}