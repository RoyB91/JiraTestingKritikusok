package main.java;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class EditIssuePage extends BasePage {

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

    private UtilRandom random;

    public EditIssuePage(IssuePage issuePage) {
        this.driver = getDriver();
        this.wait = getWait();
        this.issuePage = issuePage;
        this.random = new UtilRandom();
        PageFactory.initElements(driver, this);
    }


    public String getRandomUUID() {
        return random.getRandomGeneratedTestData();
    }

    public void clearSummaryText() {
        getSummaryField().clear();
    }

    public void pressCancelAndConfirm() {
        getCancelButton().click();
        driver.switchTo().alert().accept();
    }

    public void fillSummaryText() {
        getSummaryField().clear();
        getSummaryField().sendKeys(random.getRandomGeneratedTestData());
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
