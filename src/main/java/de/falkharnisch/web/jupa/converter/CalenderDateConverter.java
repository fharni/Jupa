package de.falkharnisch.web.jupa.converter;


import org.apache.commons.lang3.StringUtils;
import org.primefaces.component.calendar.Calendar;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


@FacesConverter(forClass = LocalDate.class, value = "localDateFacesConverter")
public class CalenderDateConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(extractPattern(component));
        try {
            return LocalDate.parse(value, formatter);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null || (value instanceof String && StringUtils.isBlank((String) value))) {
            return "";
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(extractPattern(component));
        if (value instanceof LocalDate) {
            return formatter.format((LocalDate) value);
        } else {
            return "";
        }
    }

    private String extractPattern(UIComponent component) {
        if (component instanceof Calendar) {
            Calendar calendarComponent = (Calendar) component;
            return calendarComponent.getPattern();
        }
        return "yyyy-MM-dd";
    }
}
