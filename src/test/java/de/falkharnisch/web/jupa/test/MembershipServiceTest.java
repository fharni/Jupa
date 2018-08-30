package de.falkharnisch.web.jupa.test;

import de.falkharnisch.web.jupa.database.Club;
import de.falkharnisch.web.jupa.database.Membership;
import de.falkharnisch.web.jupa.database.User;
import de.falkharnisch.web.jupa.services.ClubService;
import de.falkharnisch.web.jupa.services.MembershipService;
import de.falkharnisch.web.jupa.services.UserService;
import org.testng.annotations.Test;

import javax.inject.Inject;
import java.time.LocalDate;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class MembershipServiceTest extends AbstractArquillianTest {

    @Inject
    private ClubService clubService;

    @Inject
    private MembershipService membershipService;

    @Inject
    private UserService userService;

    @Test
    public void testGetMembership() {
        User user = userService.getUserById(1);

        List<Membership> memberships = membershipService.getMemberships(user);
        assertEquals(memberships.size(), 2, "Falsche Anzahl an Mitgliedschaften");

        List<Membership> memberships2 = membershipService.getActiveMemberships(user);
        assertEquals(memberships2.size(), 1, "Falsche Anzahl an aktiven Mitgliedschaften");
    }

    @Test(dependsOnMethods = "testGetMembership")
    public void testNewMembership() {
        User user = userService.getUserById(1);
        Club club = clubService.getClubByName("TV Eichen 1888 e.V.");

        int membershipSize = membershipService.getMemberships(user).size();
        int activeMembershipSize = membershipService.getActiveMemberships(user).size();

        membershipService.beginMembership(user, club, LocalDate.now());

        List<Membership> memberships = membershipService.getMemberships(user);
        assertEquals(memberships.size(), membershipSize + 1, "Falsche Anzahl an Mitgliedschaften");

        List<Membership> memberships2 = membershipService.getActiveMemberships(user);
        assertEquals(memberships2.size(), activeMembershipSize + 1, "Falsche Anzahl an aktiven Mitgliedschaften");
    }
}
