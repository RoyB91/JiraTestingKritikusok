import org.openqa.selenium.WebDriver;
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

}
