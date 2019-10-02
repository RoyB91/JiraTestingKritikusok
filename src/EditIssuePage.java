import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

class EditIssuePage {

    private WebElement editButton;
    private WebElement summaryField;
    private WebElement updateButton;
    private WebElement issueSummaryName;
    private WebElement cancelButton;

    public void resetIssueSummary(WebDriver driver, String url, String defaultTestText) {
        driver.navigate().to(url);
        editButton = driver.findElement(By.xpath("//*[@id=\"edit-issue\"]/span[2]"));
        editButton.click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        summaryField = driver.findElement(By.xpath("//*[@id=\"summary\"]"));
        summaryField.clear();
        summaryField.sendKeys(defaultTestText);
        updateButton = driver.findElement(By.xpath("//*[@id=\"edit-issue-submit\"]"));
        updateButton.click();
    }

    public WebElement getEditButton(WebDriver driver) {
        return driver.findElement(By.xpath("//*[@id=\"edit-issue\"]/span[2]"));
    }

    public WebElement getSummaryField(WebDriver driver) {
        return driver.findElement(By.xpath("//*[@id=\"summary\"]"));
    }

    public WebElement getUpdateButton(WebDriver driver) {
        return driver.findElement(By.xpath("//*[@id=\"edit-issue-submit\"]"));
    }

    public String getIssueSummaryName(WebDriver driver) {
        return driver.findElement(By.xpath("//*[@id=\"summary-val\"]")).getText();
    }

    public WebElement getCancelButton(WebDriver driver) {
        return driver.findElement(By.cssSelector("#edit-issue-dialog > div.jira-dialog-content > div.qf-container > div > form > div.buttons-container.form-footer > div > a"));
    }
}
