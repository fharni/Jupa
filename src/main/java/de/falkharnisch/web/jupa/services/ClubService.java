package de.falkharnisch.web.jupa.services;


import de.falkharnisch.web.jupa.database.Club;
import de.falkharnisch.web.jupa.database.Club_;
import de.falkharnisch.web.jupa.database.User;
import de.falkharnisch.web.jupa.database.User_;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@ApplicationScoped
public class ClubService extends BaseService<Club> {

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

    public List<Club> getClubByNamepart(String namePart) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Club> query = builder.createQuery(Club.class);
        Root<Club> root = query.from(Club.class);
        query.where(builder.like(builder.upper(root.get(Club_.name)),
                "%" + namePart.toUpperCase() + "%"));
        return em.createQuery(query).getResultList();
    }
}
