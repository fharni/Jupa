package de.falkharnisch.web.jupa.services;

import de.falkharnisch.web.jupa.database.Audit;
import de.falkharnisch.web.jupa.database.Audit_;
import de.falkharnisch.web.jupa.database.Club;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@ApplicationScoped
public class AuditService extends BaseService<Audit> {

    public List<Audit> getAuditsForClub(Club club) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Audit> query = builder.createQuery(Audit.class);
        Root<Audit> root = query.from(Audit.class);
        query.where(builder.equal(root.get(Audit_.club), club));
        return em.createQuery(query).getResultList();
    }
}
