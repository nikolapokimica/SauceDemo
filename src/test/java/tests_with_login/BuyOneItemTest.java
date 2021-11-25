package tests_with_login;

import data_generators.DataCreation;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ShoppingPage;
import pages.VerifyPage;

public class BuyOneItemTest extends BaseTestWithLogin {

    @Test
    public void addItemToCart() {
        ShoppingPage shoppingPage = new ShoppingPage(driver);
        shoppingPage.addRandomItemToCart();
        shoppingPage.openCart()
                    .clickCheckOut()
                    .fillOutInformationForm(
                            DataCreation.firstName(),
                            DataCreation.lastName(),
                            DataCreation.postalCode()
                    );
        try {
            new VerifyPage(driver).verifyCorrectItemsWereAdded(shoppingPage);
        } catch (Exception e) {
            Assert.fail("Wrong item added to shopping cart!");
        }
    }

    @Test
    public void buyItemAndFinishShopping() {
        ShoppingPage shoppingPage = new ShoppingPage(driver);
        shoppingPage.addRandomItemToCart();
        shoppingPage.openCart()
                    .clickCheckOut()
                    .fillOutInformationForm(
                            DataCreation.firstName(),
                            DataCreation.lastName(),
                            DataCreation.postalCode()
                    )
                    .finishShopping();
        try {
            new VerifyPage(driver).verifyOrderConfirmation("THANK YOU FOR YOUR ORDER");
        } catch (Exception e) {
            Assert.fail("Shopping failed");
        }
    }
}
