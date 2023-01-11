package com.qa.webtest.factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

public class Driverfactory {

    public WebDriver driver;
    public Properties prop;
    public static String highlight;
    public OptionsManager optionsManager;

    public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();


    public WebDriver init_driver(Properties prop) {
        String browserName = prop.getProperty("browser").trim();
        String browserVersion = prop.getProperty("browserversion").trim();
        System.out.println("BrowserName: " + browserName);
        highlight = prop.getProperty("highlight");
        optionsManager = new OptionsManager(prop);

        if (browserName.equals("chrome")) {
            WebDriverManager.chromedriver().setup();

            if(Boolean.parseBoolean(prop.getProperty("remote")))
            {
                init_remoteDriver("chrome",browserVersion);
            }
            else {
                tlDriver.set(new ChromeDriver(optionsManager.chromeOption()));
            }
        } else if (browserName.equals("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            if(Boolean.parseBoolean(prop.getProperty("remote")))
            {
                init_remoteDriver("firefox",browserVersion);
            }
            else {
                tlDriver.set(new FirefoxDriver(optionsManager.firefoxOption()));            }
        } else {
            System.out.println("Please pass the correct browser vale" + browserName);
        }

        getDriver().manage().window().fullscreen();
        getDriver().manage().deleteAllCookies();
        openUrl(prop.getProperty("url"));
        return getDriver();
    }

    private void init_remoteDriver(String browser,String browserVersion) {
        System.out.println("Running on remote grid server"+ browser);
        if(browser.equalsIgnoreCase("chrome")){
            DesiredCapabilities cap=new DesiredCapabilities();
//            cap.setBrowserName("chrome");

            //Desired cap for selenoid
            cap.setCapability("browser","chrome");
            cap.setCapability("browserversion",browserVersion);
            cap.setCapability("enableVNC",true);

            cap.setCapability(ChromeOptions.CAPABILITY, optionsManager.chromeOption());
            try {
                tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")),cap));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        } else if (browser.equalsIgnoreCase("firefox")) {
            DesiredCapabilities cap=new DesiredCapabilities();
//            cap.setBrowserName("firefox");
            cap.setCapability("browserName","firefox");
            cap.setCapability("browserVersion",browserVersion);
            cap.setCapability("enableVNC",true);
            cap.setCapability(FirefoxOptions.FIREFOX_OPTIONS, optionsManager.firefoxOption());
            try {
                tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")),cap));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

        }
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
