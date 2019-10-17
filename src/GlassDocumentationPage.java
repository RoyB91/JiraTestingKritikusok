import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

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

    @FindBy(xpath = "//a[@data-target='permissions']")
    private WebElement permissionsPart;

    private WebElement permissionCheckMark;
    private List<WebElement> list;

    GlassDocumentationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void permissionMatrix(String permissionType) {
        permissionCheckMark = driver.findElement(By.xpath("//tr[@class='permtr']//*[text()='" + permissionType + "']/./.././../../.."));
    }

    public void checkPermissionList() {
        list = permissionCheckMark.findElements(By.className("td-icon"));
    }

    public boolean getCheckMark(String permissionType) {
        permissionMatrix(permissionType);
        checkPermissionList();
        try {
            list.get(1).findElement(By.className("glass-true-icon"));
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
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

    public String getIssueTypeNameText() {
        return issueTypeName.getText();
    }

    public void goToPermissions() {
        permissionsPart.click();
    }
}
