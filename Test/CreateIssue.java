import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CreateIssue {

    private Main main = new Main();
    private WebDriverWait webDriverWait = new WebDriverWait(main.getDriver(), 20);
    private CreateIssueSrc createIssue = new CreateIssueSrc();

    @BeforeEach
    public void setup(){
        main.loginWithValidData();
        main.getDriver().manage().window().maximize();
    }

    @AfterEach
    public void close(){
        main.getDriver().close();
    }

    @Test
    public void createProject() {
        createIssue.createProject(main.getDriver(), "JETI Project (JETI)", "Bug", "create issue");
    }

    @Test
    public void createIssue() {
        WebElement createButton = main.getDriver().findElement(By.xpath("//*[@id=\"create_link\"]"));
        createButton.click();
        WebElement dropDownButtonProject = webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"project-single-select\"]/span")));
        dropDownButtonProject.click();
        try {
            WebElement clickMtp = main.getDriver().findElement(By.linkText("Main Testing Project (MTP)"));
            clickMtp.click();
        } catch (NoSuchElementException e){
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
    public void createWithEmptyFields(){
        WebElement createButton = main.getDriver().findElement(By.xpath("//*[@id=\"create_link\"]"));
        createButton.click();
        WebElement clickCreateButton = webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"create-issue-submit\"]")));
        clickCreateButton.click();
        String errorMessage = main.getDriver().findElement(By.xpath("//*[@id=\"create-issue-dialog\"]/div[2]/div[1]/div/form/div[1]/div[2]/div[1]/div")).getText();
        assertEquals(errorMessage, "You must specify a summary of the issue.");
    }

    @Test
    public void createSubTask(){
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
            if(summaries.get(i).getText().equals("text Subtask")){
                assertEquals(summaries.get(i).getText(), "text Subtask");
            }
            break;
        }
    }

    @Test
    public void selectIssueType(){
        createIssue.selectIssueType(main.getDriver(), "COALA Project (COALA)", "Task");
    }
}
