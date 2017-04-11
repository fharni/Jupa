package de.falkharnisch.web.karip.services;

import de.falkharnisch.web.karip.database.User;

import javax.enterprise.context.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

/**
 * Service class for handling with the user object.
 */
@SessionScoped
public class UserService implements Serializable {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public List<User> getUsers() {
        CriteriaBuilder builder = em.getCriteriaBuilder();

        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root<User> root = query.from(User.class);
        query.select(root);

        return em.createQuery(query).getResultList();
    }
}
