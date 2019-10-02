import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.jupiter.api.Assertions.*;

public class ManageGlassTest {

    private Main main = new Main();
    private WebDriverWait webDriverWait = new WebDriverWait(main.getDriver(), 15);

    @BeforeEach
    public void setup(){
        main.loginWithValidData();
        main.getDriver().manage().window().maximize();
    }

    @AfterEach
    public void close(){
        main.getDriver().close();
    }

    @Test
    public void manageGlass(){
        main.getDriver().navigate().to("https://jira.codecool.codecanvas.hu/projects/PP1?selectedItem=com.codecanvas.glass:glass");
        WebElement versions = main.getDriver().findElement(By.xpath("//*[@id=\"aui-uid-2\"]"));
        versions.click();
        WebElement specifiedVersion = main.getDriver().findElement(By.xpath("//*[@id=\"versions-table\"]/tbody[2]/tr[25]/td[1]/div/a"));
        specifiedVersion.click();
    }
}
