package de.falkharnisch.web.jupa.beans;

import de.falkharnisch.web.jupa.database.*;
import de.falkharnisch.web.jupa.services.AuditService;
import de.falkharnisch.web.jupa.services.GradingService;
import de.falkharnisch.web.jupa.services.UserService;
import de.falkharnisch.web.jupa.util.Util;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.Set;

@ManagedBean
@SessionScoped
public class AuditBean {

    @Inject
    private UserService userService;

    @Inject
    private AuditService auditService;

    @Inject
    private GradingService gradingService;

    private Audit selectedAudit;
    private User selectedUser;
    private Grading selectedGrading;

    private User user;
    private List<AuditMember> members;

    @PostConstruct
    private void initUser() {
        String username = Util.getUserName();
        user = userService.getUserByUsername(username);
    }

    public Audit getSelectedAudit() {
        return selectedAudit;
    }

    public void newAudit() {
        this.selectedAudit = new Audit();
    }

    boolean isShowCreateAudit() {
        return selectedAudit != null && this.members == null;
    }

    public boolean isShowAudit() {
        return selectedAudit == null;
    }

    public boolean isShowMembers() {
        return selectedAudit != null && this.members != null;
    }

    public void createAudit() {
        selectedAudit.setClub(user.getClub());
        AuditStatus auditStatus = auditService.getStatus(AuditService.STATUS.REQUEST);
        selectedAudit.setStatus(auditStatus);
        auditService.persist(selectedAudit);
        this.selectedAudit = null;
    }

    public void abort() {
        selectedAudit = null;
    }

    public List<User> autoCompleteAuditor(String query) {
        return userService.getAuditorByNamepart(query);
    }

    public List<Audit> getClubAudits() {
        return auditService.getAuditsForClub(user.getClub());
    }

    public List<Audit> getFederationAudits() {
        return auditService.getAuditsForFederation(user.getClub().getDistrict().getFederation());
    }

    public List<Audit> getAuditsForAuditor(){
        return auditService.getAuditsForAuditor(user);
    }

    @SuppressWarnings("unused")
    public void approveAudit(Audit audit) {
        auditService.approveAudit(audit);
    }

    @SuppressWarnings("unused")
    public void showMembers(Audit audit) {
        this.selectedAudit = audit;
        this.members = auditService.getMembersForAudit(audit);
    }

    @SuppressWarnings("unused")
    public void releaseAudit(Audit audit){
        AuditStatus auditStatus = auditService.getStatus(AuditService.STATUS.RELEASE);
        audit.setStatus(auditStatus);
        auditService.merge(audit);
    }

    public List<AuditMember> getMembers() {
        return members;
    }

    public Set<Discipline> getDisciplines() {
        return gradingService.getDisciplines();
    }

    public void back() {
        this.selectedAudit = null;
        this.members = null;
    }

    @SuppressWarnings("unused")
    public void removeMember(AuditMember member) {
        this.members.remove(member);
        auditService.removeAuditMember(member);
    }

    public List<User> getRemainingClubMembers() {
        List<User> users = userService.getUsersForClub(selectedAudit.getClub());
        for(AuditMember am : members) {
            users.remove(am.getUser());
        }
        return users;
    }

    public User getSelectedUser() {
        return selectedUser;
    }

    public void setSelectedUser(User selectedUser) {
        this.selectedUser = selectedUser;
    }

    public void addUserToAudit(){
        AuditMember member = new AuditMember();
        member.setAudit(selectedAudit);
        member.setUser(selectedUser);
        member.setGrading(selectedGrading);

        auditService.persistAuditMember(member);
        this.members.add(member);

        selectedUser = null;
        selectedGrading = null;
    }

    public Grading getSelectedGrading() {
        return selectedGrading;
    }

    public void setSelectedGrading(Grading selectedGrading) {
        this.selectedGrading = selectedGrading;
    }

    public List<Grading> getGradings(){
        return gradingService.getGradingsByDiscipline(selectedAudit.getDiscipline());
    }
}
