import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.*;

public class BrowseIssueTest {

    private Main main = new Main();
    private BrowseIssueSrc browseIssueSrc = new BrowseIssueSrc(main.getDriver());

    @BeforeEach
    public void setup() {
        main.loginWithValidData();
        main.getDriver().manage().window().maximize();
    }

    @AfterEach
    public void close() {
        main.getDriver().quit();
    }

    @Test
    public void detailsOfTasks() {
        browseIssueSrc.detailsOfTasks(main.getDriver());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "resources/browseIssue.csv", numLinesToSkip = 1)
    public void projectJetiContainsIssue(String projectname) {
        browseIssueSrc.navigateToAnIssue(main.getDriver(), projectname);
        assertTrue(browseIssueSrc.issueTitleIsDisplayed());
    }


    @Test
    public void browseExistingIssue() throws AWTException {
        browseIssueSrc.navigateTo();
        browseIssueSrc.selectProject();
        browseIssueSrc.setSelectPp1();
        browseIssueSrc.clickOnProjectAgain();
        browseIssueSrc.rightClickOnIssue();
        assertTrue(browseIssueSrc.issueTitleIsDisplayed());
    }
}