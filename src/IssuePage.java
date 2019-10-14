import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class IssuePage {

    private WebDriver driver;

    @FindBy(id = "edit-issue")
    private WebElement editIssueButton;

    @FindBy(id = "summary-val")
    private WebElement issueSummaryName;

    @FindBy(id = "aui-flag-container")
    private WebElement alertPopUp;

    private String defaultSummaryText = "summary";

    IssuePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void click(WebElement element) {
        element.click();
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
