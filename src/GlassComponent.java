import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;



import java.util.List;
import java.util.concurrent.TimeUnit;

public class GlassComponent {


    public void deleteComponent(WebDriver driver, String url, String componentName) {

        driver.navigate().to(url);

        WebElement matchedComp = null;

        List<WebElement> shits = driver.findElements(By.className("components-table__name"));
        for (int i = 0; i < shits.size(); i++) {
            if (shits.get(i).getAttribute("innerText").equals(componentName)) {
                matchedComp = shits.get(i);
                break;

            }
        }
        WebElement parentElement = matchedComp.findElement(By.xpath("./.."));

        WebElement dropShit = parentElement.findElement(By.className("dynamic-table__actions")).findElement(By.partialLinkText("Operation"));
        dropShit.click();

        String id = parentElement.getAttribute("data-component-id");
        WebElement deleteButton = dropShit.findElement(By.xpath("//*[@id=\"deletecomponent_" + id + "\"]"));

        deleteButton.click();

        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        WebElement submit = driver.findElement(By.xpath("//*[@id=\"submit\"]"));
        submit.click();

    }
}
