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

    public void selectIssueType(WebDriver driver, String projectName, String issueType) {
        Actions action = new Actions(driver);
        WebDriverWait webDriverWait = new WebDriverWait(driver, 15);

        driver.findElement(By.id("create_link")).click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        driver.findElement(By.xpath("//*[@id=\"project-single-select\"]/span")).click();


        webDriverWait.until(ExpectedConditions.attributeToBe(driver.findElement(By.xpath("//*[@id=\"project-field\"]")), "aria-expanded", "true"));

        try {
            WebElement projectN = driver.findElement(By.cssSelector("#coala-project-\\(coala\\)-7 > .aui-list-item-link"));
            projectN.click();

        } catch (NoSuchElementException e) {
            action.sendKeys(Keys.ESCAPE).build().perform();
        }

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        WebElement inputField = driver.findElement(By.xpath("//*[@id=\"issuetype-field\"]"));
        inputField.sendKeys("Bug");
        action.sendKeys((Keys.TAB));

    }

}
