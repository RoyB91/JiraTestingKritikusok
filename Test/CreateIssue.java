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

public class CreateIssue {

    private Main main = new Main();
    private MainNavBar mainNavBar = new MainNavBar(main.getDriver());
    private CreateIssuePage createIssuePage = new CreateIssuePage(main.getDriver());
    private WebDriverWait webDriverWait = new WebDriverWait(main.getDriver(), 15);

    @BeforeEach
    public void setup() {
        main.loginWithValidData();
        main.getDriver().manage().window().maximize();
    }

    @AfterEach
    public void close() {
        main.getDriver().quit();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "resources/createIssue.csv", numLinesToSkip = 1)
    public void createProject(String project, String issueType, String summary) {
        createIssuePage.clickElement(mainNavBar.getCreateButton());
        webDriverWait.until(ExpectedConditions.elementToBeClickable(createIssuePage.getProjectFieldButton()));
        createIssuePage.clickElement(createIssuePage.getProjectFieldButton());
        //webDriverWait.until(ExpectedConditions.attributeToBe(createIssuePage.getValueTrue(), "aria-expanded", "true"));
        try {
            createIssuePage.getProjectFieldButton().sendKeys(project);
            createIssuePage.getProjectFieldButton().sendKeys(Keys.TAB);
        } catch (NoSuchElementException e) {
            createIssuePage.getProjectFieldButton().sendKeys(Keys.ESCAPE);
        }

        //Check if user have right to create this type of project
        webDriverWait.until(ExpectedConditions.elementToBeClickable(createIssuePage.getIssueTypeField()));

        webDriverWait.until(ExpectedConditions.visibilityOf(createIssuePage.getIssueTypeField()));
        createIssuePage.clickElement(createIssuePage.getIssueTypeField());
        createIssuePage.getIssueTypeField().sendKeys(issueType);
        createIssuePage.getIssueTypeField().sendKeys(Keys.ENTER);
        //webDriverWait.until(ExpectedConditions.elementToBeClickable(createIssuePage.getWaitForIssueTypeFieldButton()));

        webDriverWait.until(ExpectedConditions.elementToBeClickable(createIssuePage.getSummaryField()));
        createIssuePage.getSummaryField().sendKeys(summary);
        webDriverWait.until(ExpectedConditions.visibilityOf(createIssuePage.getProjectFieldButton()));
        assertEquals(createIssuePage.getFieldProjectName(), project, "User has no right to create this type of issue.");

        createIssuePage.clickElement(createIssuePage.getCreateIssueButton());
    }

    @Test
    public void createIssue() {

        createIssuePage.clickElement(mainNavBar.getCreateButton());
        webDriverWait.until(ExpectedConditions.elementToBeClickable(createIssuePage.getProjectFieldButton()));
        //createIssuePage.clickElement(createIssuePage.getProjectFieldButton());
        try {
            createIssuePage.clickElement(createIssuePage.getFindMTP());
        } catch (NoSuchElementException e) {
            createIssuePage.clickElement(createIssuePage.getProjectFieldButton());
        }
        webDriverWait.until(ExpectedConditions.elementToBeClickable(createIssuePage.getIssueTypeField()));
        createIssuePage.clickElement(createIssuePage.getIssueTypeField());
        try {
            createIssuePage.clickElement(createIssuePage.getFindTask());
        } catch (NoSuchElementException e) {
            createIssuePage.clickElement(createIssuePage.getIssueTypeField());
        }
        webDriverWait.until(ExpectedConditions.elementToBeClickable(createIssuePage.getSummaryField()));
        createIssuePage.getSummaryField().sendKeys("create issue");
        createIssuePage.clickElement(createIssuePage.getCreateIssueButton());

        createIssuePage.clickElement(createIssuePage.getClickLink());
    }

    @Test
    public void createWithEmptyFields() {
        createIssuePage.clickElement(mainNavBar.getCreateButton());

        webDriverWait.until(ExpectedConditions.elementToBeClickable(createIssuePage.getCreateIssueButton()));
        createIssuePage.clickElement(createIssuePage.createIssueButton);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(createIssuePage.getIssueTypeField()));


