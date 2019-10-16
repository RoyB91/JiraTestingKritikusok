import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

public class PermissionGlassTest {

    private Main main = new Main();
    private ProjectPage projectPage = new ProjectPage(main.getDriver());
    private GlassDocumentationPage glassDocumentationPage = new GlassDocumentationPage(main.getDriver());

    @BeforeEach
    public void setup() {
        main.loginWithValidData();
        main.getDriver().manage().window().maximize();
    }

    @AfterEach
    public void close() {
        main.getDriver().quit();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "resources/permissionType.csv", numLinesToSkip = 1)
    public void verifyPermission(String url, String permissionType, String projectPermissionType) {
        main.getDriver().navigate().to(url);
        projectPage.clickOnPermissions();
        assertEquals(projectPage.permissionMatrix(projectPermissionType), "Any logged in user");
        glassDocumentationPage.goToGlassDocumentationPage();
        glassDocumentationPage.goToPermissions();
        assertTrue(glassDocumentationPage.getCheckMark(permissionType));
    }
}
