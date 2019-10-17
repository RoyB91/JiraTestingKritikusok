import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import static org.junit.jupiter.api.Assertions.*;

class EditIssueTest {

    private Main main = new Main();
    private WebDriver driver = main.getDriver();
    private EditIssuePage editIssuePage;
    private IssuePage issuePage = new IssuePage(driver);
    private LoginPage loginPage = new LoginPage(driver);

    @BeforeEach
    public void setup() {
        editIssuePage = new EditIssuePage(driver, issuePage);
        driver.manage().window().maximize();
        loginPage.loginWithValidData();

    }

    @AfterEach
    public void close() {
        driver.quit();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "resources/editIssueDataTest.csv", numLinesToSkip = 4)
    public void editMTPIssues(String url, String failMessage, String editMessage, String expected) {
        driver.navigate().to(url);
        issuePage.clickEditIssueButton();
        editIssuePage.fillSummaryText(editMessage);
        editIssuePage.pressUpdateButton();
        assertEquals(expected, issuePage.getIssueSummaryNameText());

        editIssuePage.resetIssueSummary(driver, url, issuePage.getDefaultSummaryText());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "resources/editIssueDataTest.csv", numLinesToSkip = 1)
    public void checkPermissionToEditIssue(String url, String failMessage) {
        driver.navigate().to(url);
        assertTrue(issuePage.isEditIssueButtonIsThere(), failMessage);
    }


    @ParameterizedTest
    @CsvFileSource(resources = "resources/editIssueDataTest.csv", numLinesToSkip = 4)
    public void updateIssueWithEmptySummary(String url) {
        driver.navigate().to(url);
        issuePage.clickEditIssueButton();
        editIssuePage.pressUpdateWithEmptyFields();

        assertTrue(editIssuePage.getErrorField().isDisplayed());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "resources/editIssueDataTest.csv", numLinesToSkip = 4)
    public void cancelNotSaveEditIssue(String url, String failMessage, String editMessage) {
        driver.navigate().to(url);
        issuePage.clickEditIssueButton();
        editIssuePage.clearSummaryText();
        editIssuePage.fillSummaryText(editMessage);
        editIssuePage.pressCancelAndConfirm();
        assertEquals(issuePage.getDefaultSummaryText(), issuePage.getIssueSummaryNameText());
    }

}