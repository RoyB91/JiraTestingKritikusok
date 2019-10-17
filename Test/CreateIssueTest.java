import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CreateIssueTest {

    private Main main = new Main();
    private WebDriver driver = main.getDriver();
    private LoginPage loginPage = new LoginPage(driver);
    private CreateIssuePage createIssuePage = new CreateIssuePage(main.getDriver());

    @BeforeEach
    public void setup() {
        main.getDriver().manage().window().maximize();
        loginPage.loginWithValidData();
    }

    @AfterEach
    public void close() {
        main.getDriver().quit();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "resources/createIssue.csv", numLinesToSkip = 1)
    public void createProject(String project, String issueType, String summary) {
        createIssuePage.clickTheCreateButton();
        createIssuePage.selectAProject(project);
        createIssuePage.selectAnIssue(issueType);
        createIssuePage.fillTheSummaryField(summary);
        assertEquals(createIssuePage.getFieldProjectName(), project, "User has no right to create this type of issue.");

    }

    @Test
    public void createIssue() {

        createIssuePage.clickTheCreateButton();
        createIssuePage.selectAProject("Main Testing Project (MTP)");
        createIssuePage.selectAnIssue("Task");
        createIssuePage.fillTheSummaryField("create issue");
        createIssuePage.clickTheCreateIssueButton();
        createIssuePage.clickTheCreatedIssueLink();
    }

    @Test
    public void createWithEmptyFields() {
        createIssuePage.clickTheCreateButton();
        createIssuePage.clickTheCreateIssueButton();

        assertTrue(createIssuePage.getErrorMessage().isDisplayed());
    }

    @Test
    public void createSubTask() {
        main.getDriver().navigate().to("https://jira.codecool.codecanvas.hu/browse/MTP-288");
        createIssuePage.clickOnMoreButton();
        createIssuePage.clickOnTheCreateSubTaskButton();
        createIssuePage.fillTheSummaryField("text Subtask");
        createIssuePage.clickTheCreateIssueButton();
        createIssuePage.checkTheSubTask();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "resources/changeIssueTypes.csv", numLinesToSkip = 1)
    public void selectIssueType(String projectName, String issueType) {

        createIssuePage.clickTheCreateButton();
        createIssuePage.selectAProject(projectName);

        //Check if user have right to create this type of project


        //CheckBugType
        createIssuePage.selectAnIssue(issueType);
        assertEquals(issueType, createIssuePage.getFieldIssueTypeName());
    }
}
