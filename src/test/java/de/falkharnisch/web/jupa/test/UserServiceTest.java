package de.falkharnisch.web.jupa.test;

import static org.junit.Assert.assertNotNull;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertTrue;

import java.util.List;

import javax.inject.Inject;

import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.testng.annotations.Test;

import de.falkharnisch.web.jupa.database.User;
import de.falkharnisch.web.jupa.services.UserService;

public class UserServiceTest extends AbstractArquillianTest {

    @Inject
    private UserService userService;

    @Test
    public void testSelectAll() {
        List<User> users = userService.getUserByNamepart("");
        assertNotNull(users);
        assertEquals("Falsche Anzahl an Benutzern", 11, users.size());
    }

    @Test(dependsOnMethods = "testSelectAll")
    @Transactional
    public void testInsert() {
        List<User> users = userService.getUserByNamepart("");
        int countBefore = users.size();

        User user = new User("1234", "TestPw", "Michael", "Schneider");
        userService.persist(user);

        users = userService.getUserByNamepart("");
        int countAfter = users.size();

        assertEquals("Falsche Anzahl an Benutzern", countBefore+1, countAfter);
    }

    @Test
    public void testSelectById(){
        User user = userService.getUserById(4);
        assertEquals("Referent", user.getForename());
        assertEquals("desBezirkes", user.getSurname());
    }

    @Test
    public void testSelectByUsername(){
        User user = userService.getUserByUsername("0504001000019");
        assertEquals(user.getForename(), "Chefin");
        assertEquals(user.getSurname(), "desVereins");
    }

    @Test
    public void testSelectByUsernamePart(){
        List<User> users = userService.getUserByUsernamePart("0502001");
        assertEquals("Falsche Anzahl an Benutzern", 8, users.size());
    }

    @Test
    public void testLogin(){
        boolean login = userService.login("0503001000012", "test");
        assertTrue("Sollte erfolgreich angemeldet werden", login);

        login = userService.login("0503001000012", "test12");
        assertFalse("Sollte nicht angemeldet werden", login);

        login = userService.login("0503001000013", "test");
        assertFalse("Sollte nicht angemeldet werden", login);
    }
}