package de.falkharnisch.web.jupa.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

public abstract class BaseConverter implements Converter<Object> {

    public String getAsString(FacesContext fc, UIComponent uic, Object object) {
        if (object != null) {
            return object.toString();
        } else {
            return null;
        }
    }
}
