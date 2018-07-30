package de.falkharnisch.web.jupa.page;

import org.jboss.arquillian.graphene.Graphene;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreateMemberPage {

    @FindBy(id = "contentForm:membersTab:forename")
    private WebElement forename;

    @FindBy(id = "contentForm:membersTab:surname")
    private WebElement surname;

    @FindBy(id = "contentForm:membersTab:birthday_input")
    private WebElement birthday;

    @FindBy(id = "contentForm:membersTab:email")
    private WebElement email;

    @FindBy(id = "contentForm:membersTab:beginDate_input")
    private WebElement beginDate;

    @FindBy(id = "contentForm:membersTab:saveMemberButton")
    private WebElement saveMemberButton;


    public void setForename(String forename) {
        this.forename.sendKeys(forename);
    }

    public void setSurname(String surname) {
        this.surname.sendKeys(surname);
    }

    public void setBirthday(String birthday) {
        this.birthday.click();
        this.birthday.click();
        this.birthday.sendKeys(birthday);
    }

    public void setEmail(String email) {
        this.email.sendKeys(email);
    }

    public void setBeginDate(String beginDate) {
        this.beginDate.click();
        this.beginDate.click();
        this.beginDate.sendKeys(beginDate);
    }

    public void clickSaveMember() {
        Graphene.guardAjax(saveMemberButton).click();
    }
}
