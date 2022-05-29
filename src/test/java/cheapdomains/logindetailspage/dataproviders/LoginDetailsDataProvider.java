package cheapdomains.logindetailspage.dataproviders;

import org.testng.annotations.DataProvider;

public class LoginDetailsDataProvider {
    @DataProvider(name = "usernamePasswordValidationTest")
    public Object[][] createUsernamePasswordInputData() {
        return new Object[][]{
                new Object[]{"satyr", "123456", "Username or password is incorrect, try one more time"},
                new Object[]{"satyricon", "1234", "Username or password is incorrect, try one more time"},
                new Object[]{"", "", "Username or password is incorrect, try one more time"},
        };
    }
}
