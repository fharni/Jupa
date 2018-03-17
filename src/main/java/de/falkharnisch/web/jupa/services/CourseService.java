package de.falkharnisch.web.jupa.services;

import de.falkharnisch.web.jupa.database.*;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.util.List;

@Transactional
@ApplicationScoped
public class CourseService extends BaseService<Course> {

    public List<Course> getCoursesForDistrict(@NotNull District district) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Course> query = builder.createQuery(Course.class);
        Root<Course> course = query.from(Course.class);
        Join<Course, Club> clubJoin = course.join(Course_.club);
        query.where(builder.equal(clubJoin.get(Club_.district), district));
        return em.createQuery(query).getResultList();
    }

    public List<Course> getCoursesForFederation(Federation federation) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Course> query = builder.createQuery(Course.class);
        Root<Course> course = query.from(Course.class);
        Join<Course, Club> clubJoin = course.join(Course_.club);
        Join<Club, District> districtJoin = clubJoin.join(Club_.district);
        query.where(builder.equal(districtJoin.get(District_.federation), federation));
        return em.createQuery(query).getResultList();
    }
}
