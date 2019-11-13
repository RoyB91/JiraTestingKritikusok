package test.java;

import main.java.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.TestInstance;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)

public class Initialization {



    @AfterAll
    public void teardown() {
        WebDriverManager.getDriver().quit();
    }

}
