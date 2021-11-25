package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.VerifyPage;
import utilities.PropertyManager;

public class FailedLoginTest extends BaseTest {

    @Test(dataProvider="LoginProvider")
    public void failedLogin(String username, String password) {
        LoginPage page = new LoginPage(driver);
        page.openPage(PropertyManager.getInstance().getHomePageUrl());
        page.login(username, password);
        try {
            VerifyPage verifyPage = new VerifyPage(driver);
            if (username.equals("")) verifyPage.verifyFailedLogin("Epic sadface: Username is required");
            else if (password.equals("")) verifyPage.verifyFailedLogin("Epic sadface: Password is required");
            else verifyPage.verifyFailedLogin("Epic sadface: Username and password do not match any user in this service");
        } catch (Exception e) {
            Assert.fail("User is logged in with username: " + username + " and password: " + password );
        }
    }

    @DataProvider(name="LoginProvider")
    public Object[][] getDataFromDataProvider() {
        return new Object[][]
                {
                        {PropertyManager.getInstance().getInvalidUsername(), PropertyManager.getInstance().getInvalidPassword()},
                        {PropertyManager.getInstance().getUsername(), ""},
                        {"", PropertyManager.getInstance().getPassword()}
                };
    }
}
