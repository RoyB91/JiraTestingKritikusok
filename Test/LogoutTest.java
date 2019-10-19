import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.*;


class LogoutTest {

    private Main main = new Main();
    private WebDriver driver = main.getDriver();
    private LoginPage loginPage = new LoginPage();
    private MainNavBar mainNavBar = new MainNavBar(driver);

    @BeforeEach
    public void login() {
        driver.manage().window().maximize();
        loginPage.loginWithValidData();
    }

    @AfterEach
    public void close() {
        driver.quit();
    }

    @Test
    public void logoutJira() {
        mainNavBar.logout();
        assertTrue(mainNavBar.getLogoutMessage().isDisplayed());
        main.getDriver().quit();
    }

}