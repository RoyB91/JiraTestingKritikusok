import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

public class IssueTypeSchemeGlass {

    private Main main = new Main();
    private WebDriver driver = main.getDriver();
    private ProjectPage projectPage = new ProjectPage(driver);
    private GlassDocumentationPage glassDocumentationPage = new GlassDocumentationPage(driver);


    @BeforeEach
    public void setup() {
        main.loginWithValidData();
        driver.manage().window().maximize();
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
