package test.java;

import main.java.LoginPage;
import main.java.WebDriverManager;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;

class LoginTest {

    private LoginPage loginPage = new LoginPage();

    LoginTest() throws MalformedURLException {
    }


    @Test
    public void loginWithValidDataFromLoginPage() {
        loginPage.loginWithParameters(loginPage.getUsername(), loginPage.getPassword());
        loginPage.clickAvatarPicture();

        assertTrue(loginPage.getLogOutButton().isDisplayed());

    }

    @Test
    public void loginWithWrongPassword() {
        loginPage.navigateURL();
        String wrongPassword = "1";
        loginPage.writeUsername(loginPage.getUsername());
        loginPage.writePassword(wrongPassword);
        loginPage.clickLoginButton();

        assertTrue(loginPage.getErrorMessage().isDisplayed());

    }

    @Test
    public void loginWithEmptyFields() {
        loginPage.navigateURL();
        String emptyField = "";
        loginPage.writeUsername(emptyField);
        loginPage.writePassword(emptyField);
        loginPage.clickLoginButton();

        assertTrue(loginPage.getErrorMessage().isDisplayed());

    }

    @Test
    public void caseSensitiveLoginCheck() {
        loginPage.navigateURL();
        loginPage.writeUsername(loginPage.getUsername().toUpperCase());
        loginPage.writePassword(loginPage.getPassword());
        loginPage.clickLoginButton();

        try {
            assertTrue(loginPage.getErrorMessage().isDisplayed());
        } catch (NoSuchElementException e) {
            loginPage.clickAvatarPicture();
            assertFalse(loginPage.getLogOutButton().isDisplayed());
        }

    }

}