import org.openqa.selenium.WebDriver;

public class BrowseIssueSrc {

    public void detailsOfTasks(WebDriver driver){
        driver.navigate().to("https://jira.codecool.codecanvas.hu/browse/SAND-25?filter=-3&jql=project%20%3D%20SAND");
    }

    public void projectContainsIssue(WebDriver driver, String projectName){
        driver.navigate().to("https://jira.codecool.codecanvas.hu/browse/" + projectName);
    }
}
