package de.falkharnisch.web.jupa.page;

import org.jboss.arquillian.graphene.Graphene;
import org.jboss.arquillian.graphene.page.Location;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Location("myclub.xhtml")
public class MyClubPage {

    @FindBy(id = "contentForm:clubname")
    private WebElement clubname;

    @FindBy(id = "contentForm:membersTab:createMembersButton")
    private WebElement createMemberButton;


    public String getClubname() {
        return clubname.getText();
    }

    public void createMember() {
        createMemberButton.click();
        Graphene.waitGui().until().element(createMemberButton).is().not().visible();
//		Graphene.guardAjax(createMemberButton);
    }
}
