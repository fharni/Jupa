package de.falkharnisch.web.jupa.converter;

import de.falkharnisch.web.jupa.database.Discipline;
import de.falkharnisch.web.jupa.services.GradingService;
import org.slf4j.Logger;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.NoResultException;
import java.util.List;

@Named
public class DisciplineConverter extends BaseConverter {

    @Inject
    private Logger logger;

    @Inject
    private GradingService gradingService;

    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if (value != null && value.trim().length() > 0) {
            try {
                List<Discipline> disciplines = gradingService.getDisciplines();
                for (Discipline discipline : disciplines) {
                    if (discipline.getName().equals(value)) {
                        return discipline;
                    }
                }
            } catch (NoResultException e) {
                logger.info("Vermerk %s nicht gefunden", value, e);
            }
        }
        return null;
    }
}
