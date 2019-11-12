package test.java;

import main.java.EditIssuePage;
import main.java.IssuePage;
import main.java.LoginPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;


import static org.junit.jupiter.api.Assertions.*;

class EditIssueTest {

    private IssuePage issuePage = new IssuePage();
    private LoginPage loginPage = new LoginPage();
    private EditIssuePage editIssuePage = new EditIssuePage(issuePage);


    @BeforeEach
    public void setup() {
        loginPage.loginWithValidData();

    }

    @AfterEach
    public void close() {
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/editIssueDataTest.csv", numLinesToSkip = 4)
    public void editMTPIssues(String url, String failMessage) {
        issuePage.clickEditIssueButton(url);
        editIssuePage.fillSummaryText();
        editIssuePage.pressUpdateButton();
        assertEquals(editIssuePage.getRandomUUID(), issuePage.getIssueSummaryNameText());

    }

    @ParameterizedTest
    @CsvFileSource(resources = "/editIssueDataTest.csv", numLinesToSkip = 1)
    public void checkPermissionToEditIssue(String url, String failMessage) {
        assertTrue(issuePage.isEditIssueButtonIsThere(url), failMessage);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/editIssueDataTest.csv", numLinesToSkip = 4)
    public void updateIssueWithEmptySummary(String url) {
        issuePage.clickEditIssueButton(url);
        editIssuePage.pressUpdateWithEmptyFields();

        assertTrue(editIssuePage.getErrorField().isDisplayed());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/editIssueDataTest.csv", numLinesToSkip = 4)
    public void cancelNotSaveEditIssue(String url) {
        issuePage.clickEditIssueButton(url);
        String actualSummary = issuePage.getIssueSummaryNameText();
        editIssuePage.clearSummaryText();
        editIssuePage.fillSummaryText();
        editIssuePage.pressCancelAndConfirm();
        assertEquals(actualSummary, issuePage.getIssueSummaryNameText());
    }

}