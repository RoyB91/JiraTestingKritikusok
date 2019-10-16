import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class ProjectPage {


    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(id = "project-issuetypes-summary")
    private WebElement issueTypeSummaryButton;

    @FindBy(className = "project-config-scheme-name")
    private WebElement issueTypeMessageName;


    @FindBy(id = "project-config-panel-issuetypes")
    private WebElement issueTypesTab;

    @FindBy(xpath = "//*[@id='project-config-header-name']")
    private WebElement projectSettingHeader;


    ProjectPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 15);
        PageFactory.initElements(driver, this);

    }

    public boolean checkIfProjectHasIssueName(String url, String issueTypeName) {
        driver.navigate().to(url);

        wait.until(ExpectedConditions.visibilityOf(projectSettingHeader));
        if (issueTypeName.equals(issueTypeMessageName.getText())) {
            return true;
        } else {
            return false;
        }

    }

    public String getIssueTypeMessageText() {

        return issueTypeMessageName.getText();
    }

}
