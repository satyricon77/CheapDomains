package cheapdomains.memberregistrationpage;

import cheapdomains.basetest.BaseTest;
import cheapdomains.memberregistrationpage.dataproviders.MemberRegistrationDataProvider;
import cheapdomains.pages.logindetails.LoginDetailsPage;
import cheapdomains.pages.memberregistration.MemberRegistrationPage;
import cheapdomains.testdata.LoginDetailsTestData;
import cheapdomains.testdata.MemberRegistrationTestData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class MemberRegistrationPageTest extends BaseTest {
    private MemberRegistrationPage memberRegistrationPage;
    private LoginDetailsPage loginDetailsPage;

    @BeforeMethod
    public void setUp() {
        driver.get("https://www.cheapdomains.com.au/register/member_register_test.php");
        driver.manage().window().fullscreen();
        memberRegistrationPage = new MemberRegistrationPage(driver);
        loginDetailsPage = new LoginDetailsPage(driver);
    }

    @Test(priority = 1)
    public void registrationFormElementsArePresent() {
        Assert.assertTrue(memberRegistrationPage.firstNameFieldIsPresent(), "First name field is not found");
        Assert.assertTrue(memberRegistrationPage.lastNameFieldIsPresent(), "Last name field is not found");
        Assert.assertTrue(memberRegistrationPage.addressFieldIsPresent(), "Address field is not found");
        Assert.assertTrue(memberRegistrationPage.cityFieldIsPresent(), "City field is not found");
        Assert.assertTrue(memberRegistrationPage.postcodeZipFieldIsPresent(), "Postcode/Zip field is not found");
        Assert.assertTrue(memberRegistrationPage.countryDropdownIsPresent(), "Country dropdown field is not found");
        Assert.assertTrue(memberRegistrationPage.stateFieldIsPresent(), "State field is not found");
        Assert.assertTrue(memberRegistrationPage.phoneNumberFieldIsPresent(), "Phone number field is not found");
        Assert.assertTrue(memberRegistrationPage.emailAddressFieldIsPresent(), "Email address field is not found");
        Assert.assertTrue(memberRegistrationPage.personalAccountButtonIsPresent(), "Personal account radio button is not found");
        Assert.assertTrue(memberRegistrationPage.businessAccountButtonIsPresent(), "Business account radio button is not found");
    }

    @Test(priority = 3)
    public void checkBoxTitleTest() {
        Assert.assertEquals(memberRegistrationPage.getBoxTitle(), MemberRegistrationTestData.EXPECTED_BOX_TITLE, "Not expected box title.");
    }

    @Test(priority = 4)
    public void checkSubTitleTest() {
        Assert.assertEquals(memberRegistrationPage.getSubTitle(), MemberRegistrationTestData.EXPECTED_SUB_TITLE, "Not expected subtitle.");
    }

    @Test(priority = 5)
    public void checkEmailAddressFieldMessageTest() {
        Assert.assertEquals(memberRegistrationPage.getEmailAddressFieldMessage(), MemberRegistrationTestData.EXPECTED_EMAIL_ADDRESS_FIELD_MESSAGE, "Not expected hint for email address field.");
    }

    //This is negative test cases for occasions when member try to sign up with no data provided. This test should fail.
    @Test(priority = 6)
    public void checkPopUpForUserWithNoDataTest() {
        memberRegistrationPage.clickContinueOrderButton();
        Assert.assertEquals(memberRegistrationPage.getFillMandatoryFieldsPopUpText(), MemberRegistrationTestData.EXPECTED_FILL_ALL_MANDATORY_FIELDS_POPUP, "Not expected pop-up text.");
    }

    // This is a test case for successful registration. It finds a bug when first name field becomes empty and there is no such message.
    @Test(priority = 2)
    public void createPersonalUserTest() {
        memberRegistrationPage.inputFirstName(MemberRegistrationTestData.FIRST_NAME);
        memberRegistrationPage.inputLastName(MemberRegistrationTestData.LAST_NAME);
        memberRegistrationPage.inputAddress(MemberRegistrationTestData.ADDRESS);
        memberRegistrationPage.inputCity(MemberRegistrationTestData.CITY);
        memberRegistrationPage.inputPostcodeZip(MemberRegistrationTestData.POSTCODE_ZIP);
        memberRegistrationPage.clickCountryDropdown();
        memberRegistrationPage.clickCountryName();
        memberRegistrationPage.inputState(MemberRegistrationTestData.STATE);
        memberRegistrationPage.inputPhoneNumber(MemberRegistrationTestData.PHONE_NUMBER);
        memberRegistrationPage.inputEmailAddress(MemberRegistrationTestData.EMAIL_ADDRESS);
        memberRegistrationPage.clickPersonalAccountButton();
        loginDetailsPage.inputUsername(LoginDetailsTestData.USERNAME);
        loginDetailsPage.inputPassword(LoginDetailsTestData.PASSWORD);
        memberRegistrationPage.clickContinueOrderButton();
        Assert.assertEquals(memberRegistrationPage.getSuccessfulRegistrationMessage(), MemberRegistrationTestData.SUCCESSFUL_REGISTRATION_MESSAGE, "Can't find message about registration");
    }

    @Test(priority = 7, dataProvider = "postcodeZipValidationTest", dataProviderClass = MemberRegistrationDataProvider.class)
    public void checkPostcodeZipFieldValidationTest(String postcodeZip, String validationError) {
        memberRegistrationPage.inputFirstName(MemberRegistrationTestData.FIRST_NAME);
        memberRegistrationPage.inputLastName(MemberRegistrationTestData.LAST_NAME);
        memberRegistrationPage.inputAddress(MemberRegistrationTestData.ADDRESS);
        memberRegistrationPage.inputCity(MemberRegistrationTestData.CITY);
        memberRegistrationPage.inputPostcodeZip(postcodeZip);
        memberRegistrationPage.clickCountryDropdown();
        memberRegistrationPage.clickCountryName();
        memberRegistrationPage.inputState(MemberRegistrationTestData.STATE);
        memberRegistrationPage.inputPhoneNumber(MemberRegistrationTestData.PHONE_NUMBER);
        memberRegistrationPage.inputEmailAddress(MemberRegistrationTestData.EMAIL_ADDRESS);
        memberRegistrationPage.clickPersonalAccountButton();
        loginDetailsPage.inputUsername(LoginDetailsTestData.USERNAME);
        loginDetailsPage.inputPassword(LoginDetailsTestData.PASSWORD);
        memberRegistrationPage.clickContinueOrderButton();
        Assert.assertEquals(memberRegistrationPage.getPostcodeFieldValidationMessage(), validationError, "Not expected validation message");
    }

    @Test(priority = 8, dataProvider = "firstLastNameValidationTest", dataProviderClass = MemberRegistrationDataProvider.class)
    public void checkFirstLastNameValidationTest(String firstName, String lastName, String validationError) {
        memberRegistrationPage.inputFirstName(firstName);
        memberRegistrationPage.inputLastName(lastName);
        memberRegistrationPage.inputAddress(MemberRegistrationTestData.ADDRESS);
        memberRegistrationPage.inputCity(MemberRegistrationTestData.CITY);
        memberRegistrationPage.inputPostcodeZip(MemberRegistrationTestData.POSTCODE_ZIP);
        memberRegistrationPage.clickCountryDropdown();
        memberRegistrationPage.clickCountryName();
        memberRegistrationPage.inputState(MemberRegistrationTestData.STATE);
        memberRegistrationPage.inputPhoneNumber(MemberRegistrationTestData.PHONE_NUMBER);
        memberRegistrationPage.inputEmailAddress(MemberRegistrationTestData.EMAIL_ADDRESS);
        memberRegistrationPage.clickPersonalAccountButton();
        loginDetailsPage.inputUsername(LoginDetailsTestData.USERNAME);
        loginDetailsPage.inputPassword(LoginDetailsTestData.PASSWORD);
        memberRegistrationPage.continueOrderButtonIsPresent();
        memberRegistrationPage.clickContinueOrderButton();
        Assert.assertEquals(memberRegistrationPage.getFirstLastNameFieldValidationMessage(), validationError, "Not expected validation message");
    }

    @Test(priority = 9, dataProvider = "emailValidationTest", dataProviderClass = MemberRegistrationDataProvider.class)
    public void checkEmailValidationTest(String email, String validationError) {
        memberRegistrationPage.inputFirstName(MemberRegistrationTestData.FIRST_NAME);
        memberRegistrationPage.inputLastName(MemberRegistrationTestData.LAST_NAME);
        memberRegistrationPage.inputAddress(MemberRegistrationTestData.ADDRESS);
        memberRegistrationPage.inputCity(MemberRegistrationTestData.CITY);
        memberRegistrationPage.inputPostcodeZip(MemberRegistrationTestData.POSTCODE_ZIP);
        memberRegistrationPage.clickCountryDropdown();
        memberRegistrationPage.clickCountryName();
        memberRegistrationPage.inputState(MemberRegistrationTestData.STATE);
        memberRegistrationPage.inputPhoneNumber(MemberRegistrationTestData.PHONE_NUMBER);
        memberRegistrationPage.inputEmailAddress(email);
        memberRegistrationPage.clickPersonalAccountButton();
        loginDetailsPage.inputUsername(LoginDetailsTestData.USERNAME);
        loginDetailsPage.inputPassword(LoginDetailsTestData.PASSWORD);
        memberRegistrationPage.clickContinueOrderButton();
        Assert.assertEquals(memberRegistrationPage.getEmailFieldValidationMessage(), validationError, "Not expected validation message");
    }

    @Test(priority = 10, dataProvider = "phoneNumberValidationTest", dataProviderClass = MemberRegistrationDataProvider.class)
    public void checkPhoneNumberValidationTest(String phoneNumber, String validationError) {
        memberRegistrationPage.inputFirstName(MemberRegistrationTestData.FIRST_NAME);
        memberRegistrationPage.inputLastName(MemberRegistrationTestData.LAST_NAME);
        memberRegistrationPage.inputAddress(MemberRegistrationTestData.ADDRESS);
        memberRegistrationPage.inputCity(MemberRegistrationTestData.CITY);
        memberRegistrationPage.inputPostcodeZip(MemberRegistrationTestData.POSTCODE_ZIP);
        memberRegistrationPage.clickCountryDropdown();
        memberRegistrationPage.clickCountryName();
        memberRegistrationPage.inputState(MemberRegistrationTestData.STATE);
        memberRegistrationPage.inputPhoneNumber(phoneNumber);
        memberRegistrationPage.inputEmailAddress(MemberRegistrationTestData.EMAIL_ADDRESS);
        memberRegistrationPage.clickPersonalAccountButton();
        loginDetailsPage.inputUsername(LoginDetailsTestData.USERNAME);
        loginDetailsPage.inputPassword(LoginDetailsTestData.PASSWORD);
        memberRegistrationPage.clickContinueOrderButton();
        Assert.assertEquals(memberRegistrationPage.getPhoneNumberFieldValidationMessage(), validationError, "Not expected phone number");
    }

    @Test(priority = 11)
    public void checkDefaultAuBusinessNumberType() {
        memberRegistrationPage.inputFirstName(MemberRegistrationTestData.FIRST_NAME);
        memberRegistrationPage.inputLastName(MemberRegistrationTestData.LAST_NAME);
        memberRegistrationPage.inputAddress(MemberRegistrationTestData.ADDRESS);
        memberRegistrationPage.inputCity(MemberRegistrationTestData.CITY);
        memberRegistrationPage.inputPostcodeZip(MemberRegistrationTestData.POSTCODE_ZIP);
        memberRegistrationPage.clickCountryDropdown();
        memberRegistrationPage.clickCountryNameAU();
        memberRegistrationPage.inputPhoneNumber(MemberRegistrationTestData.PHONE_NUMBER);
        memberRegistrationPage.inputEmailAddress(MemberRegistrationTestData.EMAIL_ADDRESS);
        memberRegistrationPage.clickBusinessAccountButton();
        Assert.assertEquals(memberRegistrationPage.getBusinessNumberTypeName(), MemberRegistrationTestData.DEFAULT_AU_BUSINESS_NUMBER_TYPE);
    }
}
