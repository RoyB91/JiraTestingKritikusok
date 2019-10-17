import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;


import static org.junit.jupiter.api.Assertions.*;

class CreateComponentTestWithGlass {

    private Main main = new Main();
    private WebDriver driver = main.getDriver();
    private LoginPage loginPage = new LoginPage(driver);
    private WebDriverWait wait = new WebDriverWait(driver, 15);

    private ComponentPage componentPage;
    private GlassDocumentationPage glassDocumentationPage;

    @BeforeEach
    public void setup() {
        loginPage.loginWithValidData();
        driver.manage().window().maximize();
        componentPage = new ComponentPage(driver);
        glassDocumentationPage = new GlassDocumentationPage(driver);

    }

    @ParameterizedTest
    @CsvFileSource(resources = "resources/createCompDataTest.csv", numLinesToSkip = 1)
    public void createCompAndCheckWithGlass(String url, String compName, String assignee) {
        driver.navigate().to(url);

        componentPage.fillText(componentPage.getComponentFieldName(), compName);
        componentPage.fillText(componentPage.getAssigneeField(), assignee);
        componentPage.enterKeys(componentPage.getAssigneeField(), Keys.ENTER);

        componentPage.click(componentPage.getAddButton());
        componentPage.click(componentPage.getGlassDocumentationButton());

        assertEquals(compName, glassDocumentationPage.testComponentName());
        componentPage.deleteComponent(driver, url);


        driver.quit();
    }

}