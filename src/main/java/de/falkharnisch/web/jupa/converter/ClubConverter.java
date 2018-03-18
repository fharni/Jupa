package de.falkharnisch.web.jupa.converter;

import de.falkharnisch.web.jupa.database.Club;
import de.falkharnisch.web.jupa.services.ClubService;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.NoResultException;
import java.util.logging.Level;
import java.util.logging.Logger;

@Named
public class ClubConverter implements Converter {

    @Inject
    private Logger logger;

    @Inject
    private ClubService clubService;

    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if (value != null && value.trim().length() > 0) {
            try {
                return clubService.getClubByName(value);
            } catch (NoResultException e) {
                logger.log(Level.INFO, "Club nicht gefunden", e);
            }
        }
        return null;
    }

    public String getAsString(FacesContext fc, UIComponent uic, Object object) {
        if (object instanceof Club) {
            return ((Club) object).getName();
        } else {
            return null;
        }
    }
}
