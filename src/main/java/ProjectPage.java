package main.java;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class ProjectPage extends BasePage {


    private WebDriver driver;
    private WebDriverWait wait;
    private MainNavBar mainNavBar;

    @FindBy(id = "project-issuetypes-summary")
    private WebElement issueTypeSummaryButton;

    @FindBy(className = "project-config-scheme-name")
    private WebElement issueTypeMessageName;


    @FindBy(id = "project-config-panel-issuetypes")
    private WebElement issueTypesTab;

    @FindBy(xpath = "//*[@id='project-config-header-name']")
    private WebElement projectSettingHeader;


    @FindBy(xpath = "//a[@original-title='Private Project 1']")
    private WebElement privateProject1;

    @FindBy(id = "view_project_permissions")
    private WebElement permissions;

    private WebElement permissionText;


    public ProjectPage() {
        this.driver = getDriver();
        this.mainNavBar = new MainNavBar();
        this.wait = getWait();
        PageFactory.initElements(driver, this);

    }

    public boolean checkIfProjectHasIssueName(String url, String issueTypeName) {
        driver.navigate().to(getBaseURL() + url);
        wait.until(ExpectedConditions.visibilityOf(projectSettingHeader));
        return issueTypeName.equals(issueTypeMessageName.getText());

    }

    public String permissionMatrix(String permissionType) {
        permissionText = driver.findElement(By.xpath("//tr[@data-permission-key='" + permissionType + "']//following-sibling::dd"));
        return permissionText.getText();
    }


    public void clickOnProjects() {
        mainNavBar.getProjectsButton().click();
    }

    public void clickOnAllProjects() {

        wait.until(ExpectedConditions.elementToBeClickable(mainNavBar.getAllProjects())).click();
    }

    public void clickOnPrivateProject1() {
        privateProject1.click();
    }

    public void clickOnProjectSettings() {
        driver.navigate().to("https://jira.codecool.codecanvas.hu/plugins/servlet/project-config/PP1/summary");
    }

    public void clickOnPermissions(String url) {
        driver.navigate().to(getBaseURL() + url);
        permissions.click();
    }
}
