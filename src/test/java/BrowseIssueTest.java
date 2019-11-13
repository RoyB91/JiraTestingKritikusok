package test.java;

import main.java.BrowseIssuePage;
import main.java.LoginPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.*;

public class BrowseIssueTest extends Initialization {

    private LoginPage loginPage = new LoginPage();
    private BrowseIssuePage browseIssuePage = new BrowseIssuePage();

    @BeforeEach
    public void setup() {
        loginPage.loginWithValidData();
    }




    @Test
    public void detailsOfTasks() {
        browseIssuePage.detailsOfTasks();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/browseIssue.csv", numLinesToSkip = 1)
    public void projectJetiContainsIssue(String projectname) {
        browseIssuePage.navigateToAnIssue(projectname);
        assertTrue(browseIssuePage.issueTitleIsDisplayed());
    }


    @Test
    public void browseExistingIssue() throws AWTException {
        browseIssuePage.navigateTo();
        browseIssuePage.selectProject();
        browseIssuePage.setSelectPp1();
        browseIssuePage.clickOnProjectAgain();
        browseIssuePage.rightClickOnIssue();
        assertTrue(browseIssuePage.issueTitleIsDisplayed());
    }
}