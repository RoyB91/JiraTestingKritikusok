package main.java;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage {

    private String baseURL = "https://jira.codecool.codecanvas.hu";
    private WebDriver driver = WebDriverManager.getInstance().getDriver();
    private WebDriverWait wait = new WebDriverWait(driver, 10);
    private String username = System.getenv("UserName");
    private String password = System.getenv("PASSWORD");

    BasePage() {
//        driver.manage().window().maximize();

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