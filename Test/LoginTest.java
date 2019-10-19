import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class LoginTest {

    private LoginPage loginPage = new LoginPage();

    @BeforeEach
    public void open() {

    }

    @AfterEach
    public void close() {
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

    @ParameterizedTest
    @CsvFileSource(resources = "resources/LoginTestData.csv", numLinesToSkip = 1)
    public void caseSensitiveLoginCheck(String username, String password) {
        loginPage.navigateURL();
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