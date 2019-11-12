package main.java;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class WebDriverManager {

    private String password = System.getProperty("PASSWORD");
    private String hubUrl = "https://selenium:" + password + "@seleniumhub.codecool.codecanvas.hu/wd/hub";
    private static WebDriverManager instance = null;
    private WebDriver driver;

    private WebDriverManager() {
    }

    public static WebDriverManager getInstance() {
        if (instance == null) {
            instance = new WebDriverManager();
        }
        return instance;
    }

    public WebDriver getDriver() {
//        return driver;
        try {
            this.driver = new RemoteWebDriver(new URL(hubUrl), DesiredCapabilities.chrome());
        } catch (MalformedURLException e) {
            e.fillInStackTrace();
        }
        this.driver.manage().window().maximize();
        return this.driver;
    }
}
