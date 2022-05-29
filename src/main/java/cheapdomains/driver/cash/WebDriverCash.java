package cheapdomains.driver.cash;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

// Here I encapsulated the logic of web driver creation using singleton principle so that a lot of instances of web driver  can't be created. If there is an instance of web driver than I can get it, if there is no than create and return it.
public class WebDriverCash {
    private static WebDriver webDriver;

    private WebDriverCash() {
    }

    public static WebDriver getDriver() {
        if (webDriver == null) {
            System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
            webDriver = new ChromeDriver();
        }
        return webDriver;
    }
}
