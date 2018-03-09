package de.falkharnisch.web.jupa.beans;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.security.Principal;

/**
 * Managed Bean for handling with the user service.
 */
@ManagedBean
@RequestScoped
public class AccessBean implements Serializable {

    public boolean isLoggedIn() {
        return getUserPrincipal() != null;
    }

    public boolean isShowMenuMyClub() {
        return isUserInRole("menu.my_club");
    }

    public boolean isShowMenuCourses() {
        return isUserInRole("menu.courses");
    }

    private boolean isUserInRole(String role) {
        return getExternalContext().isUserInRole(role);
    }

    private Principal getUserPrincipal() {
        return getExternalContext().getUserPrincipal();
    }

    private ExternalContext getExternalContext() {
        FacesContext fc = FacesContext.getCurrentInstance();
        return fc.getExternalContext();
    }

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/login";
    }
}
