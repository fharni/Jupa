package de.falkharnisch.web.jupa.beans.access;

import de.falkharnisch.web.jupa.database.Discipline;
import de.falkharnisch.web.jupa.database.Grading;
import de.falkharnisch.web.jupa.database.User;
import de.falkharnisch.web.jupa.database.UserGrading;
import de.falkharnisch.web.jupa.services.GradingService;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Named
@SessionScoped
public class GradingBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private GradingService gradingService;

    private Map<User, Map<Discipline, Grading>> userGradingList = new HashMap<>();

    public int getDisciplineCountForUser(User user) {
        if (!userGradingList.containsKey(user)) {
            getGradingListForUser(user);
        }
        return userGradingList.get(user).size();
    }

    public Grading getGradingForUserAndDiscipline(User user, Discipline discipline) {
        if (user != null && discipline != null) {
            return userGradingList.get(user).get(discipline);
        }
        return null;
    }

    public Map<Discipline, Grading> getGradingListForUser(User user) {
        if (!userGradingList.containsKey(user)) {
            Map<Discipline, Grading> disciplineListMap = new HashMap<>();
            userGradingList.put(user, disciplineListMap);

            List<UserGrading> gradingsByUser = gradingService.getGradingsByUser(user);
            for (UserGrading userGrading : gradingsByUser) {
                Grading grading = userGrading.getGrading();
                Discipline discipline = grading.getDiscipline();
                if (!disciplineListMap.containsKey(discipline)) {
                    disciplineListMap.put(discipline, grading);
                } else {
                    if (disciplineListMap.get(discipline).getSortOrder() < grading.getSortOrder()) {
                        disciplineListMap.replace(discipline, grading);
                    }
                }
            }
        }
        return userGradingList.get(user);
    }
}
