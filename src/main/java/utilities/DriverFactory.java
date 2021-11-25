package utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

public class DriverFactory {

    public enum DriverType {CHROME, FIREFOX};

    private WebDriver buildChromeDriver() {
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver(new ChromeOptions()
                                .addArguments("--disable-notifications")
                                .addArguments("--start-maximized")
                                );
    }

    private WebDriver buildFirefoxDriver() {
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setProfile(new FirefoxProfile());
        firefoxOptions.addPreference("dom.webnotifications.enabled", false);
        WebDriver driver = new FirefoxDriver(firefoxOptions);
        driver.manage().window().maximize();
        return driver;
    }

    public WebDriver makeDriver(DriverType driver) {
        switch(driver) {
            case CHROME: return buildChromeDriver();
            case FIREFOX: return buildFirefoxDriver();
            default: return null;
        }
    }
}
