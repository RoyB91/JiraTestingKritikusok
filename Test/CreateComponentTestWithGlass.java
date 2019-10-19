import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;


import static org.junit.jupiter.api.Assertions.*;

class CreateComponentTestWithGlass {

    private Main main = new Main();
    private WebDriver driver = main.getDriver();
    private LoginPage loginPage = new LoginPage();
    private WebDriverWait wait = new WebDriverWait(driver, 15);

    private ComponentPage componentPage;
    private GlassDocumentationPage glassDocumentationPage;

    @BeforeEach
    public void setup() {
        driver.manage().window().maximize();
        loginPage.loginWithValidData();
        componentPage = new ComponentPage(driver);
        glassDocumentationPage = new GlassDocumentationPage(driver);

    }
    @AfterEach
    public void close(){
        driver.quit();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "resources/createCompDataTest.csv", numLinesToSkip = 1)
    public void createCompAndCheckWithGlass(String url, String compName, String assignee) {
        driver.navigate().to(url);

        componentPage.createComponent(compName,assignee);
        glassDocumentationPage.goToGlassDocumentationPage();

        assertEquals(compName, glassDocumentationPage.testComponentName());

        componentPage.deleteComponent(driver, url);
    }

}