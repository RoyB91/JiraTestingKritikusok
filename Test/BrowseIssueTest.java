import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        main.getDriver().close();
    }

    @Test
    public void detailsOfTasks(){
        browseIssueSrc.detailsOfTasks(main.getDriver());
    }

    @Test
    public void projectContainsIssue(){
        browseIssueSrc.projectContainsIssue(main.getDriver(), "JETI-2");
    }
}