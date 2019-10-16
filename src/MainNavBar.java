import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainNavBar {

    private WebDriver driver;
    @FindBy(id = "create_link")
    private WebElement createButton;
    @FindBy(className = "aui-avatar-inner")
    private WebElement profilePictureButton;
    @FindBy(id = "log_out")
    private WebElement logOutButton;
    @FindBy(id = "browse_link")
    private WebElement projectsButton;
    @FindBy(id = "project_view_all_link_lnk")
    private WebElement allProjects;

    MainNavBar(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public WebElement getCreateButton() {
        return createButton;
    }

    public WebElement getProfilePictureButton() {
        return profilePictureButton;
    }

    public WebElement getLogOutButton() {
        return logOutButton;
    }

    public WebElement getProjectsButton() {
        return projectsButton;
    }

    public WebElement getAllProjects() {
        return allProjects;
    }
}
