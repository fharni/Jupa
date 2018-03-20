package de.falkharnisch.web.jupa.beans.access;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.io.Serializable;
import java.security.Principal;

/**
 * Managed Bean for handling with the user service.
 */
@ManagedBean
@RequestScoped
public class AccessBean implements Serializable {

    public boolean isLoggedIn() {
        return getUserPrincipal() != null;
    }

    boolean isUserInRole(String role) {
        return getExternalContext().isUserInRole(role);
    }

    private Principal getUserPrincipal() {
        return getExternalContext().getUserPrincipal();
    }

    private ExternalContext getExternalContext() {
        FacesContext fc = FacesContext.getCurrentInstance();
        return fc.getExternalContext();
    }

    public void logout() throws IOException {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        externalContext.invalidateSession();
        externalContext.redirect(externalContext.getApplicationContextPath() + "/index.xhtml");
    }
}
