package main.java;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriverManager {

    private static WebDriver driver;


    private WebDriverManager() {
    }

    public static WebDriver getDriver() {
        System.setProperty("webdriver.chrome.driver", "/src/test/resources/chromedriver");
        driver.manage().window().maximize();
        driver = new ChromeDriver();
        return driver;
    }

    public static void quit() {
        driver.quit();
    }
}
