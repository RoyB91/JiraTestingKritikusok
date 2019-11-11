import main.java.LoginPage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class LoginTest {

    private LoginPage loginPage = new LoginPage();

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