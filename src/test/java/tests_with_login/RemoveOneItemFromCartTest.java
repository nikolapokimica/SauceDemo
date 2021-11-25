package tests_with_login;

import data_generators.DataCreation;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ShoppingPage;
import pages.VerifyPage;

public class RemoveOneItemFromCartTest extends BaseTestWithLogin {

    @Test
    public void removeItemFromCart() {
        ShoppingPage shoppingPage = new ShoppingPage(driver);
        shoppingPage.addRandomItemToCart();
        shoppingPage.openCart();
        String itemName = shoppingPage.removeRandomItemFromCartInCheckout();
        shoppingPage.clickCheckOut()
                    .fillOutInformationForm(
                            DataCreation.firstName(),
                            DataCreation.lastName(),
                            DataCreation.postalCode()
                    );
        try {
            new VerifyPage(driver).verifyItemRemovedFromCart(itemName);
        } catch (Exception e) {
            Assert.fail("Item wasn't removed from the shopping cart");
        }
    }
}
