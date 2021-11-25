package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LayoutPage extends BasePage {

    private By menuButtonBy = By.id("react-burger-menu-btn"),
               logoutLinkBy = By.xpath("//*[@id=\"logout_sidebar_link\"]");

    public LayoutPage(WebDriver driver) {
        super(driver);
    }

    public LayoutPage openMenu() {
        click(menuButtonBy);
        return this;
    }

    public LayoutPage logout() {
        click(logoutLinkBy);
        return this;
    }
}
