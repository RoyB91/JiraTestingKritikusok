
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class EditIssueTest {

    private Main main = new Main();
    private WebDriver driver = main.getDriver();
    private WebDriverWait wait = new WebDriverWait(driver, 15);
    private EditIssuePage editIssuePage = new EditIssuePage();

    @BeforeEach
    public void setup() {
        main.loginWithValidData();
        driver.manage().window().maximize();

    }

    @Test
    public void editMTPIssues() {
        driver.navigate().to("https://jira.codecool.codecanvas.hu/browse/MTP-270");
        editIssuePage.getEditButton(driver).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement summaryField = editIssuePage.getSummaryField(driver);
        summaryField.clear();
        summaryField.sendKeys("su");
        editIssuePage.getUpdateButton(driver).click();

        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[@id=\"aui-flag-container\"]/div/div"))));
        assertEquals("su", editIssuePage.getIssueSummaryName(driver));

        editIssuePage.resetIssueSummary(driver, "https://jira.codecool.codecanvas.hu/browse/MTP-270", "summary");
        driver.close();
    }

    @Test
    public void editCOALAIssue() {
        driver.navigate().to("https://jira.codecool.codecanvas.hu/browse/COALA-337");
        try {
            assertTrue(driver.findElement(By.xpath("//*[@id=\"edit-issue\"]/span[2]")).isDisplayed());
        } catch (ElementNotVisibleException | NoSuchElementException e) {
            fail("This user has no access to edit this type of ISSUE");
        } finally {
            driver.close();
        }
    }

    @Test
    public void editTOUCANIssue() {
        driver.navigate().to("https://jira.codecool.codecanvas.hu/browse/TOUCAN-114");
        try {
            assertTrue(driver.findElement(By.xpath("//*[@id=\"edit-issue\"]/span[2]")).isDisplayed());
        } catch (ElementNotVisibleException | NoSuchElementException e) {
            fail("This user has no access to edit this type of ISSUE");
        } finally {
            driver.close();
        }
        //asdasd
    }

    @Test
    public void editJETIIssue() {
        driver.navigate().to("https://jira.codecool.codecanvas.hu/browse/JETI-152");
        try {
            assertTrue(driver.findElement(By.xpath("//*[@id=\"edit-issue\"]/span[2]")).isDisplayed());
        } catch (ElementNotVisibleException | NoSuchElementException e) {
            fail("This user has no access to edit this type of ISSUE");
        } finally {
            driver.close();
        }
    }


    @Test
    public void updateIssueWithEmptySummary() {
        driver.navigate().to("https://jira.codecool.codecanvas.hu/browse/WEAKS-8");
        editIssuePage.getEditButton(driver).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        editIssuePage.getSummaryField(driver).clear();
        editIssuePage.getUpdateButton(driver).click();
        assertTrue(driver.findElement(By.xpath("//*[@id=\"edit-issue-dialog\"]/div[2]/div[1]/div/form/div[1]/div/div[1]/div")).isDisplayed());
        driver.close();
    }

    @Test
    public void cancelNotSaveEditIssue() {
        driver.navigate().to("https://jira.codecool.codecanvas.hu/browse/MTP-270");
        editIssuePage.getEditButton(driver).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement summaryField = editIssuePage.getSummaryField(driver);
        summaryField.clear();
        summaryField.sendKeys("su");
        editIssuePage.getCancelButton(driver).click();
        driver.switchTo().alert().accept();
        assertEquals("summary", editIssuePage.getIssueSummaryName(driver));
        driver.close();
    }

}