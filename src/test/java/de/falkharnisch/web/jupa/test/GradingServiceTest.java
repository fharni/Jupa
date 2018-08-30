package de.falkharnisch.web.jupa.test;

import de.falkharnisch.web.jupa.database.Discipline;
import de.falkharnisch.web.jupa.database.Grading;
import de.falkharnisch.web.jupa.database.User;
import de.falkharnisch.web.jupa.database.UserGrading;
import de.falkharnisch.web.jupa.services.GradingService;
import de.falkharnisch.web.jupa.services.UserService;
import org.testng.annotations.Test;

import javax.inject.Inject;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class GradingServiceTest extends AbstractArquillianTest {

    @Inject
    private GradingService gradingService;

    @Inject
    private UserService userService;

    @Test
    public void testGradingsByUser() {
        User user = userService.getUserById(1);

        List<UserGrading> gradings = gradingService.getGradingsByUser(user);
        assertEquals(gradings.size(), 8, "Falsche Anzahl an Graduierungen");
    }

    @Test
    public void testDisciplinesAndGradings() {
        Set<Discipline> disciplines = gradingService.getDisciplines();

        assertEquals(disciplines.size(), 4, "Falsche Anzahl an Disziplinen");

        for (Discipline discipline : disciplines) {
            List<Grading> gradingsByDiscipline = gradingService.getGradingsByDiscipline(discipline);
            assertTrue(!gradingsByDiscipline.isEmpty(), "Keine Graduierung gefunden für " + discipline);
        }
    }

    @Test(dependsOnMethods = "testGradingsByUser")
    public void testAddGrading() {
        User user = userService.getUserById(1);

        Set<Discipline> disciplines = gradingService.getDisciplines();
        Discipline[] disciplines1 = disciplines.toArray(new Discipline[4]);
        List<Grading> gradingList = gradingService.getGradingsByDiscipline(disciplines1[0]);

        List<UserGrading> gradings = gradingService.getGradingsByUser(user);
        int size = gradings.size();

        gradingService.addGradingForUser(user, gradingList.get(16), LocalDate.now());

        gradings = gradingService.getGradingsByUser(user);
        assertEquals(gradings.size(), size + 1, "Falsche Anzahl an Graduierungen");
    }
}
