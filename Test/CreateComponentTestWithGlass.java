import main.java.ComponentPage;
import main.java.GlassDocumentationPage;
import main.java.LoginPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;


import static org.junit.jupiter.api.Assertions.*;

class CreateComponentTestWithGlass {

    private LoginPage loginPage = new LoginPage();

    private ComponentPage componentPage = new ComponentPage();
    private GlassDocumentationPage glassDocumentationPage = new GlassDocumentationPage();

    @BeforeEach
    public void setup() {
        loginPage.loginWithValidData();

    }

    @ParameterizedTest
    @CsvFileSource(resources = "resources/createCompDataTest.csv", numLinesToSkip = 1)
    public void createCompAndCheckWithGlass(String url, String compName, String assignee) {
        componentPage.createComponent(url,compName,assignee);
        glassDocumentationPage.goToGlassDocumentationPage();

        assertEquals(compName, glassDocumentationPage.testComponentName());

        componentPage.deleteComponent(url);
    }

}