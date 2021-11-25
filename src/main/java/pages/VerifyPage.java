package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;
import java.util.stream.Collectors;

public class VerifyPage extends BasePage {

    private By errorNotificationBy = By.xpath("//*[@id=\"login_button_container\"]/div/form/div[3]"),
               bannerTitleBy = By.className("title"),
               loginButtonBy = By.id("login-button"),
               orderConfirmationText = By.xpath("//*[@id=\"checkout_complete_container\"]/h2"),
               itemBy = By.className("inventory_item_name");

    public VerifyPage(WebDriver driver) {
        super(driver);
    }

    public VerifyPage verifyFailedLogin(String expectedMessage) {
        assertStringEquals(readText(errorNotificationBy), expectedMessage);
        return this;
    }

    public VerifyPage verifyLogin(String expectedText) {
        assertStringEquals(readText(bannerTitleBy), expectedText);
        return this;
    }

    public VerifyPage verifyUserIsLoggedOut(String expectedText) {
        assertStringEquals(readAttribute(loginButtonBy), expectedText);
        return this;
    }

    public VerifyPage verifyOrderConfirmation(String expectedText) {
        assertStringEquals(readText(orderConfirmationText), expectedText);
        return this;
    }

    public VerifyPage verifyItemRemovedFromCart(String itemName) {
        boolean contains = false;
        List<WebElement> items = driver.findElements(itemBy);
        if (items.stream().map(element -> element.getText()).collect(Collectors.toList()).contains(itemName)) contains = true;
        Assert.assertFalse(contains);
        return this;
    }

    public VerifyPage verifyCorrectItemsWereAdded(ShoppingPage shoppingPage) {
        Assert.assertEquals(driver.findElements(itemBy).stream().map(element -> element.getText()).collect(Collectors.toSet()),
                            shoppingPage.getListOfNamesOfCartItems().stream().collect(Collectors.toSet()));
        return this;
    }
}
