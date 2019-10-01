import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


import static org.junit.jupiter.api.Assertions.*;

class LoginTest {


    private Main main = new Main();

    @Test
    public void loginWithValidData() {
        main.loginWithValidData();
        WebElement avatarPicture = main.getDriver().findElement(By.xpath("//*[@id=\"header-details-user-fullname\"]/span/span"));
        avatarPicture.click();

        WebElement logoutOption = main.getDriver().findElement(By.xpath("//*[@id=\"log_out\"]"));
        assertTrue(logoutOption.isDisplayed());
    }

}