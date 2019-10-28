import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.*;

public class PermissionGlassTest {

    private LoginPage loginPage = new LoginPage();
    private ProjectPage projectPage = new ProjectPage();
    private GlassDocumentationPage glassDocumentationPage = new GlassDocumentationPage();

    @BeforeEach
    public void setup() {
        loginPage.loginWithValidData();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "resources/permissionType.csv", numLinesToSkip = 1)
    public void verifyPermission(String url, String permissionType, String projectPermissionType) {
        projectPage.clickOnPermissions(url);
        assertEquals(projectPage.permissionMatrix(projectPermissionType), "Any logged in user");
        glassDocumentationPage.goToGlassDocumentationPage();
        glassDocumentationPage.goToPermissions();
        assertTrue(glassDocumentationPage.getCheckMark(permissionType));
    }
}
