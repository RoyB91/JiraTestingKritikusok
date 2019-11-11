import main.java.GlassDocumentationPage;
import main.java.LoginPage;
import main.java.ProjectPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.*;

public class IssueTypeSchemeGlass {

    private LoginPage loginPage = new LoginPage();
    private ProjectPage projectPage = new ProjectPage();
    private GlassDocumentationPage glassDocumentationPage = new GlassDocumentationPage();


    @BeforeEach
    public void setup() {
        loginPage.loginWithValidData();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "resources/issueTypeSchemeDataTest.csv", numLinesToSkip = 1)
    public void glassIssueType(String url, String issueTypeName, String expected) {

        assertTrue(projectPage.checkIfProjectHasIssueName(url, issueTypeName));
        glassDocumentationPage.goToGlassDocumentationPage();
        glassDocumentationPage.goToSchemes();
        assertEquals(expected, glassDocumentationPage.getIssueTypeNameText());
    }
}
