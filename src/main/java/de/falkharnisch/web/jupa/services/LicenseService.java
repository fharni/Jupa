package de.falkharnisch.web.jupa.services;

import de.falkharnisch.web.jupa.database.*;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.time.LocalDate;
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

    public boolean isUserAuditor(User user) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<UserLicense> query = builder.createQuery(UserLicense.class);
        Root<UserLicense> root = query.from(UserLicense.class);
        Join<UserLicense, License> licenseJoin = root.join(UserLicense_.license);
        Join<License, LicenseType> licenseTypeJoin = licenseJoin.join(License_.type);
        query.where(builder.and(
                builder.equal(root.get(UserLicense_.user), user),
                builder.greaterThanOrEqualTo(root.get(UserLicense_.endDate), LocalDate.now()),
                builder.equal(licenseTypeJoin.get(LicenseType_.id), LicenseType.AUDITOR)
                )
        );
        return !em.createQuery(query).getResultList().isEmpty();
    }
}
