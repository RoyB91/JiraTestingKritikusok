package test.java;

import main.java.BasePage;
import main.java.CreateIssuePage;
import main.java.LoginPage;
import main.java.WebDriverManager;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.Keys;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CreateIssueTest extends Initialization {

    private LoginPage loginPage = new LoginPage();
    private CreateIssuePage createIssuePage = new CreateIssuePage();


    @BeforeEach
    public void setup() {
        loginPage.loginWithValidData();

    }

    @ParameterizedTest
    @CsvFileSource(resources = "/createIssue.csv", numLinesToSkip = 1)
    public void createProject(String project, String issueType, String summary, String errorMessage) {
        createIssuePage.clickTheCreateButton();
        createIssuePage.selectAProject(project);
        createIssuePage.selectAnIssue(issueType);
        createIssuePage.fillTheSummaryField(summary);
        assertEquals(createIssuePage.getFieldProjectName(), project, errorMessage);

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


//    @ParameterizedTest
//    @CsvFileSource(resources = "../src/test/java/resources/createSubtaskDataTest.csv", numLinesToSkip = 1)
//    public void createSubTask(String url) {
//        createIssuePage.clickOnMoreButton(url);
//        createIssuePage.clickOnTheCreateSubTaskButton();
//        createIssuePage.fillTheSummaryField("text Subtask");
//        createIssuePage.clickTheCreateIssueButton();
//        createIssuePage.checkTheSubTask();
//    }

//    @ParameterizedTest
//    @CsvFileSource(resources = "/changeIssueTypes.csv", numLinesToSkip = 1)
//    public void selectIssueType(String projectName, String issueType) {
//
//        createIssuePage.clickTheCreateButton();
//        createIssuePage.selectAProject(projectName);
//
//        //Check if user have right to create this type of project
//
//
//        //CheckBugType
//        createIssuePage.selectAnIssue(issueType);
//        assertEquals(projectName, createIssuePage.getFieldProjectName());
//        assertEquals(issueType, createIssuePage.getFieldIssueTypeName());
//    }
}
