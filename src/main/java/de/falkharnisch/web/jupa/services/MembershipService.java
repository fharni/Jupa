package de.falkharnisch.web.jupa.services;

import de.falkharnisch.web.jupa.database.Club;
import de.falkharnisch.web.jupa.database.Membership;
import de.falkharnisch.web.jupa.database.Membership_;
import de.falkharnisch.web.jupa.database.User;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Transactional
@ApplicationScoped
public class MembershipService extends BaseService<Membership> {

    public void beginMembership(User user, Club club, LocalDate startDate) {
        Membership membership = new Membership(user, club, startDate);
        persist(membership);
    }

    public List<Membership> getMemberships(User user) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Membership> query = builder.createQuery(Membership.class);
        Root<Membership> root = query.from(Membership.class);
        query.where(builder.equal(root.get(Membership_.user), user));
        return em.createQuery(query).getResultList();
    }

    public List<Membership> getActiveMemberships(User user) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Membership> query = builder.createQuery(Membership.class);
        Root<Membership> root = query.from(Membership.class);
        query.where(builder.and(
                builder.equal(root.get(Membership_.user), user),
                builder.isNull(root.get(Membership_.endDate))
        ));
        return em.createQuery(query).getResultList();
    }
}
