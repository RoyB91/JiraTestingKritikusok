import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

public class IssueTypeSchemeGlass {

    private Main main = new Main();
    private WebDriverWait webDriverWait = new WebDriverWait(main.getDriver(), 15);

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
    public void glassIssueType() {
//        WebElement projectsButton = main.getDriver().findElement(By.xpath("//*[@id=\"browse_link\"]"));
//        projectsButton.click();
//        main.getDriver().manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
//
//        WebElement allProjects = main.getDriver().findElement(By.xpath("//*[@id=\"project_view_all_link_lnk\"]"));
//        allProjects.click();
//        WebElement privateProject1 = main.getDriver().findElement(By.xpath("//*[@id=\"projects\"]/div/table/tbody/tr[9]/td[1]/a"));
//        privateProject1.click();
//
//        main.getDriver().manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
//
//        WebElement projectSettings = main.getDriver().findElement(By.xpath("//*[@id=\"content\"]/div[1]/div/div[2]"));
//        projectSettings.click();
//
//
//
//        WebElement issueButton = main.getDriver().findElement(By.xpath("//*[@id=\"project-issuetypes-summary\"]"));
//        issueButton.click();
//
//        String issueTypeMessage = main.getDriver().findElement(By.xpath("//*[@id=\"project-config-scheme-name\"]")).getText();
//
//        assertEquals(issueTypeMessage, "PP1: Scrum Issue Type Scheme");
//
//        WebElement glassDocumentation = main.getDriver().findElement(By.xpath("//*[@id=\"content\"]/div[1]/div/div[1]/nav/div/div[2]/ul/li[7]/a/span[2]"));
//        glassDocumentation.click();
//        WebElement schemesButton = main.getDriver().findElement(By.xpath("//*[@id=\"aui-uid-3\"]"));
//        schemesButton.click();
//        String issueTypes = main.getDriver().findElement(By.xpath("//*[@id=\"glass-general-schemes-panel\"]/div/table/tbody[1]/tr[1]/td[2]")).getText();
//        assertEquals(issueTypes, "PP1: Scrum Issue Type Scheme");
    }
}
