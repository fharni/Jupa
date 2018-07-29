package de.falkharnisch.web.jupa.beans;

import java.time.LocalDate;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import org.primefaces.event.SelectEvent;

import de.falkharnisch.web.jupa.database.Annotation;
import de.falkharnisch.web.jupa.database.Club;
import de.falkharnisch.web.jupa.database.Course;
import de.falkharnisch.web.jupa.database.CourseParticipant;
import de.falkharnisch.web.jupa.database.User;
import de.falkharnisch.web.jupa.services.ClubService;
import de.falkharnisch.web.jupa.services.CourseService;
import de.falkharnisch.web.jupa.services.UserService;
import de.falkharnisch.web.jupa.util.Util;

@ManagedBean
@SessionScoped
public class CourseBean {

    @Inject
    private ClubService clubService;

    @Inject
    private CourseService courseService;

    @Inject
    private UserService userService;

    private Course selectedCourse;
    private List<CourseParticipant> courseParticipants;
    private User newCourseParticipant;
    private Annotation selectedAnnotation;

    private User user;

    @PostConstruct
    private void initUser() {
        String username = Util.getUserName();
        user = userService.getUserByUsername(username);
    }

    public List<Course> getCoursesForDistrict() {
        return courseService.getCoursesForDistrict(user.getClub().getDistrict());
    }

    public List<Course> getCoursesForFederation() {
        return courseService.getCoursesForFederation(user.getClub().getDistrict().getFederation());
    }

    public void newCourse() {
        this.selectedCourse = new Course();
    }

    public boolean isShowCreateCourse() {
        return this.selectedCourse != null && courseParticipants == null;
    }

    public boolean isShowParticipantsList() {
        return this.selectedCourse != null && courseParticipants != null;
    }

    public boolean isShowCourseList() {
        return !isShowCreateCourse() && !isShowParticipantsList();
    }

    public void abort() {
        selectedCourse.setStartDate(null);
        selectedCourse.setEndDate(null);
        selectedCourse.setTopic("");
        selectedCourse.setClub(null);
        selectedCourse.setPlace("");
        selectedCourse.setInstructor(null);
        selectedCourse = null;
    }

    @Transactional
    public void saveCourse() {
        if (selectedCourse.getId() == null) {
            courseService.persist(selectedCourse);
        } else {
            courseService.merge(selectedCourse);
        }
        this.selectedCourse = null;
    }

    public void editCourse(Course course) {
        selectedCourse = course;
    }

    public Course getSelectedCourse() {
        return selectedCourse;
    }

    public List<Club> autoCompleteClubs(String query) {
        return clubService.getClubByNamepart(query);
    }

    public List<User> autoCompleteInstructor(String query) {
        return userService.getUserByNamepart(query);
    }

    public List<User> autoCompleteUser(String query) {
        return userService.getUserByUsernamePart(query);
    }

    public void onDateSelect(SelectEvent event) {
        selectedCourse.setEndDate((LocalDate) event.getObject());
    }

    public void loadCourseParticipants(Course course) {
        selectedCourse = course;
        courseParticipants = courseService.getParticipantsForCourse(course);
        newCourseParticipant = new User();
    }

    public List<CourseParticipant> getCourseParticipants() {
        return courseParticipants;
    }

    public void backToCourseList() {
        selectedCourse = null;
        courseParticipants = null;
    }

    public User getNewCourseParticipant() {
        return newCourseParticipant;
    }

    public void setNewCourseParticipant(User newCourseParticipant) {
        this.newCourseParticipant = newCourseParticipant;
    }

    public void addUserToCourse() {
        CourseParticipant participant = new CourseParticipant();
        participant.setCourse(selectedCourse);
        participant.setUser(newCourseParticipant);
        participant.setAnnotation(selectedAnnotation);
        courseService.persistParticipant(participant);

        newCourseParticipant = new User();
        courseParticipants.add(participant);
    }

    public List<Annotation> getAnnotations() {
        return courseService.getAnnotations();
    }

    public Annotation getSelectedAnnotation() {
        return selectedAnnotation;
    }

    public void setSelectedAnnotation(Annotation selectedAnnotation) {
        this.selectedAnnotation = selectedAnnotation;
    }
}
