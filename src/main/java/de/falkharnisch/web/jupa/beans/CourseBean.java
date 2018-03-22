package de.falkharnisch.web.jupa.beans;

import de.falkharnisch.web.jupa.database.Club;
import de.falkharnisch.web.jupa.database.Course;
import de.falkharnisch.web.jupa.database.User;
import de.falkharnisch.web.jupa.services.ClubService;
import de.falkharnisch.web.jupa.services.CourseService;
import de.falkharnisch.web.jupa.services.UserService;
import de.falkharnisch.web.jupa.util.Util;
import org.primefaces.event.SelectEvent;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

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
        return this.selectedCourse != null;
    }

    public void abort() {
        selectedCourse.setStartDate(null);
        selectedCourse.setEndDate(null);
        selectedCourse.setTopic("");
        selectedCourse.setClub(null);
        selectedCourse.setPlace("");
        selectedCourse.setInstructor(null);
        this.selectedCourse = null;
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
        this.selectedCourse = course;
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

    public void onDateSelect(SelectEvent event) {
        this.selectedCourse.setEndDate((Date) event.getObject());
    }
}
