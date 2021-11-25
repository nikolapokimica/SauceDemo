package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.*;

public class ShoppingPage extends BasePage{

    private By checkOutButtonBy = By.id("checkout"),
               openCartButtonBy = By.className("shopping_cart_link"),
               firstNameFieldBy = By.id("first-name"),
               lastNameFieldBy = By.id("last-name"),
               postalCodeFieldBy = By.id("postal-code"),
               continueButtonBy = By.id("continue"),
               finishButtonBy = By.id("finish"),
               inventoryItemBy = By.className("inventory_item"),
               cartItemBy = By.className("cart_item");

    private List<String> listOfNamesOfCartItems = new LinkedList<>();

    public ShoppingPage(WebDriver driver) {
        super(driver);
    }

    public List<String> getListOfNamesOfCartItems() {
        return listOfNamesOfCartItems;
    }

    public String addRandomItemToCart() {
        Map<Integer, String> cartItems = new HashMap<>();
        String itemName = "";
        for (int i = 1; i <= driver.findElements(inventoryItemBy).size(); i++) {
            String buttonXpath = "/html/body/div/div/div/div[2]/div/div/div/div[" + i + "]/div[2]/div[2]/button";
            String itemNameXpath = "/html/body/div/div/div/div[2]/div/div/div/div[" + i + "]/div[2]/div[1]/a/div";
            if (driver.findElement(By.xpath(buttonXpath)).getText().equals("ADD TO CART")) {
                cartItems.put(i, driver.findElement(By.xpath(itemNameXpath)).getText() );
            }
        }
        if (!cartItems.isEmpty()) {
            ArrayList<Map.Entry<Integer, String>> cartItemsEntries = new ArrayList(cartItems.entrySet());
            int randomItemNumber = new Random().nextInt(cartItemsEntries.size());
            itemName = cartItemsEntries.get(randomItemNumber).getValue();
            listOfNamesOfCartItems.add(itemName);
            int itemNumber = cartItemsEntries.get(randomItemNumber).getKey();
            String buttonXpath = "/html/body/div/div/div/div[2]/div/div/div/div[" + itemNumber + "]/div[2]/div[2]/button";
            click(By.xpath(buttonXpath));
        }
        return itemName;
    }

    public ShoppingPage clickCheckOut() {
        click(checkOutButtonBy);
        return this;
    }

    public String removeRandomItemFromCartInCheckout() {
        String itemName = "";
        List<WebElement> items = driver.findElements(cartItemBy);
        if (items.size() > 0) {
            int itemNumber = 2 + (1 + new Random().nextInt(items.size()));
            String xpath = "/html/body/div/div/div/div[2]/div/div[1]/div[" + itemNumber + "]/div[2]/a/div";
            itemName = readText(By.xpath(xpath));
            xpath = "/html/body/div/div/div/div[2]/div/div[1]/div[" + itemNumber + "]/div[2]/div[2]/button";
            click(By.xpath(xpath));
            listOfNamesOfCartItems.remove(itemName);
        }
        return itemName;
    }

    public ShoppingPage openCart() {
        click(openCartButtonBy);
        return this;
    }

    public ShoppingPage fillOutInformationForm(String firstName, String lastName, String postalCode) {
        writeText(firstNameFieldBy, firstName);
        writeText(lastNameFieldBy,lastName);
        writeText(postalCodeFieldBy, postalCode);
        click(continueButtonBy);
        return this;
    }

    public ShoppingPage finishShopping() {
        click(finishButtonBy);
        return this;
    }
}
