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
public class UserIdConverter implements Converter<User> {

    @Inject
    private Logger logger;

    @Inject
    private UserService userService;

    public User getAsObject(FacesContext fc, UIComponent uic, String value) {
        if (value != null && value.trim().length() > 0) {
            try {
                return userService.getUserById(Integer.parseInt(value));
            } catch (NoResultException e) {
                logger.info("Benutzer %s nicht gefunden", value, e);
            }
        }
        return null;
    }

    public String getAsString(FacesContext fc, UIComponent uic, User object) {
        if (object instanceof User) {
            User user = ((User) object);
            if (user.getId() != null) {
                return ((User) object).getId().toString();
            }
        }
        return null;
    }
}
