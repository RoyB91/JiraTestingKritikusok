import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.jupiter.api.Assertions.*;

public class PermissionGlassTest {

    private Main main = new Main();
    private WebDriver driver = main.getDriver();
    private LoginPage loginPage = new LoginPage();
    private ProjectPage projectPage = new ProjectPage(main.getDriver());
    private GlassDocumentationPage glassDocumentationPage = new GlassDocumentationPage(main.getDriver());

    @BeforeEach
    public void setup() {
        main.getDriver().manage().window().maximize();
        loginPage.loginWithValidData();
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
