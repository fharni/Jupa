package de.falkharnisch.web.jupa.services;

import de.falkharnisch.web.jupa.database.Federation;
import de.falkharnisch.web.jupa.database.User;
import de.falkharnisch.web.jupa.database.User_;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Service class for handling the referees.
 */
@ApplicationScoped
public class RefereeService {

    @PersistenceContext
    private EntityManager em;

    public List<User> getRefereesByFederation(@NotNull Federation federation) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<User> query = criteriaBuilder.createQuery(User.class);
        Root<User> user = query.from(User.class);
        query.where(criteriaBuilder.equal(user.get(User_.federation), federation));
        return em.createQuery(query).getResultList();
    }
}
