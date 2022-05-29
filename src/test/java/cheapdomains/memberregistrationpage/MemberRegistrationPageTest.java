package cheapdomains.memberregistrationpage;

import cheapdomains.driver.cash.WebDriverCash;
import cheapdomains.memberregistrationpage.dataproviders.MemberRegistrationDataProvider;
import cheapdomains.pages.LoginDetailsPage;
import cheapdomains.pages.MemberRegistrationPage;
import cheapdomains.testdata.LoginDetailsTestData;
import cheapdomains.testdata.MemberRegistrationTestData;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class MemberRegistrationPageTest {
    private WebDriver driver = WebDriverCash.getDriver();
    private MemberRegistrationPage memberRegistrationPage = new MemberRegistrationPage(driver);
    private LoginDetailsPage loginDetailsPage = new LoginDetailsPage(driver);

    @Test(priority = 1)
    public void checkBoxTitleTest() {
        driver.get("https://www.cheapdomains.com.au/register/member_register_test.php");
        driver.manage().window().fullscreen();
        Assert.assertEquals(memberRegistrationPage.getBoxTitle(), MemberRegistrationTestData.EXPECTED_BOX_TITLE, "Not expected box title.");
        driver.quit();
    }

    @Test(priority = 2)
    public void checkSubTitleTest() {
        driver.get("https://www.cheapdomains.com.au/register/member_register_test.php");
        driver.manage().window().fullscreen();
        Assert.assertEquals(memberRegistrationPage.getSubTitle(), MemberRegistrationTestData.EXPECTED_SUB_TITLE, "Not expected subtitle.");
        driver.quit();
    }

    @Test(priority = 4)
    public void checkEmailAddressFieldMessageTest() {
        driver.get("https://www.cheapdomains.com.au/register/member_register_test.php");
        driver.manage().window().fullscreen();
        Assert.assertEquals(memberRegistrationPage.getEmailAddressFieldMessage(), MemberRegistrationTestData.EXPECTED_EMAIL_ADDRESS_FIELD_MESSAGE, "Not expected hint for email address field.");
        driver.quit();
    }

    //This is negative test cases for occasions when member try to sign up with no data provided. This test should fail.
    @Test(priority = 5)
    public void checkPopUpForUserWithNoDataTest() {
        driver.get("https://www.cheapdomains.com.au/register/member_register_test.php");
        driver.manage().window().fullscreen();
        memberRegistrationPage.continueOrderButtonIsPresent();
        memberRegistrationPage.clickContinueOrderButton();
        Assert.assertEquals(memberRegistrationPage.getFillMandatoryFieldsPopUpText(), MemberRegistrationTestData.EXPECTED_FILL_ALL_MANDATORY_FIELDS_POPUP, "Not expected pop-up text.");
        driver.quit();
    }

    // This is a test case for successful registration. It finds a bug when first name field becomes empty and there is no such message.
    @Test(priority = 3)
    public void createPersonalUserTest() {
        driver.get("https://www.cheapdomains.com.au/register/member_register_test.php");
        driver.manage().window().maximize();
        memberRegistrationPage.firstNameFieldIsPresent();
        memberRegistrationPage.inputFirstName(MemberRegistrationTestData.FIRST_NAME);
        memberRegistrationPage.lastNameFieldIsPresent();
        memberRegistrationPage.inputLastName(MemberRegistrationTestData.LAST_NAME);
        memberRegistrationPage.addressFieldIsPresent();
        memberRegistrationPage.inputAddress(MemberRegistrationTestData.ADDRESS);
        memberRegistrationPage.cityFieldIsPresent();
        memberRegistrationPage.inputCity(MemberRegistrationTestData.CITY);
        memberRegistrationPage.postcodeZipFieldIsPresent();
        memberRegistrationPage.inputPostcodeZip(MemberRegistrationTestData.POSTCODE_ZIP);
        memberRegistrationPage.countryDropdownIsPresent();
        memberRegistrationPage.clickCountryDropdown();
        memberRegistrationPage.countryNameIsPresent();
        memberRegistrationPage.clickCountryName();
        memberRegistrationPage.stateFieldIsPresent();
        memberRegistrationPage.inputState(MemberRegistrationTestData.STATE);
        memberRegistrationPage.phoneNumberFieldIsPresent();
        memberRegistrationPage.inputPhoneNumber(MemberRegistrationTestData.PHONE_NUMBER);
        memberRegistrationPage.emailAddressFieldIsPresent();
        memberRegistrationPage.inputEmailAddress(MemberRegistrationTestData.EMAIL_ADDRESS);
        memberRegistrationPage.personalAccountButtonIsPresent();
        memberRegistrationPage.clickPersonalAccountButton();
        loginDetailsPage.usernameFieldIsPresent();
        loginDetailsPage.inputUsername(LoginDetailsTestData.USERNAME);
        loginDetailsPage.passwordFieldIsPresent();
        loginDetailsPage.inputPassword(LoginDetailsTestData.PASSWORD);
        memberRegistrationPage.continueOrderButtonIsPresent();
        memberRegistrationPage.clickContinueOrderButton();
        Assert.assertEquals(memberRegistrationPage.getSuccessfulRegistrationMessage(), MemberRegistrationTestData.SUCCESSFUL_REGISTRATION_MESSAGE, "Can't find message about registration");
        driver.quit();
    }

    @Test(priority = 6, dataProvider = "postcodeZipValidationTest", dataProviderClass = MemberRegistrationDataProvider.class)
    public void checkPostcodeZipFieldValidationTest(String postcodeZip, String validationError) {
        driver.get("https://www.cheapdomains.com.au/register/member_register_test.php");
        driver.manage().window().maximize();
        memberRegistrationPage.firstNameFieldIsPresent();
        memberRegistrationPage.inputFirstName(MemberRegistrationTestData.FIRST_NAME);
        memberRegistrationPage.lastNameFieldIsPresent();
        memberRegistrationPage.inputLastName(MemberRegistrationTestData.LAST_NAME);
        memberRegistrationPage.addressFieldIsPresent();
        memberRegistrationPage.inputAddress(MemberRegistrationTestData.ADDRESS);
        memberRegistrationPage.cityFieldIsPresent();
        memberRegistrationPage.inputCity(MemberRegistrationTestData.CITY);
        memberRegistrationPage.postcodeZipFieldIsPresent();
        memberRegistrationPage.inputPostcodeZip(postcodeZip);
        memberRegistrationPage.countryDropdownIsPresent();
        memberRegistrationPage.clickCountryDropdown();
        memberRegistrationPage.countryNameIsPresent();
        memberRegistrationPage.clickCountryName();
        memberRegistrationPage.stateFieldIsPresent();
        memberRegistrationPage.inputState(MemberRegistrationTestData.STATE);
        memberRegistrationPage.phoneNumberFieldIsPresent();
        memberRegistrationPage.inputPhoneNumber(MemberRegistrationTestData.PHONE_NUMBER);
        memberRegistrationPage.emailAddressFieldIsPresent();
        memberRegistrationPage.inputEmailAddress(MemberRegistrationTestData.EMAIL_ADDRESS);
        memberRegistrationPage.personalAccountButtonIsPresent();
        memberRegistrationPage.clickPersonalAccountButton();
        loginDetailsPage.usernameFieldIsPresent();
        loginDetailsPage.inputUsername(LoginDetailsTestData.USERNAME);
        loginDetailsPage.passwordFieldIsPresent();
        loginDetailsPage.inputPassword(LoginDetailsTestData.PASSWORD);
        memberRegistrationPage.continueOrderButtonIsPresent();
        memberRegistrationPage.clickContinueOrderButton();
        Assert.assertEquals(memberRegistrationPage.getPostcodeFieldValidationMessage(), validationError, "Not expected validation message");
        driver.quit();
    }

    @Test(priority = 7, dataProvider = "firstLastNameValidationTest", dataProviderClass = MemberRegistrationDataProvider.class)
    public void checkFirstLastNameValidationTest(String firstName, String lastName, String validationError) {
        driver.get("https://www.cheapdomains.com.au/register/member_register_test.php");
        driver.manage().window().maximize();
        memberRegistrationPage.firstNameFieldIsPresent();
        memberRegistrationPage.inputFirstName(firstName);
        memberRegistrationPage.lastNameFieldIsPresent();
        memberRegistrationPage.inputLastName(lastName);
        memberRegistrationPage.addressFieldIsPresent();
        memberRegistrationPage.inputAddress(MemberRegistrationTestData.ADDRESS);
        memberRegistrationPage.cityFieldIsPresent();
        memberRegistrationPage.inputCity(MemberRegistrationTestData.CITY);
        memberRegistrationPage.postcodeZipFieldIsPresent();
        memberRegistrationPage.inputPostcodeZip(MemberRegistrationTestData.POSTCODE_ZIP);
        memberRegistrationPage.countryDropdownIsPresent();
        memberRegistrationPage.clickCountryDropdown();
        memberRegistrationPage.countryNameIsPresent();
        memberRegistrationPage.clickCountryName();
        memberRegistrationPage.stateFieldIsPresent();
        memberRegistrationPage.inputState(MemberRegistrationTestData.STATE);
        memberRegistrationPage.phoneNumberFieldIsPresent();
        memberRegistrationPage.inputPhoneNumber(MemberRegistrationTestData.PHONE_NUMBER);
        memberRegistrationPage.emailAddressFieldIsPresent();
        memberRegistrationPage.inputEmailAddress(MemberRegistrationTestData.EMAIL_ADDRESS);
        memberRegistrationPage.personalAccountButtonIsPresent();
        memberRegistrationPage.clickPersonalAccountButton();
        loginDetailsPage.usernameFieldIsPresent();
        loginDetailsPage.inputUsername(LoginDetailsTestData.USERNAME);
        loginDetailsPage.passwordFieldIsPresent();
        loginDetailsPage.inputPassword(LoginDetailsTestData.PASSWORD);
        memberRegistrationPage.continueOrderButtonIsPresent();
        memberRegistrationPage.clickContinueOrderButton();
        Assert.assertEquals(memberRegistrationPage.getFirstLastNameFieldValidationMessage(), validationError, "Not expected validation message");
        driver.quit();
    }

    @Test(priority = 8, dataProvider = "emailValidationTest", dataProviderClass = MemberRegistrationDataProvider.class)
    public void checkEmailValidationTest(String email, String validationError) {
        driver.get("https://www.cheapdomains.com.au/register/member_register_test.php");
        driver.manage().window().maximize();
        memberRegistrationPage.firstNameFieldIsPresent();
        memberRegistrationPage.inputFirstName(MemberRegistrationTestData.FIRST_NAME);
        memberRegistrationPage.lastNameFieldIsPresent();
        memberRegistrationPage.inputLastName(MemberRegistrationTestData.LAST_NAME);
        memberRegistrationPage.addressFieldIsPresent();
        memberRegistrationPage.inputAddress(MemberRegistrationTestData.ADDRESS);
        memberRegistrationPage.cityFieldIsPresent();
        memberRegistrationPage.inputCity(MemberRegistrationTestData.CITY);
        memberRegistrationPage.postcodeZipFieldIsPresent();
        memberRegistrationPage.inputPostcodeZip(MemberRegistrationTestData.POSTCODE_ZIP);
        memberRegistrationPage.countryDropdownIsPresent();
        memberRegistrationPage.clickCountryDropdown();
        memberRegistrationPage.countryNameIsPresent();
        memberRegistrationPage.clickCountryName();
        memberRegistrationPage.stateFieldIsPresent();
        memberRegistrationPage.inputState(MemberRegistrationTestData.STATE);
        memberRegistrationPage.phoneNumberFieldIsPresent();
        memberRegistrationPage.inputPhoneNumber(MemberRegistrationTestData.PHONE_NUMBER);
        memberRegistrationPage.emailAddressFieldIsPresent();
        memberRegistrationPage.inputEmailAddress(email);
        memberRegistrationPage.personalAccountButtonIsPresent();
        memberRegistrationPage.clickPersonalAccountButton();
        loginDetailsPage.usernameFieldIsPresent();
        loginDetailsPage.inputUsername(LoginDetailsTestData.USERNAME);
        loginDetailsPage.passwordFieldIsPresent();
        loginDetailsPage.inputPassword(LoginDetailsTestData.PASSWORD);
        memberRegistrationPage.continueOrderButtonIsPresent();
        memberRegistrationPage.clickContinueOrderButton();
        Assert.assertEquals(memberRegistrationPage.getEmailFieldValidationMessage(), validationError, "Not expected validation message");
        driver.quit();
    }

    @Test(dataProvider = "phoneNumberValidationTest", dataProviderClass = MemberRegistrationDataProvider.class)
    public void checkPhoneNumberValidationTest(String phoneNumber, String validationError) {
        driver.get("https://www.cheapdomains.com.au/register/member_register_test.php");
        driver.manage().window().maximize();
        memberRegistrationPage.firstNameFieldIsPresent();
        memberRegistrationPage.inputFirstName(MemberRegistrationTestData.FIRST_NAME);
        memberRegistrationPage.lastNameFieldIsPresent();
        memberRegistrationPage.inputLastName(MemberRegistrationTestData.LAST_NAME);
        memberRegistrationPage.addressFieldIsPresent();
        memberRegistrationPage.inputAddress(MemberRegistrationTestData.ADDRESS);
        memberRegistrationPage.cityFieldIsPresent();
        memberRegistrationPage.inputCity(MemberRegistrationTestData.CITY);
        memberRegistrationPage.postcodeZipFieldIsPresent();
        memberRegistrationPage.inputPostcodeZip(MemberRegistrationTestData.POSTCODE_ZIP);
        memberRegistrationPage.countryDropdownIsPresent();
        memberRegistrationPage.clickCountryDropdown();
        memberRegistrationPage.countryNameIsPresent();
        memberRegistrationPage.clickCountryName();
        memberRegistrationPage.stateFieldIsPresent();
        memberRegistrationPage.inputState(MemberRegistrationTestData.STATE);
        memberRegistrationPage.phoneNumberFieldIsPresent();
        memberRegistrationPage.inputPhoneNumber(phoneNumber);
        memberRegistrationPage.emailAddressFieldIsPresent();
        memberRegistrationPage.inputEmailAddress(MemberRegistrationTestData.EMAIL_ADDRESS);
        memberRegistrationPage.personalAccountButtonIsPresent();
        memberRegistrationPage.clickPersonalAccountButton();
        loginDetailsPage.usernameFieldIsPresent();
        loginDetailsPage.inputUsername(LoginDetailsTestData.USERNAME);
        loginDetailsPage.passwordFieldIsPresent();
        loginDetailsPage.inputPassword(LoginDetailsTestData.PASSWORD);
        memberRegistrationPage.continueOrderButtonIsPresent();
        memberRegistrationPage.clickContinueOrderButton();
        Assert.assertEquals(memberRegistrationPage.getPhoneNumberFieldValidationMessage(), validationError, "Not expected phone number");
        driver.quit();
    }

    @Test
    public void checkDefaultAuBusinessNumberType() throws InterruptedException {
        driver.get("https://www.cheapdomains.com.au/register/member_register_test.php");
        driver.manage().window().maximize();
        memberRegistrationPage.firstNameFieldIsPresent();
        memberRegistrationPage.inputFirstName(MemberRegistrationTestData.FIRST_NAME);
        memberRegistrationPage.lastNameFieldIsPresent();
        memberRegistrationPage.inputLastName(MemberRegistrationTestData.LAST_NAME);
        memberRegistrationPage.addressFieldIsPresent();
        memberRegistrationPage.inputAddress(MemberRegistrationTestData.ADDRESS);
        memberRegistrationPage.cityFieldIsPresent();
        memberRegistrationPage.inputCity(MemberRegistrationTestData.CITY);
        memberRegistrationPage.postcodeZipFieldIsPresent();
        memberRegistrationPage.inputPostcodeZip(MemberRegistrationTestData.POSTCODE_ZIP);
        memberRegistrationPage.countryDropdownIsPresent();
        memberRegistrationPage.clickCountryDropdown();
        memberRegistrationPage.countryNameAuIsPresent();
        memberRegistrationPage.clickCountryNameAU();
        memberRegistrationPage.phoneNumberFieldIsPresent();
        memberRegistrationPage.inputPhoneNumber(MemberRegistrationTestData.PHONE_NUMBER);
        memberRegistrationPage.emailAddressFieldIsPresent();
        memberRegistrationPage.inputEmailAddress(MemberRegistrationTestData.EMAIL_ADDRESS);
        memberRegistrationPage.clickBusinessAccountButton();
        Assert.assertEquals(memberRegistrationPage.getBusinessNumberTypeName(), MemberRegistrationTestData.DEFAULT_AU_BUSINESS_NUMBER_TYPE);
    }
}
