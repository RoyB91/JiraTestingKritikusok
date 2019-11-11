package main.java;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;


public class CreateIssuePage extends BasePage {

    private WebDriver driver;
    private MainNavBar mainNavBar;
    private WebDriverWait webDriverWait;
    @FindBy(id = "project-field")
    private WebElement projectFieldButton;
    @FindBy(xpath = "//*[@id=\"issuetype-single-select\"]/span")
    private WebElement waitForIssueTypeFieldButton;
    @FindBy(xpath = "//*[@id=\"issuetype-field\"]")
    private WebElement issueTypeField;
    @FindBy(id = "summary")
    private WebElement summaryField;
    @FindBy(id = "create-issue-submit")
    private WebElement createIssueButton;
    @FindBy(xpath = "//*[@id=\"project-field\"]")
    private WebElement fieldProjectName;
    @FindBy(id = "project-field")
    private WebElement valueTrue;
    @FindBy(id = "issuetype-field")
    private WebElement fieldIssueTypeName;
    @FindBy(linkText = "Main Testing Project (MTP)")
    private WebElement findMTP;
    @FindBy(linkText = "Task")
    private WebElement findTask;
    @FindBy(css = ".issue-created-key.issue-link")
    private WebElement clickLink;
    @FindBy(className = "error")
    private WebElement errorMessage;
    @FindBy(xpath = "//*[@id=\"opsbar-operations_more\"]/span")
    private WebElement moreButton;
    @FindBy(xpath = "//*[@id=\"create-subtask\"]/a/span")
    private WebElement createSubTask;
    @FindBy(css = ".stsummary")
    private List<WebElement> summaries;

    public CreateIssuePage() {
        this.driver = getDriver();
        this.webDriverWait = getWait();
        this.mainNavBar = new MainNavBar();
        PageFactory.initElements(driver, this);
    }


    public void clickTheCreateButton() {
        mainNavBar.getCreateButton().click();
    }

    public void selectAProject(String project) {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(projectFieldButton)).click();
        projectFieldButton.sendKeys(project);
        projectFieldButton.sendKeys(Keys.TAB);
    }

    public void selectAnIssue(String issueType) {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(issueTypeField)).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(issueTypeField)).sendKeys(issueType);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(issueTypeField)).sendKeys(Keys.TAB);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(issueTypeField));
    }

    public void fillTheSummaryField(String summary) {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(summaryField)).click();
        summaryField.sendKeys(summary);
    }

    public void clickTheCreateIssueButton() {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(createIssueButton)).click();
    }

    public void clickTheCreatedIssueLink() {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(clickLink)).click();
    }

    public void clickOnMoreButton(String url) {
        driver.navigate().to(getBaseURL() + url);
        moreButton.click();
    }

    public void clickOnTheCreateSubTaskButton() {
        createSubTask.click();
    }

    public boolean checkTheSubTask() {
        for (int i = 0; i < summaries.size(); i++) {
            if (summaries.get(i).getText().equals("text Subtask")) {
                return true;
//                Assertions.assertEquals(summaries.get(i).getText(), "text Subtask");

            }
        }
        return false;
    }

    public WebElement getProjectFieldButton() {
        return projectFieldButton;
    }

    public WebElement getCreateIssueButton() {
        return createIssueButton;
    }

    public WebElement getWaitForIssueTypeFieldButton() {
        return waitForIssueTypeFieldButton;
    }

    public WebElement getIssueTypeField() {
        return issueTypeField;
    }

    public WebElement getSummaryField() {
        return summaryField;
    }

    public String getFieldProjectName() {
        webDriverWait.until(ExpectedConditions.visibilityOf(fieldProjectName));
        return fieldProjectName.getAttribute("value");
    }

    public String getFieldIssueTypeName() {
        return fieldIssueTypeName.getAttribute("value");
    }

    public WebElement getValueTrue() {
        return valueTrue;
    }

    public WebElement getFindMTP() {
        return findMTP;
    }

    public WebElement getFindTask() {
        return findTask;
    }

    public WebElement getClickLink() {
        return clickLink;
    }

    public WebElement getErrorMessage() {
        return webDriverWait.until(ExpectedConditions.visibilityOf(errorMessage));
    }
}
