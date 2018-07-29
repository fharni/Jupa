package de.falkharnisch.web.jupa.services;

import java.time.LocalDate;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

import de.falkharnisch.web.jupa.database.Club;
import de.falkharnisch.web.jupa.database.Membership;
import de.falkharnisch.web.jupa.database.User;

@Transactional
@ApplicationScoped
public class MembershipService extends BaseService<Membership> {

    public void beginMembership(User user, Club club, LocalDate startDate) {
        Membership membership = new Membership(user, club, startDate);
        persist(membership);
    }
}
