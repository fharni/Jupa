package de.falkharnisch.web.karip.beans;

import de.falkharnisch.web.karip.constants.Roles;
import de.falkharnisch.web.karip.database.Federation;
import de.falkharnisch.web.karip.database.User;
import de.falkharnisch.web.karip.services.RefereeService;

import javax.annotation.security.RolesAllowed;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import java.util.List;

/**
 * Managed Bean for manipulating referees.
 */

@ManagedBean
@SessionScoped
public class RefereeBean {

    @ManagedProperty(value = "#{accessBean}")
    private AccessBean accessBean;
    @Inject
    private RefereeService refereeService;

    public void setAccessBean(AccessBean accessBean) {
        this.accessBean = accessBean;
    }

    @RolesAllowed(Roles.CHIEF_REFEREE)
    public List<User> getReferees() {
        Federation federation = accessBean.getCurrentUser().getFederation();
        return refereeService.getRefereesByFederation(federation);
    }
}
