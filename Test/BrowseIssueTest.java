import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.*;

public class BrowseIssueTest {

    private Main main = new Main();
    private WebDriver driver = main.getDriver();
    private LoginPage loginPage = new LoginPage();
    private BrowseIssuePage browseIssuePage = new BrowseIssuePage(main.getDriver());

    @BeforeEach
    public void setup(){
        main.getDriver().manage().window().maximize();
        loginPage.loginWithValidData();
    }

    @AfterEach
    public void close() {
        main.getDriver().quit();
    }

    @Test
    public void detailsOfTasks() {
        browseIssuePage.detailsOfTasks(main.getDriver());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "resources/browseIssue.csv", numLinesToSkip = 1)
    public void projectJetiContainsIssue(String projectname) {
        browseIssuePage.navigateToAnIssue(main.getDriver(), projectname);
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