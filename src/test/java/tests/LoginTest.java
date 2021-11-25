package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.VerifyPage;
import utilities.PropertyManager;

public class LoginTest extends BaseTest {

    @Test
    public void login() {
        LoginPage page = new LoginPage(driver);
        page.openPage(PropertyManager.getInstance().getHomePageUrl());
        page.login(PropertyManager.getInstance().getUsername(),
                PropertyManager.getInstance().getPassword());

        try {
            new VerifyPage(driver).verifyLogin("PRODUCTS");
        } catch (Exception e) {
            Assert.fail("User is not logged in");
        }
    }

}
