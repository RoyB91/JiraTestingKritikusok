import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.junit.jupiter.api.Assertions.*;

public class PermissionGlassTest {

    private Main main = new Main();
    private WebDriverWait webDriverWait = new WebDriverWait(main.getDriver(), 20);

    @BeforeEach
    public void setup(){
        main.loginWithValidData();
        main.getDriver().manage().window().maximize();
    }

    @AfterEach
    public void close(){
        main.getDriver().close();
    }

    @Test
    public void verifyPermission(){
        WebElement projectsButton = main.getDriver().findElement(By.xpath("//*[@id=\"browse_link\"]"));
        projectsButton.click();
        WebElement allProjects = main.getDriver().findElement(By.xpath("//*[@id=\"project_view_all_link_lnk\"]"));
        allProjects.click();
        WebElement privateProject1 = main.getDriver().findElement(By.xpath("//*[@id=\"projects\"]/div/table/tbody/tr[9]/td[1]/a"));
        privateProject1.click();
        WebElement projectSettings = webDriverWait.until(ExpectedConditions.elementToBeClickable(main.getDriver().findElement(By.xpath("//*[@id=\"content\"]/div[1]/div/div[2]/a[1]/span[1]"))));
        projectSettings.click();
        WebElement permissions = main.getDriver().findElement(By.xpath("//*[@id=\"view_project_permissions\"]"));
        permissions.click();
        String grantedToText = main.getDriver().findElement(By.xpath("//*[@id=\"project-config-panel-permissions\"]/jira-permissions-table/div[1]/table/tbody/tr[2]/td[2]/dl")).getText();
        assertEquals(grantedToText, "application access\n" +
                "Any logged in user");
        WebElement glassDocumentation = main.getDriver().findElement(By.xpath("//*[@id=\"content\"]/div[1]/div/div[1]/nav/div/div[2]/ul/li[7]"));
        glassDocumentation.click();
        WebElement permissions2 = main.getDriver().findElement(By.xpath("//*[@id=\"glass-permissions-nav\"]/a"));
        permissions2.click();
        Boolean checkMark = main.getDriver().findElement(By.xpath("//*[@id=\"glass-permissions-panel\"]/div/table/tbody/tr[5]/td[3]/div")).isDisplayed();
        assertTrue(checkMark);
    }
}
