package de.falkharnisch.web.jupa.test;

import de.falkharnisch.web.jupa.page.HomePage;
import de.falkharnisch.web.jupa.page.LoginPage;
import org.jboss.arquillian.graphene.page.InitialPage;
import org.jboss.arquillian.graphene.page.Page;
import org.jboss.arquillian.testng.Arquillian;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

@Test(groups = "login")
public class LoginIT extends AbstractArquillianTest {

    @Page
    private HomePage homePage;

    @Test(dataProvider = Arquillian.ARQUILLIAN_DATA_PROVIDER)
    public void should_login_successfully(@InitialPage LoginPage loginPage) {
        loginPage.login("0502001000015", "test");
        assertTrue(homePage.getMenuHeader().hasLogoutButton());

        // TOOO find and fix this bug
        homePage.getMenuHeader().logout();
        homePage.getMenuHeader().logout();
        assertTrue(loginPage.hasLoginButton());
    }
}
