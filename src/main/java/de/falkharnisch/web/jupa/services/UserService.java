package de.falkharnisch.web.jupa.services;

import de.falkharnisch.web.jupa.database.User;
import de.falkharnisch.web.jupa.database.User_;

import javax.enterprise.context.ApplicationScoped;
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
                "%" + namePart.toUpperCase() + "%"));
        return em.createQuery(query).getResultList();
    }
}
