import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class BrowseProjectsTest {

    private Main main = new Main();
    private WebDriver driver = main.getDriver();
    private BrowseProjectsPage browseProjectsPage = new BrowseProjectsPage(driver);

    private String url = "https://jira.codecool.codecanvas.hu/secure/Dashboard.jspa";

    public BrowseProjectsTest() { PageFactory.initElements(driver, this); }

    @BeforeEach
    public void login() {
        driver.manage().window().maximize();
        main.loginWithValidData();
        driver.get(url);

    }

    @AfterEach
    public void close() {
        driver.quit();

    }

    @ParameterizedTest
    @CsvFileSource(resources = "resources/ViewAllProjectsTestData.csv")
    public void viewAllProjects(String project) {
        browseProjectsPage.clickProjectsDropdown();
        browseProjectsPage.clickViewAllProjects(project);
        browseProjectsPage.clickGivenProject(project);

        assertTrue(browseProjectsPage.projectSummary(project).isDisplayed());

    }

    @ParameterizedTest
    @CsvFileSource(resources = "resources/ViewAllProjectsTestData.csv")
    public void viewBusinessProjects(String project) {
        browseProjectsPage.clickProjectsDropdown();
        browseProjectsPage.clickBusinessProjects(project);
        browseProjectsPage.clickGivenProject(project);

        assertTrue(browseProjectsPage.projectSummary(project).isDisplayed());

    }

    @ParameterizedTest
    @CsvFileSource(resources = "resources/ViewAllProjectsTestData.csv")
    public void viewCoalaProject(String project) {
        browseProjectsPage.clickProjectsDropdown();
        browseProjectsPage.clickViewAllProjects(project);
        browseProjectsPage.clickGivenProject(project);
        browseProjectsPage.clickProjectSummary(project);

        assertTrue(browseProjectsPage.projectActivity().isDisplayed());

    }

    @ParameterizedTest
    @CsvFileSource(resources = "resources/ViewAllProjectsTestData.csv")
    public void viewToucanProject(String project) {
        browseProjectsPage.clickProjectsDropdown();
        browseProjectsPage.clickViewAllProjects(project);
        browseProjectsPage.clickGivenProject(project);
        browseProjectsPage.clickProjectSummary(project);

        assertTrue(browseProjectsPage.projectActivity().isDisplayed());

    }

    @ParameterizedTest
    @CsvFileSource(resources = "resources/ViewAllProjectsTestData.csv")
    public void viewJetiProject(String project) {
        browseProjectsPage.clickProjectsDropdown();
        browseProjectsPage.clickViewAllProjects(project);
        browseProjectsPage.clickGivenProject(project);
        browseProjectsPage.clickProjectSummary(project);

        assertTrue(browseProjectsPage.projectActivity().isDisplayed());

    }

}