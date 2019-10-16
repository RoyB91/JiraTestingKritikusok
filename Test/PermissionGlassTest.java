import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

public class PermissionGlassTest {

    private Main main = new Main();
    private ProjectPage projectPage = new ProjectPage(main.getDriver());

    @BeforeEach
    public void setup() {
        main.loginWithValidData();
        main.getDriver().manage().window().maximize();
    }

    @AfterEach
    public void close() {
        main.getDriver().quit();
    }

    @Test
    public void verifyPermission() {
        projectPage.clickOnProjects();
        projectPage.clickOnAllProjects();
        projectPage.clickOnPrivateProject1();
        projectPage.clickOnProjectSettings();
        projectPage.clickOnPermissions();
        assertEquals(projectPage.getBrowseProjectPermissionProjectSettings(), "Any logged in user");
        assertEquals(projectPage.getCreateIssuesPermissionProjectSettings(), "Any logged in user");
        assertEquals(projectPage.getEditIssuesPermissionProjectSettings(), "Any logged in user");
        WebElement glassDocumentation = main.getDriver().findElement(By.xpath("//*[@id=\"content\"]/div[1]/div/div[1]/nav/div/div[2]/ul/li[7]"));
        glassDocumentation.click();
        WebElement permissions2 = main.getDriver().findElement(By.xpath("//*[@id=\"glass-permissions-nav\"]/a"));
        permissions2.click();
        Boolean browseCheckMark = main.getDriver().findElement(By.xpath("//*[@id=\"glass-permissions-panel\"]/div/table/tbody/tr[5]/td[3]/div")).isDisplayed();
        assertTrue(browseCheckMark);
        Boolean createCheckMark = main.getDriver().findElement(By.xpath("//*[@id=\"glass-permissions-panel\"]/div/table/tbody/tr[8]/td[3]/div\n")).isDisplayed();
        assertTrue(createCheckMark);
        Boolean editCheckMark = main.getDriver().findElement(By.xpath("//*[@id=\"glass-permissions-panel\"]/div/table/tbody/tr[18]/td[3]/div")).isDisplayed();
        assertTrue(editCheckMark);
    }
}
