import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.junit.jupiter.api.Assertions.*;

public class IssueTypeSchemeGlass {

    private Main main = new Main();
    private WebDriverWait webDriverWait = new WebDriverWait(main.getDriver(), 15);

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
    public void glassIssueType(){
        WebElement projectsButton = main.getDriver().findElement(By.xpath("//*[@id=\"browse_link\"]"));
        projectsButton.click();
        WebElement allProjects = main.getDriver().findElement(By.xpath("//*[@id=\"project_view_all_link_lnk\"]"));
        allProjects.click();
        WebElement privateProject1 = main.getDriver().findElement(By.xpath("//*[@id=\"projects\"]/div/table/tbody/tr[9]/td[1]/a"));
        privateProject1.click();
        WebElement projectSettings = webDriverWait.until(ExpectedConditions.elementToBeClickable(main.getDriver().findElement(By.xpath("//*[@id=\"content\"]/div[1]/div/div[2]/a[1]/span[1]"))));
        projectSettings.click();
        String issueTypeMessage = main.getDriver().findElement(By.xpath("//*[@id=\"project-config-scheme-name\"]")).getText();
        assertEquals(issueTypeMessage, "PP1: Scrum Issue Type Scheme");
        WebElement glassDocumentation = main.getDriver().findElement(By.xpath("//*[@id=\"content\"]/div[1]/div/div[1]/nav/div/div[2]/ul/li[7]"));
        glassDocumentation.click();
        WebElement schemesButton = main.getDriver().findElement(By.xpath("//*[@id=\"aui-uid-3\"]"));
        schemesButton.click();
        String issueTypes = main.getDriver().findElement(By.xpath("//*[@id=\"glass-general-schemes-panel\"]/div/table/tbody[1]/tr[1]/td[2]")).getText();
        assertEquals(issueTypes, "PP1: Scrum Issue Type Scheme");
    }
}
