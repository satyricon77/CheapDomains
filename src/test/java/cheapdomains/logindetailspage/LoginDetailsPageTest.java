package cheapdomains.logindetailspage;

import cheapdomains.driver.cash.WebDriverCash;
import cheapdomains.logindetailspage.dataproviders.LoginDetailsDataProvider;
import cheapdomains.pages.LoginDetailsPage;
import cheapdomains.pages.MemberRegistrationPage;
import cheapdomains.testdata.LoginDetailsTestData;
import cheapdomains.testdata.MemberRegistrationTestData;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginDetailsPageTest {
    private WebDriver driver = WebDriverCash.getDriver();
    private LoginDetailsPage loginDetailsPage = new LoginDetailsPage(driver);
    private MemberRegistrationPage memberRegistrationPage = new MemberRegistrationPage(driver);

//Here I use priority just in order to have tests in logical sequences.
    @Test (priority = 1)
    public void checkBoxTitleTest() {
        driver.get("https://www.cheapdomains.com.au/register/member_register_test.php");
        Assert.assertEquals(loginDetailsPage.getBoxTitle(), LoginDetailsTestData.EXPECTED_BOX_TITLE, "Not expected box title.");
        driver.quit();
    }

    @Test (priority = 2)
    public void checkSubTitleTest() {
        driver.get("https://www.cheapdomains.com.au/register/member_register_test.php");
        Assert.assertEquals(loginDetailsPage.getSubTitle(), LoginDetailsTestData.EXPECTED_SUB_TITLE, "Not expected sub title.");
        driver.quit();
    }

    @Test(priority = 3, dataProvider = "usernamePasswordValidationTest", dataProviderClass = LoginDetailsDataProvider.class)
    public void checkUsernamePasswordValidationTest(String username, String password, String validationError) {
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
        loginDetailsPage.inputUsername(username);
        loginDetailsPage.passwordFieldIsPresent();
        loginDetailsPage.inputPassword(password);
        memberRegistrationPage.continueOrderButtonIsPresent();
        memberRegistrationPage.clickContinueOrderButton();
        Assert.assertEquals(loginDetailsPage.getUsernamePasswordFieldValidationMessage(), validationError, "Not expected validation message");
        driver.quit();
    }
}
