package de.falkharnisch.web.jupa.beans;


import de.falkharnisch.web.jupa.constants.Roles;
import de.falkharnisch.web.jupa.database.User;
import de.falkharnisch.web.jupa.services.UserService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.io.Serializable;
import java.security.Principal;

/**
 * Managed Bean for handling with the user service.
 */
@ManagedBean(name = "accessBean")
@SessionScoped
public class AccessBean implements Serializable {

    @Inject
    private UserService userService;

    public User getCurrentUser() {
        if (isLoggedIn()) {
            String username = getUserPrincipal().getName();
            return userService.getUserByUsername(username);
        }
        return null;
    }

    public boolean isLoggedIn() {
        return getUserPrincipal() != null;
    }

    public boolean isUserAdmin() {
        return getExternalContext().isUserInRole(Roles.USERADMIN_ROLE);
    }

    public boolean isChiefReferee() {
        return getExternalContext().isUserInRole(Roles.CHIEF_REFEREE);
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
