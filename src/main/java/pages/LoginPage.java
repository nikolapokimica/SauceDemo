package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    private By  usernameFieldBy = By.id("user-name"),
                passwordFieldBy = By.id("password") ,
                loginButtonBy = By.id("login-button");


    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage login(String username, String password) {
        writeText(usernameFieldBy, username);
        writeText(passwordFieldBy, password);
        click(loginButtonBy);
        return this;
    }
}
