import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ManageGlassTest {

    private Main main = new Main();

    @BeforeEach
    public void setup(){
        main.loginWithValidData();
        main.getDriver().manage().window().maximize();
    }

    @AfterEach
    public void close(){
        main.getDriver().quit();
    }

    @Test
    public void manageGlass(){
        main.getDriver().navigate().to("https://jira.codecool.codecanvas.hu/projects/PP1?selectedItem=com.codecanvas.glass:glass");
        WebElement versions = main.getDriver().findElement(By.xpath("//*[@id=\"aui-uid-2\"]"));
        versions.click();
        WebElement specifiedVersion = main.getDriver().findElement(By.xpath("//*[@id=\"versions-table\"]/tbody[2]/tr[25]/td[1]/div/a"));
        specifiedVersion.click();
        ArrayList<String> tabs2 = new ArrayList<String> (main.getDriver().getWindowHandles());
        String validateText = main.getDriver().switchTo().window(tabs2.get(1)).findElement(By.xpath("//*[@id=\"release-report\"]/header/div/div[1]/h2")).getText();
        assertEquals(validateText, "Version kritikusok1.0");
        main.getDriver().close();
        main.getDriver().switchTo().window(tabs2.get(0));
    }
}
