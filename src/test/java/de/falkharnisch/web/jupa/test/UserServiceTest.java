package de.falkharnisch.web.jupa.test;

import de.falkharnisch.web.jupa.database.User;
import de.falkharnisch.web.jupa.producer.EntityManagerProducer;
import de.falkharnisch.web.jupa.services.UserService;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.testng.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.testng.annotations.Test;

import javax.inject.Inject;
import java.io.File;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.testng.AssertJUnit.*;

public class UserServiceTest extends Arquillian {

    private static final String WEBAPP_SRC = "src/main/webapp";

    @Inject
    private UserService userService;

    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class)
                .addPackage("de.falkharnisch.web.jupa.database")
                .addClass(UserService.class)
                .addClass(EntityManagerProducer.class)
                .setWebXML(new File(WEBAPP_SRC, "WEB-INF/web.xml"))
                .addAsResource("test-persistence.xml", "META-INF/persistence.xml")
                .addAsResource("initial_data.sql", "META-INF/initial_data.sql")
                .addAsWebInfResource(new File(WEBAPP_SRC, "WEB-INF/faces-config.xml"))
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

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