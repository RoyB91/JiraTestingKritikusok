package main.java;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriverManager {

    private static WebDriverManager instance = null;
    private WebDriver driver = new ChromeDriver();


    private WebDriverManager() {
    }

    public static WebDriverManager getInstance() {
        if (instance == null) {
            instance = new WebDriverManager();
        }
        return instance;
    }

    public WebDriver getDriver() {
        System.setProperty("webdriver.chrome.driver", "/src/test/resources/chromedriver");
        driver.manage().window().maximize();
        return driver;
    }
}
