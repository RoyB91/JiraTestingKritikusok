import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class Main {

    public static void main(String[] args) {

    }

    private String username = System.getenv("UserName");
    private String password = System.getenv("PASSWORD");

    private WebDriver driver = new ChromeDriver();

    public WebDriver getDriver() {
        return driver;
    }

    public void loginWithValidData() {
        driver.get("https://jira.codecool.codecanvas.hu/login.jsp");
        WebElement loginField = driver.findElement(By.xpath("//*[@id=\"login-form-username\"]"));
        loginField.clear();
        loginField.sendKeys(username);

        WebElement passwordField = driver.findElement(By.xpath("//*[@id=\"login-form-password\"]"));
        passwordField.sendKeys(password);
        WebElement loginButton = driver.findElement(By.xpath("//*[@id=\"login-form-submit\"]"));
        loginButton.click();
    }
}
