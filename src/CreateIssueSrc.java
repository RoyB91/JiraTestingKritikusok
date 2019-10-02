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
        try {
            WebElement clickProject = driver.findElement(By.linkText(projectName));
            clickProject.click();
        } catch (NoSuchElementException e) {
            dropDownButtonProject.click();
        }
        WebElement dropDownButtonIssue = webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"issuetype-field\"]")));
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
        try {
            WebElement clickProject = webDriverWait.until((ExpectedConditions.elementToBeClickable(By.linkText(projectName))));
            clickProject.click();
        } catch (NoSuchElementException e) {
            WebElement dropDownButtonProject1 = webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"project-single-select\"]/span")));
            dropDownButtonProject1.click();
        }
        WebElement dropDownButtonIssue = webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"issuetype-single-select\"]/span")));
        dropDownButtonIssue.click();
        try {
            WebElement selectIssue = driver.findElement(By.linkText(issueType));
            selectIssue.click();
        } catch (NoSuchElementException e) {
            WebElement dropDownButtonIssue2 = webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"issuetype-single-select\"]/span")));
            dropDownButtonIssue2.click();
        }
        WebElement selectStory = webDriverWait.until((ExpectedConditions.elementToBeClickable(driver.findElement(By.partialLinkText("Story")))));
        selectStory.click();
        WebElement dropDownButtonIssue1 = webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"issuetype-single-select\"]/span")));
        dropDownButtonIssue1.click();
        WebElement selectBug = webDriverWait.until((ExpectedConditions.elementToBeClickable(driver.findElement(By.partialLinkText("Bug")))));
        selectBug.click();
    }

}
