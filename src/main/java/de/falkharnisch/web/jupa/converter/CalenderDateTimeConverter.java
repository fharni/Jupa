package de.falkharnisch.web.jupa.converter;


import org.primefaces.component.calendar.Calendar;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@FacesConverter(forClass = LocalDateTime.class)
public class CalenderDateTimeConverter implements Converter<LocalDateTime> {

    @Override
    public LocalDateTime getAsObject(FacesContext context, UIComponent component, String value) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(extractPattern(component));
        try {
            return LocalDateTime.parse(value, formatter);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, LocalDateTime value) {
//        if (value == null || (value instanceof String && StringUtils.isBlank((String) value))) {
//            return "";
//        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(extractPattern(component));
        if (value instanceof LocalDateTime) {
            return formatter.format((LocalDateTime) value);
        } else {
            return "";
        }
    }

    private String extractPattern(UIComponent component) {
        if (component instanceof Calendar) {
            Calendar calendarComponent = (Calendar) component;
            return calendarComponent.getPattern();
        }
        return "yyyy-MM-dd HH:mm";
    }
}
