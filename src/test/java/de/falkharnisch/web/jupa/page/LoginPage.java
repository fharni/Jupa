package de.falkharnisch.web.jupa.page;

import org.jboss.arquillian.graphene.GrapheneElement;
import org.jboss.arquillian.graphene.page.Location;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.jboss.arquillian.graphene.Graphene.guardAjax;
import static org.testng.Assert.assertEquals;

@Location("login.xhtml")
public class LoginPage {

    @FindBy(id = "input_loginForm:username")
    private WebElement username;

    @FindBy(id = "input_loginForm:password")
    private WebElement password;

    @FindBy(id = "loginForm:loginButton")
    private WebElement loginButton;

    @FindBy(id = "j_id_l")
    private GrapheneElement welcomeMessage;

    public void login(String username, String password) {
        this.username.sendKeys(username);
        this.password.sendKeys(password);

        guardAjax(loginButton).click();
    }

    public void checkLogin() {
        assertEquals(welcomeMessage.getText(),
                "Willkommen bei der Ju-Jutsu Passverwaltung",
                "Willkommenstext nicht gefunden");
    }

}
