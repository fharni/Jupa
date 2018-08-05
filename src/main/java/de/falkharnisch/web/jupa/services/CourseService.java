package de.falkharnisch.web.jupa.services;

import de.falkharnisch.web.jupa.database.*;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Transactional
@ApplicationScoped
public class CourseService extends BaseService<Course> {

    private List<Annotation> annotations;
    private Map<String, Annotation> annotationMap;

    @PostConstruct
    private void initAnnotations() {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Annotation> query = builder.createQuery(Annotation.class);
        query.from(Annotation.class);
        annotations = em.createQuery(query).getResultList();
        annotationMap = annotations.stream().collect(Collectors.toMap(Annotation::getName, a -> a));
    }

    public List<Annotation> getAnnotations() {
        return annotations;
    }

    public Annotation getAnnotationByName(String name) {
        return annotationMap.get(name);
    }

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

    public List<CourseParticipant> getParticipantsForCourse(Course course) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<CourseParticipant> query = builder.createQuery(CourseParticipant.class);
        Root<CourseParticipant> courseParticipantRoot = query.from(CourseParticipant.class);
        query.where(builder.equal(courseParticipantRoot.get(CourseParticipant_.course), course));
        return em.createQuery(query).getResultList();
    }

    public void persistParticipant(CourseParticipant entity) {
        super.persistOther(entity);
    }

    public List<Course> getCoursesForUser(User user) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Course> query = builder.createQuery(Course.class);
        Root<CourseParticipant> root = query.from(CourseParticipant.class);
        query.select(root.get(CourseParticipant_.course));
        query.where(builder.equal(root.get(CourseParticipant_.user), user));
        return em.createQuery(query).getResultList();
    }
}
