package de.falkharnisch.web.jupa.beans.access;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.io.Serializable;

@ManagedBean
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
