package de.falkharnisch.web.jupa.services;

import de.falkharnisch.web.jupa.database.Club;
import de.falkharnisch.web.jupa.database.User;
import de.falkharnisch.web.jupa.database.User_;
import org.mindrot.jbcrypt.BCrypt;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.PersistenceException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.text.DecimalFormat;
import java.util.List;

/**
 * Service class for handling with the user object.
 */
@Transactional
@ApplicationScoped
public class UserService extends BaseService<User> {

    public static final int FEDERATION_MULTIPLIKATOR = 100000;
    public static final int DISTRICT_MULTIPLIKATOR = 1000;

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
        CriteriaQuery<String> query = builder.createQuery(String.class);
        Root<User> root = query.from(User.class);
        query.select(root.get(User_.password));
        query.where(builder.equal(root.get(User_.username), username));
        try {
            String passwordHash = em.createQuery(query).getSingleResult();
            return BCrypt.checkpw(password, passwordHash);
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

    public String getMaxIdForClub(Club club) {
        // 0502001%
        DecimalFormat format = new DecimalFormat("0000000");
        int id = club.getDisplayId()
                + club.getDistrict().getDisplayId() * DISTRICT_MULTIPLIKATOR
                + club.getDistrict().getFederation().getDisplayId() * FEDERATION_MULTIPLIKATOR;

        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<String> query = builder.createQuery(String.class);
        Root<User> root = query.from(User.class);
        query.select(builder.greatest(root.get(User_.username)));
        query.where(builder.like(root.get(User_.username), format.format(id) + "%"));
        return em.createQuery(query).getSingleResult();
    }
}
