package de.falkharnisch.web.jupa.services;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import de.falkharnisch.web.jupa.database.Discipline;
import de.falkharnisch.web.jupa.database.Grading;
import de.falkharnisch.web.jupa.database.Grading_;
import de.falkharnisch.web.jupa.database.User;
import de.falkharnisch.web.jupa.database.UserGrading;
import de.falkharnisch.web.jupa.database.UserGrading_;

@Transactional
@ApplicationScoped
public class GradingService extends BaseService<UserGrading> {

    private List<Discipline> disciplines;
    private Map<Discipline, List<Grading>> disciplineGradingMap;

    @PostConstruct
    private void init() {
        disciplines = loadDisciplines();
        disciplineGradingMap = new HashMap<>();
        for (Discipline discipline : disciplines) {
            disciplineGradingMap.put(discipline, loadGradingForDiscipline(discipline));
        }
    }

    public List<UserGrading> getGradingsByUser(User user) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<UserGrading> query = builder.createQuery(UserGrading.class);
        Root<UserGrading> root = query.from(UserGrading.class);
        query.where(builder.equal(root.get(UserGrading_.user), user));
        return em.createQuery(query).getResultList();
    }

    private List<Discipline> loadDisciplines() {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Discipline> query = builder.createQuery(Discipline.class);
        query.from(Discipline.class);
        return em.createQuery(query).getResultList();
    }

    private List<Grading> loadGradingForDiscipline(Discipline discipline) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Grading> query = builder.createQuery(Grading.class);
        Root<Grading> root = query.from(Grading.class);
        query.where(builder.equal(root.get(Grading_.discipline), discipline));
        return em.createQuery(query).getResultList();
    }

    public List<Discipline> getDisciplines() {
        return disciplines;
    }

    public List<Grading> getGradingsByDiscipline(Discipline discipline) {
        return disciplineGradingMap.get(discipline);
    }

    public void addGradingForUser(User user, Grading grading, LocalDate date) {
        UserGrading userGrading = new UserGrading();
        userGrading.setUser(user);
        userGrading.setGrading(grading);
        userGrading.setDate(date);
        persist(userGrading);
    }
}
