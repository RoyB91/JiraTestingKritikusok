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
    private WebDriverWait webDriverWait = new WebDriverWait(main.getDriver(), 15);
    private GlassDocumentationPage glassDocumentationPage = new GlassDocumentationPage(main.getDriver());

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
        glassDocumentationPage.goToVersions();
        glassDocumentationPage.clickOnSpecifiedVersion();
        glassDocumentationPage.checkVersionTextOnOtherTab();
        assertEquals(glassDocumentationPage.getValidateText(), "Version kritikusok1.0");
    }
}
