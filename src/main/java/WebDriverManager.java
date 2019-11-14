package main.java;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class WebDriverManager {
    private static String password = System.getenv("PASSWORD");
    private static String hubUser = System.getenv("hubUSERNAME");
    private static String hub = System.getenv("hubURL");
    private static String hubUrl = "https://" + hubUser + ":" + password + "@" + hub;
    private static WebDriver driver = null;


    //Singleton driver
    public static WebDriver getDriver() {
        if (driver == null) {
            try {
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--start-maximized");
                DesiredCapabilities capabilities = DesiredCapabilities.chrome();
                capabilities.setCapability(ChromeOptions.CAPABILITY, options);
                driver = new RemoteWebDriver(new URL(hubUrl), capabilities);


//                DesiredCapabilities capabilities = DesiredCapabilities.chrome();
//                capabilities.setBrowserName("chrome");
//                capabilities.setPlatform(Platform.LINUX);
//                driver = new RemoteWebDriver(new URL(hubUrl), capabilities);
            } catch (MalformedURLException e) {
                e.fillInStackTrace();
            }
        }

        return driver;
    }
}
