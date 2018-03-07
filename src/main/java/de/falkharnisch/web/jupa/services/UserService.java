package de.falkharnisch.web.jupa.services;

import de.falkharnisch.web.jupa.database.Role;
import de.falkharnisch.web.jupa.database.User;
import de.falkharnisch.web.jupa.producer.qualifier.ApplicationManaged;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * Service class for handling with the user object.
 */
@ApplicationScoped
public class UserService implements Serializable {

    @Inject
    @ApplicationManaged
    private EntityManager em;

    public User getUserByUsername(@NotNull String username) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root<User> root = query.from(User.class);
        query.where(builder.equal(root.get("username"), username));
        User user = em.createQuery(query).getSingleResult();
//        for(Role role: user.getRoles()){
//            System.out.println(role.getRole());
//        }
        return user;
    }

    public List<User> getUsers() {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        query.from(User.class);
        return em.createQuery(query).getResultList();
    }

    public List<Role> getFunctionsByUsername(String name) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Role> query = builder.createQuery(Role.class);
        Root<Role> root = query.from(Role.class);
        query.from(Role.class);
        return em.createQuery(query).getResultList();
    }
}
