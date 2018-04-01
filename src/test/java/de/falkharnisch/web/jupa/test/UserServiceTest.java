package de.falkharnisch.web.jupa.test;

import de.falkharnisch.web.jupa.database.*;
import de.falkharnisch.web.jupa.producer.EntityManagerProducer;
import de.falkharnisch.web.jupa.services.UserService;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import java.io.File;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(Arquillian.class)
public class UserServiceTest {

    private static final String WEBAPP_SRC = "src/main/webapp";
    @Inject
    private UserService userService;

    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class)
                .addClass(User.class)
                .addClass(Club.class)
                .addClass(District.class)
                .addClass(Federation.class)
                .addClass(Role.class)
                .addClass(Function.class)
                .addClass(UserService.class)
                .addClass(EntityManagerProducer.class)
                .setWebXML(new File(WEBAPP_SRC, "WEB-INF/web.xml"))
                .addAsResource("test-persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource(new File(WEBAPP_SRC, "WEB-INF/faces-config.xml"))
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Test
    public void testSelectAll() {
        List<User> userByNamepart = userService.getUserByNamepart("");
        assertNotNull(userByNamepart);
    }

    @Test
    @Transactional
    public void testInsert() {
        User user = new User("1234", "Test", "Benutzer");
        userService.persist(user);
        List<User> users = userService.getUserByNamepart("");
        assertEquals("Ein Benutzer wird erwartet", 1, users.size());
    }

}