package de.falkharnisch.web.jupa.services;

import de.falkharnisch.web.jupa.database.User;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * Service class for handling with the user object.
 */
@ApplicationScoped
public class UserService implements Serializable {

    @PersistenceContext
    private EntityManager em;

    public User getUserByUsername(@NotNull String username) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root<User> root = query.from(User.class);
        query.where(builder.equal(root.get("username"), username));
        return em.createQuery(query).getSingleResult();
    }

    @Transactional
    public List<User> getUsers() {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        query.from(User.class);
        return em.createQuery(query).getResultList();
    }
}
