package de.falkharnisch.web.jupa.beans;


import de.falkharnisch.web.jupa.database.User;
import de.falkharnisch.web.jupa.services.UserService;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

@ManagedBean
@SessionScoped
public class UserBean {

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

    public String getName() {
        return user.getForename() + " " + user.getSurname();
    }

    public String getUsername() {
        return user.getUsername();
    }

    public String getClubName() {
        return user.getClub().getName();
    }

    public String getDistrictName() {
        return user.getClub().getDistrict().getName();
    }

    public String getFederationName() {
        return user.getClub().getDistrict().getFederation().getName();
    }

    public boolean isGrading() {
        return false;
    }

    public boolean isCourses() {
        return false;
    }

    public boolean isLicenses() {
        return false;
    }

}
