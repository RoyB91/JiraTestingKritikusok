import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GlassDocumentationPage {

    private WebDriver driver;

    @FindBy(xpath = "//*[@id='components-table']//*[@class='items']")
    private WebElement table;

    @FindBy(css = "[data-link-id='com.codecanvas.glass:glass']")
    private WebElement glassDocumentationButton;

    @FindBy(id = "aui-uid-3")
    private WebElement schemePage;


    @FindBy(xpath = "//*[@class='project-meta-label' and text()='Issue Type Scheme']")
    private WebElement issueTypeSchemeRow;

    @FindBy(xpath = "//*[@class='project-meta-label' and text()='Issue Type Scheme']/following-sibling::td")
    private WebElement issueTypeName;

    GlassDocumentationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void goToGlassDocumentationPage() {
        glassDocumentationButton.click();
    }


    public String testComponentName() {
        return driver.findElement(By.xpath("//*[contains(text(), 'Kritikus komponens')]")).getAttribute("innerText");
    }
    public void goToSchemes() {
        schemePage.click();
    }

    public String  getIssueTypeNameText() {
        return issueTypeName.getText();
    }
}
