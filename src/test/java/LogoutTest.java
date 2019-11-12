package test.java;

import main.java.LoginPage;
import main.java.MainNavBar;
import main.java.WebDriverManager;

import org.junit.jupiter.api.*;


import static org.junit.jupiter.api.Assertions.*;


class LogoutTest {

    private LoginPage loginPage = new LoginPage();
    private MainNavBar mainNavBar = new MainNavBar();


    @BeforeEach
    public void login() {
        loginPage.loginWithValidData();
    }

//    @AfterEach
//    public void close() {
//        loginPage.getDriver().quit();
//    }

    @Test
    public void logoutJira() {
        mainNavBar.logout();
        Assertions.assertTrue(mainNavBar.getLogoutMessage().isDisplayed());
    }

//    @AfterAll
//    public void quitDriver() {
//        WebDriverManager.getInstance().getDriver().quit();
//    }

}