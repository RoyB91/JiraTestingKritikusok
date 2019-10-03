import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.event.KeyEvent;

public class BrowseIssueSrc {


    public void detailsOfTasks(WebDriver driver) {
        driver.navigate().to("https://jira.codecool.codecanvas.hu/browse/SAND-25?filter=-3&jql=project%20%3D%20SAND");
    }

    public void projectJetiContainsIssue(WebDriver driver, String projectName) {
        driver.navigate().to("https://jira.codecool.codecanvas.hu/browse/" + projectName);
    }

    public void projectCoalaContainsIssue(WebDriver driver, String projectName) {
        driver.navigate().to("https://jira.codecool.codecanvas.hu/browse/" + projectName);
    }

    public void projectToucanContainsIssue(WebDriver driver, String projectName) {
        driver.navigate().to("https://jira.codecool.codecanvas.hu/browse/" + projectName);
    }

    public void browseExistingIssue(WebDriver driver) throws AWTException {
        WebDriverWait webDriverWait = new WebDriverWait(driver, 15);
        driver.navigate().to("https://jira.codecool.codecanvas.hu/browse/WEAKS-60?filter=-3&jql=");
        WebElement selectProject = driver.findElement(By.xpath("//*[@id=\"content\"]/div[1]/div[3]/div/form/div[1]/div[1]/div[1]/div[1]/div/div[1]/ul/li[1]/div/div"));
        selectProject.click();
        WebElement selectPp1 = driver.findElement(By.xpath("//*[@id=\"10005-1\"]/label"));
        selectPp1.click();
        WebElement selectProject1 = webDriverWait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@id=\"content\"]/div[1]/div[3]/div/form/div[1]/div[1]/div[1]/div[1]/div/div[1]/ul/li[1]/div[1]"))));
        selectProject1.click();
        Actions actions = new Actions(driver);
        WebElement selectPP136 = driver.findElement(By.xpath("//*[@id=\"content\"]/div[1]/div[3]/div/div/div/div/div/div/div/div[1]/div[1]/div/div[1]/div[2]/div/ol/li[21]"));
        actions.contextClick(selectPP136).perform();
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_DOWN);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
    }
}
