package de.falkharnisch.web.jupa.beans.access;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@RequestScoped
public class MenuAccessBean extends AccessBean implements Serializable {

    public boolean isShowMenuMyClub() {
        return isUserInRole("menu.my_club");
    }

    public boolean isShowMenuCourses() {
        return isUserInRole("menu.courses");
    }

    public boolean isShowMenuFederation() {
        return isUserInRole("menu.federation");
    }

    public boolean isShowMenuAuditor() {
        return isUserInRole("menu.auditor");
    }
}
