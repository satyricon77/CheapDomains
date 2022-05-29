package cheapdomains.basetest;

import cheapdomains.driver.cash.WebDriverCash;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;

public class BaseTest {
    protected WebDriver driver = WebDriverCash.getDriver();

    @AfterSuite
    public void driverQuit(){
        driver.quit();
    }
}
