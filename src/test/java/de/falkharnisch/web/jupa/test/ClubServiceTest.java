package de.falkharnisch.web.jupa.test;

import de.falkharnisch.web.jupa.database.Club;
import de.falkharnisch.web.jupa.services.ClubService;
import org.testng.annotations.Test;

import javax.inject.Inject;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class ClubServiceTest extends AbstractArquillianTest {

    @Inject
    private ClubService clubService;

    @Test
    public void testClubForUsername() {
        Club club = clubService.getClubForUsername("0502001000015");
        assertEquals(club.getDisplayId(), "0502001", "Verein konnte nicht gefunden werden");
    }

    @Test
    public void testClubByNamepart() {
        List<Club> clubs = clubService.getClubByNamepart("Pader");
        assertEquals(clubs.size(), 1);
        assertEquals(clubs.get(0).getName(), "TV 1875 Paderborn e.V.", "Verein konnte nicht gefunden werden");
    }

    @Test
    public void testClubByName() {
        Club club = clubService.getClubByName("TV 1875 Paderborn e.V.");
        assertEquals(club.getName(), "TV 1875 Paderborn e.V.", "Verein konnte nicht gefunden werden");
    }

    @Test(dependsOnMethods = "testClubByName")
    public void testClubsForFederation() {
        Club club = clubService.getClubByName("TV 1875 Paderborn e.V.");
        List<Club> clubs = clubService.getClubsForFederation(club.getDistrict().getFederation());
        assertEquals(clubs.size(), 5);
    }
}
