import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

class EditIssuePage {

    private WebDriver driver;
    private IssuePage issuePage;
    private WebDriverWait wait;

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


    EditIssuePage(WebDriver driver, IssuePage issuePage) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 15);
        this.issuePage = issuePage;
        PageFactory.initElements(driver, this);
    }

    public void clearSummaryText() {
        getSummaryField().clear();
    }

    public void pressCancelAndConfirm() {
        getCancelButton().click();

        driver.switchTo().alert().accept();

    }

    public void fillSummaryText(String text) {
        getSummaryField().clear();
        getSummaryField().sendKeys(text);
    }

    public void pressUpdateButton() {
        getUpdateButton().click();
        wait.until(ExpectedConditions.visibilityOf(issuePage.getAlertPopUp()));
    }

    public void pressUpdateWithEmptyFields() {
        getSummaryField().clear();
        getUpdateButton().click();
        wait.until(ExpectedConditions.visibilityOf(getErrorField()));

    }


    public void resetIssueSummary(WebDriver driver, String url, String defaultTestText) {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        IssuePage issuePage = new IssuePage(driver);
        driver.navigate().to(url);
        issuePage.getEditIssueButton().click();
        wait.until(ExpectedConditions.visibilityOf(getJiraDialogContent()));

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        summaryField.clear();
        summaryField.sendKeys(defaultTestText);
        updateButton.click();
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
