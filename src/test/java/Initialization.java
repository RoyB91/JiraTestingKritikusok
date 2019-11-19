package test.java;

import main.java.WebDriverManager;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.WebDriver;

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class Initialization {

    public WebDriver driver;

//    @BeforeClass
//    public static void init() {
//        WebDriverManager.initDriver();
//    }
//
//    @AfterClass
//    public static void teardown() {
//        WebDriverManager.getDriver().close();
//        WebDriverManager.quit();
//    }

    @BeforeEach
    public  void init() {
        WebDriverManager.initDriver();
    }

    @AfterEach
    public  void teardown() {
        WebDriverManager.getDriver().close();
        WebDriverManager.quit();
    }


}
