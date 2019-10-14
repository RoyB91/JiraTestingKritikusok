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
    private WebDriverWait wait = new WebDriverWait(driver, 15);
    private EditIssuePage editIssuePage;
    private IssuePage issuePage;


    @BeforeEach
    public void setup() {
        editIssuePage = new EditIssuePage(driver);
        issuePage = new IssuePage(driver);
        main.loginWithValidData();
        driver.manage().window().maximize();

    }

    @AfterEach
    public void close() {
        driver.quit();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "resources/editIssueDataTest.csv", numLinesToSkip = 4)
    public void editMTPIssues(String url, String failMessage, String editMessage, String expected) {
        driver.navigate().to(url);
        issuePage.click(issuePage.getEditIssueButton());

        wait.until(ExpectedConditions.visibilityOf(editIssuePage.getJiraDialogContent()));

        editIssuePage.clearField(editIssuePage.getSummaryField());
        editIssuePage.fillText(editIssuePage.getSummaryField(), editMessage);
        editIssuePage.click(editIssuePage.getUpdateButton());

        wait.until(ExpectedConditions.visibilityOf(issuePage.getAlertPopUp()));
        assertEquals(expected, issuePage.getIssueSummaryNameText());

        //ask this ,Where/when should i rewrite this.
        editIssuePage.resetIssueSummary(driver, url, issuePage.getDefaultSummaryText());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "resources/editIssueDataTest.csv", numLinesToSkip = 1)
    public void checkPermissionToEditIssue(String url, String failMessage) {
        driver.navigate().to(url);
        try {
            assertTrue(issuePage.getEditIssueButton().isDisplayed());
        } catch (ElementNotVisibleException | NoSuchElementException e) {
            fail(failMessage);
        } finally {
            driver.quit();
        }
    }


    @ParameterizedTest
    @CsvFileSource(resources = "resources/editIssueDataTest.csv", numLinesToSkip = 4)
    public void updateIssueWithEmptySummary(String url) {
        driver.navigate().to(url);

        issuePage.click(issuePage.getEditIssueButton());

        wait.until(ExpectedConditions.visibilityOf(editIssuePage.getJiraDialogContent()));
        editIssuePage.clearField(editIssuePage.getSummaryField());
        editIssuePage.click(editIssuePage.getUpdateButton());

        wait.until(ExpectedConditions.visibilityOf(editIssuePage.getErrorField()));

        assertTrue(editIssuePage.getErrorField().isDisplayed());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "resources/editIssueDataTest.csv", numLinesToSkip = 4)
    public void cancelNotSaveEditIssue(String url,String failMessage,String editMessage) {
        driver.navigate().to(url);
        issuePage.click(issuePage.getEditIssueButton());

        wait.until(ExpectedConditions.visibilityOf(editIssuePage.getJiraDialogContent()));
        editIssuePage.clearField(editIssuePage.getSummaryField());
        editIssuePage.fillText(editIssuePage.getSummaryField(), editMessage);
        editIssuePage.click(editIssuePage.getCancelButton());

        driver.switchTo().alert().accept();

        assertEquals(issuePage.getDefaultSummaryText(), issuePage.getIssueSummaryNameText());
    }

}