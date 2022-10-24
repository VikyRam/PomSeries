package com.qa.webtest.factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

public class Driverfactory {

    public WebDriver driver;
    public Properties prop;
    public static String highlight;
    public OptionsManager optionsManager;

    public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();


    public WebDriver init_driver(Properties prop) {
        String browserName = prop.getProperty("browser");
        System.out.println("BrowserName: " + browserName);
        highlight = prop.getProperty("highlight");
        optionsManager = new OptionsManager(prop);

        if (browserName.equals("chrome")) {
            WebDriverManager.chromedriver().setup();
//            driver = new ChromeDriver(optionsManager.chromeOption());
            tlDriver.set(new ChromeDriver(optionsManager.chromeOption()));
        } else if (browserName.equals("firefox")) {
            WebDriverManager.firefoxdriver().setup();

            driver = new FirefoxDriver();
        } else {
            System.out.println("Please pass the correct browser vale" + browserName);
        }

        getDriver().manage().window().fullscreen();
        getDriver().manage().deleteAllCookies();
        openUrl(prop.getProperty("url"));
        return getDriver();
    }


    /*
     * getdriver():It will return a thread local copy of the webdriver
     * */
    public static synchronized WebDriver getDriver() {
        return tlDriver.get();
    }

    public Properties init_prop() {
        prop = new Properties();
        FileInputStream ip = null;
        String envName = System.getProperty("env");//qa,stage,dev

        if (envName == null) {
            System.out.println("Running on prod");

            try {
                ip = new FileInputStream("./src/test/resources/config/config.properties");
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }

        } else {
            System.out.println("Running on " + envName + " env");

            try {
                switch (envName.toLowerCase()) {
                    case "qa":
                        ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
                        break;
                    case "stage":
                        ip = new FileInputStream("./src/test/resources/config/stage.config.properties");
                        break;

                    default:
                        break;
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }


        try {
            prop.load(ip);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        return prop;
    }

    public String getScreenshot() {
        File src = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
        String path = System.getProperty("user.dir") + "/screenshot/" + System.currentTimeMillis() + ".png";
        File destination = new File(path);
        try {
            FileUtils.copyFile(src, destination);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return path;

    }


    public void openUrl(String url) {
        try {
            if (url == null)
                throw new Exception("URL is null");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        getDriver().navigate().to(url);
    }


    public void openUrl(URL url) {
        try {
            if (url == null)
                throw new Exception("URL is null");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        getDriver().navigate().to(url);
    }

    public void openUrl(String  baseUrl,String path) {
        try {
            if (baseUrl == null)
                throw new Exception("URL is null");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        getDriver().navigate().to(baseUrl+"/"+path);
    }

    public void openUrl(String  baseUrl,String path,String query) {
        try {
            if (baseUrl == null)
                throw new Exception("URL is null");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        getDriver().navigate().to(baseUrl+"/"+"?"+path);
    }



}
