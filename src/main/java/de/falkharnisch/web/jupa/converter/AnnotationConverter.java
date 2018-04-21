package de.falkharnisch.web.jupa.converter;

import de.falkharnisch.web.jupa.database.Annotation;
import de.falkharnisch.web.jupa.services.CourseService;
import org.slf4j.Logger;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.NoResultException;

@Named
public class AnnotationConverter implements Converter {

    @Inject
    private Logger logger;

    @Inject
    private CourseService courseService;

    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if (value != null && value.trim().length() > 0) {
            try {
                return courseService.getAnnotationByName(value);
            } catch (NoResultException e) {
                logger.info("Vermerk %s nicht gefunden", value, e);
            }
        }
        return null;
    }

    public String getAsString(FacesContext fc, UIComponent uic, Object object) {
        if (object instanceof Annotation) {
            return ((Annotation) object).getName();
        } else {
            return null;
        }
    }
}
