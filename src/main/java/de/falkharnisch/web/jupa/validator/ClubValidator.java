package de.falkharnisch.web.jupa.validator;

import de.falkharnisch.web.jupa.database.Club;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("clubValidator")
public class ClubValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) {
        if (!(value instanceof Club)) {
            FacesMessage msg = new FacesMessage("Verein existiert nicht.", "Dieser Verein existiert nicht.");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
    }
}
