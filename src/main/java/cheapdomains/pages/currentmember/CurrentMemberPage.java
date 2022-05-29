package cheapdomains.pages.currentmember;

import cheapdomains.pages.abstraction.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CurrentMemberPage extends AbstractPage {
    @FindBy(xpath = "//input [@value='Login Now']")
    private WebElement loginButton;
    @FindBy(xpath = "//input [@name='username_login']")
    private WebElement usernameLoginField;
    @FindBy(xpath = "//input [@name='password_login']")
    private WebElement passwordLoginField;
    @FindBy(xpath = "//td [@class='box_title' and text()='Current Member']")
    private WebElement boxTitle;
    @FindBy(xpath = "//td [@class='box_sub3' and contains (text(),'enter your username and password')]")
    private WebElement subTitle;
    // TODO, mocked data (for the time when validation is implemented).
    @FindBy
    private WebElement validationUserFieldError;
    // TODO, mocked data (for the time when validation is implemented).
    @FindBy
    private WebElement validationPasswordFieldError;
    @FindBy(xpath = "//a [contains (@class, 'visit-vodien')]")
    private WebElement visitVodienButton;

    public CurrentMemberPage(WebDriver driver) {
        super(driver);
    }

    public void inputUsername(String username) {
        usernameLoginField.clear();
        usernameLoginField.sendKeys(username);
    }

    public void inputPassword(String password) {
        passwordLoginField.clear();
        passwordLoginField.sendKeys(password);
    }

    public void clickLoginButton() {
        loginButton.click();
    }

    public String getBoxTitle() {
        return boxTitle.getText();
    }

    public String getSubTitle() {
        return subTitle.getText();
    }

    public String getLoginValidationError() {
        return validationUserFieldError.getText();
    }

    public String getPasswordValidationError() {
        return validationPasswordFieldError.getText();
    }

    public boolean loginFieldIsPresent() {
        return usernameLoginField.isDisplayed();
    }

    public boolean passwordFieldIsPresent() {
        return passwordLoginField.isDisplayed();
    }

    public boolean loginButtonIsPresent() {
        return loginButton.isDisplayed();
    }

    public boolean visitVodienButtonIsPresent() {
        return visitVodienButton.isDisplayed();
    }
}

