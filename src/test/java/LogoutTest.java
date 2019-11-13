package test.java;

import main.java.LoginPage;
import main.java.MainNavBar;

import org.junit.jupiter.api.*;


class LogoutTest extends Initialization {

    private LoginPage loginPage = new LoginPage();
    private MainNavBar mainNavBar = new MainNavBar();


    @BeforeEach
    public void login() {
        loginPage.loginWithValidData();
    }

    @Test
    public void logoutJira() {
        mainNavBar.logout();
        Assertions.assertTrue(mainNavBar.getLogoutMessage().isDisplayed());
    }


}