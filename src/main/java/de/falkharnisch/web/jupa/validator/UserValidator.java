package de.falkharnisch.web.jupa.validator;

import de.falkharnisch.web.jupa.database.User;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("userValidator")
public class UserValidator implements Validator<User> {

    @Override
    public void validate(FacesContext context, UIComponent component, User value) {
        if (value != null && !(value instanceof User)) {
            FacesMessage msg = new FacesMessage("Benutzer existiert nicht.", "Dieser Benutzer existiert nicht.");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
    }
}
