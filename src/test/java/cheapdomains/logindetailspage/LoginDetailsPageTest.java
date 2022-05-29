package cheapdomains.logindetailspage;

import cheapdomains.basetest.BaseTest;
import cheapdomains.logindetailspage.dataproviders.LoginDetailsDataProvider;
import cheapdomains.pages.logindetails.LoginDetailsPage;
import cheapdomains.pages.memberregistration.MemberRegistrationPage;
import cheapdomains.testdata.LoginDetailsTestData;
import cheapdomains.testdata.MemberRegistrationTestData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginDetailsPageTest extends BaseTest {
    private LoginDetailsPage loginDetailsPage;
    private MemberRegistrationPage memberRegistrationPage;

    @BeforeMethod
    public void setUp() {
        driver.get("https://www.cheapdomains.com.au/register/member_register_test.php");
        driver.manage().window().fullscreen();
        loginDetailsPage = new LoginDetailsPage(driver);
        memberRegistrationPage = new MemberRegistrationPage(driver);
    }

    @Test (priority = 1)
    public void loginDetailsElementsArePresent(){
        Assert.assertTrue(loginDetailsPage.usernameFieldIsPresent(), "Username field is not found");
        Assert.assertTrue(loginDetailsPage.passwordFieldIsPresent(), "Password field is not found");
    }

    //Here I use priority just in order to have tests in logical sequences.
    @Test(priority = 2)
    public void checkBoxTitleTest() {
        Assert.assertEquals(loginDetailsPage.getBoxTitle(), LoginDetailsTestData.EXPECTED_BOX_TITLE, "Not expected box title.");
    }

    @Test(priority = 3)
    public void checkSubTitleTest() {
        Assert.assertEquals(loginDetailsPage.getSubTitle(), LoginDetailsTestData.EXPECTED_SUB_TITLE, "Not expected sub title.");
    }

    @Test(priority = 4, dataProvider = "usernamePasswordValidationTest", dataProviderClass = LoginDetailsDataProvider.class)
    public void checkUsernamePasswordValidationTest(String username, String password, String validationError) {
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
        loginDetailsPage.inputUsername(username);
        loginDetailsPage.inputPassword(password);
        memberRegistrationPage.clickContinueOrderButton();
        Assert.assertEquals(loginDetailsPage.getUsernamePasswordFieldValidationMessage(), validationError, "Not expected validation message");
    }
}
