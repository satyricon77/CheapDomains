package cheapdomains.memberregistrationpage.dataproviders;

import org.testng.annotations.DataProvider;

public class MemberRegistrationDataProvider {
    @DataProvider(name = "postcodeZipValidationTest")
    public Object[][] createPostcodeZipInputData() {
        return new Object[][]{
                new Object[]{"somepostcode", "Provided value is incorrect, try one more time"},
                new Object[]{"123TEST123", "Provided value is incorrect, try one more time"},
                new Object[]{"%*(ZIP", "Provided value is incorrect, try one more time"},
        };
    }

    @DataProvider(name = "firstLastNameValidationTest")
    public Object[][] createFirstLastNameInputData() {
        return new Object[][]{
                new Object[]{"Danylo1", "Volovych", "Only alphabetic characters allowed"},
                new Object[]{"$Danylo", "Volovych", "Only alphabetic characters allowed"},
                new Object[]{"Danylo", "Volovych$", "Only alphabetic characters allowed"},
                new Object[]{"Danylo", "Volovych1", "Only alphabetic characters allowed"},
                new Object[]{"Данил", "Волович", "Only alphabetic characters allowed"},
        };
    }

    @DataProvider(name = "emailValidationTest")
    public Object[][] createEmailInputData() {
        return new Object[][]{
                new Object[]{"dstestemail.com", "Email address is invalid"},
                new Object[]{"dstestemail@.com", "Email address is invalid"},
                new Object[]{"ds...testemail@gmail.com", "Email address is invalid"},
                new Object[]{"дсемеил@gmail.com", "Email address is invalid"},
        };
    }

    @DataProvider(name = "phoneNumberValidationTest")
    public Object[][] createPhoneNumberInputData() {
        return new Object[][]{
                new Object[]{"phone number", "Phone number is incorrect"},
                new Object[]{"-38067434934", "Phone number is incorrect"},
                new Object[]{"067_149_50_25", "Phone number is incorrect"},
        };
    }
}
