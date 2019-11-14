package test.java;

import main.java.BasePage;
import main.java.LoginPage;
import main.java.WebDriverManager;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.*;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class Initialization {

    private BasePage basePage;

    @BeforeClass
    public void init() {
        basePage.getDriver();
    }

    @AfterClass
    public void teardown() {
        WebDriverManager.getDriver().quit();
    }

}