        assertTrue(createIssuePage.getErrorMessage().isDisplayed());
    }

    @Test
    public void createSubTask() {
        main.getDriver().navigate().to("https://jira.codecool.codecanvas.hu/browse/MTP-288");
        WebElement moreButton = main.getDriver().findElement(By.xpath("//*[@id=\"opsbar-operations_more\"]/span"));
        moreButton.click();
        WebElement createSubTask = main.getDriver().findElement(By.xpath("//*[@id=\"create-subtask\"]/a/span"));
        createSubTask.click();
        WebElement summary = webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"summary\"]")));
        summary.click();
        summary.sendKeys("text Subtask");
        WebElement createButton = main.getDriver().findElement(By.xpath("//*[@id=\"create-issue-submit\"]"));
        createButton.click();
        List<WebElement> summaries = main.getDriver().findElements(By.cssSelector(".stsummary"));
        for (int i = 0; i < summaries.size(); i++) {
            if (summaries.get(i).getText().equals("text Subtask")) {
                assertEquals(summaries.get(i).getText(), "text Subtask");
                break;
            }
        }
    }

    @ParameterizedTest(name = "{index} => projectName={0}")
    @CsvSource({"COALA Project (COALA)", "JETI Project (JETI)", "TOUCAN projekt (TOUCAN)"})
    public void selectIssueType(String projectName) {
        WebDriverWait webDriverWait = new WebDriverWait(main.getDriver(), 5);

        main.getDriver().findElement(By.id("create_link")).click();
        main.getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        main.getDriver().findElement(By.xpath("//*[@id=\"project-single-select\"]/span")).click();
        webDriverWait.until(ExpectedConditions.attributeToBe(main.getDriver().findElement(By.xpath("//*[@id=\"project-field\"]")), "aria-expanded", "true"));

        try {
            WebElement inputProject = main.getDriver().findElement(By.xpath("//*[@id=\"project-field\"]"));
            inputProject.sendKeys(projectName);
            inputProject.sendKeys(Keys.TAB);
        } catch (NoSuchElementException e) {
            main.getDriver().findElement(By.xpath("//*[@id=\"project-field\"]")).sendKeys(Keys.ESCAPE);
        }
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"issuetype-single-select\"]/span")));
        //Check if user have right to create this type of project
        String fieldProjectName = main.getDriver().findElement(By.xpath("//*[@id=\"project-field\"]")).getAttribute("value");

        assertEquals(fieldProjectName, projectName, "User has no right to create this type of issue.");

        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"issuetype-single-select\"]/span")));
        //CheckBugType
        WebElement inputField = main.getDriver().findElement(By.xpath("//*[@id=\"issuetype-field\"]"));
        inputField.sendKeys("Bug");
        inputField.sendKeys(Keys.TAB);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"issuetype-single-select\"]/span")));
        String resultBug = main.getDriver().findElement(By.xpath("//*[@id=\"issuetype-field\"]")).getAttribute("value");
        webDriverWait.until(ExpectedConditions.elementToBeClickable(main.getDriver().findElement(By.xpath("//*[@id=\"issuetype-single-select\"]/span"))));
        assertEquals("Bug", resultBug);

        //CheckTaskType
        WebElement inputField2 = main.getDriver().findElement(By.xpath("//*[@id=\"issuetype-field\"]"));
        webDriverWait.until(ExpectedConditions.elementToBeClickable(main.getDriver().findElement(By.xpath("//*[@id=\"issuetype-single-select\"]/span"))));
        inputField2.sendKeys("Task");
        inputField2.sendKeys(Keys.TAB);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"issuetype-single-select\"]/span")));
        String resultTask = main.getDriver().findElement(By.xpath("//*[@id=\"issuetype-field\"]")).getAttribute("value");
        webDriverWait.until(ExpectedConditions.elementToBeClickable(main.getDriver().findElement(By.xpath("//*[@id=\"issuetype-single-select\"]/span"))));
        assertEquals("Task", resultTask);

        //CheckStoryType
        WebElement inputField3 = main.getDriver().findElement(By.xpath("//*[@id=\"issuetype-field\"]"));
        webDriverWait.until(ExpectedConditions.elementToBeClickable(main.getDriver().findElement(By.xpath("//*[@id=\"issuetype-single-select\"]/span"))));
        inputField3.sendKeys("Story");
        inputField3.sendKeys(Keys.TAB);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"issuetype-single-select\"]/span")));
        String resultStory = main.getDriver().findElement(By.xpath("//*[@id=\"issuetype-field\"]")).getAttribute("value");
        webDriverWait.until(ExpectedConditions.elementToBeClickable(main.getDriver().findElement(By.xpath("//*[@id=\"issuetype-single-select\"]/span"))));
        assertEquals("Story", resultStory);


    }
}
