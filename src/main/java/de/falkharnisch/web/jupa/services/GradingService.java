package de.falkharnisch.web.jupa.services;

import de.falkharnisch.web.jupa.database.Grading;
import de.falkharnisch.web.jupa.database.User;
import de.falkharnisch.web.jupa.database.UserGrading;
import de.falkharnisch.web.jupa.database.UserGrading_;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@ApplicationScoped
public class GradingService extends BaseService<Grading> {

    public List<UserGrading> getGradingsByUser(User user) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<UserGrading> query = builder.createQuery(UserGrading.class);
        Root<UserGrading> root = query.from(UserGrading.class);
        query.where(builder.equal(root.get(UserGrading_.user), user));
        return em.createQuery(query).getResultList();
    }
}
