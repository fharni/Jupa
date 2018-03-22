package de.falkharnisch.web.jupa.util;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

public class Util {

    public static String getUserName() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        return session.getAttribute("username").toString();
    }
}
