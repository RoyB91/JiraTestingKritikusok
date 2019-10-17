import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class LoginTest {

    private Main main = new Main();
    private WebDriver driver = main.getDriver();
    private LoginPage loginPage = new LoginPage(driver);
    private String username = System.getenv("UserName");
    private String password = System.getenv("PASSWORD");

    @BeforeEach
    public void open() {
        driver.manage().window().maximize();
        driver.get("https://jira.codecool.codecanvas.hu/login.jsp");

    }

    @AfterEach
    public void close(){
        driver.quit();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "resources/LoginTestData.csv", numLinesToSkip = 1)
    public void loginWithValidDataFromLoginPage(String username, String password) {
        loginPage.loginWithParameters(username, password);
        loginPage.clickAvatarPicture();

        assertTrue(loginPage.getLogOutButton().isDisplayed());

    }

    @Test
    public void loginWithWrongPassword() {
        String wrongPassword = "1";
        loginPage.writeUsername(username);
        loginPage.writePassword(wrongPassword);
        loginPage.clickLoginButton();

        assertTrue(loginPage.getErrorMessage().isDisplayed());

    }

    @ParameterizedTest
    @CsvFileSource(resources = "resources/LoginTestData.csv", numLinesToSkip = 1)
    public void loginValidDataFromMainPage(String username, String password) {
        loginPage.writeUsername(username);
        loginPage.writePassword(password);
        loginPage.clickLoginButton();
        loginPage.clickAvatarPicture();

        assertTrue(loginPage.getLogOutButton().isDisplayed());

    }

    @Test
    public void loginWithEmptyFields() {
        String emptyField = "";
        loginPage.writeUsername(emptyField);
        loginPage.writePassword(emptyField);
        loginPage.clickLoginButton();

        assertTrue(loginPage.getErrorMessage().isDisplayed());

    }

    @ParameterizedTest
    @CsvFileSource(resources = "resources/LoginTestData.csv", numLinesToSkip = 1)
    public void caseSensitiveLoginCheck(String username, String password) {
        loginPage.writeUsername(username.toUpperCase());
        loginPage.writePassword(password);
        loginPage.clickLoginButton();

        try {
            assertTrue(loginPage.getErrorMessage().isDisplayed());
        } catch (NoSuchElementException e) {
            loginPage.clickAvatarPicture();
            assertFalse(loginPage.getLogOutButton().isDisplayed());
        }

    }

}