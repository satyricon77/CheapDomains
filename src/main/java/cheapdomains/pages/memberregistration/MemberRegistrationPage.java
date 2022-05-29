package cheapdomains.pages.memberregistration;

import cheapdomains.pages.abstraction.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MemberRegistrationPage extends AbstractPage {
    @FindBy(xpath = "//td [@class='box_title' and contains (text(),'Registration Form')]")
    private WebElement boxTitle;
    @FindBy(xpath = "//td [@class='box_sub3' and contains(text(), 'NEW member')]")
    private WebElement subTitle;
    @FindBy(xpath = "//input [@name='first_name']")
    private WebElement firstNameField;
    @FindBy(xpath = "//input [@name='last_name']")
    private WebElement lastNameField;
    @FindBy(xpath = "//input [@name='address']")
    private WebElement addressField;
    @FindBy(xpath = "//input [@name='city']")
    private WebElement cityField;
    @FindBy(xpath = "//input [@name='post_code']")
    private WebElement postcodeZipField;
    @FindBy(xpath = "//select [@id='country_name']")
    private WebElement countryDropDown;
    @FindBy(xpath = "//select[@id='country_name']//option [@value='UA']")
    private WebElement countyNameUA;
    @FindBy(xpath = "//select[@id='country_name']//option [@value='AU'][1]")
    private WebElement countryNameAU;
    @FindBy(xpath = "//input [@name='state']")
    private WebElement stateField;
    @FindBy(xpath = "//input [@id='phone_number']")
    private WebElement phoneNumberField;
    @FindBy(xpath = "//td [contains (text(),'check that your email address is correctly entered')]")
    private WebElement emailAddressFieldMessage;
    @FindBy(xpath = "//input [@name='email']")
    private WebElement emailField;
    @FindBy(xpath = "//input [@id='account_type_personal']")
    private WebElement personalAccountRadioButton;
    @FindBy(xpath = "//input [@id='account_type_business']")
    private WebElement businessAccountRadioButton;
    @FindBy(xpath = "//input [@name='business_name']")
    private WebElement businessNameField;
    @FindBy(xpath = "//input [@name='business_number']")
    private WebElement businessNumberField;
    @FindBy(xpath = "//input [@value='Continue Order']")
    private WebElement continueOrderButton;
    @FindBy(xpath = "//* [@id='business_type_element']/option [@value='ABN']")
    private WebElement businessNumberType;
    // TODO, mocked data (for the time when "fill all mandatory fields" pop-up is implemented).
    private WebElement fillMandatoryFieldsPopUp;
    // TODO, mocked data (for the time when message about successful registration will appear).
    private WebElement successfulRegistrationMessage;
    // TODO, mocked data (for the time when message about incorrect input will be implemented).
    private WebElement postcodeZipFieldValidationError;
    // TODO, mocked data (for the time when message about incorrect input will be implemented).
    private WebElement firstLastNameFiledValidationError;
    // TODO, mocked data (for the time when message about incorrect input will be implemented).
    private WebElement emailFieldValidationError;
    // TODO, mocked data (for the time when message about incorrect input will be implemented)
    private WebElement phoneNumberFieldValidationError;

    public MemberRegistrationPage(WebDriver driver) {
        super(driver);
    }

    public boolean continueOrderButtonIsPresent() {
        return continueOrderButton.isDisplayed();
    }

    public void clickContinueOrderButton() {
        continueOrderButton.click();
    }

    public String getBoxTitle() {
        return boxTitle.getText();
    }

    public String getSubTitle() {
        return subTitle.getText();
    }

    public String getEmailAddressFieldMessage() {
        return emailAddressFieldMessage.getText();
    }

    public String getFillMandatoryFieldsPopUpText() {
        return fillMandatoryFieldsPopUp.getText();
    }

    public void inputFirstName(String name) {
        firstNameField.sendKeys(name);
    }

    public void inputLastName(String lastName) {
        lastNameField.sendKeys(lastName);
    }

    public void inputAddress(String address) {
        addressField.sendKeys(address);
    }

    public void inputCity(String city) {
        cityField.sendKeys(city);
    }

    public void inputPostcodeZip(String postcodeZip) {
        postcodeZipField.sendKeys(postcodeZip);
    }

    public void clickCountryDropdown() {
        countryDropDown.click();
    }

    public void clickCountryName() {
        countyNameUA.click();
    }

    public void clickCountryNameAU() {
        countryNameAU.click();
    }

    public void inputState(String state) {
        stateField.sendKeys(state);
    }

    public void inputPhoneNumber(String phoneNumber) {
        phoneNumberField.sendKeys(phoneNumber);
    }

    public void inputEmailAddress(String email) {
        emailField.sendKeys(email);
    }

    public void clickPersonalAccountButton() {
        personalAccountRadioButton.click();
    }

    public void clickBusinessAccountButton() {
        businessAccountRadioButton.click();
    }

    public void inputBusinessName(String businessName) {
        businessNameField.sendKeys(businessName);
    }

    public void inputBusinessNumber(String businessNumber) {
        businessNumberField.sendKeys(businessNumber);
    }

    public boolean firstNameFieldIsPresent() {
        return firstNameField.isDisplayed();
    }

    public boolean lastNameFieldIsPresent() {
        return lastNameField.isDisplayed();
    }

    public boolean addressFieldIsPresent() {
        return addressField.isDisplayed();
    }

    public boolean cityFieldIsPresent() {
        return cityField.isDisplayed();
    }

    public boolean postcodeZipFieldIsPresent() {
        return postcodeZipField.isDisplayed();
    }

    public boolean countryDropdownIsPresent() {
        return countryDropDown.isDisplayed();
    }

    public boolean countryNameIsPresent() {
        return countyNameUA.isDisplayed();
    }

    public boolean countryNameAuIsPresent() {
        return countryNameAU.isDisplayed();
    }

    public boolean stateFieldIsPresent() {
        return stateField.isDisplayed();
    }

    public boolean phoneNumberFieldIsPresent() {
        return phoneNumberField.isDisplayed();
    }

    public boolean emailAddressFieldIsPresent() {
        return emailField.isDisplayed();
    }

    public boolean personalAccountButtonIsPresent() {
        return personalAccountRadioButton.isDisplayed();
    }

    public boolean businessAccountButtonIsPresent() {
        return businessAccountRadioButton.isDisplayed();
    }

    public boolean businessNameFieldIsPresent() {
        return businessNameField.isDisplayed();
    }

    public boolean businessNumberFieldIsPresent() {
        return businessNameField.isDisplayed();
    }

    public String getSuccessfulRegistrationMessage() {
        return successfulRegistrationMessage.getText();
    }

    public String getPostcodeFieldValidationMessage() {
        return postcodeZipFieldValidationError.getText();
    }

    public String getFirstLastNameFieldValidationMessage() {
        return firstLastNameFiledValidationError.getText();
    }

    public String getEmailFieldValidationMessage() {
        return emailFieldValidationError.getText();
    }

    public String getPhoneNumberFieldValidationMessage() {
        return phoneNumberFieldValidationError.getText();
    }

    public String getBusinessNumberTypeName() {
        return businessNumberType.getText();
    }
}


