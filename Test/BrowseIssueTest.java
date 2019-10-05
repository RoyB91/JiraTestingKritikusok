import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.*;

public class BrowseIssueTest {

    private Main main = new Main();
    private BrowseIssueSrc browseIssueSrc = new BrowseIssueSrc();

    @BeforeEach
    public void setup(){
        main.loginWithValidData();
        main.getDriver().manage().window().maximize();
    }

    @AfterEach
    public void close(){
        main.getDriver().quit();
    }

    @Test
    public void detailsOfTasks(){
        browseIssueSrc.detailsOfTasks(main.getDriver());
    }

    @Test
    public void projectJetiContainsIssue(){
        browseIssueSrc.projectJetiContainsIssue(main.getDriver(), "JETI-2");
        String errorMessage = main.getDriver().findElement(By.xpath("//*[@id=\"summary-val\"]\n")).getText();
        assertEquals(errorMessage, "Story");
    }

    @Test
    public void projectCoalaContainsIssue(){
        browseIssueSrc.projectCoalaContainsIssue(main.getDriver(), "COALA-2");
        String errorMessage = main.getDriver().findElement(By.xpath("//*[@id=\"issue-content\"]/div/div/h1")).getText();
        assertEquals(errorMessage, "You can't view this issue");
    }

    @Test
    public void projectToucanContainsIssue(){
        browseIssueSrc.projectToucanContainsIssue(main.getDriver(), "TOUCAN-2");
        String errorMessage = main.getDriver().findElement(By.xpath("//*[@id=\"issue-content\"]/div/div/h1")).getText();
        assertEquals(errorMessage, "You can't view this issue");
    }

    @Test
    public void browseExistingIssue() throws AWTException {
        browseIssueSrc.browseExistingIssue(main.getDriver());
    }
}