import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.event.KeyEvent;

public class BrowseIssuePage {

    private WebDriver driver;
    private WebDriverWait webDriverWait;

    @FindBy(xpath = "//*[@id=\"summary-val\"]\n")
    private WebElement errorMessage;
    @FindBy(className = "fieldLabel")
    private WebElement selectProject;
    @FindBy(id = "fieldpid")
    private WebElement clickOnProjectAgain;
    @FindBy(xpath = "//label[@title='Private Project 1']")
    private WebElement selectPp1;
    @FindBy(xpath = "//li[@data-key='PP1-36']")
    private WebElement selectPP136;
    @FindBy(id = "summary-val")
    private WebElement issueTitleText;

    BrowseIssuePage(WebDriver driver){
        this.driver = driver;
        this.webDriverWait = new WebDriverWait(driver, 15);
        PageFactory.initElements(driver, this);
    }


    public void detailsOfTasks(WebDriver driver) {
        driver.navigate().to("https://jira.codecool.codecanvas.hu/browse/SAND-25?filter=-3&jql=project%20%3D%20SAND");
    }

    public void navigateToAnIssue(WebDriver driver, String projectName) {
        driver.navigate().to("https://jira.codecool.codecanvas.hu/browse/" + projectName);
    }

    public boolean issueTitleIsDisplayed() {
        try {
            errorMessage.isDisplayed();
            return true;
        } catch (ElementNotVisibleException | NoSuchElementException e){
            return false;
        }
    }

    public void navigateTo() {
        driver.navigate().to("https://jira.codecool.codecanvas.hu/browse/WEAKS-60?filter=-3&jql=");

    }

    public void selectProject(){
        selectProject.click();
    }

    public void setSelectPp1(){
        webDriverWait.until(ExpectedConditions.elementToBeClickable(selectPp1)).click();
    }

    public void clickOnProjectAgain(){
        webDriverWait.until(ExpectedConditions.elementToBeClickable(clickOnProjectAgain)).click();
    }


    public void rightClickOnIssue() throws AWTException{
        Actions actions = new Actions(driver);
        actions.contextClick(selectPP136).perform();
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_DOWN);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
    }
}
