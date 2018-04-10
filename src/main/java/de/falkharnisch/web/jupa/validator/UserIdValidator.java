package de.falkharnisch.web.jupa.validator;

import de.falkharnisch.web.jupa.database.User;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("userIdValidator")
public class UserIdValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) {
        if (value != null && !(value instanceof User)) {
            FacesMessage msg = new FacesMessage("Benutzer existiert nicht.", "Diesr Benutzer existiert nicht.");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
    }
}
