import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


class LogoutTest {

    private Main main = new Main();
    private WebDriver driver = main.getDriver();
    private LoginPage loginPage = new LoginPage(driver);

    @Test
    public void logoutJira() {
        loginPage.loginWithValidData();
        WebElement avatarPicture = main.getDriver().findElement(By.xpath("//*[@id=\"header-details-user-fullname\"]/span/span"));
        avatarPicture.click();

        WebElement logoutOption = main.getDriver().findElement(By.xpath("//*[@id=\"log_out\"]"));
        logoutOption.click();
        main.getDriver().quit();
    }

}