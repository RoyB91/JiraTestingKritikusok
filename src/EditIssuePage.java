import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

class EditIssuePage {

    private WebDriver driver;


    @FindBy(id = "summary")
    private WebElement summaryField;

    @FindBy(id = "edit-issue-submit")
    private WebElement updateButton;

    @FindBy(xpath = "//*[@class='cancel']")
    private WebElement cancelButton;

    @FindBy(className = "jira-dialog-content")
    private WebElement jiraDialogContent;

    @FindBy(className = "error")
    private WebElement errorField;


    EditIssuePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void click(WebElement element) {
        element.click();
    }

    public void fillText(WebElement element, String text) {
        element.sendKeys(text);
    }

    public void clearField(WebElement element) {
        element.clear();
    }

    public void resetIssueSummary(WebDriver driver, String url, String defaultTestText) {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        IssuePage issuePage = new IssuePage(driver);
        driver.navigate().to(url);
        click(issuePage.getEditIssueButton());
        wait.until(ExpectedConditions.visibilityOf(getJiraDialogContent()));

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        clearField(summaryField);
        fillText(summaryField, defaultTestText);
        click(updateButton);
    }

    public WebElement getSummaryField() {
        return summaryField;
    }

    public WebElement getUpdateButton() {
        return updateButton;
    }

    public WebElement getJiraDialogContent() {
        return jiraDialogContent;
    }

    public WebElement getErrorField() {
        return errorField;
    }

    public WebElement getCancelButton() {
        return cancelButton;
    }
}
