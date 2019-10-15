import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import java.util.List;
import java.util.concurrent.TimeUnit;

public class ComponentPage {

    private WebDriver driver;

    @FindBy(name = "name")
    private WebElement componentFieldName;

    @FindBy(id = "assigneeType-field")
    private WebElement assigneeField;

    @FindBy(className = "components-add__confirm")
    private WebElement addButton;


    @FindBy(xpath = "//*[@id='components-table']//*[text()='Kritikus komponens']")
    private WebElement testComponent;


    @FindBy(css = "[data-link-id='com.codecanvas.glass:glass']")
    private WebElement glassDocumentationButton;

    ComponentPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void click(WebElement element) {
        element.click();
    }

    public void fillText(WebElement element, String text) {
        element.sendKeys(text);
    }

    public void enterKeys(WebElement element, Keys key) {
        element.sendKeys(key);
    }

    public void deleteComponent(WebDriver driver, String url) {

        driver.navigate().to(url);

        WebElement parentElement = testComponent.findElement(By.xpath("../../.."));
        String id = parentElement.getAttribute("data-component-id");

        WebElement dropShit = parentElement.findElement(By.className("dynamic-table__actions")).findElement(By.partialLinkText("Operation"));
        dropShit.click();

        WebElement deleteButton = dropShit.findElement(By.xpath("//*[@id=\"deletecomponent_" + id + "\"]"));

        deleteButton.click();

        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        WebElement submit = driver.findElement(By.xpath("//*[@id=\"submit\"]"));
        submit.click();

    }


    public WebElement getComponentFieldName() {
        return componentFieldName;
    }

    public WebElement getAssigneeField() {
        return assigneeField;
    }

    public WebElement getAddButton() {
        return addButton;
    }

    public WebElement getGlassDocumentationButton() {
        return glassDocumentationButton;
    }
}
