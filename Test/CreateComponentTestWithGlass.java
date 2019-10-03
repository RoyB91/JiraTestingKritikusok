import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;


import static org.junit.jupiter.api.Assertions.*;

class CreateComponentTestWithGlass {

    private Main main = new Main();
    private WebDriver driver = main.getDriver();
    private WebDriverWait wait = new WebDriverWait(driver, 15);
    private EditIssuePage editIssuePage = new EditIssuePage();
    private GlassComponent glassComponent = new GlassComponent();

    @BeforeEach
    public void setup() {
        main.loginWithValidData();
        driver.manage().window().maximize();

    }

    @Test
    public void createCompAndCheckWithGlass() {
        driver.navigate().to("https://jira.codecool.codecanvas.hu/projects/PP1?selectedItem=com.atlassian.jira.jira-projects-plugin:components-page");
        WebElement inputFieldCompName = driver.findElement(By.xpath("//*[@id=\"components-add__component\"]/div[1]/input"));
        inputFieldCompName.sendKeys("Kritikus komponens");

        WebElement assigneeField = driver.findElement(By.xpath("//*[@id=\"assigneeType-field\"]"));
        assigneeField.sendKeys("Unassigned");
        assigneeField.sendKeys(Keys.ENTER);
        driver.findElement(By.xpath("//*[@id=\"components-add__component\"]/div[5]/button")).click();


        driver.findElement(By.xpath("//*[@id=\"content\"]/div[1]/div/div[1]/nav/div/div[2]/ul/li[7]/a/span[1]")).click();


        WebElement table = driver.findElement(By.xpath("//*[@id=\"components-table\"]")).findElement(By.className("items"));


        String compName = table.findElement(By.xpath("//*[contains(text(), 'Kritikus komponens')]")).getAttribute("innerText");
        assertEquals("Kritikus komponens", compName);
        glassComponent.deleteComponent(driver, "https://jira.codecool.codecanvas.hu/projects/PP1?selectedItem=com.atlassian.jira.jira-projects-plugin:components-page", "Kritikus komponens");
    }

}