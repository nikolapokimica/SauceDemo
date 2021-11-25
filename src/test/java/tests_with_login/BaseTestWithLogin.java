package tests_with_login;

import org.testng.annotations.BeforeMethod;
import pages.LoginPage;
import pages.VerifyPage;
import tests.BaseTest;
import utilities.PropertyManager;

public abstract class BaseTestWithLogin extends BaseTest {

    protected VerifyPage verifyPage;

    @Override
    @BeforeMethod(alwaysRun = true)
    public void setup() {
        super.setup();
        LoginPage page = new LoginPage(driver);
        page.openPage(PropertyManager.getInstance().getHomePageUrl());
        page.login(PropertyManager.getInstance().getUsername(),
                PropertyManager.getInstance().getPassword());
        verifyPage = new VerifyPage(driver);
    }

}
