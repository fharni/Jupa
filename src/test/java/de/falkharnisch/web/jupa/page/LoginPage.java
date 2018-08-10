package de.falkharnisch.web.jupa.page;

import org.jboss.arquillian.graphene.page.Location;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.jboss.arquillian.graphene.Graphene.guardAjax;

@Location("login.xhtml")
public class LoginPage {

    @FindBy(id = "headerForm:menu-links")
    private MenuHeader menuHeader;

    @FindBy(id = "input_loginForm:username")
    private WebElement username;

    @FindBy(id = "input_loginForm:password")
    private WebElement password;

    @FindBy(id = "loginForm:loginButton")
    private WebElement loginButton;

    public void login(String username, String password) {
        this.username.sendKeys(username);
        this.password.sendKeys(password);

        guardAjax(loginButton).click();
    }

    public boolean hasLoginButton() {
        return loginButton.isDisplayed();
    }
}
