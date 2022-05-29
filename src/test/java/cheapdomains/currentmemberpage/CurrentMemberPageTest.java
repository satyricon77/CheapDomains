package cheapdomains.currentmemberpage;

import cheapdomains.currentmemberpage.dataproviders.CurrentMemberDataProvider;
import cheapdomains.driver.cash.WebDriverCash;
import cheapdomains.pages.CurrentMemberPage;
import cheapdomains.testdata.CurrentMemberTestData;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CurrentMemberPageTest {
    private WebDriver driver = WebDriverCash.getDriver();
    private CurrentMemberPage currentMemberPage = new CurrentMemberPage(driver);

//Here I use priority just in order to have tests in logical sequences.
    @Test (priority = 1)
    public void checkBoxTitleTest(String boxTitle) {
        driver.get("https://www.cheapdomains.com.au/register/member_register_test.php");
        Assert.assertEquals(currentMemberPage.getBoxTitle(), CurrentMemberTestData.EXPECTED_BOX_TITLE, "Not expected box title.");
        driver.quit();
    }

    @Test (priority = 2)
    public void checkSubTitleTest() {
        driver.get("https://www.cheapdomains.com.au/register/member_register_test.php");
        Assert.assertEquals(currentMemberPage.getSubTitle(), CurrentMemberTestData.EXPECTED_SUB_TITLE, "Not expected subtitle.");
        driver.quit();
    }

    @Test (priority = 3)
    public void checkLoginTest(){
        driver.get("https://www.cheapdomains.com.au/register/member_register_test.php");
        driver.manage().window().fullscreen();
        currentMemberPage.loginFieldIsPresent();
        currentMemberPage.inputUsername(CurrentMemberTestData.USERNAME);
        currentMemberPage.passwordFieldIsPresent();
        currentMemberPage.inputPassword(CurrentMemberTestData.PASSWORD);
        currentMemberPage.loginButtonIsPresent();
        currentMemberPage.clickLoginButton();
        Assert.assertTrue(driver.getCurrentUrl().contains("login_process"));
        Assert.assertTrue(currentMemberPage.visitVodienButtonIsPresent());
        driver.quit();
    }

    //This test represents the situation when we have validation errors for incorrect input. This test must fail. It's negative test case.
    @Test (priority = 3 , dataProvider = "loginValidationTest", dataProviderClass = CurrentMemberDataProvider.class)
    public void checkLoginFieldValidationTest(String login, String validationError) {
        driver.get("https://www.cheapdomains.com.au/register/member_register_test.php");
        driver.manage().window().fullscreen();
        currentMemberPage.loginFieldIsPresent();
        currentMemberPage.inputUsername(login);
        currentMemberPage.passwordFieldIsPresent();
        currentMemberPage.inputPassword(CurrentMemberTestData.PASSWORD);
        currentMemberPage.loginButtonIsPresent();
        currentMemberPage.clickLoginButton();
        Assert.assertEquals(currentMemberPage.getLoginValidationError(), validationError);
        driver.quit();
    }

    //This test represents the situation when we have validation errors for incorrect input. This test must fail. It's negative test case.
    @Test(priority = 4, dataProvider = "passwordValidationTest", dataProviderClass = CurrentMemberDataProvider.class)
    public void checkPasswordFieldValidationTest(String password, String validationError){
        driver.get("https://www.cheapdomains.com.au/register/member_register_test.php");
        driver.manage().window().fullscreen();
        currentMemberPage.loginFieldIsPresent();
        currentMemberPage.inputUsername(CurrentMemberTestData.USERNAME);
        currentMemberPage.passwordFieldIsPresent();
        currentMemberPage.inputPassword(password);
        currentMemberPage.loginButtonIsPresent();
        currentMemberPage.clickLoginButton();
        Assert.assertEquals(currentMemberPage.getPasswordValidationError(), validationError, "Not expected validation message");
        driver.quit();
    }
}
