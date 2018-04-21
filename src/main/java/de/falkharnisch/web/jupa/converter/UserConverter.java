package de.falkharnisch.web.jupa.converter;

import de.falkharnisch.web.jupa.database.User;
import de.falkharnisch.web.jupa.services.UserService;
import org.slf4j.Logger;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.NoResultException;

@Named
public class UserConverter implements Converter {

    @Inject
    private Logger logger;

    @Inject
    private UserService userService;

    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if (value != null && value.trim().length() > 0) {
            try {
                String[] left = value.split("\\(");
                String[] right = left[1].split("\\)");
                return userService.getUserByUsername(right[0]);
            } catch (NoResultException e) {
                logger.info("Benutzer %s nicht gefunden", value, e);
            }
        }
        return null;
    }

    public String getAsString(FacesContext fc, UIComponent uic, Object object) {
        if (object instanceof User) {
            return object.toString();
        } else {
            return null;
        }
    }
}
