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


    public void clickProjectsDropdown() {
        waitForClickable(projectsButton);
        projectsButton.click();

    }

    public void clickViewAllProjects() {
        waitForClickable(viewAllProjectsButton);
        viewAllProjectsButton.click();

    }

    public void clickGivenProject(String project) {
        waitForClickable(browseProject(project));
        browseProject(project).click();

    }

    public void clickBusinessProjects() {
        waitForClickable(businessProjects);
        businessProjects.click();

    }

    public void clickProjectSummary(String project) {
        waitForClickable(projectSummary(project));
        projectSummary(project).click();
        waitForVisible(projectActivity());

    }

    public void waitForClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));

    }

    public void waitForVisible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));

    }

    public boolean isElementDisplayed(WebElement element) {
        return element.isDisplayed();

    }


    public WebElement browseProject(String projectName) {
        if (projectName.equals("TOUCAN"))
            return driver.findElement(By.linkText(projectName + " projekt"));

        return driver.findElement(By.linkText(projectName + " Project"));

    }

    public WebElement projectActivity() {
        return driver.findElement(By.xpath("//*[@title='Activity']"));

    }

    public WebElement projectSummary(String projectName) {
        return driver.findElement(By.xpath("//a[@href='/projects/" + projectName + "/summary']"));

    }

    @FindBy(id = "browse_link") private WebElement projectsButton;

    @FindBy(id = "project_view_all_link_lnk") private WebElement viewAllProjectsButton;

    @FindBy(id = "project_type_business_lnk") private WebElement businessProjects;

}
