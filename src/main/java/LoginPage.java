package main.java;

import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasePage {

    private WebDriver driver;
    private WebDriverWait wait;
    private String loginPageUrl = "/login.jsp";
    private String dashBoardLoginPageUrl;
    private String finalURL = getBaseURL() + loginPageUrl;

    public LoginPage() {
        this.driver = getDriver();
        this.wait = getWait();
        PageFactory.initElements(driver, this);

    }

    public void loginWithValidData() {
        try {
            driver.navigate().to(getBaseURL() + loginPageUrl);
            writeUsername(getUsername());
            writePassword(getPassword());
            clickLoginButton();
        } catch (UnhandledAlertException e) {
            waitForAjaxControls(10);
            driver.switchTo().alert().accept();
        }
    }

    public String getLoginPageUrl() {
        return loginPageUrl;
    }

    public void navigateURL() {
        driver.navigate().to(finalURL);
    }

    public void loginWithParameters(String username, String password) {
        driver.get(finalURL);
        writeUsername(username);
        writePassword(password);
        clickLoginButton();

    }

    public void writeUsername(String username) {
        loginField.click();
        loginField.clear();
        loginField.sendKeys(username);

    }

    public void writePassword(String password) {
        passwordField.click();
        passwordField.clear();
        passwordField.sendKeys(password);

    }

    public void clickLoginButton() {
        loginButton.click();

    }

    public void clickAvatarPicture() {
        wait.until(ExpectedConditions.elementToBeClickable(avatarPicture));
        avatarPicture.click();

    }


    @FindBy(id = "login-form-username")
    private WebElement loginField;

    @FindBy(id = "login-form-password")
    private WebElement passwordField;

    @FindBy(id = "login-form-submit")
    private WebElement loginButton;

    @FindBy(id = "header-details-user-fullname")
    private WebElement avatarPicture;

    @FindBy(id = "log_out")
    private WebElement logOutButton;

    @FindBy(className = "error")
    private WebElement errorMessage;

    public WebElement getLogOutButton() {
        return logOutButton;
    }

    public WebElement getErrorMessage() {
        return errorMessage;
    }


}
