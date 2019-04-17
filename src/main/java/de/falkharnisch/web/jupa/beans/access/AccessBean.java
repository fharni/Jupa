package de.falkharnisch.web.jupa.beans.access;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Serializable;
import java.util.Set;

/**
 * Managed Bean for handling with the user service.
 */
@ManagedBean
@RequestScoped
public class AccessBean implements Serializable {

	private static final long serialVersionUID = 7712314330400916256L;

	public boolean isLoggedIn() {
        return getSession().getAttribute("username") != null;
    }

    boolean isUserInRole(String role) {
        Object usergroups = getSession().getAttribute("usergroups");
        return usergroups instanceof Set && ((Set<?>) usergroups).contains(role);
    }

    private HttpSession getSession() {
        return (HttpSession)
                FacesContext.
                        getCurrentInstance().
                        getExternalContext().
                        getSession(false);
    }

    public void logout() throws IOException {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        externalContext.invalidateSession();
        externalContext.redirect(externalContext.getApplicationContextPath() + "/index.xhtml");
    }
}
