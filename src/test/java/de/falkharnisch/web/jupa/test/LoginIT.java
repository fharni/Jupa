package de.falkharnisch.web.jupa.test;

import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.graphene.page.InitialPage;
import org.jboss.arquillian.testng.Arquillian;
import org.testng.annotations.Test;

import de.falkharnisch.web.jupa.page.LoginPage;

@RunAsClient
public class LoginIT extends AbstractArquillianTest {

//    @Page
//    private LoginPage loginPage;

//    @Page
//    private HomePage homePage;

    @Test(dataProvider = Arquillian.ARQUILLIAN_DATA_PROVIDER)
    public void should_login_successfully(@InitialPage LoginPage loginPage) {
        loginPage.login("0502001000015", "falk");
    }
}
