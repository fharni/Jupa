package de.falkharnisch.web.jupa.beans;

import de.falkharnisch.web.jupa.database.Function;
import de.falkharnisch.web.jupa.database.User;
import de.falkharnisch.web.jupa.database.UserRole;
import de.falkharnisch.web.jupa.services.LicenseService;
import de.falkharnisch.web.jupa.services.UserService;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@ManagedBean(name = "loginBean")
@SessionScoped
public class LoginBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private UserService userService;

    @Inject
    private LicenseService licenseService;

    private String password;
    private String uname;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String loginProject() {
        boolean result = userService.login(uname, password);
        if (result) {
            // get Http Session and store username
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                    .getExternalContext().getSession(false);
            session.setAttribute("username", uname);
            User user = userService.getUserByUsername(uname);

            Set<String> userGroups = getGroups(user);

            if (licenseService.isUserAuditor(user)) {
                userGroups.add("menu.auditor");
            }

            session.setAttribute("usergroups", userGroups);
            return "home";
        } else {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Ungültige Anmeldung!",
                            "Bitte versuchen Sie es erneut!"));

            return "login";
        }
    }

    private Set<String> getGroups(User user) {
        Set<String> userGroups = new HashSet<>();
        for (UserRole userRole : user.getUserRoles()) {
            for (Function function : userRole.getRole().getFunctions()) {
                userGroups.add(function.getFunction());
            }
        }
        return userGroups;
    }


}
