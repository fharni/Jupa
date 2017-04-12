package de.falkharnisch.web.karip.beans;


import de.falkharnisch.web.karip.database.User;
import de.falkharnisch.web.karip.services.UserService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

/**
 * Managed Bean for handling with the user service.
 */
@ManagedBean
@SessionScoped
public class UserBean implements Serializable {

    @Inject
    private UserService userService;

    public User getCurrentUser() {
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext externalContext = fc.getExternalContext();

        if (isLoggedIn()) {
            String username = externalContext.getUserPrincipal().getName();
            return userService.getUserByUsername(username);
        }
        return null;
    }

    public boolean isLoggedIn() {
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext externalContext = fc.getExternalContext();
        return externalContext.getUserPrincipal() != null;
    }

    public List<User> getUsers() {
        return userService.getUsers();
    }

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/login";
    }
}
