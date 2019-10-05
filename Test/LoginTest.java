import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class LoginTest {


    private Main main = new Main();
    private WebDriver driver = main.getDriver();
    private String username = System.getenv("UserName");
    private String password = System.getenv("PASSWORD");
    private WebDriverWait wait = new WebDriverWait(driver, 15);

    @AfterEach
    public void close(){
        driver.quit();
    }

    @Test
    public void loginWithValidDataFromLoginPage() {

        main.loginWithValidData();
        WebElement avatarPicture = driver.findElement(By.xpath("//*[@id=\"header-details-user-fullname\"]/span/span"));
        avatarPicture.click();

        WebElement logoutOption = driver.findElement(By.xpath("//*[@id=\"log_out\"]"));
        assertTrue(logoutOption.isDisplayed());
    }

    @Test
    public void loginWithWrongPassword() {
        driver.get("https://jira.codecool.codecanvas.hu/login.jsp");
        WebElement loginField = driver.findElement(By.xpath("//*[@id=\"login-form-username\"]"));
        loginField.clear();
        loginField.sendKeys(username);

        WebElement passwordField = driver.findElement(By.xpath("//*[@id=\"login-form-password\"]"));
        passwordField.sendKeys("1");
        WebElement loginButton = driver.findElement(By.xpath("//*[@id=\"login-form-submit\"]"));
        loginButton.click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[@id=\"login-form\"]/div[1]/div[1]"))));
        assertTrue(driver.findElement(By.xpath("//*[@id=\"login-form\"]/div[1]/div[1]")).isDisplayed());
    }

    @Test
    public void loginValidDataFromMainPage() {
        driver.get("https://jira.codecool.codecanvas.hu/secure/Dashboard.jspa");
        WebElement loginField = driver.findElement(By.xpath("//*[@id=\"login-form-username\"]"));
        loginField.clear();
        loginField.sendKeys(username);

        WebElement passwordField = driver.findElement(By.xpath("//*[@id=\"login-form-password\"]"));
        passwordField.sendKeys(password);
        WebElement loginButton = driver.findElement(By.xpath("//*[@id=\"login\"]"));
        loginButton.click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);


        WebElement avatarPicture = driver.findElement(By.xpath("//*[@id=\"header-details-user-fullname\"]/span/span"));
        avatarPicture.click();

        WebElement logoutOption = driver.findElement(By.xpath("//*[@id=\"log_out\"]"));
        assertTrue(logoutOption.isDisplayed());
    }

    @Test
    public void loginWithEmptyFields() {
        driver.get("https://jira.codecool.codecanvas.hu/login.jsp");
        WebElement loginField = driver.findElement(By.xpath("//*[@id=\"login-form-username\"]"));
        loginField.clear();

        WebElement passwordField = driver.findElement(By.xpath("//*[@id=\"login-form-password\"]"));
        passwordField.clear();

        WebElement loginButton = driver.findElement(By.xpath("//*[@id=\"login-form-submit\"]"));
        loginButton.click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[@id=\"login-form\"]/div[1]/div[1]"))));
        assertTrue(driver.findElement(By.xpath("//*[@id=\"login-form\"]/div[1]/div[1]")).isDisplayed());
    }

    @Test
    public void caseSensitiveLoginCheck() {
        driver.get("https://jira.codecool.codecanvas.hu/login.jsp");
        WebElement loginField = driver.findElement(By.xpath("//*[@id=\"login-form-username\"]"));
        loginField.clear();
        loginField.sendKeys(username.toUpperCase());

        WebElement passwordField = driver.findElement(By.xpath("//*[@id=\"login-form-password\"]"));
        passwordField.sendKeys("1");
        WebElement loginButton = driver.findElement(By.xpath("//*[@id=\"login-form-submit\"]"));
        loginButton.click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[@id=\"login-form\"]/div[1]/div[1]"))));
        assertTrue(driver.findElement(By.xpath("//*[@id=\"login-form\"]/div[1]/div[1]")).isDisplayed());
    }

}