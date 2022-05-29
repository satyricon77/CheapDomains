package cheapdomains.currentmemberpage.dataproviders;

import org.testng.annotations.DataProvider;

public class CurrentMemberDataProvider {
    @DataProvider(name = "loginValidationTest")
    public Object[][] createLoginInputData() {
        return new Object[][]{
                new Object[]{"*danylo()volovych*", "Login is incorrect, try one more time"},
                new Object[]{"*d^a^nylo_volovych", "Login is incorrect, try one more time"},
                new Object[]{"логиннейм", "Login is incorrect, try one more time"},
        };
    }


    @DataProvider(name = "passwordValidationTest")
    public Object[][] createPasswordInputData() {
        return new Object[][]{
                new Object[]{"123", "Password is incorrect, try one more time"},
                new Object[]{"****", "Password is incorrect, try one more time"},
                new Object[]{"простопароль", "Password is incorrect, try one more time"},
        };
    }
}



