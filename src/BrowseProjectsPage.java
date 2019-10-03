import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BrowseProjectsPage {

    public WebElement getProjectsButton(WebDriver driver) {
        return driver.findElement(By.xpath("//*[@id=\"browse_link\"]"));
    }

    public WebElement getViewAllProjectsButton(WebDriver driver) {
        return driver.findElement(By.xpath("//*[@id=\"project_view_all_link_lnk\"]"));

    }


    public WebElement getCoalaProject(WebDriver driver) {
        return driver.findElement(By.xpath("//*[@id=\"projects\"]/div/table/tbody/tr[2]/td[1]/a"));
    }

    public WebElement getCoalaNameDisplayed(WebDriver driver) {

        return driver.findElement(By.xpath("//*[@id=\"content\"]/div[1]/div/div[1]/header/div/div[2]/h1/div/div/a"));
    }

    public WebElement getCoalaAvatarDisplayed(WebDriver driver) {
        return driver.findElement(By.xpath("//*[@id=\"content\"]/div[1]/div/div[1]/header/div/div[1]/a/span/span/img"));
    }

    public WebElement getBusinessProjects(WebDriver driver) {
        return driver.findElement(By.xpath("//*[@id=\"project_type_business_lnk\"]"));
    }

    public WebElement getToucanProject(WebDriver driver) {
        return driver.findElement(By.xpath("//*[@id=\"projects\"]/div/table/tbody/tr[3]/td[1]/a"));
    }

    public WebElement getToucanName(WebDriver driver) {

        return driver.findElement(By.xpath("//*[@id=\"content\"]/div[1]/div/div[1]/header/div/div[2]/h1/div/div/a"));
    }

    public WebElement getToucanAvatar(WebDriver driver) {
        return driver.findElement(By.xpath("//*[@id=\"content\"]/div[1]/div/div[1]/header/div/div[1]/a/span/span/img"));
    }

    public WebElement getCoalaSummary(WebDriver driver) {
        return driver.findElement(By.xpath("//*[@id=\"content\"]/div[1]/div/div[1]/nav/div/div[1]/ul/li[1]/a/span[2]"));
    }

    public WebElement getCoalaActivity(WebDriver driver) {
        return driver.findElement(By.xpath("//*[@id=\"summary-subnav-title\"]/span"));

    }

    public WebElement getToucanSummary(WebDriver driver) {
        return driver.findElement(By.xpath("//*[@id=\"content\"]/div[1]/div/div[1]/nav/div/div[1]/ul/li[1]/a/span[2]"));

    }

    public WebElement getToucanActivity(WebDriver driver) {
        return driver.findElement(By.xpath("//*[@id=\"summary-subnav-title\"]/span"));

    }

    public WebElement getToucanProjectFromAllProjects(WebDriver driver) {
        return driver.findElement(By.xpath("//*[@id=\"projects\"]/div/table/tbody/tr[16]/td[1]/a"));

    }

    public WebElement getJetiProjectFromAllProjects(WebDriver driver) {
        return driver.findElement(By.xpath("//*[@id=\"projects\"]/div/table/tbody/tr[5]/td[1]/a"));

    }

    public WebElement getJetiSummary(WebDriver driver) {
        return driver.findElement(By.xpath("//*[@id=\"content\"]/div[1]/div/div[1]/nav/div/div[1]/ul/li[1]/a/span[2]"));

    }

    public WebElement getJetiActivity(WebDriver driver) {
        return driver.findElement(By.xpath("//*[@id=\"summary-subnav-title\"]/span"));

    }

}
