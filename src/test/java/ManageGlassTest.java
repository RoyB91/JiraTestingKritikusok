package test.java;

import main.java.GlassDocumentationPage;
import main.java.LoginPage;
import main.java.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.*;

public class ManageGlassTest extends Initialization{

    private LoginPage loginPage = new LoginPage();
    private GlassDocumentationPage glassDocumentationPage = new GlassDocumentationPage();

    @BeforeEach
    public void setup() {
        loginPage.loginWithValidData();
    }


    @ParameterizedTest
    @CsvFileSource(resources = "/manageGlassDataTest.csv", numLinesToSkip = 1)
    public void manageGlass(String url) {
        glassDocumentationPage.goToVersions(url);
        glassDocumentationPage.clickOnSpecifiedVersion();
        glassDocumentationPage.checkVersionTextOnOtherTab();
        assertEquals(glassDocumentationPage.getValidateText(), "Version kritikusok1.0");
    }
}
