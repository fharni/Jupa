package de.falkharnisch.web.jupa.beans;

import de.falkharnisch.web.jupa.database.Federation;
import de.falkharnisch.web.jupa.services.ClubService;
import de.falkharnisch.web.jupa.util.Util;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

@ManagedBean
@SessionScoped
public class FederationBean {

    @Inject
    private ClubService clubService;

    private Federation federation;

    @PostConstruct
    private void initUser() {
        String username = Util.getUserName();
        federation = clubService.getClubForUsername(username).getDistrict().getFederation();
    }

    public Federation getFederation() {
        return federation;
    }
}
