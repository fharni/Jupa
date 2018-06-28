package de.falkharnisch.web.jupa.beans;

import de.falkharnisch.web.jupa.database.Club;
import de.falkharnisch.web.jupa.database.User;
import de.falkharnisch.web.jupa.services.ClubService;
import de.falkharnisch.web.jupa.util.Util;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import java.util.List;

@ManagedBean
@SessionScoped
public class ClubBean {

    @Inject
    private ClubService clubService;

    @ManagedProperty(value = "#{userEditBean}")
    private UserEditBean userEditBean;

    @ManagedProperty(value = "#{auditBean}")
    private AuditBean auditBean;

    public UserEditBean getUserEditBean() {
        return userEditBean;
    }

    public void setUserEditBean(UserEditBean userEditBean) {
        this.userEditBean = userEditBean;
    }

    public AuditBean getAuditBean() {
        return auditBean;
    }

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
        return clubService.getUsersForClub(club);
    }

    public String getDistrict() {
        return club.getDistrict().getName();
    }

    public String getFederation() {
        return club.getDistrict().getFederation().getName();
    }

    public boolean isShowCreateMember() {
        return userEditBean.isShowCreateMember() && !isShowCreateAudit();
    }

    public boolean isShowMembers() {
        return !userEditBean.isShowCreateMember() && !isShowCreateAudit();
    }

    public boolean isShowCreateAudit() {
        return auditBean.isShowCreateAudit();
    }
}
