package de.falkharnisch.web.jupa.beans;

import de.falkharnisch.web.jupa.database.Federation;
import de.falkharnisch.web.jupa.services.ClubService;
import de.falkharnisch.web.jupa.util.Util;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class FederationBean implements Serializable {

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
