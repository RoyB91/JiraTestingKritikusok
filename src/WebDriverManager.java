import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriverManager {

    private WebDriver driver;

    private static WebDriverManager instance = null;

    private WebDriverManager() {
        this.driver = new ChromeDriver();
    }

    public static WebDriverManager getInstance() {
        if (instance == null) {
            instance = new WebDriverManager();
        }
        return instance;
    }

    public WebDriver getDriver() {
        return driver;
    }
}
