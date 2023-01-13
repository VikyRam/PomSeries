package com.qa.webtest.factory;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.Properties;

public class OptionsManager {

    private Properties prop;
    private ChromeOptions co;
    private FirefoxOptions fo;


    public OptionsManager(Properties prop) {
        this.prop = prop;
    }

    public ChromeOptions chromeOption() {
        co = new ChromeOptions();
        co.addArguments("--disable-dev-shm-usage");//overcome limited resource problems
        co.addArguments("--no-sandbox");//Bypass OS Security model
        if (Boolean.parseBoolean(prop.getProperty("headless"))) {
            co.addArguments("--headless");

        }
        if (Boolean.parseBoolean(prop.getProperty("incognito"))) {
            co.addArguments("--incognito");
        }
        return co;
    }

    public FirefoxOptions firefoxOption() {
        fo = new FirefoxOptions();
        if (Boolean.parseBoolean(prop.getProperty("headless"))) {
            co.addArguments("--headless");
        }
        if (Boolean.parseBoolean(prop.getProperty("incognito"))) {
            co.addArguments("--incognito");
        }
        return fo;
    }

}
