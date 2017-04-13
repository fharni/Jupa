package de.falkharnisch.web.karip.services;

import de.falkharnisch.web.karip.database.User;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

/**
 * Service class for handling with the user object.
 */
@ApplicationScoped
public class UserService implements Serializable {

    @PersistenceContext
    private EntityManager em;

    public User getUserByUsername(String username) {
        TypedQuery<User> query = em.createQuery("SELECT u from User u where u.username = :username", User.class);
        query.setParameter("username", username);
        return query.getSingleResult();
    }

    @Transactional
    public List<User> getUsers() {
        TypedQuery<User> query = em.createQuery("SELECT u from User u", User.class);
        return query.getResultList();
    }
}
