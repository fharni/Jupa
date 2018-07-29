package de.falkharnisch.web.jupa.services;


import de.falkharnisch.web.jupa.database.*;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
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

    public List<Club> getClubByNamepart(String namePart) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Club> query = builder.createQuery(Club.class);
        Root<Club> root = query.from(Club.class);
        query.where(builder.like(builder.upper(root.get(Club_.name)),
                "%" + namePart.toUpperCase() + "%"));
        return em.createQuery(query).getResultList();
    }

    public Club getClubByName(String name) throws NoResultException {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Club> query = builder.createQuery(Club.class);
        Root<Club> root = query.from(Club.class);
        query.where(builder.like(root.get(Club_.name), name));
        return em.createQuery(query).getSingleResult();
    }

    public List<Club> getClubsForFederation(Federation federation) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Club> query = builder.createQuery(Club.class);
        Root<Club> club = query.from(Club.class);
        Join<Club, District> districtJoin = club.join(Club_.district);
        query.where(builder.equal(districtJoin.get(District_.federation), federation));
        return em.createQuery(query).getResultList();
    }
}
