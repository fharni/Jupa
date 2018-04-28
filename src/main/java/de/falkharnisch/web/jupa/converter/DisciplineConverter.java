package de.falkharnisch.web.jupa.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.NoResultException;

import org.slf4j.Logger;

import de.falkharnisch.web.jupa.services.GradingService;

@Named
public class DisciplineConverter extends BaseConverter {

    @Inject
    private Logger logger;

    @Inject
    private GradingService gradingService;

    @Override
	public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if (value != null && value.trim().length() > 0) {
            try {
                return gradingService.getDisciplines().stream()
                	.filter(discipline -> discipline.getName().equals(value))
                	.findFirst()
                	.orElse(null);
            } catch (NoResultException e) {
                logger.info("Vermerk %s nicht gefunden", value, e);
            }
        }
        return null;
    }
}
