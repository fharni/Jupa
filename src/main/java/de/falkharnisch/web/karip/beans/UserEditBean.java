package de.falkharnisch.web.karip.beans;

import de.falkharnisch.web.karip.constants.Roles;
import de.falkharnisch.web.karip.database.User;
import de.falkharnisch.web.karip.services.UserService;

import javax.annotation.security.RolesAllowed;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import java.util.List;

/**
 * Managed bean for manipulating other user data.
 */
@ManagedBean
@SessionScoped
public class UserEditBean {

    @Inject
    private UserService userService;

    private User selectedUser;

    @RolesAllowed(Roles.USERADMIN_ROLE)
    public List<User> getUsers() {
        return userService.getUsers();
    }

    public User getSelectedUser() {
        return selectedUser;
    }

    public void setSelectedUser(User selectedUser) {
        this.selectedUser = selectedUser;
    }
}
