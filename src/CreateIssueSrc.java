import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateIssueSrc {


    public void createProject(WebDriver driver, String projectName, String issueType, String summary) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, 15);
        WebElement createButton = driver.findElement(By.xpath("//*[@id=\"create_link\"]"));
        createButton.click();
        WebElement dropDownButtonProject = webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"project-single-select\"]/span")));
        dropDownButtonProject.click();
        WebElement dropDownButtonIssue = webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"issuetype-field\"]")));
        try {
            WebElement clickProject = driver.findElement(By.linkText(projectName));
            clickProject.click();
        } catch (NoSuchElementException e) {
            dropDownButtonProject.click();
        }
        dropDownButtonIssue.click();
        try {
            WebElement selectBug = driver.findElement(By.linkText(issueType));
            selectBug.click();
        } catch (NoSuchElementException e) {
            dropDownButtonIssue.click();
        }
        WebElement editSummary = webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"summary\"]")));
        editSummary.sendKeys(summary);
        WebElement clickCreateButton = driver.findElement(By.xpath("//*[@id=\"create-issue-submit\"]"));
        clickCreateButton.click();
    }

    public void selectIssueType(WebDriver driver, String projectName, String issueType) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, 15);
        WebElement createButton = driver.findElement(By.xpath("//*[@id=\"create_link\"]"));
        createButton.click();
        WebElement dropDownButtonProject = webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"project-single-select\"]/span")));
        dropDownButtonProject.click();
        WebElement dropDownButtonIssue = webDriverWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#issuetype-single-select > .icon")));
        try {
            WebElement clickProject = driver.findElement(By.linkText(projectName));
            clickProject.click();
        } catch (NoSuchElementException e) {
            dropDownButtonProject.click();
        }
        dropDownButtonIssue.click();
        try {
            WebElement selectIssue = driver.findElement(By.linkText(issueType));
            selectIssue.click();
        } catch (NoSuchElementException e) {
            dropDownButtonIssue.click();
        }
        driver.findElement(By.cssSelector("#issuetype-single-select > .icon")).click();
        WebElement selectStory = webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Story")));
        selectStory.click();
        driver.findElement(By.cssSelector("#issuetype-single-select > .icon")).click();
        WebElement selectBug = webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Bug")));
        selectBug.click();
    }

}
