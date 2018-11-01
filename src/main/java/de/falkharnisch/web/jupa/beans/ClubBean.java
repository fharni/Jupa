package de.falkharnisch.web.jupa.beans;

import de.falkharnisch.web.jupa.database.Club;
import de.falkharnisch.web.jupa.database.User;
import de.falkharnisch.web.jupa.services.ClubService;
import de.falkharnisch.web.jupa.services.UserService;
import de.falkharnisch.web.jupa.util.Util;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class ClubBean implements Serializable {

    @Inject
    private ClubService clubService;

    @Inject
    private UserService userService;

    //@ManagedProperty(value = "#{userEditBean}")
    @Inject
    private UserEditBean userEditBean;

    //@ManagedProperty(value = "#{auditBean}")
    @Inject
    private AuditBean auditBean;

    @SuppressWarnings("unused")
    public UserEditBean getUserEditBean() {
        return userEditBean;
    }

    @SuppressWarnings("unused")
    public void setUserEditBean(UserEditBean userEditBean) {
        this.userEditBean = userEditBean;
    }

    @SuppressWarnings("unused")
    public AuditBean getAuditBean() {
        return auditBean;
    }

    @SuppressWarnings("unused")
    public void setAuditBean(AuditBean auditBean) {
        this.auditBean = auditBean;
    }

    private Club club;

    @PostConstruct
    private void initUser() {
        String username = Util.getUserName();
        club = clubService.getClubForUsername(username);
    }

    public String getClubName() {
        return club.getName();
    }

    public List<User> getClubMembers() {
        return userService.getUsersForClub(club);
    }

    public String getDistrict() {
        return club.getDistrict().getName();
    }

    public String getFederation() {
        return club.getDistrict().getFederation().getName();
    }

    public boolean isShowCreateMember() {
        return userEditBean.isShowCreateMember();
    }

    public boolean isShowMembers() {
        return !userEditBean.isShowCreateMember();
    }

    public boolean isShowCreateAudit() {
        return auditBean.isShowCreateAudit();
    }

    public boolean isShowAudits() {
        return auditBean.isShowAudit();
    }

    public boolean isShowAuditMembers() {
        return auditBean.isShowMembers();
    }

    public List<Club> getFederationClubs() {
        return clubService.getClubsForFederation(club.getDistrict().getFederation());
    }
}
