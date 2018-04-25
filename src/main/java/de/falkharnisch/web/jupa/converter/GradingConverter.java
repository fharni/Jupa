package de.falkharnisch.web.jupa.converter;

import de.falkharnisch.web.jupa.database.Discipline;
import de.falkharnisch.web.jupa.database.Grading;
import de.falkharnisch.web.jupa.services.GradingService;
import org.slf4j.Logger;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.NoResultException;
import java.util.List;

@Named
public class GradingConverter implements Converter {

    @Inject
    private Logger logger;

    @Inject
    private GradingService gradingService;

    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if (value != null && value.trim().length() > 0) {
            try {
                String[] left = value.split(" \\(");
                String gradingName = left[0];
                String[] right = left[1].split("\\)");
                String disciplineName = right[0];

                List<Discipline> disciplines = gradingService.getDisciplines();
                for (Discipline discipline : disciplines) {
                    if (discipline.getName().equals(disciplineName)) {
                        List<Grading> gradings = gradingService.getGradingsByDiscipline(discipline);
                        for (Grading grading : gradings) {
                            if (grading.getName().equals(gradingName)) {
                                return grading;
                            }
                        }
                    }
                }
            } catch (NoResultException e) {
                logger.info("Graduierung %s nicht gefunden", value, e);
            }
        }
        return null;
    }

    public String getAsString(FacesContext fc, UIComponent uic, Object object) {
        if (object instanceof Grading) {
            Grading grading = ((Grading) object);
            return grading.getName() + " (" + grading.getDiscipline().getName() + ")";
        } else {
            return null;
        }
    }
}
