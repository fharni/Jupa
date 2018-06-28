package de.falkharnisch.web.jupa.beans;

import de.falkharnisch.web.jupa.database.Audit;
import de.falkharnisch.web.jupa.database.User;
import de.falkharnisch.web.jupa.services.AuditService;
import de.falkharnisch.web.jupa.services.UserService;
import de.falkharnisch.web.jupa.util.Util;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import java.util.List;

@ManagedBean
@SessionScoped
public class AuditBean {

    @Inject
    private UserService userService;

    @Inject
    private AuditService auditService;

    private Audit selectedAudit;

    private User user;

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

    public boolean isShowCreateAudit() {
        return selectedAudit != null;
    }

    public void createAudit() {
        selectedAudit.setClub(user.getClub());
        auditService.persist(selectedAudit);
        this.selectedAudit = null;
    }

    public void abort() {
        selectedAudit = null;
    }

    public List<User> autoCompleteInstructor(String query) {
        return userService.getUserByNamepart(query);
    }
}
