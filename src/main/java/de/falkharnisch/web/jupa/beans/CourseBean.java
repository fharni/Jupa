package de.falkharnisch.web.jupa.beans;

import de.falkharnisch.web.jupa.database.Club;
import de.falkharnisch.web.jupa.database.Course;
import de.falkharnisch.web.jupa.database.User;
import de.falkharnisch.web.jupa.services.ClubService;
import de.falkharnisch.web.jupa.services.CourseService;
import de.falkharnisch.web.jupa.services.UserService;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
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
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext externalContext = fc.getExternalContext();
        String username = externalContext.getUserPrincipal().getName();
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
        this.selectedCourse = null;
    }

    @Transactional
    public void saveCourse() {
        courseService.persist(selectedCourse);
        this.selectedCourse = null;
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
}
