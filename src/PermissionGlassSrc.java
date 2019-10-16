import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PermissionGlassSrc {

    private WebDriver driver;
    private WebDriverWait webDriverWait;
    private MainNavBar mainNavBar;
    @FindBy(xpath = "//a[@original-title='Private Project 1']")
    private WebElement privateProject1;
    @FindBy(className = "aui-button aui-button-subtle aui-sidebar-settings-button")
    private WebElement projectSettings;
    @FindBy(id = "view_project_permissions")
    private WebElement permissions;
    @FindBy(xpath = "//tr[@data-permission-key='BROWSE_PROJECTS']//following-sibling::dd")
    private WebElement browseProjectPermissionProjectSettings;
    @FindBy(xpath = "//tr[@data-permission-key='CREATE_ISSUES']//following-sibling::dd")
    private WebElement createIssuesPermissionProjectSettings;
    @FindBy(xpath = "//tr[@data-permission-key='EDIT_ISSUES']//following-sibling::dd")
    private WebElement editIssuesPermissionProjectSettings;

    PermissionGlassSrc(WebDriver driver){
        this.driver = driver;
        this.mainNavBar = new MainNavBar(driver);
        this.webDriverWait = new WebDriverWait(driver, 15);
        PageFactory.initElements(driver, this);
    }

    public void clickOnProjects(){
        mainNavBar.getProjectsButton().click();
    }

    public void clickOnAllProjects(){
        mainNavBar.getAllProjects().click();
    }

    public void clickOnPrivateProject1(){
        privateProject1.click();
    }

    public void clickOnProjectSettings(){
        driver.navigate().to("https://jira.codecool.codecanvas.hu/plugins/servlet/project-config/PP1/summary");
    }

    public void clickOnPermissions(){
        permissions.click();
    }

    public String getBrowseProjectPermissionProjectSettings() {
        return browseProjectPermissionProjectSettings.getText();
    }

    public String getCreateIssuesPermissionProjectSettings() {
        return createIssuesPermissionProjectSettings.getText();
    }

    public String getEditIssuesPermissionProjectSettings() {
        return editIssuesPermissionProjectSettings.getText();
    }
}
