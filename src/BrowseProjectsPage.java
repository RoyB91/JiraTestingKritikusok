import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BrowseProjectsPage {

    private WebDriver driver;
    private WebDriverWait wait;


    BrowseProjectsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 15);
        PageFactory.initElements(driver, this);

    }

    public WebElement projectActivity() {
        return driver.findElement(By.xpath("//*[@title='Activity']"));

    }

    public WebElement projectSummary(String projectName) {
        return driver.findElement(By.xpath("//a[@href='/projects/" + projectName + "/summary']"));

    }

    public WebElement browseProject(String projectName) {
        if (projectName.equals("TOUCAN"))
            return driver.findElement(By.linkText(projectName + " projekt"));

        return driver.findElement(By.linkText(projectName + " Project"));

    }

    public void clickProjectSummary(String project) {
        projectSummary(project).click();
        wait.until(ExpectedConditions.visibilityOf(projectActivity()));

    }


    public void clickProjectsDropdown() {
        getProjectsButton().click();
        wait.until(ExpectedConditions.elementToBeClickable(getViewAllProjectsButton()));

    }

    public void clickViewAllProjects(String project) {
        getViewAllProjectsButton().click();
        wait.until(ExpectedConditions.elementToBeClickable(browseProject(project)));

    }

    public void clickGivenProject(String project) {
        browseProject(project).click();
        wait.until(ExpectedConditions.visibilityOf(browseProject(project)));

    }

    public void clickBusinessProjects(String project) {
        getBusinessProjects().click();
        wait.until(ExpectedConditions.elementToBeClickable(browseProject(project)));

    }

    @FindBy(id = "browse_link") private WebElement projectsButton;

    @FindBy(id = "project_view_all_link_lnk") private WebElement viewAllProjectsButton;

    @FindBy(id = "project_type_business_lnk") private WebElement businessProjects;

    public WebElement getProjectsButton() {
        return projectsButton;
    }

    public WebElement getViewAllProjectsButton() { return viewAllProjectsButton; }

    public WebElement getBusinessProjects() {
        return businessProjects;
    }



}
