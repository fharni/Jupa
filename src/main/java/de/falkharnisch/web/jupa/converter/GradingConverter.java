package de.falkharnisch.web.jupa.converter;

import de.falkharnisch.web.jupa.database.Grading;
import de.falkharnisch.web.jupa.services.GradingService;
import org.slf4j.Logger;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.NoResultException;

@Named
public class GradingConverter implements Converter {

    @Inject
    private Logger logger;

    @Inject
    private GradingService gradingService;

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if (value != null && value.trim().length() > 0) {
            try {
                String[] left = value.split(" \\(");
                if (left.length != 2) return null;

                String gradingName = left[0];
                String[] right = left[1].split("\\)");
                if (right.length != 2) return null;
                String disciplineName = right[0];

                return gradingService.getDisciplines().stream()
                        .filter(discipline -> discipline.getName().equals(disciplineName))
                        .flatMap(discipline -> gradingService.getGradingsByDiscipline(discipline).stream())
                        .filter(grading -> grading.getName().equals(gradingName))
                        .findFirst()
                        .orElse(null);

            } catch (NoResultException e) {
                logger.info("Graduierung %s nicht gefunden", value, e);
            }
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object object) {
        if (object instanceof Grading) {
            Grading grading = ((Grading) object);
            return grading.getName() + " (" + grading.getDiscipline().getName() + ")";
        } else {
            return null;
        }
    }
}
