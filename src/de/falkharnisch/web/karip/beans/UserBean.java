package de.falkharnisch.web.karip.beans;


import de.falkharnisch.web.karip.database.User;
import de.falkharnisch.web.karip.services.UserService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
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

    public String getName() {
        return "Falk Harnisch";
    }

    public List<User> getUsers() {
        return userService.getUsers();
    }
}
