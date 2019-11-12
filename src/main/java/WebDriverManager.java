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

    private String password = System.getProperty("PASSWORD");
    private String hubUrl = "https://selenium:CoolCanvas19.@seleniumhub.codecool.codecanvas.hu/wd/hub";
    private static WebDriverManager instance = null;
    private WebDriver driver;
    private ChromeOptions options = new ChromeOptions();

    private WebDriverManager() {
    }

    public static WebDriverManager getInstance() {
        if (instance == null) {
            instance = new WebDriverManager();
        }
        return instance;
    }

    public WebDriver getDriver() {
        try {
            DesiredCapabilities capabilities = DesiredCapabilities.chrome();
            capabilities.setBrowserName("chrome");
            capabilities.setPlatform(Platform.LINUX);
            driver = new RemoteWebDriver(new URL(hubUrl), capabilities);
            return driver;

        } catch (MalformedURLException e) {
            e.fillInStackTrace();
        }


        return driver;
    }
}
