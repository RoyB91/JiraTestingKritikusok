package test.java;

import main.java.ComponentPage;
import main.java.GlassDocumentationPage;
import main.java.LoginPage;
import main.java.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;


import static org.junit.jupiter.api.Assertions.*;

class CreateComponentTestWithGlass extends Initialization {

    private LoginPage loginPage = new LoginPage();

    private ComponentPage componentPage = new ComponentPage();
    private GlassDocumentationPage glassDocumentationPage = new GlassDocumentationPage();

    @BeforeEach
    public void setup() {
        loginPage.loginWithValidData();

    }

    @ParameterizedTest
    @CsvFileSource(resources = "../src/test/java/resources/createCompDataTest.csv", numLinesToSkip = 1)
    public void createCompAndCheckWithGlass(String url, String compName, String assignee) {
        componentPage.createComponent(url, compName, assignee);
        glassDocumentationPage.goToGlassDocumentationPage();

        assertEquals(compName, glassDocumentationPage.testComponentName());

        componentPage.deleteComponent(url);
    }

}