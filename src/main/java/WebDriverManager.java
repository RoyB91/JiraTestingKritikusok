package main.java;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Platform;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class WebDriverManager {
    private static String password = System.getProperty("PASSWORD");
    private static String hubUrl = "https://selenium:" + password + "@seleniumhub.codecool.codecanvas.hu/wd/hub";
    //    private static WebDriverManager instance = null;
    private static WebDriver driver = null;

//    private WebDriverManager() {
//    }

//    public static WebDriverManager getInstance() {
//        if (instance == null) {
//            instance = new WebDriverManager();
//        }
//        return instance;
//    }

    public static WebDriver getDriver() {
        if (driver == null) {
            try {
                DesiredCapabilities capabilities = DesiredCapabilities.chrome();
                capabilities.setBrowserName("chrome");
                capabilities.setPlatform(Platform.LINUX);
                driver = new RemoteWebDriver(new URL(hubUrl), capabilities);
            } catch (MalformedURLException e) {
                e.fillInStackTrace();
            }
        }

        return driver;
    }

//    public WebDriver getDriver() {
//        try {
//            DesiredCapabilities capabilities = DesiredCapabilities.chrome();
//            capabilities.setBrowserName("chrome");
//            capabilities.setPlatform(Platform.LINUX);
//            driver = new RemoteWebDriver(new URL(hubUrl), capabilities);
//            return driver;
//
//        } catch (MalformedURLException e) {
//            e.fillInStackTrace();
//        }
//
//
//        return driver;
//    }
}
