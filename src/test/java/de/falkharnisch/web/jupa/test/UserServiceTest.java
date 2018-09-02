package de.falkharnisch.web.jupa.test;

import de.falkharnisch.web.jupa.database.Club;
import de.falkharnisch.web.jupa.database.User;
import de.falkharnisch.web.jupa.services.ClubService;
import de.falkharnisch.web.jupa.services.UserService;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.testng.annotations.Test;

import javax.inject.Inject;
import java.time.LocalDate;
import java.util.List;

import static org.testng.Assert.*;

public class UserServiceTest extends AbstractArquillianTest {

    @Inject
    private ClubService clubService;

    @Inject
    private UserService userService;

    @Test
    public void testSelectAll() {
        List<User> users = userService.getUserByNamepart("");
        assertNotNull(users);
        assertEquals(users.size(), 16, "Falsche Anzahl an Benutzern");
    }

    @Test(dependsOnMethods = "testSelectAll")
    @Transactional
    public void testInsert() {
        List<User> users = userService.getUserByNamepart("");
        int countBefore = users.size();

        User user = new User("1234", "TestPw", "Michael", "Schneider", "michael@schneider.de", LocalDate.now());
        userService.persist(user);

        users = userService.getUserByNamepart("");
        int countAfter = users.size();

        assertEquals(countAfter, countBefore + 1, "Falsche Anzahl an Benutzern");
    }

    @Test
    public void testSelectById() {
        User user = userService.getUserById(4);
        assertEquals(user.getForename(), "Brian");
        assertEquals(user.getSurname(), "Smith");
    }

    @Test
    public void testSelectByUsername() {
        User user = userService.getUserByUsername("0504001000019");
        assertEquals(user.getForename(), "Maike");
        assertEquals(user.getSurname(), "John");
    }

    @Test
    public void testSelectByUsernamePart() {
        List<User> users = userService.getUserByUsernamePart("0502001");
        assertEquals(users.size(), 12, "Falsche Anzahl an Benutzern");
    }

    @Test
    public void testLogin() {
        boolean login = userService.login("0503001000012", "wolfgang");
        assertTrue(login, "Sollte erfolgreich angemeldet werden");

        login = userService.login("0503001000012", "test12");
        assertFalse(login, "Sollte nicht angemeldet werden");

        login = userService.login("0503001000013", "test");
        assertFalse(login, "Sollte nicht angemeldet werden");
    }

    @Test
    public void testMaxIdForClub() {
        Club c = clubService.getClubByName("TV 1875 Paderborn e.V.");
        String maxIdForClub = userService.getMaxIdForClub(c);
        assertEquals(maxIdForClub, "0502001000121", "Die höchste ID passt nicht");
    }

    @Test
    public void testUsersForClub() {
        Club c = clubService.getClubByName("TV 1875 Paderborn e.V.");
        List<User> usersForClub = userService.getUsersForClub(c);
        assertEquals(usersForClub.size(), 12, "Für den Verein konnten nicht die korrekten Mitglieder gefunden werden");
    }
}