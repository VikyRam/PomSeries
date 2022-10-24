package com.qa.webtest.pages;

import com.qa.webtest.utils.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class productInfoPage {

    private WebDriver driver;
    private ElementUtil eleUtil;
    private By productHeader = By.xpath("//div[@id='content']//h1");
    private By productImages= By.xpath("//ul[@class='thumbnails']//img");
    private By productMetaData=By.cssSelector("div#content ul.list-unstyled:nth-of-type(1) li");
    private By productPriceData=By.cssSelector("div#content ul.list-unstyled:nth-of-type(2) li");
    private By quantity=By.xpath("//input[@name='quantity']");
    private By addToCartButton=By.xpath("//button[@id='button-cart']");

    private Map<String , String> productInfoMap;



    public productInfoPage(WebDriver driver) {
        this.driver = driver;
        eleUtil = new ElementUtil(driver);
    }

    public String getProductHeader() {
        String productHeaderText= eleUtil.doGetText(productHeader);
        System.out.println("Poduct Header is-"+productHeaderText);
        return productHeaderText;
    }

    public int getImageCount()
    {
        return eleUtil.waitForElementsToBeVisible(productImages,10).size();
    }


    public Map<String, String> getProductInfo()
    {
        productInfoMap=new LinkedHashMap<String,String>();
        productInfoMap.put("Name",getProductHeader());
        getProductMetaData();
        getProductPriceData();
        return productInfoMap;
    }


    private void getProductMetaData()
    {
      List<WebElement>metaDataList= eleUtil.getElements(productMetaData);
      for (WebElement e:metaDataList)
      {
          String text=e.getText();
          String meta[]=text.split(":");
          String metaKey= meta[0].trim();
          String metaValue=meta[1].trim();
          productInfoMap.put(metaKey,metaValue);
      }
    }

    private void getProductPriceData()
    {
        List<WebElement>metaPriceList= eleUtil.getElements(productPriceData);
         String price=metaPriceList.get(0).getText().trim();
        String exPrice=metaPriceList.get(1).getText().trim();
        productInfoMap.put("Price",price);
        productInfoMap.put("ExtaxPrice",exPrice);
    }

}
