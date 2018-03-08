package de.falkharnisch.web.jupa.beans;

import de.falkharnisch.web.jupa.database.Club;
import de.falkharnisch.web.jupa.database.User;
import de.falkharnisch.web.jupa.services.ClubService;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.util.List;

@ManagedBean
@SessionScoped
public class ClubBean {

    @Inject
    private ClubService clubService;

    private Club club;

    @PostConstruct
    private void initUser() {
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext externalContext = fc.getExternalContext();
        String username = externalContext.getUserPrincipal().getName();
        club = clubService.getClubForUsername(username);
    }

    public String getClubName() {
        return club.getName();
    }

    public List<User> getClubMembers() {
        return clubService.getUsersForClub(club);
    }
}
