package test.java;

import main.java.LoginPage;

import org.junit.jupiter.api.*;
import org.openqa.selenium.NoSuchElementException;

//import static org.junit.jupiter.api.Assertions.*;

class LoginTest extends Initialization {

    private LoginPage loginPage = new LoginPage();


    @Test
    public void loginWithValidDataFromLoginPage() {
        loginPage.loginWithParameters(loginPage.getUsername(), loginPage.getPassword());
        loginPage.clickAvatarPicture();

        Assertions.assertTrue(loginPage.getLogOutButton().isDisplayed());

    }

    @Test
    public void loginWithWrongPassword() {
        loginPage.navigateURL();
        String wrongPassword = "1";
        loginPage.writeUsername(loginPage.getUsername());
        loginPage.writePassword(wrongPassword);
        loginPage.clickLoginButton();

        Assertions.assertTrue(loginPage.getErrorMessage().isDisplayed());

    }

    @Test
    public void loginWithEmptyFields() {
        loginPage.navigateURL();
        String emptyField = "";
        loginPage.writeUsername(emptyField);
        loginPage.writePassword(emptyField);
        loginPage.clickLoginButton();

        Assertions.assertTrue(loginPage.getErrorMessage().isDisplayed());

    }

//    @Test
//    public void caseSensitiveLoginCheck() {
//        loginPage.navigateURL();
//        loginPage.writeUsername(loginPage.getUsername().toUpperCase());
//        loginPage.writePassword(loginPage.getPassword());
//        loginPage.clickLoginButton();
//
//        try {
//            Assertions.assertTrue(loginPage.getErrorMessage().isDisplayed());
//        } catch (NoSuchElementException e) {
//            loginPage.clickAvatarPicture();
//            Assertions.assertFalse(loginPage.getLogOutButton().isDisplayed());
//        }
//
//    }

}