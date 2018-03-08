package de.falkharnisch.web.jupa.beans;

import de.falkharnisch.web.jupa.database.User;
import de.falkharnisch.web.jupa.services.UserService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

/**
 * Managed bean for manipulating other user data.
 */
@ManagedBean
@SessionScoped
public class UserEditBean {

    @Inject
    private UserService userService;

    private User selectedUser;

    public User getSelectedUser() {
        return selectedUser;
    }

    public void setSelectedUser(User selectedUser) {
        this.selectedUser = selectedUser;
    }
}
