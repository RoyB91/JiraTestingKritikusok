import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class BrowseProjects {

    private Main main = new Main();
    private WebDriver driver = main.getDriver();
    private WebDriverWait wait = new WebDriverWait(driver, 15);
    private BrowseProjectsPage browseProjectsPage = new BrowseProjectsPage();

    @BeforeEach
    public void login() {
        driver.manage().window().maximize();
        main.loginWithValidData();
    }

    @AfterEach
    public void close() {
        driver.close();

    }

    @Test
    public void viewAllProjects() {
        driver.get("https://jira.codecool.codecanvas.hu/secure/Dashboard.jspa");
        browseProjectsPage.getProjectsButton(driver).click();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        //wait.until(ExpectedConditions.elementToBeClickable(browseProjectsPage.getViewAllProjectsButton(driver)));
        browseProjectsPage.getViewAllProjectsButton(driver).click();
        wait.until(ExpectedConditions.elementToBeClickable(browseProjectsPage.getCoalaProject(driver)));
        browseProjectsPage.getCoalaProject(driver).click();

        wait.until(ExpectedConditions.visibilityOf(browseProjectsPage.getCoalaNameDisplayed(driver)));
        assertTrue(browseProjectsPage.getCoalaNameDisplayed(driver).isDisplayed());
        assertTrue(browseProjectsPage.getCoalaAvatarDisplayed(driver).isDisplayed());

    }

    @Test
    public void viewBusinessProjects() {
        driver.get("https://jira.codecool.codecanvas.hu/secure/Dashboard.jspa");
        browseProjectsPage.getProjectsButton(driver).click();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        //wait.until(ExpectedConditions.elementToBeClickable(browseProjectsPage.getBusinessProjects(driver)));
        browseProjectsPage.getBusinessProjects(driver).click();
        wait.until(ExpectedConditions.elementToBeClickable(browseProjectsPage.getToucanProject(driver)));
        browseProjectsPage.getToucanProject(driver).click();

        wait.until(ExpectedConditions.visibilityOf(browseProjectsPage.getToucanName(driver)));
        assertTrue(browseProjectsPage.getToucanName(driver).isDisplayed());
        assertTrue(browseProjectsPage.getToucanAvatar(driver).isDisplayed());

    }

    @Test
    public void viewCoalaProject() {
        driver.get("https://jira.codecool.codecanvas.hu/secure/Dashboard.jspa");
        browseProjectsPage.getProjectsButton(driver).click();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        //wait.until(ExpectedConditions.elementToBeClickable(browseProjectsPage.getViewAllProjectsButton(driver)));
        browseProjectsPage.getViewAllProjectsButton(driver).click();

        wait.until(ExpectedConditions.elementToBeClickable(browseProjectsPage.getCoalaProject(driver)));
        browseProjectsPage.getCoalaProject(driver).click();

        wait.until(ExpectedConditions.elementToBeClickable(browseProjectsPage.getCoalaSummary(driver)));
        browseProjectsPage.getCoalaSummary(driver).click();
        wait.until(ExpectedConditions.visibilityOf(browseProjectsPage.getCoalaActivity(driver)));
        assertTrue(browseProjectsPage.getCoalaActivity(driver).isDisplayed());

    }

    @Test
    public void viewToucanProject() {
        driver.get("https://jira.codecool.codecanvas.hu/secure/Dashboard.jspa");
        browseProjectsPage.getProjectsButton(driver).click();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        //wait.until(ExpectedConditions.elementToBeClickable(browseProjectsPage.getViewAllProjectsButton(driver)));
        browseProjectsPage.getViewAllProjectsButton(driver).click();

        wait.until(ExpectedConditions.elementToBeClickable(browseProjectsPage.getToucanProjectFromAllProjects(driver)));
        browseProjectsPage.getToucanProjectFromAllProjects(driver).click();

        wait.until(ExpectedConditions.elementToBeClickable(browseProjectsPage.getToucanSummary(driver)));
        browseProjectsPage.getToucanSummary(driver).click();
        wait.until(ExpectedConditions.visibilityOf(browseProjectsPage.getToucanActivity(driver)));
        assertTrue(browseProjectsPage.getToucanActivity(driver).isDisplayed());

    }

    @Test
    public void viewJetiProject() {
        driver.get("https://jira.codecool.codecanvas.hu/secure/Dashboard.jspa");
        browseProjectsPage.getProjectsButton(driver).click();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        //wait.until(ExpectedConditions.elementToBeClickable(browseProjectsPage.getViewAllProjectsButton(driver)));
        browseProjectsPage.getViewAllProjectsButton(driver).click();

        wait.until(ExpectedConditions.elementToBeClickable(browseProjectsPage.getJetiProjectFromAllProjects(driver)));
        browseProjectsPage.getJetiProjectFromAllProjects(driver).click();

        wait.until(ExpectedConditions.elementToBeClickable(browseProjectsPage.getJetiSummary(driver)));
        browseProjectsPage.getJetiSummary(driver).click();
        wait.until(ExpectedConditions.visibilityOf(browseProjectsPage.getJetiActivity(driver)));
        assertTrue(browseProjectsPage.getJetiActivity(driver).isDisplayed());

    }

}