package main.java;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

public class ComponentPage extends BasePage {

    private WebDriver driver;

    @FindBy(name = "name")
    private WebElement componentFieldName;

    @FindBy(id = "assigneeType-field")
    private WebElement assigneeField;

    @FindBy(className = "components-add__confirm")
    private WebElement addButton;


    @FindBy(xpath = "//*[@id='components-table']//*[text()='Kritikus komponens']")
    private WebElement testComponent;

    public ComponentPage(){
        this.driver = getDriver();
        PageFactory.initElements(driver, this);
    }

    public void deleteComponent(String url) {

        driver.navigate().to(getBaseURL() + url);

        WebElement parentElement = testComponent.findElement(By.xpath("../../.."));
        String id = parentElement.getAttribute("data-component-id");

        WebElement dropDown = parentElement.findElement(By.className("dynamic-table__actions")).findElement(By.partialLinkText("Operation"));
        dropDown.click();

        WebElement deleteButton = dropDown.findElement(By.xpath("//*[@id='deletecomponent_" + id + "']"));
        deleteButton.click();

        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        WebElement submit = driver.findElement(By.xpath("//*[@id='submit']"));
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


    public void createComponent(String url, String compName, String assignee) {
        driver.navigate().to(getBaseURL() + url);
        componentFieldName.sendKeys(compName);
        assigneeField.sendKeys(assignee);
        assigneeField.sendKeys(Keys.ENTER);

        addButton.click();
    }
}
