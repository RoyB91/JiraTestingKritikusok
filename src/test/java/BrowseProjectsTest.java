package test.java;

import main.java.BrowseProjectsPage;
import main.java.LoginPage;
import main.java.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BrowseProjectsTest extends Initialization {

    private LoginPage loginPage = new LoginPage();
    private BrowseProjectsPage browseProjectsPage = new BrowseProjectsPage();


    public BrowseProjectsTest() {
    }

    @BeforeEach
    public void login() {
        loginPage.loginWithValidData();

    }

    @ParameterizedTest
    @CsvFileSource(resources = "/BrowseProjectsTestData.csv")
    public void viewAllProjects(String project) {
        browseProjectsPage.clickProjectsDropdown();
        browseProjectsPage.clickViewAllProjects();
        browseProjectsPage.clickGivenProject(project);

        assertTrue(browseProjectsPage.isElementDisplayed(browseProjectsPage.projectSummary(project)));

    }

    @ParameterizedTest
    @CsvFileSource(resources = "/BrowseProjectsTestData.csv")
    public void viewBusinessProjects(String project) {
        browseProjectsPage.clickProjectsDropdown();
        browseProjectsPage.clickBusinessProjects();
        browseProjectsPage.clickGivenProject(project);

        assertTrue(browseProjectsPage.isElementDisplayed(browseProjectsPage.projectSummary(project)));

    }

    @ParameterizedTest
    @CsvFileSource(resources = "/BrowseProjectsTestData.csv")
    public void viewProjects(String project) {
        browseProjectsPage.clickProjectsDropdown();
        browseProjectsPage.clickViewAllProjects();
        browseProjectsPage.clickGivenProject(project);
        browseProjectsPage.clickProjectSummary(project);

        assertTrue(browseProjectsPage.isElementDisplayed(browseProjectsPage.projectActivity()));

    }

}