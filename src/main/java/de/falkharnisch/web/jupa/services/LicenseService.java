package de.falkharnisch.web.jupa.services;

import de.falkharnisch.web.jupa.database.User;
import de.falkharnisch.web.jupa.database.UserLicense;
import de.falkharnisch.web.jupa.database.UserLicense_;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@ApplicationScoped
public class LicenseService extends BaseService<UserLicense> {

    public List<UserLicense> getLicensesForUser(User user) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<UserLicense> query = builder.createQuery(UserLicense.class);
        Root<UserLicense> root = query.from(UserLicense.class);
        query.where(builder.equal(root.get(UserLicense_.user), user));
        return em.createQuery(query).getResultList();
    }
}
