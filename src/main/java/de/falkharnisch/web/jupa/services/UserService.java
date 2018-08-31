package de.falkharnisch.web.jupa.services;

import de.falkharnisch.web.jupa.database.*;
import org.mindrot.jbcrypt.BCrypt;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.PersistenceException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

/**
 * Service class for handling with the user object.
 */
@Transactional
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
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<String> query = builder.createQuery(String.class);
        Root<User> root = query.from(User.class);
        query.select(builder.greatest(root.get(User_.username)));
        query.where(builder.like(root.get(User_.username), club.getDisplayId() + "%"));
        return em.createQuery(query).getSingleResult();
    }

    public List<User> getUsersForClub(Club club) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root<Membership> root = query.from(Membership.class);
        query.select(root.get(Membership_.user));
        query.where(builder.and(
                builder.equal(root.get(Membership_.club), club)),
                builder.isNull(root.get(Membership_.endDate))
        );
        return em.createQuery(query).getResultList();
    }

    public List<User> getAuditorByNamepart(String namePart) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root<UserLicense> root = query.from(UserLicense.class);
        Join<UserLicense, User> userJoin = root.join(UserLicense_.user);
        Join<UserLicense, License> licenseJoin = root.join(UserLicense_.license);
        Join<License, LicenseType> licenseTypeJoin = licenseJoin.join(License_.type);
        query.select(root.get(UserLicense_.user));
        query.where(builder.and(
                builder.like(builder.upper(userJoin.get(User_.surname)), namePart.toUpperCase() + "%"),
                builder.greaterThanOrEqualTo(root.get(UserLicense_.endDate), LocalDate.now()),
                builder.equal(licenseTypeJoin.get(LicenseType_.id), LicenseType.AUDITOR)
                )
        );
        return em.createQuery(query).getResultList();
    }
}
