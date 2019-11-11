package main.java;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class GlassDocumentationPage extends BasePage {

    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(xpath = "//*[@id='components-table']//*[@class='items']")
    private WebElement table;

    @FindBy(xpath = "//span[@title='Glass Documentation']")
    private WebElement glassDocumentationButton;

    @FindBy(id = "aui-uid-3")
    private WebElement schemePage;

    @FindBy(id = "aui-uid-2")
    private WebElement versions;

    @FindBy(xpath = "//*[@class='project-meta-label' and text()='Issue Type Scheme']")
    private WebElement issueTypeSchemeRow;

    @FindBy(xpath = "//*[@class='project-meta-label' and text()='Issue Type Scheme']/following-sibling::td")
    private WebElement issueTypeName;

    @FindBy(xpath = "//a[@data-target='permissions']")
    private WebElement permissionsPart;

    @FindBy(xpath = "//td[@class='versions-table__name']//a[text()='kritikusok1.0']")
    private WebElement testVersions;

    @FindBy(xpath = "//div[@class='aui-page-header-main']//h2")
    private WebElement validateText;

    private WebElement permissionCheckMark;
    private List<WebElement> list;

    public GlassDocumentationPage(){
        this.driver = getDriver();
        this.wait = getWait();
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
        wait.until(ExpectedConditions.visibilityOf(glassDocumentationButton));
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

    public void goToVersions(String url) {
        driver.navigate().to(getBaseURL() + url);
        versions.click();
    }

    public void clickOnSpecifiedVersion() {
        testVersions.click();
    }

    public String getValidateText() {
        return validateText.getText();
    }

    public void checkVersionTextOnOtherTab() {
        ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs2.get(1));
    }
}
