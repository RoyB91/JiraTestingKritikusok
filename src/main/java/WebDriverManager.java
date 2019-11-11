package main.java;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class WebDriverManager {

    private static WebDriverManager instance = null;
    private WebDriver driver;
    private String nodeUrl = "https://selenium:{password}.@seleniumhub.codecool.codecanvas.hu/wd/hub";

    private WebDriverManager() {

    }

    public static WebDriverManager getInstance() {

            if (instance == null) {
                instance = new WebDriverManager();
            }
            return instance;

    }

    public WebDriver getDriver(){
        driver.manage().window().maximize();
        DesiredCapabilities capability = DesiredCapabilities.chrome();
        capability.setBrowserName("chrome");
        capability.setPlatform(Platform.LINUX);
        try {

            driver = new RemoteWebDriver(new URL(nodeUrl), capability);
        }catch (MalformedURLException e){

        }
        return driver;
    }
}
