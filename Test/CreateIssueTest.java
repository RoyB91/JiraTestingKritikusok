import com.google.gson.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

public class CreateIssue {

    private Main main = new Main();
    private WebDriverWait webDriverWait = new WebDriverWait(main.getDriver(), 15);
    private CreateIssueSrc createIssue = new CreateIssueSrc();

    @BeforeEach
    public void setup() {
        main.loginWithValidData();
        main.getDriver().manage().window().maximize();
    }

    @AfterEach
    public void close() {
        main.getDriver().close();
    }

    @Test
    public void createProject() {
        createIssue.createProject(main.getDriver(), "JETI Project (JETI)", "Bug", "create issue");
    }

    @Test
    public void createIssue() {

        WebElement createButton = main.getDriver().findElement(By.id("create_link"));
        createButton.click();
        WebElement dropDownButtonProject = webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"project-single-select\"]/span")));
        dropDownButtonProject.click();
        try {
            WebElement clickMtp = main.getDriver().findElement(By.linkText("Main Testing Project (MTP)"));
            clickMtp.click();
        } catch (NoSuchElementException e) {
            dropDownButtonProject.click();
        }
        WebElement dropDownButtonIssue = webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"issuetype-field\"]")));
        dropDownButtonIssue.click();
        try {
            WebElement selectTask = main.getDriver().findElement(By.linkText("Task"));
            selectTask.click();
        } catch (NoSuchElementException e) {
            dropDownButtonIssue.click();
        }
        WebElement editSummary = webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"summary\"]")));
        editSummary.sendKeys("create issue");
        WebElement clickCreateButton = main.getDriver().findElement(By.xpath("//*[@id=\"create-issue-submit\"]"));
        clickCreateButton.click();
        WebElement clickLink = webDriverWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".issue-created-key.issue-link")));
        clickLink.click();
    }

    @Test
    public void createWithEmptyFields() {
        WebElement createButton = main.getDriver().findElement(By.id("create_link"));

        createButton.click();
        WebElement clickCreateButton = webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"create-issue-submit\"]")));
        clickCreateButton.click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"issuetype-single-select\"]/span")));


        assertTrue(main.getDriver().findElement(By.xpath("//*[@id=\"create-issue-dialog\"]/div[2]/div[1]/div/form/div[1]/div[2]/div[1]/div")).isDisplayed());
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
    @CsvSource({"COALA Project (COALA)", "JETI PROJECT (JETI)", "TOUCAN projekt (TOUCAN)"})
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
        //Check if user have right to create this type of project
        String fieldProjectName = main.getDriver().findElement(By.xpath("//*[@id=\"project-field\"]")).getAttribute("value");

        assertEquals(fieldProjectName, projectName, "User has no right to create this type of issue.");

        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"issuetype-single-select\"]/span")));
        //CheckBugType
        WebElement inputField = main.getDriver().findElement(By.xpath("//*[@id=\"issuetype-field\"]"));
        inputField.sendKeys("Bug");
        inputField.sendKeys(Keys.ENTER);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"issuetype-single-select\"]/span")));
        String resultBug = main.getDriver().findElement(By.xpath("//*[@id=\"issuetype-field\"]")).getAttribute("value");
        webDriverWait.until(ExpectedConditions.elementToBeClickable(main.getDriver().findElement(By.xpath("//*[@id=\"issuetype-single-select\"]/span"))));
        assertEquals("Bug", resultBug);

        //CheckTaskType
        WebElement inputField2 = main.getDriver().findElement(By.xpath("//*[@id=\"issuetype-field\"]"));
        webDriverWait.until(ExpectedConditions.elementToBeClickable(main.getDriver().findElement(By.xpath("//*[@id=\"issuetype-single-select\"]/span"))));
        inputField2.sendKeys("Task");
        inputField2.sendKeys(Keys.ENTER);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"issuetype-single-select\"]/span")));
        String resultTask = main.getDriver().findElement(By.xpath("//*[@id=\"issuetype-field\"]")).getAttribute("value");
        webDriverWait.until(ExpectedConditions.elementToBeClickable(main.getDriver().findElement(By.xpath("//*[@id=\"issuetype-single-select\"]/span"))));
        assertEquals("Task", resultTask);

        //CheckStoryType
        WebElement inputField3 = main.getDriver().findElement(By.xpath("//*[@id=\"issuetype-field\"]"));
        webDriverWait.until(ExpectedConditions.elementToBeClickable(main.getDriver().findElement(By.xpath("//*[@id=\"issuetype-single-select\"]/span"))));
        inputField3.sendKeys("Story");
        inputField3.sendKeys(Keys.ENTER);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"issuetype-single-select\"]/span")));
        String resultStory = main.getDriver().findElement(By.xpath("//*[@id=\"issuetype-field\"]")).getAttribute("value");
        webDriverWait.until(ExpectedConditions.elementToBeClickable(main.getDriver().findElement(By.xpath("//*[@id=\"issuetype-single-select\"]/span"))));
        assertEquals("Story", resultStory);


    }
}
