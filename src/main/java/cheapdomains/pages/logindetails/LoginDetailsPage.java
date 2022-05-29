package cheapdomains.pages.logindetails;

import cheapdomains.pages.abstraction.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginDetailsPage extends AbstractPage {
    @FindBy(xpath = "//input [@name='username']")
    private WebElement usernameField;
    @FindBy(xpath = "//input [@name='password']")
    private WebElement passwordField;
    @FindBy(xpath = "//td [@class='box_title' and text()='Login Details']")
    private WebElement boxTitle;
    @FindBy(xpath = "//td [@class='box_sub3' and contains (text(),'to access your account')]")
    private WebElement subTitle;
    //TODO, mocked data (for the time when validation messages for these fields are implemented).
    private WebElement usernamePasswordFieldValidationError;

    public LoginDetailsPage(WebDriver driver) {
        super(driver);
    }

    public void inputUsername(String username) {
        usernameField.sendKeys(username);
    }

    public void inputPassword(String password) {
        passwordField.sendKeys(password);
    }

    public boolean usernameFieldIsPresent() {
        return usernameField.isDisplayed();
    }

    public boolean passwordFieldIsPresent() {
        return passwordField.isDisplayed();
    }

    public String getUsernamePasswordFieldValidationMessage() {
        return usernamePasswordFieldValidationError.getText();
    }

    public String getBoxTitle() {
        return boxTitle.getText();
    }

    public String getSubTitle() {
        return subTitle.getText();
    }
}
