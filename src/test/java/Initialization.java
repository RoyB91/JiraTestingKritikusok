package test.java;

import main.java.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.WebDriver;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)

public class Initialization {

    private WebDriver driver;

    @BeforeAll
    public void init() {
        driver = WebDriverManager.getDriver();
    }

    @AfterAll
    public void teardown() {
        WebDriverManager.getDriver().quit();
    }

}
