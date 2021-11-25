package tests_with_login;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LayoutPage;

public class LogoutTest extends BaseTestWithLogin {

    @Test
    public void logout() {
        new LayoutPage(driver)
                .openMenu()
                .logout();
        try {
            verifyPage.verifyUserIsLoggedOut("Login");
        } catch (Exception e) {
            Assert.fail("User is not logged out");
        }
    }
}
