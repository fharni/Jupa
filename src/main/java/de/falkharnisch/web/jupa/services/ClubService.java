package de.falkharnisch.web.jupa.services;


import de.falkharnisch.web.jupa.database.Club;
import de.falkharnisch.web.jupa.database.User;
import de.falkharnisch.web.jupa.database.User_;
import de.falkharnisch.web.jupa.producer.qualifier.ApplicationManaged;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@ApplicationScoped
public class ClubService {

    @Inject
    @ApplicationManaged
    private EntityManager em;

    public Club getClubForUsername(String username) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root<User> root = query.from(User.class);
        query.where(builder.equal(root.get(User_.username), username));
        User user = em.createQuery(query).getSingleResult();
        return user.getClub();
    }

    public List<User> getUsersForClub(Club club) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root<User> root = query.from(User.class);
        query.where(builder.equal(root.get(User_.club), club));
        return em.createQuery(query).getResultList();
    }
}
