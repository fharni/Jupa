package de.falkharnisch.web.jupa.test;

import de.falkharnisch.web.jupa.page.CreateMemberPage;
import de.falkharnisch.web.jupa.page.LoginPage;
import de.falkharnisch.web.jupa.page.MenuHeader;
import de.falkharnisch.web.jupa.page.MyClubPage;
import org.jboss.arquillian.graphene.page.InitialPage;
import org.jboss.arquillian.graphene.page.Page;
import org.jboss.arquillian.testng.Arquillian;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;

@Test(dependsOnGroups = "login")
public class ClubAdminIT extends AbstractArquillianTest {

    @Page
    private MenuHeader menuHeader;

    @Page
    private MyClubPage myClubPage;

    @Page
    private CreateMemberPage createMemberPage;

    @Test(dataProvider = Arquillian.ARQUILLIAN_DATA_PROVIDER)
    public void testPassport(@InitialPage LoginPage loginPage) throws InterruptedException {
        loginPage.login("0504001000019", "test");
        menuHeader.goToMyClub();

        assertEquals(myClubPage.getClubname(), "SG Erfstadt 1970 e.V.");
        myClubPage.createMember();
        createMemberPage.setForename("Max");
        createMemberPage.setSurname("Mustermann");
        createMemberPage.setEmail("max@mustermann.de");

        createMemberPage.setBirthday("02.02.2018");
        TimeUnit.SECONDS.sleep(2);
        createMemberPage.setBeginDate("01.01.2018");
    }
}
