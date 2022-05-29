package cheapdomains.currentmemberpage;

import cheapdomains.basetest.BaseTest;
import cheapdomains.currentmemberpage.dataproviders.CurrentMemberDataProvider;
import cheapdomains.pages.currentmember.CurrentMemberPage;
import cheapdomains.testdata.CurrentMemberTestData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CurrentMemberPageTest extends BaseTest {
    private CurrentMemberPage currentMemberPage;

    @BeforeMethod
    public void setUp() {
        driver.get("https://www.cheapdomains.com.au/register/member_register_test.php");
        driver.manage().window().fullscreen();
        currentMemberPage = new CurrentMemberPage(driver);
    }

    @Test (priority = 1)
    public void currentMemberElementsArePresentTest() {
        Assert.assertTrue(currentMemberPage.loginFieldIsPresent(), "Login field is not found");
        Assert.assertTrue(currentMemberPage.passwordFieldIsPresent(), "Password field is not found");
        Assert.assertTrue(currentMemberPage.loginButtonIsPresent(), "Login button is not found");
    }

    //Here I use priority just in order to have tests in logical sequences.
    @Test(priority = 2)
    public void checkBoxTitleTest() {
        Assert.assertEquals(currentMemberPage.getBoxTitle(), CurrentMemberTestData.EXPECTED_BOX_TITLE, "Not expected box title.");
    }

    @Test(priority = 3)
    public void checkSubTitleTest() {
        Assert.assertEquals(currentMemberPage.getSubTitle(), CurrentMemberTestData.EXPECTED_SUB_TITLE, "Not expected subtitle.");
    }

    @Test(priority = 4)
    public void checkLoginTest() {
        currentMemberPage.inputUsername(CurrentMemberTestData.USERNAME);
        currentMemberPage.inputPassword(CurrentMemberTestData.PASSWORD);
        currentMemberPage.clickLoginButton();
        Assert.assertTrue(driver.getCurrentUrl().contains("login_process"));
        Assert.assertTrue(currentMemberPage.visitVodienButtonIsPresent(), "Visit vodien button is not found");
    }

    //This test represents the situation when we have validation errors for incorrect input. This test must fail. It's negative test case.
    @Test(priority = 5, dataProvider = "loginValidationTest", dataProviderClass = CurrentMemberDataProvider.class)
    public void checkLoginFieldValidationTest(String login, String validationError) {
        currentMemberPage.inputUsername(login);
        currentMemberPage.inputPassword(CurrentMemberTestData.PASSWORD);
        currentMemberPage.clickLoginButton();
        Assert.assertEquals(currentMemberPage.getLoginValidationError(), validationError, "Not expected validation message");
    }

    //This test represents the situation when we have validation errors for incorrect input. This test must fail. It's negative test case.
    @Test(priority = 6, dataProvider = "passwordValidationTest", dataProviderClass = CurrentMemberDataProvider.class)
    public void checkPasswordFieldValidationTest(String password, String validationError) {
        currentMemberPage.inputUsername(CurrentMemberTestData.USERNAME);
        currentMemberPage.inputPassword(password);
        currentMemberPage.clickLoginButton();
        Assert.assertEquals(currentMemberPage.getPasswordValidationError(), validationError, "Not expected validation message");
    }
}
