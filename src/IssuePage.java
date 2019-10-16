import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class IssuePage {

    private WebDriver driver;
    private WebDriverWait wait;
    private EditIssuePage editIssuePage;


    @FindBy(id = "edit-issue")
    private WebElement editIssueButton;

    @FindBy(id = "summary-val")
    private WebElement issueSummaryName;

    @FindBy(id = "aui-flag-container")
    private WebElement alertPopUp;

    private String defaultSummaryText = "summary";

    IssuePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 15);
        editIssuePage = new EditIssuePage(driver, this);
        PageFactory.initElements(driver, this);
    }

    public void click(WebElement element) {
        element.click();
    }


    public void clickEditIssueButton() {
        getEditIssueButton().click();
        wait.until(ExpectedConditions.visibilityOf(editIssuePage.getJiraDialogContent()));

    }

    public boolean isEditIssueButtonIsThere() {
        try {
            getEditIssueButton().isDisplayed();
            return true;
        } catch (ElementNotVisibleException | NoSuchElementException e) {
            return false;
        }
    }

    public String getIssueSummaryNameText() {
        return issueSummaryName.getText();
    }

    public WebElement getEditIssueButton() {
        return editIssueButton;
    }


    public WebElement getAlertPopUp() {
        return alertPopUp;
    }

    public String getDefaultSummaryText() {
        return defaultSummaryText;
    }
}
