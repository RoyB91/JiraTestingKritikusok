package main.java;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;


public abstract class BasePage {

    private String baseURL = System.getenv("baseURL");
    private WebDriver driver = WebDriverManager.getDriver();
    private int timeout = Integer.parseInt(System.getenv("TIMEOUT"));
    private WebDriverWait wait = new WebDriverWait(driver,timeout);
    private String username = System.getenv("UserName");
    private String password = System.getenv("PASSWORD");

    BasePage() {
    }

    public String getBaseURL() {
        return baseURL;
    }

    public WebDriver getDriver() {

        return driver;
    }

    public WebDriverWait getWait() {
        return wait;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

}