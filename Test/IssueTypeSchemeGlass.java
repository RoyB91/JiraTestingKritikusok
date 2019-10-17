import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.*;

public class IssueTypeSchemeGlass {

    private Main main = new Main();
    private WebDriver driver = main.getDriver();
    private LoginPage loginPage = new LoginPage(driver);
    private ProjectPage projectPage = new ProjectPage(driver);
    private GlassDocumentationPage glassDocumentationPage = new GlassDocumentationPage(driver);


    @BeforeEach
    public void setup() {
        loginPage.loginWithValidData();
        main.getDriver().manage().window().maximize();
    }

    @AfterEach
    public void close() {
        main.getDriver().quit();
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
