import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateIssuePage {

    private WebDriver driver;
    private MainNavBar mainNavBar;
    @FindBy(id = "project-field")
    WebElement projectFieldButton;
    @FindBy(xpath = "//*[@id=\"issuetype-single-select\"]/span")
    WebElement waitForIssueTypeFieldButton;
    @FindBy(xpath = "//*[@id=\"issuetype-field\"]")
    private WebElement issueTypeField;
    @FindBy(id = "summary")
    WebElement summaryField;
    @FindBy(id = "create-issue-submit")
    WebElement createIssueButton;
    @FindBy(xpath = "//*[@id=\"project-field\"]")
    WebElement fieldProjectName;
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

    CreateIssuePage(WebDriver driver) {
        this.driver = driver;
        this.mainNavBar = new MainNavBar(driver);
        PageFactory.initElements(driver, this);
    }

    public void clickElement(WebElement element) {
        element.click();
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
        return errorMessage;
    }
}
