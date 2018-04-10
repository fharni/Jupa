package de.falkharnisch.web.jupa.services;

import de.falkharnisch.web.jupa.database.User;
import de.falkharnisch.web.jupa.database.User_;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.PersistenceException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Service class for handling with the user object.
 */
@ApplicationScoped
public class UserService extends BaseService<User> {

    public User getUserById(@NotNull Integer id) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root<User> root = query.from(User.class);
        query.where(builder.equal(root.get(User_.id), id));
        return em.createQuery(query).getSingleResult();
    }

    public User getUserByUsername(@NotNull String username) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root<User> root = query.from(User.class);
        query.where(builder.equal(root.get(User_.username), username));
        return em.createQuery(query).getSingleResult();
    }

    public List<User> getUserByNamepart(String namePart) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root<User> root = query.from(User.class);
        query.where(builder.like(builder.upper(root.get(User_.surname)),
                namePart.toUpperCase() + "%"));
        return em.createQuery(query).getResultList();
    }

    public boolean login(String username, String password) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root<User> root = query.from(User.class);
        query.where(builder.and(
                builder.equal(root.get(User_.username), username),
                builder.equal(root.get(User_.password), password)
        ));
        try {
            em.createQuery(query).getSingleResult();
            return true;
        } catch (PersistenceException e) {
            return false;
        }
    }

    public List<User> getUserByUsernamePart(String idpart) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root<User> root = query.from(User.class);
        query.where(builder.like(root.get(User_.username), idpart + "%"));
        return em.createQuery(query).getResultList();
    }
}
