package de.falkharnisch.web.jupa.test;

import de.falkharnisch.web.jupa.database.User;
import de.falkharnisch.web.jupa.database.UserLicense;
import de.falkharnisch.web.jupa.services.LicenseService;
import de.falkharnisch.web.jupa.services.UserService;
import org.testng.annotations.Test;

import javax.inject.Inject;
import java.util.List;

import static org.testng.Assert.*;

public class LicenseServiceTest extends AbstractArquillianTest {

    @Inject
    private LicenseService licenseService;

    @Inject
    private UserService userService;

    @Test
    public void testSelectById() {
        User user = userService.getUserById(1);

        List<UserLicense> licenses = licenseService.getLicensesForUser(user);
        assertEquals(licenses.size(), 7, "Falsche Anzahl an Lizenzen");
    }

    @Test
    public void testIsAuditor() {
        User user = userService.getUserById(1);
        assertTrue(licenseService.isUserAuditor(user), "Benutzer ist kein Prüfer");

        User user2 = userService.getUserById(2);
        assertFalse(licenseService.isUserAuditor(user2), "Benutzer ist Prüfer");
    }
}