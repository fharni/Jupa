package de.falkharnisch.web.jupa.beans;

import de.falkharnisch.web.jupa.database.Course;
import de.falkharnisch.web.jupa.database.User;
import de.falkharnisch.web.jupa.services.CourseService;
import de.falkharnisch.web.jupa.services.UserService;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.util.List;

@ManagedBean
@SessionScoped
public class CourseBean {

    @Inject
    private CourseService courseService;

    @Inject
    private UserService userService;

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
}
