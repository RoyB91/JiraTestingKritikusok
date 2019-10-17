import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class BrowseProjectsTest {

    private Main main = new Main();
    private WebDriver driver = main.getDriver();
    private LoginPage loginPage = new LoginPage(driver);
    private BrowseProjectsPage browseProjectsPage = new BrowseProjectsPage(driver);

    private String url = "https://jira.codecool.codecanvas.hu/secure/Dashboard.jspa";

    public BrowseProjectsTest() { PageFactory.initElements(driver, this); }

    @BeforeEach
    public void login() {
        driver.manage().window().maximize();
        loginPage.loginWithValidData();
        driver.get(url);

    }

    @AfterEach
    public void close() {
        driver.quit();

    }

    @ParameterizedTest
    @CsvFileSource(resources = "resources/BrowseProjectsTestData.csv")
    public void viewAllProjects(String project) {
        browseProjectsPage.clickProjectsDropdown();
        browseProjectsPage.clickViewAllProjects();
        browseProjectsPage.clickGivenProject(project);

        assertTrue(browseProjectsPage.isElementDisplayed(browseProjectsPage.projectSummary(project)));

    }

    @ParameterizedTest
    @CsvFileSource(resources = "resources/BrowseProjectsTestData.csv")
    public void viewBusinessProjects(String project) {
        browseProjectsPage.clickProjectsDropdown();
        browseProjectsPage.clickBusinessProjects();
        browseProjectsPage.clickGivenProject(project);

        assertTrue(browseProjectsPage.isElementDisplayed(browseProjectsPage.projectSummary(project)));

    }

    @ParameterizedTest
    @CsvFileSource(resources = "resources/BrowseProjectsTestData.csv")
    public void viewProjects(String project) {
        browseProjectsPage.clickProjectsDropdown();
        browseProjectsPage.clickViewAllProjects();
        browseProjectsPage.clickGivenProject(project);
        browseProjectsPage.clickProjectSummary(project);

        assertTrue(browseProjectsPage.isElementDisplayed(browseProjectsPage.projectActivity()));

    }

}