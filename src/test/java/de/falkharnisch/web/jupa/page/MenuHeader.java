package de.falkharnisch.web.jupa.page;

import org.jboss.arquillian.graphene.Graphene;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MenuHeader {

    @FindBy(id = "headerForm:home")
    private WebElement home;

    @FindBy(id = "headerForm:myclub")
    private WebElement myclub;

    @FindBy(id = "headerForm:passport")
    private WebElement passport;

    @FindBy(id = "headerForm:logout")
    private WebElement logout;

    public void goToPassport() {
        Graphene.guardHttp(passport).click();
    }

    public void goToMyClub() {
        Graphene.guardHttp(myclub).click();
    }

    public boolean hasLogoutButton() {
        return logout.isDisplayed();
    }

    public void logout() {
        Graphene.guardHttp(logout).click();
    }
}
