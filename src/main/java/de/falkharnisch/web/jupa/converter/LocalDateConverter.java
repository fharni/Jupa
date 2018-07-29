package de.falkharnisch.web.jupa.converter;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Faces converter for support of LocalDate
 * @author Juneau
 * @see https://www.javacodegeeks.com/2015/06/utilizing-the-java-8-date-time-api-with-jsf-and-java-ee-7.html
 */
@FacesConverter(value="localDateConverter")
public class LocalDateConverter implements javax.faces.convert.Converter {

    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");

	@Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
          return LocalDate.parse(value, FORMAT);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        LocalDate dateValue = (LocalDate) value;
        return dateValue.format(FORMAT);
    }
}