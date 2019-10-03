import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

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

}
